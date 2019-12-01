/*
 * @author: Prionti Nasir
 */

package Model;

import java.io.*;
import java.util.ArrayList;


public class TextFileReader {

    private File file;

    /**
     * constructor for a TextFileReader which takes in a filename
     * and saves the corresponding file as the file it will read from
     * @param filename String defining name of file
     */
    public TextFileReader(String filename){
        this.file = new File(filename);
    }

    /**
     * reads the file it has saved a pointer to, collects each line
     * in the file into a list and returns to the caller
     * @return list of lines in file
     * @throws IOException if file cannot be read
     */
    public ArrayList<String> readTextFile() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null){
            lines.add(line);
            line = br.readLine();
        }
        return lines;
    }
}
