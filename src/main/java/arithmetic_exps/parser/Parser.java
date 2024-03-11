package arithmetic_exps.parser;

// FOR S EXPRESSIONS
public class Parser {
    public final List<Token> tokens;

    public Parser(final List<Token> tokens) {
        this.tokens = tokens;
    }

    // couldn't parse?  Return null.
    public ParseResult<Op> parseOp(final int startPos) {
        if (startPos < 0 || startPos >= tokens.size()) {
            return null;
        }
        final Token token = tokens.get(startPos);
        if (token instanceof PlusToken) {
            return new ParseResult<Op>(new PlusOp(), startPos + 1);
        } else if (token instanceof MinusToken) {
            return new ParseResult<Op>(new MinusOp(), startPos + 1);
        } else if (token instanceof EqualsToken) {
            return new ParseResult<Op>(new EqualsOp(), startPos + 1);
        } else {
            return null;
        }
    }

    public ParseResult<Exp> parseExp(final int startPos) {
        // NEXT TIME: refactor and finish parser
        if (startPos < 0 || startPos >= tokens.size()) {
            return null;
        }
        final Token token = tokens.get(startPos);
        if (token instanceof IntegerLiteralToken) {
            final IntegerLiteralToken asInt = (IntegerLiteralToken)token;
            final int value = asInt.value;
            return new ParseResult<Exp>(new IntegerLiteralExp(value),
                                        startPos + 1);
        } else if (token instanceof VariableToken) {
            final VariableToken asVariable = (VariableToken)token;
            final Variable variable = asVariable.variable;
            return new ParseResult<Exp>(new VariableExp(variable),
                                        startPos + 1);
        } else if (token instanceof LeftParenToken) {
            // `(` op exp exp `)`
            // `(` `+` `1` `2` `)`
            //
            // `(` thing* `)`
            // thing ::= exp | op

            ParseResult<Op> op = null;
            ParseResult<Exp> leftExp = null;
            ParseResult<Exp> rightExp = null;
            if ((op = parseOp(startPos + 1)) != null &&
                (leftExp = parseExp(op.nextPos)) != null &&
                (rightExp = parseExp(leftExp.nextPos)) != null &&
                rightExp.nextPos < tokens.size() &&
                tokens.get(rightExp.nextPos) instanceof RightParenToken) {
                return new ParseResult<Exp>(new OpExp(op.parsed,
                                                      leftExp.parsed,
                                                      rightExp.parsed),
                                            rightExp.nextPos + 1);
            } else if (...
        } else {
            return null;
        }
    }
}
