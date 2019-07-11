package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.exceptions.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author fabricio
 */

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucket;

    public void uploadFileLocal(String localFilePath){

        try {

            File file = new File(localFilePath);
            LOG.info("Starting upload");
            s3Client.putObject(new PutObjectRequest(bucket, "Test.jpeg", file));
            LOG.info("Starting has done");

        } catch (AmazonServiceException e){

            LOG.info("AmazonException " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());

        } catch (AmazonClientException e){

            LOG.info("AmazonClientException" + e.getMessage());
        }


    }

    public URI uploadFile(MultipartFile multipartFile){
        try {

            String filename = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            return uploadFile(is,filename,contentType);

        } catch (IOException e){
            throw new FileException("IO Exception: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String fileName, String contentType){
        try {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            s3Client.putObject(new PutObjectRequest(bucket, fileName, is, objectMetadata));

            return s3Client.getUrl(bucket, fileName).toURI();

        } catch (URISyntaxException e){
            throw new FileException("Cannot convert a URL to URI");
        }

    }
}
