package in.roopsai;

import in.roopsai.controllers.CommandController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CommandController commandController = new CommandController();
        commandController.start();
    }
}
