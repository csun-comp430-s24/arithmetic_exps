public class MinusToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof MinusToken;
    }
    public String toString() {
        return "MinusToken";
    }
    public int hashCode() {
        return 1;
    }
}
