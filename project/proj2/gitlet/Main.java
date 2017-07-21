package gitlet;

import ucb.util.CommandArgs;

import javax.activation.CommandMap;
import java.io.IOException;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) throws IOException {
        //system.initialize();
        CommandParser cmd = new CommandParser();

        switch (args[0]) {
            case "init":
                cmd.init();
                break;
            case "add":
            	cmd.add();
                break;
            case "commit":
                
                break;
            case "rm":
                break;
            case "log":
				break;
			case "global-log":
				break;
			case "find":
				break;
			case "status":
				break;
			case "checkout":
				break;
			case "branch":
				break;
			case "rm-branch":
				break;
			case "reset":
				break;
			case "merge":
				break;
				
			default:
				System.out.printf("Command does not exist");
        }
    }

}