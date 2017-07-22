package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class Commit implements Serializable {
    String parent;
    String message;
    String savingPostition;
    Date commitDate;
    FileTree files;

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

    static Commit load(String path) {
        return (Commit) Tools.load(path);
    }

    static Commit load(File folder, String fileName) {
        return (Commit) Tools.load(Tools.getPath(folder, fileName));
    }

    String save(File folder) {
        this.savingPostition = folder.getAbsolutePath() + "/" + this.toString();
        Tools.save(this, this.savingPostition);
        return this.savingPostition;
    }

    String save() {
        assert (!this.savingPostition.equals(""));
        Tools.save(this, this.savingPostition);
        return this.savingPostition;
    }

    boolean containBlob(Blob fileName) {
        return files.contains(fileName);
    }

    boolean containFile(String fileName) {
        return files.contains(fileName);
    }

    void reMove(String fileName) {
        files.reMove(fileName);
    }

    void addBlob(Blob file) { //only possible call in staging area.
        file.save(CommandParser.blobsSta.getAbsolutePath());
        files.addFile(file);
        this.save();
    }

    public String toString() {
        String info = String.valueOf(files.hashCode());
        return Utils.sha1(parent, info, message, commitDate.toString());
    }

}
