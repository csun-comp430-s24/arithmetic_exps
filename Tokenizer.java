import java.util.List;

// TODO: regex version
public class Tokenizer {
    // BEGIN STATIC VARIABLES
    public static final Map<String, Token> RESERVED_WORDS =
        new HashMap<String, Token>() {{
            put("return", new ReturnToken());
            put("let", new LetToken());
        }};
    // val SYMBOLS = Seq(("==", DoubleEqualsToken),
    //                   ("=", SingleEqualsToken), ...
    public static final List<SymbolPair> SYMBOLS =
        new ArrayList<SymbolPair>() {{
            add(new SymbolPair("==", new DoubleEqualsToken()));
            add(new SymbolPair("=", new SingleEqualsToken()));
            add(new SymbolPair("(", new LeftParenToken()));
            add(new SymbolPair(")", new RightParenToken()));
            add(new SymbolPair("+", new PlusToken()));
            add(new SymbolPair("-", new MinusToken()));
        }};
    // END STATIC VARIABLES
    
    // BEGIN INSTANCE VARIABLES
    private final String input;
    private int pos;
    // END INSTANCE VARIABLES

    public Tokenizer(final String input) {
        this.input = input;
        pos = 0;
    }

    // category 2: reserved words OR identifiers
    // returns null if this isn't an identifier or reserved word
    private Token tryTokenizeIdentifierOrReservedWord() {
        if (Character.isLetter(input.charAt(pos))) {
            final StringBuffer read = new StringBuffer();
            read.append(input.charAt(pos));
            pos++;
            while (pos < input.length() &&
                   Character.isLetterOrDigit(input.charAt(pos))) {
                read.append(input.charAt(pos));
                pos++;
            }
            final String asString = read.toString();
            Token reservedWord = RESERVED_WORDS.get(asString);
            if (reservedWord != null) {
                return reservedWord;
            } else {
                return new IdentifierToken(asString);
            }
        } else {
            return null;
        }
    }
    
    // category 1: symbols
    // +, -, ==, =, (, )
    //
    // returns null if this isn't a symbol
    private Token tryTokenizeSymbol() {
        for (final SymbolPair pair : SYMBOLS) {
            if (input.startsWith(pair.asString, pos)) {
                pos += pair.asString.length();
                return pair.asToken;
            }
        }
        return null;
        // if (input.startsWith("==", pos)) {
        //     pos += 2;
        //     return new DoubleEqualsToken(); // a==Token
        // } else if (input.startsWith("=", pos)) {
        //     pos++;
        //     return new SingleEqualsToken();
        // } else if (input.startsWith("+", pos)) {
        //     pos++;
        //     return new PlusToken();
        // } else if (input.startsWith("-", pos)) {
        //     pos++;
        //     return new MinusToken();
        // } else if (input.startsWith("(", pos)) {
        //     pos++;
        //     return new LeftParenToken();
        // } else if (input.startsWith(")", pos)) {
        //     pos++;
        //     return new RightParenToken();
        // } else {
        //     return null;
        // }
    }
    // category 3: integers
    // returns null if this isn't an integer
    private Token tryTokenizeInteger() {
        final StringBuffer read = new StringBuffer();
        while (Character.isDigit(input.charAt(pos))) {
            read.append(input.charAt(pos));
            pos++;
        }

        final String asString = read.toString();
        if (asString.length() == 0) {
            return null;
        } else {
            return new IntegerLiteralToken(Integer.parseInt(asString));
        }
    }

    private void skipWhitespace() {
        while (pos < input.length() &&
               Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }

    // returns null if we are at end of input
    private Token readToken() throws TokenizerException {
        skipWhitespace();
        if (pos < input.length()) {
            Token token = null;
            if ((token = tryTokenizeIdentifierOrReservedWord()) == null &&
                (token = tryTokenizeSymbol()) == null &&
                (token = tryTokenizeInteger()) == null) {
                throw new TokenizerException("Unrecognized character: " + input.charAt(pos));
            }
        } else {
            return null;
        }
    }
    
    public static List<Token> tokenize(String input) throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        Token token = null;
        while ((token = readToken()) != null) {
            tokens.add(token);
        }
        return tokens;
    }
}
