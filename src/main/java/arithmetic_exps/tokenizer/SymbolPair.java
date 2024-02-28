package arithmetic_exps.tokenizer;

public class SymbolPair {
    public final String asString;
    public final Token asToken;
    public SymbolPair(final String asString,
                      final Token asToken) {
        this.asString = asString;
        this.asToken = asToken;
    }

    public boolean equals(final Object other) {
        if (other instanceof SymbolPair) {
            final SymbolPair otherPair = (SymbolPair)other;
            return (asString.equals(otherPair.asString) &&
                    asToken.equals(otherPair.asToken));
        } else {
            return false;
        }
    }

    public String toString() {
        return ("SymbolPair(" +
                asString +
                ", " +
                asToken.toString() +
                ")");
    }

    public int hashCode() {
        return asString.hashCode() + asToken.hashCode();
    }
}
