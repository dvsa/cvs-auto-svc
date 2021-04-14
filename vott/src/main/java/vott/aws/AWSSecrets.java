package vott.aws;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

public class AWSSecrets {

    private static String getSecretAWS(Map<String, String> env){
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

    private static String getSecretLocal() {
        Properties dbProperties = new Properties();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("database.properties"))) {
            dbProperties.load(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return dbProperties.getProperty("database.config");
    }

    public static String getSecret(Map<String, String> env) {
        if (env.get("ENVIRONMENT") != null){
            return getSecretAWS(env);
        } else {
            return getSecretLocal();
        }
    }

}