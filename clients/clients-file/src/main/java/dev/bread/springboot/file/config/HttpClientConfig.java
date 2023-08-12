package dev.bread.springboot.file.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;

import static dev.bread.springboot.file.config.Property.*;
import static dev.bread.springboot.file.config.Property.SECRET_KEY;

public class HttpClientConfig {

    public static final AmazonS3 S3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(END_POINT, REGION_NAME))
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
            .build();

    public static final String BUCKET_NAME = "mybox-file";

    public static PutObjectRequest fileUploadConfig(String folderName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");

        return new PutObjectRequest(BUCKET_NAME, folderName,
                new ByteArrayInputStream(new byte[0]), objectMetadata);
    }
}
