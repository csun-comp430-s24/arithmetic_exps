# Working with Non-S-Expressions #

## Concrete Grammar ##

```
primary ::= INTEGER | VARIABLE | `(` exp `)`
multexp ::= primary ((`*` | `/`) primary)*
addexp ::= multexp ((`+` | `-`) multexp)*
exp ::= addexp
```

## Abstract Grammar ##

```
binop ::= `*` | `/` | `+` | `-`
exp ::= INTEGER | VARIABLE |
        exp op exp
```
