package gitlet;

import java.io.Serializable;

/**
 * Created by hanxiangren on 20/07/2017.
 */
public class Branch implements Serializable {
    String commit_id;
    String name;


    public Branch(String __commit_id, String __name) {
        commit_id = __commit_id;
        name = __name;
    }

    public Branch load(String path) {
        return (Branch) Tools.load(path);
    }

    public String save(String path) {
        String location = path + "/" + this.toString();
        Tools.save(this, location);
        return location;
    }

    public String toString() {
        return name;
    }
}
