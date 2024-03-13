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

    public ParseResult<Exp> parsePrimary(final int startPos) throws ParseException { ... }
    
    // multexp ::= primary ((`*` | `/`) primary)*
    public ParseResult<Exp> parseMultExp(final int startPos) throws ParseException {
        ParseResult<Exp> retval = parsePrimary(startPos);
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final Token token = getToken(retval.nextPos);
                final Op op = null;
                if (token instanceof MultiplyToken) {
                    op = new MultOp();
                } else if (token instanceof DivToken) {
                    op = new DivOp();
                } else {
                    throw new ParseException("Bad operator");
                }
                final ParseResult<Exp> other = parsePrimary(retval.nextPos + 1);
                retval = new ParseResult<Exp>(new OpExp(op,
                                                        retval.parsed,
                                                        other.parsed),
                                              other.nextPos);
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return retval;
    }
    
    // type Parser[A] = (List[Token]) => Option[(A, List[Token])]
    //
    // def token(expected: Token): Parser[Unit] = {
    //   (tokens: List[Token]) => {
    //     tokens match {
    //       head :: tail if head == expected => Some(((), tail))
    //       _ => None
    //     }
    //   }
    // }

    // val integer: Parser[Int] = {
    //   (tokens: Token[Token]) => {
    //     tokens match {
    //       IntegerToken(i) :: tail => Some((i, tail))
    //       _ => None
    //     }
    //   }
    // }

    // def and[A, B](p1: Parser[A], p2: Parser[B]): Parser[(A, B)] = {
    //   (tokens1: List[Token]) => {
    //     p1(tokens1) match {
    //       case Some((a, tokens2)) => {
    //         p2(tokens2) match {
    //           case Some((b, tokens3)) => Some(((a, b), tokens3))
    //           case _ => None
    //         }
    //       }
    //       case _ => None
    //    }
    //  }
    // }
    //
    // def or[A](p1: Parser[A], p2: Parser[A]): Parser[A] = {
    //   (tokens1: List[Token]) => {
    //     p1(tokens1) match {
    //       case Some((a, rest)) => Some((a, rest))
    //       case _ => {
    //         p2(tokens1) match {
    //           case Some((b, rest)) => Some((b, rest))
    //           case None => None
    //         }
    //       }
    //     }
    //   }
    // }
    //
    // def map[A, B](p: Parser[A], f: A => B): Parser[B] = {
    //   (tokens1: List[Token]) => {
    //     p(tokens1) match {
    //       case Some((a, tokens2)) => Some((f(a), tokens2))
    //       case _ => None
    //     }
    //   }
    // }
    //
    // def star[A](p: Parser[A]): Parser[List[A]] = ...
    //
    // lazy val exp: Parser[Exp] =
    //   (integer ^^ (i => IntegerLiteralExp(i))) |
    //   (variable ^^ (name => VariableExp(name))) |
    //   ((and(token(LeftParenToken), // Parser[(Unit, (Unit, (Variable, (Exp, (Exp, Unit)))))] 
    //         and(token(LetToken),
    //             and(variable
    //                 and(exp,
    //                     and(exp,
    //                         token(RightParenToken))))))
    //     token(RightParenToken)) ^^ { case _ ~ _ ~ x ~ e1 ~ e2 ~ _ => LetExp(x, e1, e2) }) |
    //   ((LeftParenToken ~
    //     op ~
    //     exp ~
    //     exp ~
    //     RightParenToken) ^^ { case _ ~ o ~ e1 ~ e2 ~ _ => OpExp(o, e1, e2) }) |
    //   ((LeftParenToken ~
    //     SingleEqualsToken ~
    //     variable ~
    //     exp ~
    //     RightParenToken) ^^ { _ ~ _ ~ x ~ e ~ _ => AssignExp(x, e) })
    
    // def parseExp(tokens1: List[Token]): (Exp, List[Token]) = {
    //   tokens1 match {
    //     case IntegerLiteralToken(i) :: tokens2 => {
    //       Some((IntegerLiteralExp(i), tokens2))
    //     }
    //     case LeftParenToken :: LetToken :: tokens2 => {
    //       let (variable, tokens3) = parseVariable(tokens2)
    //       let (initializer, tokens4) = parseExp(tokens3)
    //       let (body, tokens5) = parseExp(tokens4)
    //       tokens5 match {
    //         case RightParenToken :: tokens6 => (LetExp(variable, initializer, body), tokens6)
    //         case _ => throw ParseException(...)
    //       }
    //    }
    //   for {
    //     (token, tokens2) <- getToken(tokens)
    //     
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
