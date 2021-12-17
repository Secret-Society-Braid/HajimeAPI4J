package HajimeAPI4J.async;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.connect.Request;

public class TaxOrMusicRequestAsync implements Runnable {
    
    //Declare fields
    private Logger logger = LoggerFactory.getLogger(TaxOrMusicRequestAsync.class);
    private List<Map<String, Object>> result = Collections.emptyList();
    private Map<String, Object> param; 
    private Request req = null;
    private boolean flag = false;
     
    public TaxOrMusicRequestAsync(Map<String, Object> param) {
        this.req = new Request(Request.LIST);
        this.param = param;
    }
    
    @Override
    public void run() {
        try {
            result = req.listToken(param);
            flag = true;
        } catch (IOException e) {
                logger.error("Exception was thrown while running async thread.", e);
        }
    }
    
    public List<Map<String, Object>> getResult() {
        return result;
    }
    
    public boolean getFlag() {
        return flag;
    }
}
