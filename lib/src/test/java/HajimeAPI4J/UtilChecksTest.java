package HajimeAPI4J;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.util.Checks;
import HajimeAPI4J.exception.IllegalParameterException;
import HajimeAPI4J.exception.NoSuchURIException;

public class UtilChecksTest {
    
    // Test for Checks class

    private final String nonNullString = "something";

    @Test
    public void softNonNullTest() {
        assertTrue(Checks.softRequireNonNull(nonNullString));
    }

    @Test
    public void softNullTest() {
        assertFalse(Checks.softRequireNonNull(null));
    }
    
    @Test
    public void hardNonNullTest() {
        assertThrows(NullPointerException.class, () -> Checks.hardRequireNonNull(null));
    }

    @Test
    public void requireSameTokenTest() {
        HajimeAPI4J.Token token = HajimeAPI4J.Token.LIST;
        assertThrows(IllegalParameterException.class, () -> Checks.requireSameToken(token, nonNullString));
    }

    @Test
    public void listMustHaveParamTest() {
        Map<String, String> param = new HashMap<>();
        assertThrows(NoSuchURIException.class, () -> Checks.listMustHaveParam(param));
    }

    @Test
    public void taxMustHaveParamTest() {
        Map<String, String> param = new HashMap<>();
        assertThrows(NoSuchURIException.class, () -> Checks.taxMustHaveParam(param));
    }

    @Test
    public void musicMustHaveParamTest() {
        Map<String, String> param = new HashMap<>();
        assertThrows(NoSuchURIException.class, () -> Checks.musicMustHaveParam(param));
    }
}
