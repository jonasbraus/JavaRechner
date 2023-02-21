package Calculator;

import java.util.*;

public class Calculator {
    private List<Character> operators = new ArrayList<>();

    public Calculator() {
        // information
        operators.add('*');
        operators.add('/');
        operators.add('+');
        operators.add('-');
    }

    public float calculate(String calculation) throws Exception
    {
        List<TermElement> term = new ArrayList<>();

        // Build the Term Objects
        String temp = "";
        OperatorTerm.Operator tempOP = null;

        for (int i = 0; i < calculation.length(); i++) {
            char current = calculation.charAt(i);

            if (!operators.contains(current)) {
                temp += current;
            } else {
                term.add(new Number(Float.parseFloat(temp)));
                temp = "";

                switch (current) {
                    case '*':
                        tempOP = OperatorTerm.Operator.Mul;
                        break;
                    case '/':
                        tempOP = OperatorTerm.Operator.Div;
                        break;
                    case '+':
                        tempOP = OperatorTerm.Operator.Plus;
                        break;
                    case '-':
                        tempOP = OperatorTerm.Operator.Minus;
                        break;
                }

                term.add(new OperatorTerm(tempOP));
            }
        }

        term.add(new Number(Float.parseFloat(temp)));

        TermElement[] elements = new TermElement[term.size()];

        for(int i = 0; i < elements.length; i++)
        {
            elements[i] = term.get(i);
        }

        // calculating Mul and Div
        for (int i = 0; i < elements.length; i++) {

            if (elements[i] != null) {

                if (elements[i].type == TermElement.Type.Operator) {

                    if (((OperatorTerm) (elements[i])).op == OperatorTerm.Operator.Mul
                            || ((OperatorTerm) (elements[i])).op == OperatorTerm.Operator.Div) {

                        float num1 = ((Number) (elements[i - 1])).value;
                        float num2 = ((Number) (elements[i + 1])).value;
                        OperatorTerm.Operator op = ((OperatorTerm) (elements[i])).op;

                        elements[i - 1] = elements[i] = elements[i + 1] = null;

                        elements[i + 1] = new Number(calc(num1, num2, op));
                    }
                }
            }
        }

        //cleear the null entries
        term = new ArrayList<>();

        for(TermElement t : elements)
        {
            if(t != null)
            {
                term.add(t);
            }
        }


        //rebuild elements
        elements = new TermElement[term.size()];

        for(int i = 0; i < elements.length; i++)
        {
            elements[i] = term.get(i);
        }


        //calculate plus minus
        for (int i = 0; i < elements.length; i++) {

            if (elements[i] != null) {

                if (elements[i].type == TermElement.Type.Operator) {

                    float num1 = ((Number) (elements[i - 1])).value;
                    float num2 = ((Number) (elements[i + 1])).value;
                    OperatorTerm.Operator op = ((OperatorTerm) (elements[i])).op;

                    elements[i - 1] = elements[i] = elements[i + 1] = null;

                    elements[i + 1] = new Number(calc(num1, num2, op));

                }
            }
        }

        //print the result
        for(TermElement t : elements)
        {
            if(t != null)
            {
                return ((Number)(t)).value;
            }
        }

        return -1;
    }

    //calculate a basic term based on its operator
    public float calc(float num1, float num2, OperatorTerm.Operator op) {
        float res = 0;

        switch (op) {
            case Plus:
                res = num1 + num2;
                break;
            case Minus:
                res = num1 - num2;
                break;
            case Mul:
                res = num1 * num2;
                break;
            case Div:
                res = num1 / num2;
                break;
        }

        return res;
    }
}