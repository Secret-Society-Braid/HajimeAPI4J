package HajimeAPI4J.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.parse.ParseMusic;
import HajimeAPI4J.exception.NoSuchURIException;

public class ParseMusicTest {

    private static HajimeAPI4J api;
    private static ParseMusic parse;

    @BeforeAll
    public static void setup() throws NoSuchURIException, IOException, InterruptedException {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.MUSIC);
        builder.addParameter(HajimeAPI4J.Music_Params.ID, "3525");
        api = builder.build();
        parse = new ParseMusic(api.get());
    }

    @Test
    public void testNodes() throws NoSuchURIException, IOException, InterruptedException {
        assertNotNull(parse.getName());
        assertNotNull(parse.getType());
        assertNotNull(parse.getInternalId());
        assertNotNull(parse.getLink());
        assertNotNull(parse.getApi());
        assertNotNull(parse.getJsonNode());

        assertEquals(parse.getInternalId(), new ParseMusic(parse.getAPIInstance().get()).getInternalId());
    }
    
}
