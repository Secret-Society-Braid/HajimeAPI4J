package hajimeapi4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import hajimeapi4j.api.HajimeAPI4J;
import hajimeapi4j.api.util.Checks;
import hajimeapi4j.exception.IllegalParameterException;
import hajimeapi4j.exception.NoSuchURIException;

public class UtilChecksTest {
    
    // Test for Checks class

    private final String nonNullString = "something";

    @Test
    public void TestConstructor() {
        try {
            Class<?> checksClass = Class.forName("hajimeapi4j.api.util.Checks");
        Constructor<?>[] constructors = checksClass.getDeclaredConstructors();
        constructors[0].setAccessible(true);

        Checks checks = (Checks) constructors[0].newInstance();

        } catch (ClassNotFoundException | SecurityException | IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            if(e.getCause() == null) {
                fail(e);
            } else {
                assertEquals("UnsupportedOperationException", e.getCause().getClass().getSimpleName());
            }
        }
    }

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
