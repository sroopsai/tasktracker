package in.roopsai;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Task task = new Task("Java Development");
        System.out.println(task.toJson());
    }
}
