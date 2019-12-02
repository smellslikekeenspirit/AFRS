/*
 * @author: Prionti Nasir
 */

package View;

/**
 * Concrete implementation of the View in the MVC
 */
public class ConsoleWriter implements IView {

    /**
     * displays given string to the console
     * @param string string given by the caller
     */
    @Override
    public void display(String string) {
        System.out.println(string);
    }
}
