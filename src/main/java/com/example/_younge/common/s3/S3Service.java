package com.example._younge.common.s3;

import com.example._younge.common.exception.EmptyImageFileException;
import com.example._younge.common.exception.InvalidImageFileException;
import com.example._younge.common.exception.S3UploadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadProfileImage(Long id, MultipartFile file) {
        validateProfileImage(file);

        String key = "members/" + id + "/profile/" + UUID.randomUUID() + getExtension(file.getOriginalFilename());

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(file.getContentType()).build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            return key;
        } catch (IOException e) {

            throw new S3UploadFailedException();
        }
    }

    public String createPresignedUrl(String key) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucket).key(key).build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))
                .getObjectRequest(getObjectRequest).build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }

    private void validateProfileImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new EmptyImageFileException();
        }

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new InvalidImageFileException();
        }
    }

    private String getExtension(String fileName) {

        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }
}
