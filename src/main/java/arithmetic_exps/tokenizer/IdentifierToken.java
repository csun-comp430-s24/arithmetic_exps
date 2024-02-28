package arithmetic_exps.tokenizer;

public class IdentifierToken implements Token {
    public final String name;
    public IdentifierToken(final String name) {
        this.name = name;
    }

    public boolean equals(final Object other) {
        return (other instanceof IdentifierToken &&
                name.equals(((IdentifierToken)other).name));
    }

    public String toString() {
        return "IdentifierToken(" + name + ")";
    }

    public int hashCode() {
        return name.hashCode();
    }
}

            
