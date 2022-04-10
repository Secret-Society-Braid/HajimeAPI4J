package HajimeAPI4J.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J.Status;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.HajimeAPI4JImpl;

public class ExceptionAssertionTest {

    // test for exception throwing

    @Test
    public void buildWithNullTokenTest() {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault((String) null);
        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    public void buildWithUnknownTokenTest() {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault("unknown");
        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    public void illegalStateTest() {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4JImpl.Token.LIST);
        builder.addParameter(HajimeAPI4JImpl.List_Params.TYPE, HajimeAPI4JImpl.List_Type.MUSIC.toString());
        HajimeAPI4JImpl api = builder.build();
        api.setStatus(Status.FINISHED);
        assertThrows(IllegalStateException.class, api::getURI);
    }
}
