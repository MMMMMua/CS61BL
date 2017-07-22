package gitlet;

import java.io.File;
import java.io.Serializable;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class Blob implements Serializable {
    String SHA_code;
    String name;
    String content;

    public Blob(String __name, String __content) {
        name = __name;
        content = __content;
        SHA_code = Utils.sha1(content);
    }

    public Blob(String path) {
        File file = new File(path);
        name = file.getName();
        content = new String(Utils.readContents(file));
        SHA_code = Utils.sha1(content);
    }

    public static Blob load(String path) {
        return (Blob) Tools.load(path);
    }

    public String save(String path) {
        String location = path + "/" + this.toString();
        Tools.save(this, location);
        return location;
    }

    public String toString() {
        return SHA_code;
    }

}
