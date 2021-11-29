package util;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.AWSLogsClient;
import com.amazonaws.services.logs.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.jayway.jsonpath.JsonPath;
import data.GenericData;
import exceptions.AutomationException;

import java.util.*;

public class AwsUtil {

    private static Loader loader = new LocalLoaderImpl();

    public static boolean isCertificateCreated(String testNumber, String vin){

        Regions clientRegion = Regions.EU_WEST_1;
        AWSSecurityTokenService stsClient =
                AWSSecurityTokenServiceClientBuilder.standard().withRegion(clientRegion).build();
        String uuid = String.valueOf(UUID.randomUUID());
        AssumeRoleRequest assumeRequest = new AssumeRoleRequest()
                .withRoleArn(System.getProperty("AWS_ROLE"))
                .withDurationSeconds(5000)
                .withRoleSessionName(uuid);
        AssumeRoleResult assumeResult =
                stsClient.assumeRole(assumeRequest);

        BasicSessionCredentials temporaryCredentials =
                new BasicSessionCredentials(
                        assumeResult.getCredentials().getAccessKeyId(),
                        assumeResult.getCredentials().getSecretAccessKey(),
                        assumeResult.getCredentials().getSessionToken());
        String bucketName = loader.getS3Bucket();

        String fileName = testNumber + "_" + vin + ".pdf";
        String key =  loader.getBranchName()+ "/" + fileName;

        AmazonS3 s3Client = new AmazonS3Client(temporaryCredentials);

        System.out.println("Waiting on file " + key + " to be created... on bucket: " + bucketName);

        for(int i = 0; i < 240 ; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 2 == 0) {
                int j = i % 2;
                System.out.println("waited for: " + j + " seconds...");
            }
            if (s3Client.doesObjectExist(bucketName, key)) {
                return s3Client.doesObjectExist(bucketName, key);
            }
        }
        System.out.println("file " + key + " was not created in 120 seconds or less...");
        return false;
    }

    public static boolean checkLogsFor(String log, String keyValuePair) {

        Regions clientRegion = Regions.EU_WEST_1;
        AWSSecurityTokenService stsClient =
                AWSSecurityTokenServiceClientBuilder.standard().withRegion(clientRegion).build();

        System.out.println(System.getProperty("AWS_ROLE"));

        AssumeRoleRequest assumeRequest = new AssumeRoleRequest()
                .withRoleArn(System.getProperty("AWS_ROLE"))
                .withDurationSeconds(3600)
                .withRoleSessionName(UUID.randomUUID().toString());
        AssumeRoleResult assumeResult =
                stsClient.assumeRole(assumeRequest);

        BasicSessionCredentials temporaryCredentials =
                new BasicSessionCredentials(
                        assumeResult.getCredentials().getAccessKeyId(),
                        assumeResult.getCredentials().getSecretAccessKey(),
                        assumeResult.getCredentials().getSessionToken());

        AWSLogs logsClient = new AWSLogsClient(temporaryCredentials).withRegion(clientRegion);
        String logGroup = log + "-" + loader.getBranchName();

        for (int times = 0; times < 15; times++) {

            System.out.println("... " + times + " ...");
            DescribeLogStreamsRequest describeLogStreamsRequest = new DescribeLogStreamsRequest()
                    .withLogGroupName(logGroup)
                    .withOrderBy("LastEventTime")
                    .withDescending(true)
                    .withLimit(1);
            DescribeLogStreamsResult describeLogStreamsResult = logsClient.describeLogStreams(describeLogStreamsRequest);

            LogStream logStream = describeLogStreamsResult.getLogStreams().get(0);
            GetLogEventsRequest getLogEventsRequest = new GetLogEventsRequest()
//                    .withStartTime(currentTimestamp.getMillis())
//                    .withEndTime(currentTimestamp.plusMinutes(1).getMillis())
                    .withLogGroupName(logGroup)
                    .withLogStreamName(logStream.getLogStreamName());

            GetLogEventsResult result = logsClient.getLogEvents(getLogEventsRequest);
            for (OutputLogEvent event : result.getEvents()) {
                System.out.println("*****************************");
                System.out.println("# event: " + event.getMessage());

                System.out.println("Looking for: " + keyValuePair);

                if (event.getMessage().contains(keyValuePair)) {
                    System.out.println("!!!!!!!!!!!!!!!###### FOUND !!! ######!!!!!!!!!!!!!!!");
                    System.out.println("$$$$$$$$$$$   " + logStream.getLogStreamName() + "   $$$$$$$$$$$");
                    return true;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;

    }

    public static boolean checkDispatcherLogsForData(String... keyValuePairs) {
        Regions clientRegion = Regions.EU_WEST_1;
        AWSSecurityTokenService stsClient =
                AWSSecurityTokenServiceClientBuilder.standard().withRegion(clientRegion).build();

        System.out.println(System.getProperty("AWS_ROLE"));

        AssumeRoleRequest assumeRequest = new AssumeRoleRequest()
                .withRoleArn(System.getProperty("AWS_ROLE"))
                .withDurationSeconds(3600)
                .withRoleSessionName(UUID.randomUUID().toString());
        AssumeRoleResult assumeResult =
                stsClient.assumeRole(assumeRequest);

        BasicSessionCredentials temporaryCredentials =
                new BasicSessionCredentials(
                        assumeResult.getCredentials().getAccessKeyId(),
                        assumeResult.getCredentials().getSecretAccessKey(),
                        assumeResult.getCredentials().getSessionToken());

        AWSLogs logsClient = new AWSLogsClient(temporaryCredentials).withRegion(clientRegion);
        String logGroup = "/aws/lambda/edh-dispatcher-" + loader.getBranchName();

        logStreamLoop:
        for (int times = 0; times < 50; times++) {
            System.out.println("... " + times + " ...");
            DescribeLogStreamsRequest describeLogStreamsRequest = new DescribeLogStreamsRequest()
                    .withLogGroupName(logGroup)
                    .withOrderBy("LastEventTime")
                    .withDescending(true)
                    .withLimit(20);

            DescribeLogStreamsResult describeLogStreamsResult = logsClient.describeLogStreams(describeLogStreamsRequest);

            LogStream logStream = describeLogStreamsResult.getLogStreams().get(0);
            GetLogEventsRequest getLogEventsRequest = new GetLogEventsRequest()
//                    .withStartTime(currentTimestamp.getMillis())
//                    .withEndTime(currentTimestamp.plusMinutes(1).getMillis())
                    .withLogGroupName(logGroup)
                    .withLogStreamName(logStream.getLogStreamName());

            GetLogEventsResult result = logsClient.getLogEvents(getLogEventsRequest);

            eventLoop:
            for (OutputLogEvent event : result.getEvents()) {
                System.out.println("\n----------------------------------------------------------------------");
                for (String keyValuePair : keyValuePairs) {
                    System.out.println("searching inside event for: " + keyValuePair);
                    if (!event.getMessage().contains(keyValuePair)) {
                        System.out.println("########################## " + keyValuePair + " NOT FOUND in event: \n" + event.getMessage() + "\n ##########################");
                        continue eventLoop;
                    }
                }
                System.out.println("!!!!!!!!!!!!!!!###### FOUND !!! ######!!!!!!!!!!!!!!!");
                System.out.println("$$$$$$$$$$$   " + logStream.getLogStreamName() + "   $$$$$$$$$$$");
                return true;
            }

            try {
                System.out.println("########################## " + keyValuePairs + " NOT FOUND in log stream: \n" + logStream.getLogStreamName() + "\n ##########################");
                System.out.println("waiting 2 seconds");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    public static String getNextSystemNumberInSequence() {
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
        String tableName = "cvs-" + loader.getBranchName() + "-test-number";
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<ScanOutcome> items = table.scan("attribute_exists(systemNumber)", // FilterExpression
                "systemNumber", // ProjectionExpression
                null, // ExpressionAttributeNames - not used in this example
                null // ExpressionAttributeValues - not used in this example
        );

        System.out.println("Scan of " + tableName + " for items with systemNumber not null");
        Iterator<Item> iterator = items.iterator();
        String lastSystemNumberUsed = null;
        while (iterator.hasNext()) {
            lastSystemNumberUsed = GenericData.getValueFromJsonPath(iterator.next().toJSONPretty(), "$.systemNumber");
        }
        if (lastSystemNumberUsed != null) {
            int nextSystemNumberInSequence = Integer.parseInt(lastSystemNumberUsed) + 1;
            return Integer.toString(nextSystemNumberInSequence);
        } else {
            throw new AutomationException("No value found for last used systemNumber");
        }
    }

    public static String getNextTrailerIdInSequence() {
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
        String tableName = "cvs-" + loader.getBranchName() + "-test-number";
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<ScanOutcome> items = table.scan("attribute_exists(trailerId)", // FilterExpression
                "trailerId, sequenceNumber, trailerLetter", // ProjectionExpression
                null, // ExpressionAttributeNames - not used in this example
                null // ExpressionAttributeValues - not used in this example
        );

        System.out.println("Scan of " + tableName + " for items with trailerId not null");
        Iterator<Item> iterator = items.iterator();
        String dynamoInfo = null;
        while (iterator.hasNext()) {
            dynamoInfo = iterator.next().toJSONPretty();
        }
        int lastTrailerSequenceNumberUsed = GenericData.extractIntegerValueFromJsonString(dynamoInfo, "$.sequenceNumber");
        String trailerLetter = GenericData.getValueFromJsonPath(dynamoInfo, "$.trailerLetter");
        if (lastTrailerSequenceNumberUsed != 0 && trailerLetter != null) {
            int nextTrailerNumberInSequence = lastTrailerSequenceNumberUsed + 1;
            return trailerLetter + nextTrailerNumberInSequence;
        } else {
            throw new AutomationException("No value found for last used sequence number or trailer letter");
        }
    }

    /**
     * @deprecated replaced by #insertJsonInTable(java.lang.String, java.lang.String, java.lang.String)()
     */
    @Deprecated
    public static void insertJsonInTable(String json, String tableName) {
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

        Table table = dynamoDB.getTable("cvs-" + loader.getBranchName() + "-" + tableName);
        String vin = GenericData.getValueFromJsonPath(json, "$.vin");

        try {
            Item item = Item.fromJSON(json);
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(item);
            System.out.println("PutItem succeeded:\n" + item.toJSONPretty());

        } catch (Exception e) {
            System.err.println("Unable to add item with vin: " + vin);
            System.err.println(e);
        }
    }

    public static void insertJsonInTable(String json, String tableName, String primaryKey) {
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

        Table table = dynamoDB.getTable("cvs-" + loader.getBranchName() + "-" + tableName);
        String valueForPrimaryKey = GenericData.getValueFromJsonPath(json, "$." + primaryKey);

        try {
            Item item = Item.fromJSON(json);
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(item);
            System.out.println("PutItem succeeded:\n" + item.toJSONPretty());

        } catch (Exception e) {
            System.err.println("Unable to add item with " + primaryKey + ": " + valueForPrimaryKey);
            e.printStackTrace();
        }
    }

    public static void insertActivity(String jsonBody) {

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
        String tableName = "cvs-" + loader.getBranchName() + "-activities";

        Table table = dynamoDB.getTable(tableName);

        String id = GenericData.getValueFromJsonPath(jsonBody, "$.id");

        try {
            Item item = Item.fromJSON(jsonBody);
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(item);
            System.out.println("PutItem succeeded:\n" + item.toJSONPretty());

        } catch (Exception e) {
            System.err.println("Unable to add item with id: " + id);
            System.err.println(e);
        }
    }

    public static void insertVehicle(String jsonBody) {
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
        String tableName = "cvs-" + loader.getBranchName() + "-technical-records";

        Table table = dynamoDB.getTable(tableName);

        String sysNo = GenericData.getValueFromJsonPath(jsonBody, "$.systemNumber");

        try {
            Item item = Item.fromJSON(jsonBody);
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(item);
            System.out.println("PutItem succeeded:\n" + item.toJSONPretty());

        } catch (Exception e) {
            System.err.println("Unable to add item with systemNumber: " + sysNo);
            System.err.println(e);
        }
    }

    public static void deleteActivitiesForUser(String testerName) {
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
        String tableName = "cvs-" + loader.getBranchName() + "-activities";

        Table table = dynamoDB.getTable(tableName);

        Index index = table.getIndex("StaffIndex");
        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("testerStaffId = :staff_id")
                .withValueMap(new ValueMap()
                        .withString(":staff_id", testerName));

        ItemCollection<QueryOutcome> items = index.query(spec);
        for (Item item : items) {
            String id = JsonPath.read(item.toJSON(), "$.id");
            System.out.println("Delete item:\n" + item.toJSONPretty());

            DeleteItemOutcome outcome = table.deleteItem("id", id);
        }
    }

    public static void updateEmailsForTestStation(String primaryKeyValue, String... updateValue) {
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
        String actualTableName = "cvs-" + loader.getBranchName() + "-test-stations";
        Table table = dynamoDB.getTable(actualTableName);


        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("testStationId", primaryKeyValue)
                .withUpdateExpression("set testStationEmails = :fieldToUpdate")
                .withValueMap(new ValueMap().withList(":fieldToUpdate", Arrays.asList(updateValue)))
                .withReturnValues(ReturnValue.UPDATED_NEW);

        try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

        } catch (Exception e) {
            System.err.println("Unable to update item: primaryKeyValue");
            System.err.println(e.getMessage());
        }
    }

    public static void deleteActivityById(String id) {
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
        String tableName = "cvs-" + loader.getBranchName() + "-activities";

        Table table = dynamoDB.getTable(tableName);

        try {

            System.out.println("deleting item with id: " + id + " ....");
            DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                    .withPrimaryKey("id", id);
//                    .withConditionExpression("#ip = :val")
//                    .withNameMap(new NameMap()
//                            .with("#ip", "InProduction"))
//                    .withValueMap(new ValueMap()
//                            .withBoolean(":val", false))
//                    .withReturnValues(ReturnValue.ALL_OLD);
            DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
            System.out.println("Printing item that was deleted...");
        } catch (Exception e) {
            System.err.println("Error deleting item in " + tableName);
            System.err.println(e.getMessage());
        }

    }

    public static void deleteTestResultId(String testResultId) {
        System.out.println("deleting the test-result: " + testResultId);
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
        String tableName = "cvs-" + loader.getBranchName() + "-test-results";

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":result_id", new AttributeValue().withS(testResultId));

        ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName)
                .withFilterExpression("testResultId = :result_id")
                .withProjectionExpression("vin, testResultId")
                .withExpressionAttributeValues(expressionAttributeValues);
        ScanResult result = client.scan(scanRequest);
        System.out.println("result.toString: " + result.toString());
        System.out.println("result.getCount: " + result.getCount());
        System.out.println("result.getItems:" + result.getItems());

        Table table = dynamoDB.getTable(tableName);

        for (Map<String, AttributeValue> item : result.getItems()) {
            System.out.println("item.size: " + item.size());
            System.out.println("item.values: " + item.values());
            System.out.println("item.get(vin).getS(): " + item.get("vin").getS());
            DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                    .withPrimaryKey("vin", item.get("vin").getS(), "testResultId", testResultId);
            DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
        }

    }

    public static void deleteVehicleById(String systemNumber) {
        System.out.println("deleting the vehicle: " + systemNumber);
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
        String tableName = "cvs-" + loader.getBranchName() + "-technical-records";

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":system_no", new AttributeValue().withS(systemNumber));

        ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName)
                .withFilterExpression("systemNumber = :system_no")
                .withProjectionExpression("vin, systemNumber")
                .withExpressionAttributeValues(expressionAttributeValues);
        ScanResult result = client.scan(scanRequest);
        System.out.println("result.toString: " + result.toString());
        System.out.println("result.getCount: " + result.getCount());
        System.out.println("result.getItems:" + result.getItems());

        Table table = dynamoDB.getTable(tableName);

        for (Map<String, AttributeValue> item : result.getItems()) {
            System.out.println("item.size: " + item.size());
            System.out.println("item.values: " + item.values());
            System.out.println("item.get(systemNumber).getS(): " + item.get("systemNumber").getS());
            DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                    .withPrimaryKey("systemNumber", systemNumber, "vin", item.get("vin").getS());
            DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
        }

    }


}