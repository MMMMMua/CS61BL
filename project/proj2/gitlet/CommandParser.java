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

        String currAdds = HEAD.getAbsolutePath();
        String currPath = new String(Utils.readContents(new File(currAdds)));
        CurrentBranch = Branch.load(currPath);
        CurrentCommit = Commit.load(commitsHis.getAbsolutePath() + "/" + CurrentBranch.commit_id);

        List<String> staging = Utils.plainFilenamesIn(commitsSta);
        StagingCommit = Commit.load(commitsSta.getAbsolutePath() + "/" + staging.get(0));
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
        if (homeDir.isDirectory())
            return true;
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
        Commit first = new Commit(null, "initial commit");
        Commit staging = new Commit();

        first.save(commitsHis);
        staging.save(commitsSta);

        Branch master = new Branch(first.toString(), "master"); //branch tracks a commit
        master.save(branches.getAbsolutePath());

        resetHeader(master);
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

        if (!StagingCommit.containFile(fileName) && !CurrentCommit.containFile(fileName)) {
            System.out.printf("No reason to remove the file.");
            return;
        }

        StagingCommit.reMove(fileName);
        if (!CurrentCommit.containFile(fileName)) { //what is the logic of deleting
            Tools.fileDel(workDir, fileName); // if file does not exist, it's fine.
        }
    }

    public void log() throws IOException {
        initSystem();

        String logs = "";
        while (true) {
            logs += "===\n";
            logs += "Commit";
            logs += CurrentCommit.toString();
            logs += "\n";
            logs += CurrentCommit.commitDate.toString();
            logs += CurrentCommit.message;

            if (CurrentCommit.savingPostition == "") {
                CurrentCommit = Commit.load(CurrentCommit.savingPostition);
                break;
            }
        }
        System.out.println(logs);
    }

    public void globalLog() {

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
