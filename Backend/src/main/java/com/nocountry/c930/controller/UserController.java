package com.nocountry.c930.controller;

import com.nocountry.c930.auth.dto.UserRegistrationDto;
import com.nocountry.c930.dto.CampaignBasicDto;
import com.nocountry.c930.dto.PageDto;
import com.nocountry.c930.dto.UserDto;
import com.nocountry.c930.repository.UserRepository;
import com.nocountry.c930.service.ICampaignService;
import com.nocountry.c930.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICampaignService campaignService;


    @GetMapping("/{id}")
    @ApiOperation(value = "Get User Info",
            notes = "Gets all the user information, including tournaments that manages and teams that belongs to.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID is invalid (must use numbers value only"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Long idUser) {

        UserDto dto = userService.getUser(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(dto);


    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an user",
            notes = "Deletes an user, you can only delete your own user unless you are an Admin")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID is invalid (User number values only)"),
            @ApiResponse(code = 401, message = "You can only delete your own user"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long idUser) {

        if (userService.deleteUser(idUser)) {
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be deleted");
        }

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates your Info",
            notes = "Updates the user information, only can update your own information")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID is invalid (User number values only)"),
            @ApiResponse(code = 401, message = "You can only update your own information"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long idUser, @RequestBody UserRegistrationDto dto) {

        UserDto responseUserDto = userService.updateUser(idUser, dto);

        return ResponseEntity.status(HttpStatus.OK).body(responseUserDto);

    }

    @GetMapping("/{id}/campaigns")
    @ApiOperation(value = "Gets all the campaigns made by an user")
    public ResponseEntity<List<CampaignBasicDto>> getCampaignByUser(@PathVariable(name = "id") Long idUser) {

        List<CampaignBasicDto> result = campaignService.listCampaignByUser(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(result);


    }


    @GetMapping()
    @ApiOperation(value = "List All Users",
            notes = "Gives you a paginated list of all the users, only administrators can use this endpoint")
    public ResponseEntity<PageDto<UserDto>> getAllUsers(@PageableDefault(size = 5) Pageable page,
                                                        HttpServletRequest request) {

        PageDto<UserDto> result = userService.listAllUsers(page, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
