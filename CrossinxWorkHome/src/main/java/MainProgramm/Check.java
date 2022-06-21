package MainProgramm;

import java.util.ArrayList;


public class Check {
    private final ArrayList<Character> expression;                              // list of expression symbols
    
    /**
     *
     * @param expression
     */
    public Check(ArrayList<Character> expression){
        this.expression = expression;
    }
    
    /**
     *
     * @return true if expression hasn`t syntax errors
     */
    public boolean fullCheck(){
        if(!checkRightSymbol())
            return false;
        
        if(!checkBrackets())
            return false;
        
        if(!checkOrderUsingBrackets())
            return false;
        
        if(!checkSingsOrder())
            return false;
        
        if(!checkDotsOrder())
            return false;
        
        return true;
    }
    
    /*
    *   method for checking correction of symbols in expression
    */
    private boolean checkRightSymbol(){
        String rightSymbol = "0123456789().+-*/";
        for(char symbol: expression){
            if(rightSymbol.indexOf(symbol) == -1)
                return false;
        }
        
        return true;
    } 
    
    /*
    *   method for checking number and order of brackets
    */
    private boolean checkBrackets(){
        int pair = 0;
        for(char symbol: expression){
            switch(symbol){
                case('('):
                    pair++;
                    break;
                case(')'):
                    pair--;
                    break;                    
            }
            if(pair<0)
                return false;
        }
        if(pair != 0)
            return false;
        return true;
    }
    
    /**
     *  check following:
     *  left and right brakcets should be used correct and have right order in expression 
     */
    private boolean checkOrderUsingBrackets(){
        String number = "0123456789";
        String sign = "+*/-";
        char symbol, prevSymbol = ' ', nextSymbol = ' ';
        for(int i = 0; i < expression.size(); i++){
            symbol = expression.get(i);
            prevSymbol = (i != 0) ? expression.get(i - 1) : ' ';                //  if now we check the first element of the expression, set him 'space' value
                                                                                //  else set preview element
            nextSymbol = (i == expression.size() - 1)? ' ' : expression.get(i + 1);     // if checked last element in expression set space
                                                
            switch(symbol){
                case('('):                               
                    if(number.indexOf(prevSymbol) != -1 || prevSymbol == '.' || // if preview symbol is number, dot or right bracket - it is syntax error
                            prevSymbol == ')')
                        return false;
                    
                    if(sign.indexOf(nextSymbol) != -1 || nextSymbol == ')' ||   // if next symbol is any sign, right bracket or dor -  it is syntax error
                            nextSymbol == '.')
                        return false;
                    break;
                case(')'):                   
                    if(sign.indexOf(prevSymbol) != -1 || prevSymbol == '.' ||   // if preview symbol is number, dot or right bracket - it is syntax error
                            prevSymbol == '(')
                        return false;
                    
                    if(number.indexOf(nextSymbol) != -1 || nextSymbol == '(' || // if next symbol is any sign, right bracket or dor -  it is syntax error
                            nextSymbol == '.')
                        return false;
                    break;
                default:
                    break;
            }
        }
        
        return true;
    }
    
    /**
     * method for checking sign`s syntax
     */
    private boolean checkSingsOrder(){
        String sign = "+*/-";
        char symbol, prevSymbol = ' ', nextSymbol = ' ';
        for(int i = 0; i < expression.size(); i++){
            symbol = expression.get(i);
            if(sign.indexOf(symbol) != -1){
                prevSymbol = (i != 0) ? expression.get(i - 1) : ' ';            //  if now we check the first element of the expression, set him 'space' value
                                                                                //  else set preview element
                nextSymbol = (i == expression.size() - 1)? ' ' : expression.get(i + 1);     // if checked last element in expression set space

                if(sign.indexOf(prevSymbol) != -1 || prevSymbol == '.' ||       // if preview symbol is sign,space or dot - it is syntax error
                     prevSymbol == ' ')
                        return false;

                if(sign.indexOf(nextSymbol) != -1 || nextSymbol == '.' ||       // if next symbol is sign,space or dot - it is syntax error
                    nextSymbol == ' ')
                        return false;
                }

        }
        
        return true;
    }
    
    /**
     * method for checking dot`s syntax
     */
    private boolean checkDotsOrder(){
        String resetElem = "+*/-()";                                            // elements wich reset count of dots 
        int countDot = 0;                                                       // if expression has some dots in a row, fixed this error
        char symbol, prevSymbol = ' ', nextSymbol = ' ';
        for(int i = 0; i < expression.size(); i++){
            symbol = expression.get(i);
            if(symbol == '.'){
                
                if(countDot == 0){
                    countDot++;
                    prevSymbol = (i != 0) ? expression.get(i - 1) : ' ';        //  if now we check the first element of the expression, set him 'space' value
                                                                                //  else set preview element
                    nextSymbol = (i == expression.size() - 1)? ' ' : expression.get(i + 1);     // if checked last element in expression set space

                    if( prevSymbol == ' ')                                      // if preview symbol is space - it is syntax error
                        return false;

                    if( nextSymbol == ' ')                                      // if next symbol is space or dot - it is syntax error
                        return false;
                    
                }else{                                                          // if expression has more one dot in a row
                    return false;
                }
                
            }else if(countDot > 0){                                             // if dot has recently appeared => countDot >0
                
                if(resetElem.indexOf(symbol) != -1)                             // countDot is reset when not number appear
                    countDot = 0;
            }
        }
        
        return true;
    }
}
