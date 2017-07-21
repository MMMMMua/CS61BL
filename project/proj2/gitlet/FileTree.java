package gitlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hanxiangren on 20/07/2017.
 */

public class FileTree implements Serializable {

    ArrayList<String> files;

    public FileTree() {
        files = new ArrayList<String>();
    }

    public FileTree(String... __files) {
        Collections.addAll(files, __files);
    }

    public ArrayList<String> getcontent() {
        return files;
    }

    public void addFile(String file) {
        if (files.contains(file)) {
            System.out.printf("file already exists");
        } else {
            files.add(file);
        }
    }

    public void removeFile(String file) {
        if (files.contains(file)) {
            files.remove(files.indexOf(file));
        } else {
            System.out.printf("file does not exist");
        }
    }
}
