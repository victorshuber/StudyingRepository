package MainProgramm;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Stack;



public class Calc {
    public ArrayList<Character> expression;                                     // list for symbols
    public String fileName;                                                     // input file name
    public double answer;
    /**
     * @param fileName
     */
    public Calc(String fileName){
        this.expression = new ArrayList();
        this.fileName = fileName;
    }

    /**
     *  invoke all methods
     */
    public void calculation(){
        ReaderWriter readWriter = new ReaderWriter();
        readWriter.getExpressionFile(expression, fileName);
        String expressionString = convertToString();
        
        Check check = new Check(expression);
        if(check.fullCheck()){
            if(getResult())
                readWriter.writer(expressionString, answer);
            else
                readWriter.writer(expressionString, null);
        }else{
            readWriter.writer(expressionString, null);
        }
    }
 
    /*
    * expression convert to string
    */
    private String convertToString(){
        String expressionString = "";
        for(char symbol : expression){
            expressionString += symbol;
        }
        return expressionString;
    }
   
    // Inner class for storing indexes of subexpression
    static class Indexs{
        static int leftIndex;
        static int rightIndex;
        static int depth;
    }
    
    /*
    *   Return true if all ok, and false if null
    */
    private boolean getResult(){
        
        // around all in brackets
        expression.add(0, '(');
        expression.add(')');
        findBrackets();
        
        do{
            answer = subCalculation();
            
            compression(answer);
            clearIndexs();
            findBrackets();
            
        }while(Indexs.depth != 0);
        
        return true;
    }
        
    /*
    *   Find subexpression
    */
    private void findBrackets(){
        int depth = 0, leftI = 0;
        // find deepest pair brackets
        for(int i = 0; i < expression.size(); i++){
            if(expression.get(i) == '('){
                leftI = i;
                depth++;
            }
            if(expression.get(i) == ')'){
                if(Indexs.depth <= depth){
                    Indexs.leftIndex = leftI;
                    Indexs.rightIndex = i;
                    Indexs.depth = depth;
                }
                depth--;
            }
        }
    }

    /*
    *   Clear states inner object
    */
    private void clearIndexs(){
        Indexs.leftIndex = 0;
        Indexs.rightIndex = 0;
        Indexs.depth = 0;
    }    
    
    /* 
    *   Calculation sub expression 
    */
    private Double subCalculation(){
        
        String number = "0123456789";
        char symbol;
        int integerPart = 0, 
            fractionalPart = 0, 
            countFractCount = 0;
        
        double subExpression=0,
                doubleNumberValue = 0;
        
        boolean doubleNumber = false,
                mult = true, 
                divide = false,
                subExpressionFree = true;
        
        Stack<Double> valueOfSubexpression = new Stack<>();                     
        Stack<Character> plusMinusSignList = new Stack<>();                     
        plusMinusSignList.push('+');

        for(int i = Indexs.leftIndex; i < Indexs.rightIndex; i++){

            // Convert char array number to Double value
            symbol = expression.get(i);
            if(number.indexOf(symbol) != -1){
                if(!doubleNumber){
                    integerPart = integerPart*10 + Character.getNumericValue(symbol);            // create integer part
                }else{
                    fractionalPart = fractionalPart*10 + Character.getNumericValue(symbol);      // create fractional part
                    countFractCount++;
                }
            }
            if(symbol == '.')                                                   // if find dot - we are in center of number
                doubleNumber = true;                                            // now begin fractional part

            
            // Calculation part of subexpression
            if("+-*/".indexOf(symbol) != -1 || i == Indexs.rightIndex - 1){            // end of the double number
                doubleNumberValue = integerPart + (double)fractionalPart/ pow(10,countFractCount);
                doubleNumber = false;
                countFractCount = 0;
                integerPart = 0;
                fractionalPart = 0;
                
                // pipeline calculation (only for multiplication and divide)
                if((mult || divide) && subExpressionFree){                      // 2*3*1/5...
                    subExpression = doubleNumberValue;
                    subExpressionFree = false;
                }else if((mult || divide) && !subExpressionFree ){
                    subExpression = (mult) ? subExpression * doubleNumberValue 
                            : subExpression / doubleNumberValue;
                }
                
                // reset sign flags
                divide = false;
                mult = false;
                
                // MainProgramm.Check sign
                switch(symbol){
                    case'+':
                        plusMinusSignList.push('+');
                        valueOfSubexpression.push(subExpression);
                        subExpression = 0;
                        subExpressionFree = true;
                        mult = true;
                        break;
                    case'-':
                        plusMinusSignList.push('-');
                        valueOfSubexpression.push(subExpression);
                        subExpression = 0;
                        subExpressionFree = true;
                        mult = true;
                        break;
                    case '*':
                        mult = true;
                        break;
                    case '/':
                        divide = true;
                        break;
                    default:
                        valueOfSubexpression.push(subExpression);
                } 
            }
        }
        
        
        
        // Sum all parts
        double sum = 0, addendum = 0;
        char sign;
        while(!valueOfSubexpression.empty()){
            sign = plusMinusSignList.pop();
            addendum = valueOfSubexpression.pop();
            sum = (sign == '+') ? sum + addendum : sum - addendum;  
        }

        return sum;
    }
    
    /*
    * replace subexpression by answer
    */
    private void compression(Double answer){ 
        String answerString = answer.toString();
        char[] answerCharArray = answerString.toCharArray();

            int i = Indexs.leftIndex;
            for(; i <= Indexs.rightIndex; i++){// delete sub expression
                expression.remove(Indexs.leftIndex);
            }
            i = Indexs.leftIndex;
            for(int j = 0; j < answerCharArray.length; i++, j++){
                expression.add(i, answerCharArray[j]);
            }
    }
    
    public static void main(String[] args) {
        Calc calc = new Calc("src/main/expression.txt");
        calc.calculation();
    }
    
}
