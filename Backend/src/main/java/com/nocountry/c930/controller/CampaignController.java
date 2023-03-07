package com.nocountry.c930.controller;


import com.nocountry.c930.dto.*;
import com.nocountry.c930.repository.CampaignRepository;
import com.nocountry.c930.service.ICampaignService;
import com.nocountry.c930.service.ICommentService;
import com.nocountry.c930.entity.DonationEntity;
import com.nocountry.c930.service.IDonationService;
import com.nocountry.c930.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private ICampaignService campaignService;
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IDonationService donationService;
    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping()
    @ApiOperation(value = "Creates a new campaign",
            notes = "Must be a logged user, you need to add at least 1 donation tier")
    public ResponseEntity<CampaignBasicDto> createCampaign(@ModelAttribute CampaignCreationDto dto) throws IOException {

        CampaignBasicDto campaign = campaignService.createCampaign(dto);

        return ResponseEntity.status(HttpStatus.OK).body(campaign);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an campaign",
            notes = "Deletes an campaign, only admin and campaign's user creator are allowed to delete")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Campaign ID is invalid (User number values only)"),
            @ApiResponse(code = 401, message = "You can only delete your own campaign"),
            @ApiResponse(code = 404, message = "Campaign not found")})
    public ResponseEntity<String> deleteCampaign(@PathVariable(name = "id") Long idCampaign) {

        if (campaignService.getCampaign(idCampaign) != null) {
            campaignService.deleteCampaign(idCampaign);
            return ResponseEntity.status(HttpStatus.OK).body("Campaign deleted successfully");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campaign cannot be deleted");
        }

    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get Campaign Info",
            notes = "Gets all the campaign information")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Campaign ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Campaign not found")})
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable(name = "id") Long idCampaign) {

        CampaignDto dto = campaignService.getCampaign(idCampaign);

        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates a campaign info",
            notes = "You can only update the name, description and status" + '\n' +
                    "Since Status is an enum you need to put an integer 0 for open and 1 for closed")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Campaign ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "Campaign not found")})

    public ResponseEntity<CampaignDto> updateCampaign(@PathVariable(name = "id") Long idCampaign, @RequestBody CampaignCreationDto dto) {

        CampaignDto campaignUpdated = null;
        try {
            campaignUpdated = campaignService.updateCampaign(idCampaign, dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(campaignUpdated);
    }


    @GetMapping()
    @ApiOperation(value = "List All Campaigns",
            notes = "Gives a paginated list of all the campaigns that are OPEN")
    public ResponseEntity<PageDto<CampaignBasicDto>> getAllCampaigns(@PageableDefault(size = 10) Pageable page,
                                                                     HttpServletRequest request) {


        PageDto<CampaignBasicDto> result = campaignService.listAllCampaigns(page, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}/comments")
    @ApiOperation(value = "Post a comment in a campaign")

    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "id") Long idCampaign, @RequestBody PostCommentDto dto) {

        CommentDto comment = commentService.createComment(idCampaign, dto);

        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @GetMapping("/{id}/comments")
    @ApiOperation(value = "Gets a campaigns comments list")
    public ResponseEntity<Set<CommentDto>> getComments(@PathVariable(name = "id") Long idCampaign) {

        Set<CommentDto> comments = commentService.getCampaignComments(idCampaign);

        return ResponseEntity.status(HttpStatus.OK).body(comments);


    }

    @DeleteMapping("/{id}/comments/{idComment}")
    @ApiOperation(value = "Deletes a comment",
            notes = "Only the user that created the comment or an Admin can delete it")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "id") Long idCampaign,
                                                @PathVariable(name = "idComment") Long idComment) {

        if (commentService.deleteComment(idCampaign, idComment)) {
            return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment cannot be deleted");
        }
    }

    @GetMapping(value = "/{id}/donations")
    @ApiOperation(value = "List All Donations",
            notes = "Gives a list of all donations")
    public ResponseEntity<?> getAllDonations(@PathVariable(name = "id") Long idCampaign) {

        Set<DonationDto> donations = campaignService.findAllDonations(idCampaign);

        return ResponseEntity.ok(donations);

    }

    @PostMapping(value = "/{id}/donations")
    @ApiOperation(value = "Makes a donation")
    public ResponseEntity<?> makeADonation(@PathVariable(name = "id") Long idCampaign,
                                           @RequestParam(name = "idDonationTier") Long idDonationTier) {

        DonationDto dto = donationService.createDonation(idCampaign, idDonationTier);

        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/{id}/updateImages")
    public ResponseEntity<?> updateDescriptionImages(@PathVariable(name ="id") Long idCampaign,
                                                     @ModelAttribute UpdateImagesDto images,
                                                     @ModelAttribute UpdateTierImagesDto tierImages)
            throws IOException {

        if (images.getDescriptionImages() != null) {
            campaignService.replaceDescriptionImages(idCampaign, images);
        } else {

            campaignService.replaceTierImages(idCampaign,tierImages);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Images updated correctly");
    }

}
