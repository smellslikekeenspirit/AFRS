import Controller.RequestHandler;
import View.*;
import Model.Database;
import Controller.RequestHandler;
import java.util.Scanner;

public class AFRS {

    public static void main(String[] args) {
        System.out.println("Initializing AFRS");
        try {
            IView view = new ConsoleWriter();
            Database database = new Database();
            RequestHandler requestHandler = new RequestHandler(database);
            Scanner inputGetter = new Scanner(System.in);
            while(true) {
                System.out.println("Enter a request (type exit to stop):");
                String userInput = inputGetter.nextLine().trim().toLowerCase();
                if(userInput == "exit") {
                    break;
                }
                String response = requestHandler.handleRequest(userInput);
                view.display(response);
            }
            database.saveDatabase();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

}
