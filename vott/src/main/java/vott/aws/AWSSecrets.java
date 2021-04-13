package vott.aws;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;

import java.nio.ByteBuffer;
import java.util.Map;

public class AWSSecrets {

    public static String getSecret(Map<String, String> env) {

        String secretName = env.get("SECRET");
        String endpoint = "secretsmanager.eu-west-1.amazonaws.com";
        String region = "eu-west-1";

        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        AWSSecretsManager client = clientBuilder.build();

        String secret;
        ByteBuffer binarySecretData;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName).withVersionStage("AWSCURRENT");
        GetSecretValueResult getSecretValueResult = null;
        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        } catch(ResourceNotFoundException e) {
            System.out.println("The requested secret " + secretName + " was not found");
        } catch (InvalidRequestException e) {
            System.out.println("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
            System.out.println("The request had invalid params: " + e.getMessage());
        }

        if(getSecretValueResult == null) {
            return null;
        }

        // Depending on whether the secret was a string or binary, one of these fields will be populated
        if(getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            return secret;
        }
        else {
            binarySecretData = getSecretValueResult.getSecretBinary();
             return binarySecretData.toString();
        }

    }

//    public static String getSecret(Map<String, String> env) {
//
//        String secretName = env.get("SECRET");
//        String region = "eu-west-1";
//
//        // Create a Secrets Manager client
//        AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
//                .withRegion(region)
//                .build();
//
//        // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
//        // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
//        // We rethrow the exception by default.
//
//        String decodedBinarySecret;
//        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
//                .withSecretId(secretName);
//        GetSecretValueResult getSecretValueResult = null;
//
//        try {
//            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
//        } catch (DecryptionFailureException e) {
//            // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
//            // Deal with the exception here, and/or rethrow at your discretion.
//            throw e;
//        } catch (InternalServiceErrorException e) {
//            // An error occurred on the server side.
//            // Deal with the exception here, and/or rethrow at your discretion.
//            throw e;
//        } catch (InvalidParameterException e) {
//            // You provided an invalid value for a parameter.
//            // Deal with the exception here, and/or rethrow at your discretion.
//            throw e;
//        } catch (InvalidRequestException e) {
//            // You provided a parameter value that is not valid for the current state of the resource.
//            // Deal with the exception here, and/or rethrow at your discretion.
//            throw e;
//        } catch (ResourceNotFoundException e) {
//            // We can't find the resource that you asked for.
//            // Deal with the exception here, and/or rethrow at your discretion.
//            throw e;
//        }
//
//        // Decrypts secret using the associated KMS CMK.
//        // Depending on whether the secret is a string or binary, one of these fields will be populated.
//        if (getSecretValueResult.getSecretString() != null) {
//            return getSecretValueResult.getSecretString();
//        }
//        else {
//            decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
//            return decodedBinarySecret;
//        }
//
//        // Your code goes here.
//    }

}