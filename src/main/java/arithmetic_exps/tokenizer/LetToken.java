package arithmetic_exps.tokenizer;

public class LetToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof LetToken;
    }

    public String toString() {
        return "LetToken";
    }

    public int hashCode() {
        return 5;
    }
}
