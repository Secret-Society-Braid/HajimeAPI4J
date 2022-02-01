package HajimeAPI4J.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.exception.IllegalParameterException;

public class Checks {
    
    private static final Logger logger = LoggerFactory.getLogger(Checks.class);

    public static boolean softRequireNonNull(Object obj) {
        if(obj == null) {
            logger.error("Object is null.");
            return false;
        }
        return true;
    }

    public static void hardRequireNonNull(Object obj) {
        if(obj == null) {
            logger.error("Object must not be null.");
            throw new NullPointerException("Object must not be null.");
        }
    }

    public static void listRequiresTypeParam(HajimeAPI4J.List_Params param) {
        if(softRequireNonNull(param)) {
            logger.error("List_Params must not be null.");
            throw new IllegalParameterException("Type parameter must not be null.");
        }
    }

}
