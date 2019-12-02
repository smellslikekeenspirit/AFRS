/*
 * @author: Prionti Nasir
 */

package Model;

import java.io.*;


public class TextFileWriter {

    private File file;

    public TextFileWriter(String filename){
        this.file = new File(filename);
    }

    public void writeToTextFile(String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.flush();
    }
}
