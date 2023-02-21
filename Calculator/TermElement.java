package Calculator;

public abstract class TermElement
{
    public Type type;

    public abstract void print();

    public enum Type
    {
        Number, Operator
    }
}
