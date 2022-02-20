package HajimeAPI4J;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J.List_Params;
import HajimeAPI4J.api.HajimeAPI4J.List_Type;
import HajimeAPI4J.api.HajimeAPI4J.Music_Params;
import HajimeAPI4J.api.HajimeAPI4J.Tax_Params;
import HajimeAPI4J.api.HajimeAPI4J.Token;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.HajimeAPI4JImpl;
import HajimeAPI4J.exception.NoSuchURIException;
import HajimeAPI4J.exception.ServerNotRespondError;

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
            throw new AssertionError("Test Failed", e);
        }
    }

    @Test
    public void sendTaxRequestTest() {
        HajimeAPIBuilder taxBuilder = HajimeAPIBuilder.createDefault(Token.TAX);
        try {
            HajimeAPI4JImpl tax = taxBuilder.addParameter(Tax_Params.IDOL_NAME, "藤原肇").build();
            assertNotNull(tax.get());
        } catch (IOException | ServerNotRespondError | NoSuchURIException | NullPointerException | InterruptedException e) {
            throw new AssertionError("Test Failed", e);
        }
    }

    @Test
    public void sendMusicRequestTest() {
        HajimeAPIBuilder musicBuilder = HajimeAPIBuilder.createDefault(Token.MUSIC);
        try {
            HajimeAPI4JImpl music = musicBuilder.addParameter(Music_Params.ID, "3525").build();
            assertNotNull(music.get());
        } catch (IOException | ServerNotRespondError | NoSuchURIException | NullPointerException | InterruptedException e) {
            throw new AssertionError("Test Failed", e);
        }
    }

}
