package arithmetic_exps.tokenizer;

public class SingleEqualsToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof SingleEqualsToken;
    }

    public String toString() {
        return "SingleEqualsToken";
    }

    public int hashCode() {
        return 3;
    }
}
