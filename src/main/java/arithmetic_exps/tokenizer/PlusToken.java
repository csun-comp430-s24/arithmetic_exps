package arithmetic_exps.tokenizer;

public class PlusToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof PlusToken;
    }
    public String toString() {
        return "PlusToken";
    }
    public int hashCode() {
        return 2;
    }
}
