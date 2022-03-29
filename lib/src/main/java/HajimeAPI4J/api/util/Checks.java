package HajimeAPI4J.api.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.exception.IllegalParameterException;
import HajimeAPI4J.exception.NoSuchParameterException;
import HajimeAPI4J.exception.NoSuchURIException;
import HajimeAPI4J.exception.ServerNotRespondError;

public class Checks {
    
    private static final Logger logger = LoggerFactory.getLogger(Checks.class);

    private Checks() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static boolean softRequireNonNull(Object obj) {
        return Objects.nonNull(obj);
    }

    public static void hardRequireNonNull(Object obj) {
        Objects.requireNonNull(obj, "Object must not be null.");
    }

    public static void requireSameToken(HajimeAPI4J.Token token, String current) {
        hardRequireNonNull(current);
        if(!token.toString().equals(current)) {
            throwIllegalParameterException("Expected token: " + token.toString() + ", but currently set as : " + current);
        }
    }

    public static void listMustHaveParam(Map<String, String> param) throws NoSuchURIException {
        hardRequireNonNull(param);
        if(!param.containsKey("type")) {
            throwNoSuchURIException(new IllegalParameterException("List must have type parameter."));
        }
    }

    public static void taxMustHaveParam(Map<String, String> param) throws NoSuchURIException {
        hardRequireNonNull(param);
        if(!((param.containsKey(HajimeAPI4J.Tax_Params.ID.toString()))
            || (param.containsKey(HajimeAPI4J.Tax_Params.IDOL_NAME.toString()))
            || (param.containsKey(HajimeAPI4J.Tax_Params.UNIT_NAME.toString())))) {
                throwNoSuchURIException(new IllegalParameterException("there is no unit_name or idol_name or tax_id parameter. At least one of them is required."));
        }
    }

    public static void musicMustHaveParam(Map<String, String> param) throws NoSuchURIException {
        hardRequireNonNull(param);
        if(!param.containsKey(HajimeAPI4J.Music_Params.ID.toString())) {
            throwNoSuchURIException(new IllegalParameterException("Music must have id parameter."));
        }
    }

    public static void availableListParam(HajimeAPI4J.List_Params param) {
        hardRequireNonNull(param);
        if(!Arrays.asList(HajimeAPI4J.List_Params.values()).contains(param)) {
            throwNoSuchParameterException(String.format("List parameter is not available : %s", param));
        }
    }

    public static void availableTaxParam(HajimeAPI4J.Tax_Params param) {
        hardRequireNonNull(param);
        if(!Arrays.asList(HajimeAPI4J.Tax_Params.values()).contains(param)) {
            throwNoSuchParameterException(String.format("Tax parameter is not available : %s", param));
        }
    }

    public static void availableMusicParam(HajimeAPI4J.Music_Params param) {
        hardRequireNonNull(param);
        if(!Arrays.asList(HajimeAPI4J.Music_Params.values()).contains(param)) {
            throwNoSuchParameterException(String.format("Music parameter is not available : %s", param));
        }
    }

    public static void serverAlive() {
        if(!CheckServerStatus.isServerAlive())
            throw new ServerNotRespondError("Server did not respond correctly.");
    }

    //throws

    private static final void throwIllegalParameterException(String message) {
        throwIllegalParameterException(message, null);
    }

    private static final void throwIllegalParameterException(String message, Throwable e) {
        logger.error(message);
        throw new IllegalParameterException(message, e);
    }

    private static final void throwNoSuchParameterException(String message) {
        throwNoSuchParameterException(message, null);
    }

    private static final void throwNoSuchParameterException(String message, @Nullable Throwable e) {
        logger.error(message);
        throw new NoSuchParameterException(String.format("Provided message : %s%nCaused by: %s", message, e == null ? "null" : e.getMessage()));
    }

    private static final void throwNoSuchURIException(Throwable e) throws NoSuchURIException {
        throwNoSuchURIException(null, e);
    }

    private static final void throwNoSuchURIException(String message, Throwable e) throws NoSuchURIException {
        logger.error(message);
        throw new NoSuchURIException(String.format("Provided message : %s%nCaused by: %s", message, e.getMessage()));
    }

}
