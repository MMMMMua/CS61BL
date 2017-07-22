package gitlet;

import java.io.IOException;

/**
 * Driver class for Gitlet, the tiny stupid version-control system.
 *
 * @author Hanxiang Ren
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND> ....
     */
    static String incorrectOper = "Incorrect Operands.";
    static String enterCmd = "Plese enter a command.";
    static String noSuchCmd = "No command with that name exists.";
    static String enterMes = "Please enter a commit message.";

    public static void main(String... args) throws IOException, ClassNotFoundException {
        //system.initialize();
        if (args.length == 0) {
            System.out.println(enterCmd);
            return;
        }

        CommandParser cmd = new CommandParser();

        switch (args[0]) {
            case "init":
                cmd.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println(incorrectOper);
                } else {
                    cmd.add(args[1]);
                }
                break;
            case "commit":
                if (args.length < 2) {
                    System.out.println(enterMes);
                } else {
                    cmd.commit(args[1]);
                }
                break;
            case "rm":
                if (args.length < 2) {
                    System.out.println(incorrectOper);
                } else {
                    cmd.rm(args[1]);
                }
                break;
            case "log":
                cmd.log();
                break;
            case "global-log":
                cmd.globalLog();
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
                System.out.println(noSuchCmd);
        }
    }

}
