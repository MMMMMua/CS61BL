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
		//gitlet.Commit cmt = new gitlet.Commit("master", str);
        Commit cmt = Commit.load("/Users/hanxiangren/Program/cs61b/workspace/project/proj2/demo/.gitlet/history/commits/73d374d8bbb989a1d52a9f9040c2674a67a9b91d73d374d8bbb989a1d52a9f9040c2674a67a9b91d");
        System.out.printf("%s", cmt.toString());
	}
}


