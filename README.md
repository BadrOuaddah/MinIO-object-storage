# MinIO-object-storage 🦩

This is a simple example of Object storage with MinIO 🦩

## Versions
+ Java 17
+ Spring boot 3.2.4
+ Maven 4.0.0
+ MinIO 8.5.9

## Dependencies in pom.xml

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web-services</artifactId>
        </dependency>

        <dependency>
            <groupId>io.minio</groupId>
            <artifactId>minio</artifactId>
            <version>8.5.9</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

## Steps and demo

- Run docker-compose with this command :
  ```cmd
  docker-compose up
  ```

- Open MinIO server with 9090 number port :

  http://localhost:9090/
  
![image](https://github.com/user-attachments/assets/588cef8d-db37-4e9a-8aa6-0725be612bfa)

- Do login using the username and password from the environment in Docker Compose.

- After login
  
  ![image](https://github.com/user-attachments/assets/77f2129a-b5af-46dc-8d1b-bc7c327e1112)


- Run application in the FileUploader class to use minioClient with the specified endpoint and credentials, create a new bucket named dev, and upload a sample PDF file from the resources folder.

> Sample.pdf

![image](https://github.com/user-attachments/assets/3c9aec54-b028-4f28-92eb-0474da15a09a)

- The result after running app
  
> Terminal

```bash
io.minio.ObjectWriteResponse@3bf7ca37
'sample.pdf' is successfully uploaded as object 'sample.pdf' to bucket 'dev'.
http://localhost:9000/dev/sample?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20250212%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250212T174809Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=7a5ec2c05205dd7757bbcb1333607fb258158c453933ac8c2da77f6100650d29
Object: sample with size: 18810
```

> MinIO Server

![image](https://github.com/user-attachments/assets/aa12eee5-7d71-4093-974b-c400b61524ba)
