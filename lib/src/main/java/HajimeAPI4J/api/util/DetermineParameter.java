package HajimeAPI4J.api.util;

import org.slf4j.*;

import HajimeAPI4J.api.HajimeAPI4J;

public final class DetermineParameter {

    private String[] parameters;
    private Logger logger = LoggerFactory.getLogger(DetermineParameter.class);

    /**
     *　コンストラクタ
     */
    public DetermineParameter(String... parameters) {
        logger.info("Constructing...");
        this.parameters = parameters;
    }

    /**
     * /list?type=musicのリクエストを送った際に返されるレスポンスの親パラメータ名
     */
    public static final String[] listMusicParameters = {
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
    public static final String[] listOtherParameters = {
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
    public static final String[] taxParameters = {
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
     * レスポンスが配列で返されるかどうかを判定する
     * @param token リクエストのパラメータ名
     * @return レスポンスが配列で返される場合はtrue、そうでない場合はfalse
     */
    public boolean isReturnedAsArray(String token) {
        if(token == null) {
            return false;
        }
        return token.equals("list") 
            || token.equals("member")
            || token.equals("music")
            || token.equals("lyrics") 
            || token.equals("composer")
            || token.equals("arrange")
            || token.equals("disc")
            || token.equals("live")
            || token.equals("unit");
    }

    
}
