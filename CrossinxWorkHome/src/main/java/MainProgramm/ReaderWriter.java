package MainProgramm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReaderWriter {

    /**
     * Read expression from file
     * @param expression    - list of the symbols
     * @param fileName      - name input file
     */
    public void getExpressionFile( ArrayList<Character> expression,String fileName){

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            int symbol;
            while ((symbol = bufferedReader.read()) != -1) {
                 expression.add((char) symbol);
             }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReaderWriter.class.getName()).log(Level.SEVERE, "File is not found :(", ex);
        }
        catch (IOException ex) {
            Logger.getLogger(ReaderWriter.class.getName()).log(Level.SEVERE, "File is not found :(", ex);
        }
    }
    
    /**
     *  Write expression with answer
     * @param expression - list of the symbols
     * @param answer     - result of calculation
     */
    public void writer(String expression, Object answer){
        System.out.println(expression + " = " + answer);
        
    }
}
