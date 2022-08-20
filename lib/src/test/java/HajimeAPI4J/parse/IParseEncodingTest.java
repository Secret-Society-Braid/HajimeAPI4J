package hajimeapi4j.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hajimeapi4j.api.util.datatype.Member;
import hajimeapi4j.api.util.internal.IParse;

public class IParseEncodingTest {

    @Test
    public void CheckUTF8Encoding() {
        IParse parse = new Member();
        String expected = "UTF-8";
        assertEquals(expected, parse.getEncoding());
    }
    
}
