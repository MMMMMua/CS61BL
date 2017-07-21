package gitlet;


import java.io.*;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class Tools {

    public static Object load(String path) {
        Object result = new Object();
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            result = in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException io) {
            io.printStackTrace();
            return null;
        } catch (ClassNotFoundException cl) {
            cl.printStackTrace();
            return null;
        }
        return result;
    }

    public static void save(Object obj, String path) {
        try {
            FileOutputStream fileout = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(obj);
            out.close();
            fileout.close();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
