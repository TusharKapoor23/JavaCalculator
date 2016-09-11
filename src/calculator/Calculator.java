package calculator;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
    public static boolean isNumber(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    
    public static boolean isOperator(String s){
        return "+".equals(s)||"-".equals(s)||"/".equals(s)||"*".equals(s)||"^".equals(s);
    }
    
    
    public static int operatorPrecedence(String s){
        if (s.equals("+")||s.equals("-"))
            return 1;
        else if(s.equals("*")||s.equals("/"))
            return 2;
        else if(s.equals("^"))
            return 3;
        else
            return 0;       
    }
    
    
    public static double evaluate(double operand1, double operand2,String operator){
        if(operator.equals("+"))
            return operand2 + operand1;
        else if(operator.equals("-"))
            return operand2 - operand1;
        else if(operator.equals("*"))
            return operand2 * operand1;
        else if(operator.equals("^"))
            return Math.pow(operand2, operand1);
        else
            return operand2 / operand1;
    }
    
    
    //Conver to Postfix DONT EDIT
    public static String convert(String s){
        StringBuilder postfix = new StringBuilder();
        StringTokenizer str = new StringTokenizer(s,"-+/*^()", true);
        Queue <String> operands = new ArrayDeque<>();
        Stack <String> operator = new Stack<>();
        while(str.hasMoreElements()){
            String temp = str.nextToken();
            switch(temp){
                case "+":
                case "-":
                case "/":
                case "*":
                case "^":
                    while(!operator.isEmpty() && operatorPrecedence(temp)<=operatorPrecedence(operator.peek())){
                        operands.add(operator.pop());
                    }
                    operator.push(temp);
                    break;
                case "(":
                    operator.push(temp);
                    break;
                case ")":                    
                    String ans = operator.pop();
                    
                    while(!ans.equals("(")){      
                        operands.add(ans);
                        ans = operator.pop();
                    }                    
                    break;
                default:
                    operands.add(temp);                        
            }
        }
        if(!str.hasMoreElements()){
            while(!operator.isEmpty()){
                operands.add(operator.pop());
            }
        }
        for(String ans : operands){
            postfix.append(ans);
            postfix.append(" ");
        }        
    return postfix.toString().trim();
    }
    
    
    //Make the final answer. DONOT EDIT
    public static double makeAnswer(String s){
        Stack <Double> ans = new Stack<>();
        StringTokenizer str = new StringTokenizer(s);
        while(str.hasMoreElements()){
            String temp = str.nextToken();
            if(isNumber(temp)){
                ans.push(Double.parseDouble(temp));
            }
            if(isOperator(temp)){
                Double operand1 = Double.parseDouble(ans.pop().toString());
                Double operand2 = Double.parseDouble(ans.pop().toString());    
                ans.push(evaluate(operand1, operand2, temp));
            }
        }
        return ans.pop();
    }
}