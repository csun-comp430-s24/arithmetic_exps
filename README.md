# Arithmetic Expressions #

## Syntax ##

```
op ::= `+` | `-` | `==`
exp ::= INTEGER | VARIABLE |
        `(` `let` VARIABLE exp exp `)` |
        `(` op exp exp `)` |
        `(` `=` VARIABLE exp `)`
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
