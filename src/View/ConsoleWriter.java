/*
 * @author: Prionti Nasir
 */

package View;

public class ConsoleWriter implements IView {

    @Override
    /**
     * displays given string to the console
     * @param string string given by the caller
     */
    public void display(String string) {
        System.out.println(string);
    }
}
