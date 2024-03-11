package arithmetic_exps.parser;

public class ParseResult<A> {
    public final A parsed;
    public final int nextPos;

    public ParseResult(final A parsed,
                       final int nextPos) {
        this.parsed = parsed;
        this.nextPos = nextPos;
    }
}
