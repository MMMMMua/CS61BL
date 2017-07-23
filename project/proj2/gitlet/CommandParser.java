package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hanxiangren on 19/07/2017.
 */
public class CommandParser implements Serializable {
    static File workDir = new File("demo");
    static File homeDir = new File(workDir.getAbsolutePath() + "/.gitlet");
    static File staging = new File(homeDir.getAbsolutePath() + "/staging");
    static File history = new File(homeDir.getAbsolutePath() + "/history");
    static File systems = new File(homeDir.getAbsolutePath() + "/systems");
    static File blobsHis = new File(history.getAbsolutePath() + "/blobs");
    static File commitsHis = new File(history.getAbsolutePath() + "/commits");
    static File blobsSta = new File(staging.getAbsolutePath() + "/blobs");
    static File commitsSta = new File(staging.getAbsolutePath() + "/commits");
    static File branches = new File(systems.getAbsolutePath() + "/branches");
    static File HEAD = new File(systems.getAbsolutePath() + "/HEAD");

    static Branch CurrentBranch;
    static Commit CurrentCommit;
    static Commit StagingCommit;


    private void initSystem() throws IOException {
        if (!hasInited()) {
            System.out.printf("Not in an initialized gitlet directory.\n");
            return;
        }

        String currPath = new String(Utils.readContents(HEAD));
        CurrentBranch = Branch.load(currPath);
        CurrentCommit = Commit.load(commitsHis, CurrentBranch.commit_id);

        List<String> staging = Utils.plainFilenamesIn(commitsSta);
        StagingCommit = Commit.load(commitsSta, staging.get(0));
        //there is only one file in that directory.
    }

    private void createFile() throws IOException {
        staging.mkdir();
        history.mkdir();
        systems.mkdir();

        blobsHis.mkdir();
        commitsHis.mkdir();
        blobsSta.mkdir();
        commitsSta.mkdir();
        branches.mkdir();

        HEAD.createNewFile();
    }

    private boolean hasInited() throws IOException {
        if (homeDir.isDirectory()) {
            return true;
        }
        if (!workDir.isDirectory()) {
            workDir.mkdir();
        }
        homeDir.mkdir();
        return false;
    }

    private void resetHeader(Branch newHead) {
        Utils.writeContents(HEAD, newHead.savingPosition.getBytes());
    }

    private void resetCurrCmt(Commit newCommit) {
        CurrentBranch.commit_id = newCommit.toString();
        CurrentBranch.save();
    }

    private void initCommit() {
        CurrentCommit = new Commit("", "initial commit");
        StagingCommit = new Commit();

        CurrentCommit.save(commitsHis);
        StagingCommit.save(commitsSta);

        CurrentBranch = new Branch(CurrentCommit.toString(), "master"); //branch tracks a commit
        CurrentBranch.save(branches);

        resetHeader(CurrentBranch);
    }

    void init() throws IOException {
        if (hasInited()) {
            System.out.printf("A gitlet version-control system already exists in the current directory.\n");
            return;
        }

        createFile();
        initCommit();
    }

    public void add(String fileName) throws IOException {
        initSystem();

        if (!Tools.validFile(workDir, fileName)) {
            System.out.printf("file does not exist.");
            return;
        }

        Blob fileToStage = new Blob(workDir, fileName);

        if (!CurrentCommit.containBlob(fileToStage)) {
            StagingCommit.addBlob(fileToStage);
        }
    }


    public void commit(String message) throws IOException, ClassNotFoundException {
        initSystem();

        if (StagingCommit.files.equals(CurrentCommit.files)) {
            System.out.printf("No changes added to the commit.\n");
            return;
        }

        StagingCommit.message = message;
        StagingCommit.parent = CurrentCommit.savingPostition;
        StagingCommit.save(commitsHis);

        Tools.folderMove(blobsSta, blobsHis);
        Tools.folderDelt(blobsSta);

        resetCurrCmt(StagingCommit);
    }

    public void rm(String fileName) throws IOException {
        initSystem();

        String path = Tools.getPath(workDir, fileName);
        if (!StagingCommit.containFile(path) && !CurrentCommit.containFile(path)) {
            System.out.printf("No reason to remove the file.");
            return;
        }

        StagingCommit.reMove(path);
        if (!CurrentCommit.containFile(path)) { //what is the logic of deleting
            Tools.fileDel(workDir, fileName); // if file does not exist, it's fine.
        }
    }

    public void log() throws IOException {
        initSystem();

        while (true) {
            System.out.println(CurrentCommit.commitMessage());

            if (!CurrentCommit.parent.equals("")) {
                CurrentCommit = Commit.load(CurrentCommit.parent);
            } else {
                break;
            }
        }
    }

    public void globalLog() throws IOException {
        initSystem();
        List<String> commits = Utils.plainFilenamesIn(commitsHis);
        for (String cmt: commits) {
            Commit Cmt = Commit.load(commitsHis, cmt);
            System.out.println(Cmt.commitMessage());
        }
    }

    public void find() {

    }

    public void status() {

    }

    public void checkOut() {

    }

    public void branch() {

    }

    public void rmBranch() {

    }

    public void reset() {

    }

    public void merge() {

    }
}
