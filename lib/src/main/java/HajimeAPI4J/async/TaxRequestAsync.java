package HajimeAPI4J.async;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.connect.Request;

public class TaxRequestAsync implements Runnable {
    
    //Declare fields
    private Logger logger = LoggerFactory.getLogger(TaxRequestAsync.class);
    private Map<String, Object> result;
    private Map<String, Object> param; 
    private Request req = null;
    private boolean flag = false;
     
    public TaxRequestAsync(Map<String, Object> param) {
        this.req = new Request(Request.TAX);
        this.param = param;
    }
    
    @Override
    public void run() {
        try {
            result = req.taxOrMusic(param);
            flag = true;
        } catch (IOException e) {
                logger.error("Exception was thrown while running async thread.", e);
        }
    }
    
    public Map<String, Object> getResult() {
        return result;
    }
    
    public boolean getFlag() {
        return flag;
    }
}
