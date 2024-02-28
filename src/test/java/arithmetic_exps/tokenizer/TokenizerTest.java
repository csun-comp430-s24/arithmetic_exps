package arithmetic_exps.tokenizer;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TokenizerTest {
    @Test
    public void testEmptyFile() throws TokenizerException {
        assertTrue(Tokenizer.tokenize("").isEmpty());
    }
}
