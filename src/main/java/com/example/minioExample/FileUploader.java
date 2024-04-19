package com.example.minioExample;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://127.0.0.1:9000")
                            .credentials("sGSlI88JNcEJlkTeG2Jr", "ZiXPazkajKDM8QTqehPXms1oJnvzbPGSpjRGmULc")
                            .build();

            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("dev").build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("dev").build());
            } else {
                System.out.println("Bucket 'dev' already exists.");
            }

            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("dev")
                            .object("pic-dev.zip")
                            .filename("C:\\Users\\\\Admin\\pic.zip")
                            .build());
            System.out.println(
                    "'/Users/Admin/pic.zip' is successfully uploaded as "
                            + "object 'pic-dev.zip' to bucket 'dev'.");


            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket("dev").build());

            System.out.println(results.iterator().next().get().objectName());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}