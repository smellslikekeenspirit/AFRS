/*
 * @author: Prionti Nasir
 */

package Model;

import java.io.*;

/**
 * Layer used to write to text files
 */
public class TextFileWriter {

    /**
     * the text file to write to
     */
    private File file;


    /**
     * @param filename name of the text file to write to
     */
    public TextFileWriter(String filename){
        this.file = new File(filename);
    }

    /**
     * writes the given text to the file this writer has
     * @param text the given text
     * @throws IOException if writing to the file has failed
     */
    public void writeToTextFile(String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.flush();
    }
}
