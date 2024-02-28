package arithmetic_exps.tokenizer;

public class RightParenToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof RightParenToken;
    }

    public String toString() {
        return "RightParenToken";
    }

    public int hashCode() {
        return 6;
    }
}
