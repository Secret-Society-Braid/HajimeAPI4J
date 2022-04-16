package HajimeAPI4J.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.util.datatype.Member;
import HajimeAPI4J.api.util.internal.IParse;

public class IParseEncodingTest {

    @Test
    public void CheckUTF8Encoding() {
        IParse parse = new Member();
        String expected = "UTF-8";
        assertEquals(expected, parse.getEncoding());
    }
    
}
