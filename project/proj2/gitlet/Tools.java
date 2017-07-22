package gitlet;


import java.io.*;
import java.util.List;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class Tools {

    static Object load(String path) {
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

    static void save(Object obj, String path) {
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

    static Object deepCopy(Object obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }

    public static void folderMove(File from, File dest) throws FileNotFoundException {
        List<String> filesToMove = Utils.plainFilenamesIn(from);

        if (filesToMove != null) {
            for (String file : filesToMove) {
                File fileToMove = new File(from.getAbsolutePath() + "/" + file);
                File fileMoveTo = new File(dest.getAbsolutePath() + "/" + file);
                byte[] fileContent = Utils.readContents(fileToMove);
                Utils.writeContents(fileMoveTo, fileContent);
            }
        }
    }

    public static void folderDelt(File folder) {
        List<String> filesToDel = Utils.plainFilenamesIn(folder);

        if (filesToDel != null) {
            for (String file : filesToDel) {
                File fileToDel = new File(folder.getAbsolutePath() + "/" + file);
                Utils.restrictedDelete(fileToDel);
            }
        }
    }


    public static String getPath(File folder, String fileName) {
        return folder.getAbsolutePath() + "/" + fileName;
    }

    public static boolean validFile(File workDir, String fileName) {
        return (new File(getPath(workDir, fileName)).exists());
    }

    public static boolean fileDel(File folder, String fileName) {
        return Utils.restrictedDelete(new File(getPath(folder, fileName)));
    }

}
