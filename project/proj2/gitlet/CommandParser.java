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


    private void initSystem() {
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
        Commit first = new Commit();
        Commit staging = new Commit();

        first.save(commitsHis.getAbsolutePath());
        staging.save(commitsSta.getAbsolutePath());

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

    public void add(String fileName) {
        initSystem();

        Blob fileToStage = new Blob(workDir + "/" + fileName);

        if (!CurrentCommit.containBlob(fileToStage)) {
            StagingCommit.addBlob(fileToStage);
        } else {
            System.out.printf("file does not exist.\n");
        }
    }


    public void commit(String message) throws IOException, ClassNotFoundException {
        initSystem();

        if (StagingCommit.files.equals(CurrentCommit.files)) {
            System.out.printf("No changes added to the commit.\n");
            return;
        }

        Commit NewStagingCommit = (Commit) Tools.deepCopy(StagingCommit);

        StagingCommit.message = message;
        StagingCommit.parent = CurrentCommit.toString();
        StagingCommit.save(commitsHis.getAbsolutePath());

        Tools.folderMove(blobsSta, blobsHis);
        Tools.folderDelt(blobsSta);

        resetCurrCmt(StagingCommit);
    }

    public void rm(String fileName) {
        initSystem();

        if (!StagingCommit.containFile(fileName)) { //what is the logic of deleting
            //TODO
        }
        Blob fileToRm = new Blob();
    }

    public void log() {

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
