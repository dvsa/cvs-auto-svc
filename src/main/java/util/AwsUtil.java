package util;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import data.GenericData;
import data.GenericData;
import exceptions.AutomationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static void insertJsonFromFileInTable(String fileName, String tableName) throws JSONException {

        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTestResultId = String.valueOf(UUID.randomUUID());

//        AWSCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();
//        Regions clientRegion = Regions.EU_WEST_1;
        AWSSecurityTokenService stsClient =
                AWSSecurityTokenServiceClientBuilder.standard().build();
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
//        STSAssumeRoleSessionCredentialsProvider arscp =
//                new STSAssumeRoleSessionCredentialsProvider.Builder(System.getProperty("AWS_ROLE"), uuid)
//                        .withStsClient(stsClient)
//                        .build();
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withRegion(clientRegion)
//                .withCredentials(arscp)
//                .build();
//
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("cvs-" + System.getProperty("BRANCH") + "-" + tableName);


        String json = GenericData.readJsonValueFromFile(fileName, "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        // create alteration to change test result id in the request body with the random generated test result id
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationTestResultId));
        String alteredJson = GenericData.applyJsonAlterations(json, alterations);

        JSONObject jsonObject = new JSONObject(alteredJson);
        List<String> excludePrimaryKeys = Arrays.asList("vin", "testResultId", "testerStaffId");
        List<String> jsonKeys = GenericData.getNonPrimaryKeyNames(jsonObject, excludePrimaryKeys);
        try {
            Item item = Item.fromJSON(alteredJson);
//            PrimaryKey primaryKey = new PrimaryKey();
//            primaryKey.addComponent("vin", randomVin);
//            primaryKey.addComponent("testResultId", randomTestResultId);
//            item = item.withPrimaryKey(primaryKey);
//            for (String key : jsonKeys) {
//                if (jsonObject.get(key).getClass().equals(JSONObject.class)) {
//                    item = item.withJSON(key, GenericData.getJsonObjectInPath(alteredJson, "$." + key));
//                }
////                else if (jsonObject.get(key).getClass().equals(JSONArray.class)) {
////                    List array  = Collections.singletonList(jsonObject.get(key));
////                    item = item.withList(key, array);
////                }
//                else {
//                    item = item.with(key, jsonObject.get(key));
//                }
//            }
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(item);
            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + randomVin);
            System.err.println(e);
        }
    }

}