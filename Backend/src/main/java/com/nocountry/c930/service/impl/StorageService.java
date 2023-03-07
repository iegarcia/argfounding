package com.nocountry.c930.service.impl;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class StorageService {

    private final AmazonS3 space;
    
    @Value("${aws.accesKey}")
    private String accesKey;
    
    @Value("${aws.secretKey}")
    private String secretKey;

    public StorageService() {

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("accesKey", "secretKey")
        );

        space = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://argfoundingimages.nyc3.digitaloceanspaces.com", "nyc3"))
                .build();
    }

    public String uploadImage(MultipartFile image) throws IOException {


        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        space.putObject(new PutObjectRequest("", image.getOriginalFilename(), image.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));

        return "https://argfoundingimages.nyc3.cdn.digitaloceanspaces.com/" + image.getOriginalFilename();


    }
}
