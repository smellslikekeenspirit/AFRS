package Model;

import java.io.*;
import java.util.ArrayList;

public class TextFileReader {

    private File file;

    public TextFileReader(String filename){
        this.file = new File(filename);
    }

    public ArrayList<String> readTextFile() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.readLine() != null){
            lines.add(br.readLine());
        }
        return lines;
    }
}
