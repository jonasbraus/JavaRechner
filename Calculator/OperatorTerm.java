package Calculator;

public class OperatorTerm extends TermElement {
    public Operator op;

    @Override
    public void print() {
        switch (op) {
            case Plus:
                System.out.println("+");
                break;
            case Minus:
                System.out.println("-");
                break;
            case Mul:
                System.out.println("*");
                break;
            case Div:
                System.out.println("/");
                break;
        }
    }

    public OperatorTerm(OperatorTerm.Operator op) {
        this.op = op;
        type = Type.Operator;
    }

    public enum Operator {
        Plus, Minus, Mul, Div
    }
}
