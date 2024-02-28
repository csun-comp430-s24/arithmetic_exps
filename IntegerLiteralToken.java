public class IntegerLiteralToken implements Token {
    public final int value;
    public IntegerLiteralToken(final int value) {
        this.value = value;
    }

    public boolean equals(final Object other) {
        if (other instanceof IntegerLiteralToken) {
            final IntegerLiteralToken asInt = (IntegerLiteralToken)other;
            return value == asInt.value;
        } else {
            return false;
        }
    }

    public String toString() {
        return "IntegerLiteralToken(" + value + ")";
    }

    public int hashCode() {
        return value;
    }
}
            
