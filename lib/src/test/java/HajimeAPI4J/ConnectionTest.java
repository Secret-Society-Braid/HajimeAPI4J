package HajimeAPI4J;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import HajimeAPI4J.connect.CheckServerStatus;
import HajimeAPI4J.connect.Request;
import mock.ParseURLMock;

public class ConnectionTest {
    
    @Test
    public void aliveTest() {
        //tests whether server is alive.
        assertTrue(CheckServerStatus.isServerAlive());
    }

    @Test
    public void checkURL() {
        //tests whether URL is collect.
        HashMap<String, Object> listParam = new HashMap<>();
        listParam.put("type", "music");

        assertEquals("https://api.fujiwarahaji.me/v1/list?type=music", ParseURLMock.parseURL(listParam));

    }

    @Test
    public void getResponseTest() {
        //tests whether response is collect.
        Request list = new Request(Request.LIST);
        Request tax = new Request(Request.TAX);
        Request music = new Request(Request.MUSIC);

        //for list
        HashMap<String, Object> listParam = new HashMap<>();
        listParam.put("type", "music");

        //for tax
        HashMap<String, Object> taxParam = new HashMap<>();
        taxParam.put("id", 1665);

        //for music
        HashMap<String, Object> musicParam = new HashMap<>();
        musicParam.put("id", 3525);

        try {
            assertNotNull(list.listToken(listParam));
            assertNotNull(tax.taxOrMusic(taxParam));
            assertNotNull(music.taxOrMusic(musicParam));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
