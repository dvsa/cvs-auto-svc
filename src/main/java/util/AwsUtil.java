package util;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import data.GenericData;
import data.GenericData;
import exceptions.AutomationException;
import org.json.JSONException;

import java.util.*;

import java.util.UUID;

public class AwsUtil {

    private static Loader loader;

    static {
        EnvironmentType envType = TypeLoader.getType();
        switch (envType) {
            case CI_DEVELOP:
                loader = new CIDevelopLoaderImpl();
                break;
            case LOCAL:
                loader = new LocalLoaderImpl();
                break;
            default:
                throw new AutomationException("Environment configuration not found");
        }
    }

    public static boolean isCertificateCreated(String uuid, String vin){

        Regions clientRegion = Regions.EU_WEST_1;
        String bucketName = loader.getS3Bucket();

        String fileName = uuid + "_" + vin + "_1.pdf";
        String key =  loader.getBranchName()+ "/" + fileName;

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();

        System.out.println("Waiting on file " + key + "to be created... on bucket: " + bucketName);

        for(int i = 0; i < 15 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(s3Client.doesObjectExist(bucketName, key)){
                return s3Client.doesObjectExist(bucketName, key);
            }
            System.out.println("waited for: " + i + " seconds...");
        }
        System.out.println("file " + key + " was not created in 15 or less seconds..");
        return false;
    }

    public static void insertJsonnTable(String json, String tableName) {
        Regions clientRegion = Regions.EU_WEST_1;
        AWSSecurityTokenService stsClient =
                AWSSecurityTokenServiceClientBuilder.standard().withRegion(clientRegion).build();
        String uuid = String.valueOf(UUID.randomUUID());
        AssumeRoleRequest assumeRequest = new AssumeRoleRequest()
                .withRoleArn(System.getProperty("AWS_ROLE"))
                .withDurationSeconds(3600)
                .withRoleSessionName(uuid);
        AssumeRoleResult assumeResult =
                stsClient.assumeRole(assumeRequest);

        BasicSessionCredentials temporaryCredentials =
                new BasicSessionCredentials(
                        assumeResult.getCredentials().getAccessKeyId(),
                        assumeResult.getCredentials().getSecretAccessKey(),
                        assumeResult.getCredentials().getSessionToken());
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(temporaryCredentials);
        client.setRegion(Region.getRegion(clientRegion));
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("cvs-" + System.getProperty("BRANCH") + "-" + tableName);
        String vin = GenericData.getValueFromJsonPath(json, "$.vin");



        try {
            Item item = Item.fromJSON(json);
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getItem().toString());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + vin);
            System.err.println(e);
        }
    }

}