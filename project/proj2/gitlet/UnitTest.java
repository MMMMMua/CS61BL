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
    public void TestMain() throws IOException {
		String[] arg = {"init"};
        Main test = new Main();
		test.main(arg);
    }

	@Test
	public void TestCommit() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("abc");
		str.add("def");
	}

	@Test
    public void TestAdd() throws IOException {
        String[] arg = {"add", "Untitled.txt"};
        Main test = new Main();
        test.main(arg);
        Commit cmt = (Commit) Tools.load("/Users/hanxiangren/Program/cs61b/workspace/project/proj2/demo/.gitlet/staging/commits/0c31b368ca63e1702c8e2bffad01b54baf5da7e7");
    }

    @Test
    public void TestRecover() throws IOException {
        Blob blb = (Blob) Tools.load("/Users/hanxiangren/Program/cs61b/workspace/project/proj2/demo/.gitlet/staging/blobs/2d0e228e8e8dcb694fafcd6695a8e8d20ea3985d");
        System.out.println(blb.content);
    }
}


