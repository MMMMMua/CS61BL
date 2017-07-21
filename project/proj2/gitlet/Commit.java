package gitlet;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class Commit implements Serializable {
    String parent;
    String message;
    Date commitDate;
    FileTree files;
    //ArrayList<String> files;


    public Commit() {
        parent = "";
        message = "";
        commitDate = new Date();
        files = new FileTree();
    }

    public Commit(String __parent, String __message) {
        parent = __parent;
        message = __message;
        commitDate = new Date();
        files = new FileTree();
    }

    public String save(String path) {
        String location = path + "/" + this.toString();
        Tools.save(this, location);
        return location;
    }

    public static Commit load(String path) {
        return (Commit) Tools.load(path);
    }

    public String toString() {
        String info = "";
        for (String file : files.getcontent()) {
            info += file;
        }

        String SHA_code = gitlet.Utils.sha1(parent, info, commitDate.toString());
        return SHA_code;
    }
}
