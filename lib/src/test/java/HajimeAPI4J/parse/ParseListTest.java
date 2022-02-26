package HajimeAPI4J.parse;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.parse.ParseList;
import HajimeAPI4J.exception.NoSuchURIException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParseListTest {

    private static HajimeAPI4J api;
    private static ParseList parse;

    @BeforeAll
    public static void Setup() throws NoSuchURIException, IOException, InterruptedException {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.LIST);
        builder.addParameter(HajimeAPI4J.List_Params.TYPE, HajimeAPI4J.List_Type.MUSIC.toString());
        builder.addParameter(HajimeAPI4J.List_Params.LIMIT, "10");
        api = builder.build();
        parse = new ParseList(api.get());
    }

    @Test
    public void TestGetName() {
        for(int i = 0; i < 10; i++) {
            assertNotNull(parse.getName(i));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> parse.getName(10));
    }

}
