package arithmetic_exps.tokenizer;

public class LeftParenToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof LeftParenToken;
    }

    public String toString() {
        return "LeftParenToken";
    }

    public int hashCode() {
        return 4;
    }
}
