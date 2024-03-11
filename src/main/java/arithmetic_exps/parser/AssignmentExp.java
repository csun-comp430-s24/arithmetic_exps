package arithmetic_exps.parser;

public class AssignmentExp implements Exp {
    public final Variable variable;
    public final Exp exp;
    public AssignmentExp(final Variable variable,
                         final Exp exp) {
        this.variable = variable;
        this.exp = exp;
    }
}
