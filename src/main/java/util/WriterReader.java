package util;

import exceptions.AutomationException;

import java.io.*;

public class WriterReader {
    public static String instanceToken="";

    private static final String FILE_NAME = "fileUtils.txt";

    public static void saveUtils() {
        System.out.println(instanceToken);
        FileUtils p1 = new FileUtils(instanceToken);
//        FileUtils p1 = new FileUtils(WebDriverBrowsertack.getToken());
        try(FileOutputStream f = new FileOutputStream(new File(FILE_NAME));ObjectOutputStream o = new ObjectOutputStream(f) ) {

            o.writeObject(p1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AutomationException("File Utils not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AutomationException("Error initializing stream");
        }

    }

    public static String getToken() {

        FileUtils fileUtils;
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            saveUtils();
        }

        try(FileInputStream fi = new FileInputStream(file); ObjectInputStream oi = new ObjectInputStream(fi)) {

            fileUtils = (FileUtils) oi.readObject();

        } catch (IOException e) {
            e.printStackTrace();
            throw new AutomationException("File Utils not found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new AutomationException("Error initializing stream");
        }
        return fileUtils.getToken();
    }


    public static void main(String[] args) throws Exception {
        UserNamePasswordFlow.createToken();
        instanceToken=UserNamePasswordFlow.token;
        getToken();
    }

}
