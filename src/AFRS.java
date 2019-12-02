import Controller.RequestHandler;
import View.*;
import Model.Database;
import java.util.Scanner;


/**
 * The main entry point of this program.
 */
public class AFRS {

    /**
     * Run to start and interact with the system
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("Initializing AFRS");
        // database will throw exceptions if it fails to load correctly
        try {
            IView view = new ConsoleWriter();
            Database database = new Database("data/");
            RequestHandler requestHandler = new RequestHandler(database);
            Scanner inputGetter = new Scanner(System.in);
            // handle all requests sent by the user
            while(true) {
                System.out.println("Enter a request (type exit to stop):");
                String userInput = inputGetter.nextLine().trim();
                if(userInput.toLowerCase().equals("exit")) {
                    break;
                }
                String response = requestHandler.handleRequest(userInput);
                view.display(response);
            }
            // save all reservations made by the user to the text files
            database.saveDatabase();
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
