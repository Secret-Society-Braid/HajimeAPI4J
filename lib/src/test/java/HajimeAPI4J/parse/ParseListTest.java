package hajimeapi4j.parse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hajimeapi4j.api.HajimeAPI4J;
import hajimeapi4j.api.HajimeAPIBuilder;
import hajimeapi4j.api.util.parse.ParseList;
import hajimeapi4j.exception.NoSuchURIException;

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
            assertNotNull(parse.getType(i));
            assertNotNull(parse.getApi(i));
            assertNotNull(parse.getNode(i));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> parse.getName(10));
    }

}
