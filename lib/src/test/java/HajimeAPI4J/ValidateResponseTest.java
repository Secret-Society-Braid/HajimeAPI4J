package HajimeAPI4J;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J.List_Params;
import HajimeAPI4J.api.HajimeAPI4J.List_Type;
import HajimeAPI4J.api.HajimeAPI4J.Token;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.HajimeAPI4JImpl;
import HajimeAPI4J.api.util.parse.ParseList;
import HajimeAPI4J.exception.NoSuchURIException;

public class ValidateResponseTest {

    @BeforeEach
    public void timeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void validateListSize() {
        HajimeAPI4JImpl api = HajimeAPIBuilder.createDefault(Token.LIST)
            .addParameter(List_Params.TYPE, List_Type.MUSIC.toString())
            .addParameter(List_Params.LIMIT, "1")
            .build();
        ParseList parseList = null;
        try {
            parseList = new ParseList(api.get());
        } catch (NoSuchURIException | IOException | InterruptedException e) {
            throw new AssertionError(e);
        }
        assertTrue(parseList.converse().asList().size() == 1);
        
    }

    @Test
    public void validateListSizeForEach() {
        for(int i = 1; i < 11; i++) {
            HajimeAPI4JImpl api = HajimeAPIBuilder.createDefault(Token.LIST)
                .addParameter(List_Params.TYPE, List_Type.MUSIC.toString())
                .addParameter(List_Params.LIMIT, String.valueOf(i))
                .build();
            ParseList parseList = null;
            try {
                parseList = new ParseList(api.get());
                TimeUnit.SECONDS.sleep(2);
            } catch (NoSuchURIException | IOException | InterruptedException e) {
                throw new AssertionError(e);
            }
            assertTrue(parseList.converse().asList().size() == i);
        } 
    }
    
}
