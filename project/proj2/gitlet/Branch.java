package gitlet;

import java.io.File;
import java.io.Serializable;

/**
 * Created by hanxiangren on 20/07/2017.
 */
public class Branch implements Serializable {
    String commit_id;
    String name;
    String savingPosition;

    Branch(String __commit_id, String __name) {
        commit_id = __commit_id;
        name = __name;
        savingPosition = new String();
    }

    static Branch load(String path) {
        return (Branch) Tools.load(path);
    }

    String save(String path) {
        savingPosition = path + "/" + this.toString();
        Tools.save(this, savingPosition);
        return savingPosition;
    }

    String save(File folder) {
        savingPosition = Tools.getPath(folder, this.toString());
        Tools.save(this, savingPosition);
        return savingPosition;
    }

    String save() {
        assert (!savingPosition.equals(""));
        Tools.save(this, savingPosition);
        return savingPosition;
    }

    public String toString() {
        return name;
    }
}
