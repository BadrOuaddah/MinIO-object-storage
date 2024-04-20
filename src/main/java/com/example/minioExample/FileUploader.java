package com.example.minioExample;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

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

            ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("dev")
                            .object("compressed")
                            .filename("C:\\Users\\\\Admin\\pic.zip")
                            .build());
            System.out.println(objectWriteResponse);
            System.out.println(
                    "'/Users/Admin/pic.zip' is successfully uploaded as "
                            + "object 'pic-dev.zip' to bucket 'dev'.");

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket("dev")
                            .object("compressed")
                            .build());

            System.out.println(url);

            ListObjectsArgs dev = ListObjectsArgs.builder().bucket("dev").includeVersions(true).recursive(true).build();
            Iterable<Result<Item>> results = minioClient.listObjects(dev);
            Iterator<Result<Item>> iterator = results.iterator();


            while (iterator.hasNext()) {
                Item i = iterator.next().get();
                System.out.println("Object: " + i.objectName() +"with size: "+ i.size());
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}