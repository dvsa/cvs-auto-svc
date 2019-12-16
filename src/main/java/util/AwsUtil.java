package util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
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
        System.out.println("file " + key + "was not created in 10 or less seconds..");
        return false;
    }

}