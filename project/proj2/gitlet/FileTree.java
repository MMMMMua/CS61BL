package gitlet;

import java.awt.event.ComponentAdapter;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by hanxiangren on 20/07/2017.
 */

public class FileTree implements Serializable {

    HashMap<String, String> files;

    FileTree() {
        files = new HashMap<String, String>();
    }

    public HashMap<String, String> getcontent() {
        return files;
    }

    void addFile(Blob file) {
        files.put(file.name, file.SHA_code); //put will overwrite that key-val if exists.
    }

    void removeFile(String name) {
        if (files.containsKey(name)) {
            files.remove(name);
        } else {
            System.out.printf("file does not exist");
        }
    }

    boolean contains(Blob file) {
        return files.containsKey(file.name) && files.get(file.name).equals(file.content);
    }

    boolean contains(String fileName) {
        return files.containsKey(fileName);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() == this.getClass())) {
            return false;
        }
        return ((FileTree) obj).files.equals(this.files);
    }

    public void reMove(String fileName) {
        if (files.containsKey(fileName)) {
            files.remove(fileName);
            Tools.fileDel(CommandParser.blobsSta, fileName);
        }
    }
}
