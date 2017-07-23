package gitlet;

import ucb.junit.textui;
import org.junit.Test;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

/** The suite of all JUnit tests for the gitlet package.
 *  @author
 */
public class UnitTest {

    /** Run the JUnit tests in the loa package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTest.class);
    }

    /** A dummy test to avoid complaint. */
    @Test
    public void placeholderTest() {
    }

    @Test
    public void TestMain() throws IOException, ClassNotFoundException {
		String[] arg = {"init"};
        Main test = new Main();
		test.main(arg);
    }

    @Test
    public void TestAdd() throws IOException, ClassNotFoundException {
        String[] arg = {"add", "a.txt"};
        Main test = new Main();
        test.main(arg);
        //Commit cmt = (Commit) Tools.load("/Users/hanxiangren/Program/cs61b/workspace/project/proj2/demo/.gitlet/staging/commits/5bd381c223457d7a316f4bbb6bdc35a3806a606c");
    }

	@Test
	public void TestCommit() throws IOException, ClassNotFoundException {
		String[] arg = {"commit", "rm a file"};
		Main test = new Main();
		test.main(arg);
	}

    @Test
    public void TestRecover() throws IOException {
        Blob blb = (Blob) Tools.load("/Users/hanxiangren/Program/cs61b/workspace/project/proj2/demo/.gitlet/history/blobs/af5367aa7c6b996432807a1efd7fc3afa19aff06");
        //Commit first = ()
        System.out.println(blb.content);
    }

    @Test public void TestLog() throws IOException, ClassNotFoundException {
        String[] arg = {"log"};
        Main test = new Main();
        test.main(arg);
    }
    @Test
    public void TestGlobalLog() throws IOException, ClassNotFoundException {
        String[] arg = {"global-log"};
        Main test = new Main();
        test.main(arg);
    }

    @Test
    public void TestRm() throws IOException, ClassNotFoundException {
        String[] arg = {"rm", "a.txt"};
        Main test = new Main();
        test.main(arg);
    }
}


