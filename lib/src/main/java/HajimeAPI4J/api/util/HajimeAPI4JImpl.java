package HajimeAPI4J.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.HajimeAPI4J;

public class HajimeAPI4JImpl implements HajimeAPI4J {

    private Logger logger = LoggerFactory.getLogger(HajimeAPI4JImpl.class);

    private String uri = null;
    private HajimeAPI4J.Token token = null;
    private HajimeAPI4J.Status status = null;

    public HajimeAPI4JImpl() {

    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public String getURI() {
        return uri;
    }

    
    
    
}
