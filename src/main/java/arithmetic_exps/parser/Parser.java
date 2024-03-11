package arithmetic_exps.parser;

// FOR S EXPRESSIONS
public class Parser {
    public final List<Token> tokens;

    public Parser(final List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token getToken(final int position) throws ParseException {
        if (position < 0 || position >= tokens.size()) {
            throw new ParseException("No token at position: " + position);
        } else {
            return tokens.get(position);
        }
    }
    
    // couldn't parse?  Return null.
    public ParseResult<Op> parseOp(final int startPos) throws ParseException {
        final Token token = getToken(startPos);
        if (token instanceof PlusToken) {
            return new ParseResult<Op>(new PlusOp(), startPos + 1);
        } else if (token instanceof MinusToken) {
            return new ParseResult<Op>(new MinusOp(), startPos + 1);
        } else if (token instanceof EqualsToken) {
            return new ParseResult<Op>(new EqualsOp(), startPos + 1);
        } else {
            throw new ParseException("Not an Op: " + token.toString());
        }
    }

    public ParseResult<Variable> parseVariable(final int startPos) throws ParseException {
        final Token token = getToken(startPos);
        if (token instanceof VariableToken) {
            return new ParseResult<Exp>(new VariableExp(((VariableToken)token).variable),
                                        startPos + 1);
        } else {
            throw new ParseException("Not a variable: " + token.toString());
        }
    }

    public void assertTokenHereIs(final int pos, final Token expectedToken) throws ParseException {
        final Token tokenHere = getToken(pos);
        if (!tokenHere.equals(expectedToken)) {
            throw new ParseException("Expected: " + expectedToken.toString() +
                                     "; received: " + tokenHere.toString());
        }
    }
    
    public ParseResult<Exp> parseExp(final int startPos) throws ParseException {
        // NEXT TIME: complete parser (read all tokens), parser for non-s-expressions
        final Token token = getToken(startPos);
        if (token instanceof IntegerLiteralToken) {
            final IntegerLiteralToken asInt = (IntegerLiteralToken)token;
            final int value = asInt.value;
            return new ParseResult<Exp>(new IntegerLiteralExp(value),
                                        startPos + 1);
        } else if (token instanceof LeftParenToken) {
            final Token nextToken = getToken(startPos + 1);
            if (nextToken instanceof LetToken) {
                // `(` `let` VARIABLE exp exp `)`
                final ParseResult<Variable> variable = parseVariable(startPos + 2);
                final ParseResult<Exp> initializer = parseExp(variable.nextPos);
                final ParseResult<Exp> body = parseExp(initializer.nextPos);
                assertTokenHereIs(body.nextPos, new RightParenToken());
                return new ParseResult<Exp>(new LetExp(variable.parsed,
                                                       initializer.parsed,
                                                       body.parsed),
                                            body.nextPos + 1);
            } else if (nextToken instanceof SingleEqualsToken) {
                // `(` `=` VARIABLE exp `)`
                final ParseResult<Variable> variable = parseVariable(startPos + 2);
                final ParseResult<Exp> exp = parseExp(variable.nextPos);
                assertTokenHereIs(exp.nextPos, new RightParenToken());
                return new ParseResult<Exp>(new AssignmentExp(variable.parsed,
                                                              exp.parsed),
                                            exp.nextPos + 1);
            } else {
                // `(` op exp exp `)`
                final ParseResult<Op> op = parseOp(startPos + 1);
                final ParseResult<Exp> leftExp = parseExp(op.nextPos);
                final ParseResult<Exp> rightExp = parseExp(leftExp.nextPos);
                assertTokenHereIs(rightExp.nextPos, new RightParenToken());

                return new ParseResult<Exp>(new OpExp(op.parsed,
                                                      leftExp.parsed,
                                                      rightExp.parsed),
                                            rightExp.nextPos + 1);
            }
        } else {
            final ParseResult<Variable> variable = parseVariable(startPos);
            return new ParseResult<Exp>(new VariableExp(variable.parsed),
                                        variable.nextPos);
        }
    }
}
