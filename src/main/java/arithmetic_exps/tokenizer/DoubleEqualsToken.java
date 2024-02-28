package arithmetic_exps.tokenizer;

public class DoubleEqualsToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof DoubleEqualsToken;
    }
    public String toString() {
        return "DoubleEqualsToken";
    }
    public int hashCode() {
        return 0;
    }
}
