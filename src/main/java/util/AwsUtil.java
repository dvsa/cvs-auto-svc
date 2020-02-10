package util;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import exceptions.AutomationException;

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

    public static void insertInTable(String tableName, String json) {

        Regions clientRegion = Regions.EU_WEST_1;
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.eu-west-1.amazonaws.com", clientRegion.getName()))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        int year = 2015;
        String title = "The Big New Movie";

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("year", year, "title", title);

        try {
            System.out.println("Attempting to read the item...");
            Item outcome = table.getItem(spec);
            System.out.println("GetItem succeeded: " + outcome);

        }
        catch (Exception e) {
            System.err.println("Unable to read item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
    }

}