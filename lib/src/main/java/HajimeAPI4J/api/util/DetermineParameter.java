package HajimeAPI4J.api.util;

import java.util.Objects;

import org.slf4j.*;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.exception.NoSuchURIException;

public final class DetermineParameter {

    private String token;
    private boolean[] isMusic;
    private Logger logger = LoggerFactory.getLogger(DetermineParameter.class);

    /**
     *　コンストラクタ
     */
    public DetermineParameter(String token, boolean... isMusic) {
        logger.info("Constructing...");
        this.token = token;
        this.isMusic = isMusic;
    }

    /**
     * /list?type=musicのリクエストを送った際に返されるレスポンスの親パラメータ名
     */
    protected static final String[] listMusicParameters = {
        HajimeAPI4J.NAME,
        HajimeAPI4J.TYPE,
        HajimeAPI4J.MUSIC_TYPE,
        HajimeAPI4J.SONG_ID,
        HajimeAPI4J.LINK,
        HajimeAPI4J.API
    };

    /**
     * /list?type=music以外のリクエストを送った際に返されるレスポンスの親パラメータ名
     */
    protected static final String[] listOtherParameters = {
        HajimeAPI4J.NAME,
        HajimeAPI4J.TYPE,
        HajimeAPI4J.TAX_ID,
        HajimeAPI4J.LINK,
        HajimeAPI4J.API,
        HajimeAPI4J.DATE,
        HajimeAPI4J.PRODUCTION,
        HajimeAPI4J.KANA,
        HajimeAPI4J.CV,
        HajimeAPI4J.CVKANA
    };

    /**
     * /taxのリクエストを送った際に返されるレスポンスの親パラメータ名
     */
    protected static final String[] taxParameters = {
        HajimeAPI4J.NAME,
        HajimeAPI4J.TYPE,
        HajimeAPI4J.TAX_ID,
        HajimeAPI4J.LINK,
        HajimeAPI4J.API,
        HajimeAPI4J.KANA,
        HajimeAPI4J.CV,
        HajimeAPI4J.CVKANA,
        HajimeAPI4J.PRODUCTION,
        HajimeAPI4J.DATE,
        HajimeAPI4J.PLACE,
        HajimeAPI4J.MEMBER,
        HajimeAPI4J.SETLIST,
        HajimeAPI4J.LYRICS,
        HajimeAPI4J.COMPOSER,
        HajimeAPI4J.ARRANGE,
        HajimeAPI4J.MUSIC
    };

    /**
     * /musicのリクエストを送った際に返されるレスポンスの親パラメータ名
     */
    protected static final String[] musicParameters = {
        HajimeAPI4J.NAME,
        HajimeAPI4J.TYPE,
        HajimeAPI4J.MUSIC_ID,
        HajimeAPI4J.LINK,
        HajimeAPI4J.API,
        HajimeAPI4J.LYRICS,
        HajimeAPI4J.COMPOSER,
        HajimeAPI4J.ARRANGE,
        HajimeAPI4J.LYRICS_URL,
        HajimeAPI4J.MEMBER,
        HajimeAPI4J.LIVE
    };

    public String[] getParameters() throws NoSuchURIException {
        Objects.requireNonNull(token);
        if(token.equals("list") && isMusic[0]) {
            return listMusicParameters;
        } else if(token.equals("list") && !isMusic[0]) {
            return listOtherParameters;
        } else if(token.equals("tax")) {
            return taxParameters;
        } else if(token.equals("music")) {
            return musicParameters;
        } else {
            throw new NoSuchURIException("No such URI");
        }
    }
    
}
