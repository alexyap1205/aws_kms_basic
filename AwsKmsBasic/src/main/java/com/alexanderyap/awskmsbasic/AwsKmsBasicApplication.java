package com.alexanderyap.awskmsbasic;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DataKeySpec;
import com.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class AwsKmsBasicApplication {

    private static final byte[] EXAMPLE_DATA = "Hello World".getBytes(StandardCharsets.UTF_8);

    public static void main(String[] args) {

        GenerateDataKey();

    }

    private static void GenerateDataKey() {
        String keyId = "arn:aws:kms:ap-southeast-2:002046995830:key/1e5da6b1-865f-4348-b4a8-3f424f572464";
        String keySpec = "SYMMETRIC_DEFAULT";

        AWSKMS kmsClient = AWSKMSClientBuilder.standard().build();

        // Generate a data key

        GenerateDataKeyRequest dataKeyRequest = new GenerateDataKeyRequest();
        dataKeyRequest.setKeyId(keyId);
        dataKeyRequest.setKeySpec(DataKeySpec.AES_256);

        GenerateDataKeyResult dataKeyResult = kmsClient.generateDataKey(dataKeyRequest);

        ByteBuffer plaintextKey = dataKeyResult.getPlaintext();

        ByteBuffer encryptedKey = dataKeyResult.getCiphertextBlob();

        System.out.printf(
                "Successfully generated an encrypted data key: %s%n",
                Base64.getEncoder().encodeToString(encryptedKey.array())
        );
    }

}
