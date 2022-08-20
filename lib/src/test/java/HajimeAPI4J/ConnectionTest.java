package hajimeapi4j;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hajimeapi4j.api.HajimeAPIBuilder;
import hajimeapi4j.api.HajimeAPI4J.List_Params;
import hajimeapi4j.api.HajimeAPI4J.List_Type;
import hajimeapi4j.api.HajimeAPI4J.Music_Params;
import hajimeapi4j.api.HajimeAPI4J.Tax_Params;
import hajimeapi4j.api.HajimeAPI4J.Token;
import hajimeapi4j.api.util.HajimeAPI4JImpl;
import hajimeapi4j.exception.NoSuchURIException;
import hajimeapi4j.exception.ServerNotRespondError;

public class ConnectionTest {

    @BeforeEach
    public void setTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
    
    @Test
    public void sendListRequestTest() {
        HajimeAPIBuilder listBuilder = HajimeAPIBuilder.createDefault(Token.LIST);
        try {
            HajimeAPI4JImpl list = listBuilder.addParameter(List_Params.TYPE, List_Type.MUSIC.toString()).build();
            assertNotNull(list.get());
        } catch (IOException | ServerNotRespondError | NoSuchURIException | NullPointerException | InterruptedException e) {
            fail(e);
        }
    }

    @Test
    public void sendTaxRequestTest() {
        HajimeAPIBuilder taxBuilder = HajimeAPIBuilder.createDefault(Token.TAX);
        try {
            HajimeAPI4JImpl tax = taxBuilder.addParameter(Tax_Params.IDOL_NAME, "藤原肇").build();
            assertNotNull(tax.get());
        } catch (IOException | ServerNotRespondError | NoSuchURIException | NullPointerException | InterruptedException e) {
            fail(e);
        }
    }

    @Test
    public void sendMusicRequestTest() {
        HajimeAPIBuilder musicBuilder = HajimeAPIBuilder.createDefault(Token.MUSIC);
        try {
            HajimeAPI4JImpl music = musicBuilder.addParameter(Music_Params.ID, "3525").build();
            assertNotNull(music.get());
        } catch (IOException | ServerNotRespondError | NoSuchURIException | NullPointerException | InterruptedException e) {
            fail(e);
        }
    }

}
