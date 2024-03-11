package arithmetic_exps.parser;

// case class LetExp(name: Variable,
//                   initializer: Exp,
//                   body: Exp) extends Exp
//
// indirect enum Exp {
//    case LetExp(Variable, Exp, Exp)
//    ...
// }
public class LetExp implements Exp {
    public final Variable name;
    public final Exp initializer;
    public final Exp body;
    public LetExp(final Variable name,
                  final Exp initializer,
                  final Exp body) {
        this.name = name;
        this.initializer = initializer;
        this.body = body;
    }
    public int hashCode() {
        return (name.hashCode() +
                initializer.hashCode() +
                body.hashCode());
    }
    public String toString() {
        return ("LetExp(" +
                name.toString() +
                ", " +
                initializer.toString() +
                ", " +
                body.toString() +
                ")");
    }
    public boolean equals(final Object other) {
        if (other instanceof LetExp) {
            final LetExp asExp = (LetExp)other;
            return (name.equals(asExp.name) &&
                    initializer.equals(asExp.initializer) &&
                    body.equals(asExp.body));
        } else {
            return false;
        }
    }
}

    
