package in.roopsai;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: App <operation> <id> <title>");
            System.exit(0);
        }

        String command = args[0].toLowerCase();
        TaskList taskList = new TaskList();
        if (command.equals("add")) {
            String title = args[1];
            Task task = new Task(title);
            taskList.add(task);
        } else if (command.equals("list")) {
            if (args.length == 1) {
                taskList.list();
            } else {
                taskList.list(args[1]);
            }
        } else if (command.equals("delete")) {
            taskList.delete(Integer.parseInt(args[1]));
        }

    }
}
