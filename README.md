# Arithmetic Expressions #

## Concrete Grammar ##

```
op ::= `+` | `-` | `==`
exp ::= INTEGER | VARIABLE |
        `(` `let` VARIABLE exp exp `)` |
        `(` op exp exp `)` |
        `(` `=` VARIABLE exp `)`
program ::= exp
```

## Abstract Grammar ##

```
op ::= `+` | `-` | `==`
exp ::= INTEGER | VARIABLE |
        `let` VARIABLE exp exp |
        `binop` op exp exp |
        `assign` VARIABLE exp
program ::= exp
```

```
(let x 2
  (+ x 3))
```

```java
new LetExp(new Variable("x"),
           new IntegerLiteralExp(2),
           new OpExp(new PlusOp(),
                     new VariableExp(new Variable("x")),
                     new IntegerLiteralExp(3)))
```

register ::= `$t0` | `$t1`
instruction ::= `add` register register register |
                `sub` register register register
line :: = instruction | label
label :: = NAME `:`
program ::= line*
```

### Basic Idea with Tokenization ###

```
while (there is stuff to read) {
  skipWhitespace();
  if (there is stuff to read) {
    token = readToken();
    addToken(token);
  }
}
```

## Compiling the Code ##

```console
mvn compile
mvn test
mvn jacoco:report
```

### Viewing Code Coverage Report ###

After you run everything above, you can see the code coverage report in `target/site/jacoco/index.html`.
It can be opened in any web browser.
