package gitlet;

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
        SHA_code = Utils.sha1(name, content);
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
