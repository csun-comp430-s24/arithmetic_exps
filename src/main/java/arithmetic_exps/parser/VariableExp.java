package arithmetic_exps.parser;

public class VariableExp implements Exp {
    public final Variable name;
    public VariableExp(final Variable name) {
        this.name = name;
    }
    public int hashCode() {
        return name.hashCode();
    }
    public boolean equals(final Object other) {
        return (other instanceof VariableExp &&
                name.equals(((VariableExp)other).name));
    }
    public String toString() {
        return "VariableExp(" + name + ")";
    }
}
        
