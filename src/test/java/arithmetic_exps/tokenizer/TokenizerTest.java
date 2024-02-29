package arithmetic_exps.tokenizer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.List;

public class TokenizerTest {
    @Test
    public void testEmptyFile() throws TokenizerException {
        assertTrue(Tokenizer.tokenize("").isEmpty());
    }

    public void testSingleToken(final String input,
                                final Token expected) throws TokenizerException {
        final List<Token> tokens = Tokenizer.tokenize(input);
        assertEquals(1, tokens.size());
        assertEquals(expected, tokens.get(0));
    }
    
    @Test
    public void testLet() throws TokenizerException {
        testSingleToken("let", new LetToken());
    }

    @Test
    public void testLetWhitespaceAtEnd() throws TokenizerException {
        testSingleToken("let ", new LetToken());
    }

    @Test
    public void testSingleDigitInteger() throws TokenizerException {
        testSingleToken("1", new IntegerLiteralToken(1));
    }

    @Test
    public void testMultiDigitInteger() throws TokenizerException {
        testSingleToken("123", new IntegerLiteralToken(123));
    }
    
    // +-
    // + -
    // +   -
    //    +-
    //    + -
    // 123let
    // $ - CAN'T TOKENIZE
    // % - CAN'T TOKENIZE    
}
