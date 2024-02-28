# Arithmetic Expressions #

```
op ::= `+` | `-` | `==`
exp ::= INTEGER | VARIABLE |
        `(` `let` VARIABLE exp exp `)` |
        `(` op exp exp `)` |
        `(` `=` VARIABLE exp `)`
```

```
while (there is stuff to read) {
  skipWhitespace();
  if (there is stuff to read) {
    token = readToken();
    addToken(token);
  }
}
```
