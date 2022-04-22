package HajimeAPI4J;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;

public class ImplTest {
    
    @Test
    public void getterTest() {
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.LIST);
        builder
            .addParameter(HajimeAPI4J.List_Params.TYPE, HajimeAPI4J.List_Type.MUSIC.toString())
            .addParameter(HajimeAPI4J.List_Params.LIMIT, "10");
        HajimeAPI4J api = builder.build();

        assertNotNull(api);
        assertEquals(HajimeAPI4J.Token.LIST, api.getToken());
        assertEquals(HajimeAPI4J.Status.NOT_INITIALIZED, api.getStatus());
        assertFalse(api.isCache());

        assertNotNull(api.getParams());
        Map<String, String> params = api.getParams();

        assertEquals(2, params.size());
        assertTrue(params.containsKey(HajimeAPI4J.List_Params.TYPE.toString()));
        assertTrue(params.containsKey(HajimeAPI4J.List_Params.LIMIT.toString()));

        assertEquals(params.get(HajimeAPI4J.List_Params.TYPE.toString()), HajimeAPI4J.List_Type.MUSIC.toString());
        assertEquals(params.get(HajimeAPI4J.List_Params.LIMIT.toString()), "10");

        assertNotNull(api.getDefaultExecutorService());
    }
}
