package util;

import exceptions.AutomationException;

import java.io.*;

public class WriterReader {


    private static final String FILE_NAME = "fileUtils.txt";

    public static void saveUtils() {


        FileUtils p1 = new FileUtils(WebDriverBrowsertack.getToken());

        try {
            FileOutputStream f = new FileOutputStream(new File(FILE_NAME));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(p1);

            o.close();
            f.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AutomationException("File Utils not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AutomationException("Error initializing stream");
        }

    }

    public static String getToken() {

        FileUtils pr1 = null;
        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                saveUtils();
            }

            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);

            pr1 = (FileUtils) oi.readObject();

            oi.close();
            fi.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return pr1.getToken();

    }


    public static void main(String[] args) {
        getToken();
    }

}
