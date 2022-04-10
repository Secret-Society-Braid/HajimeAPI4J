package HajimeAPI4J.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.parse.ParseTax;
import HajimeAPI4J.exception.NoSuchURIException;

public class ParseTaxTest {
    
    private static HajimeAPI4J api;
    private static ParseTax parse;

    @BeforeAll
    public static void setup() throws NoSuchURIException, IOException, InterruptedException {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.TAX);
        builder.addParameter(HajimeAPI4J.Tax_Params.ID, "1665");
        api = builder.build();
        parse = new ParseTax(api.get());
    }

    @Test
    public void testNodes() throws NoSuchURIException, IOException, InterruptedException {
        assertNotNull(parse.getName());
        assertNotNull(parse.getType());
        assertNotNull(parse.getInternalId());
        assertNotNull(parse.getLink());
        assertNotNull(parse.getApi());
        assertNotNull(parse.getJsonNode());

        assertEquals(parse.getInternalId(), new ParseTax(parse.getAPIInstance().get()).getInternalId());
    }
}
