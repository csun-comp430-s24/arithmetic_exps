package arithmetic_exps.parser;

public class OpExp implements Exp {
    public final Op op;
    public final Exp left;
    public final Exp right;
    public OpExp(final Op op,
                 final Exp left,
                 final Exp right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }
}
