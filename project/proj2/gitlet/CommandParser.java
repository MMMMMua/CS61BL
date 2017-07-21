package gitlet;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class CommandParser {
    public static File home_dir;
    public static File staging;
    public static File history;
    public static File systems;
    public static File blobs;
    public static File commits;
    public static File branches;
    public static File HEAD;

    private static void createfile() throws IOException {
        //home_dir.mkdir();
        staging = new File(home_dir.getAbsolutePath() + "/staging");
        history = new File(home_dir.getAbsolutePath() + "/history");
        systems = new File(home_dir.getAbsolutePath() + "/systems");

        staging.mkdir();
        history.mkdir();
        systems.mkdir();

        blobs = new File(history.getAbsolutePath() + "/blobs");
        commits = new File(history.getAbsolutePath() + "/commits");
        branches = new File(systems.getAbsolutePath() + "/branches");

        blobs.mkdir();
        commits.mkdir();
        branches.mkdir();

        HEAD = new File(systems.getAbsolutePath() + "/HEAD");
        HEAD.createNewFile();
    }

    private static boolean has_inited() throws IOException {
        File test = new File("../demo/.gitlet");
        if (test.isDirectory())
            return true;
        home_dir = test;
        home_dir.mkdir();
        return false;
    }


    private static void init_commit() {
        Commit first = new Commit();
        first.save(commits.getAbsolutePath());

        Branch master = new Branch(first.toString(), "master"); //branch tracks a commit
        String head_pos = master.save(branches.getAbsolutePath());


        Utils.writeContents(HEAD, head_pos.getBytes());
    }

    public static void init() throws IOException {
        if (has_inited()) {
            System.out.printf("A gitlet version-control system already exists in the current directory.\n");
            return;
        }

        createfile();
        init_commit();
        //System.out.printf("Gitlet has initiated.\n");
    }

    public static void add(String filename) {
        File staging = new File(home_dir + "/" + filename);
        if (staging.exists()) {
            // TODO
        }
        else {
            System.out.printf("File does not exist.");
        }
    }

    public static void commit() throws IOException {

    }
}
