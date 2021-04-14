package vott.aws;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;

import java.nio.ByteBuffer;
import java.util.Map;

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

    private static String getSecretLocal(Map<String, String> env){
        return env.get("DB_CONFIG");
    }

    public static String getSecret(Map<String, String> env) {
        if (env.get("ENVIRONMENT") == "local"){
            return getSecretLocal(env);
        } else {
            return getSecretAWS(env);
        }
    }

}