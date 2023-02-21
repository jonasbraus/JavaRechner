package Calculator;

public class Number extends TermElement
{
    public float value = 0;



    @Override
    public void print()
    {
        System.out.println(value);
    }

    public Number(float value) 
    {
        this.value = value;
        type = Type.Number;
    }
}
