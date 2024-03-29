package arithmetic_exps.parser;

public class IntegerLiteralExp implements Exp {
    public final int value;
    public IntegerLiteralExp(final int value) {
        this.value = value;
    }
    public int hashCode() {
        return value;
    }
    public boolean equals(final Object other) {
        // if (other instanceof IntegerLiteralExp asExp) {
        //     return value == asExp.value;
        // if (other instanceof IntegerLiteralExp) {
        //     final IntegerLiteralExp asExp = (IntegerLiteralExp)other;
        //     return value == asExp.value;
        // } else {
        //     return false;
        // }
        return (other instanceof IntegerLiteralExp &&
                value == ((IntegerLiteralExp)other).value);
    }
    public String toString() {
        return "IntegerLiteralExp(" + value + ")";
    }
}
