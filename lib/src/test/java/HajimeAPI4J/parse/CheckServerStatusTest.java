package hajimeapi4j.parse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

public class CheckServerStatusTest {
    
    @Test
    public void nonNullConstructorTest() {
        try {
            Class<?> clazz = Class.forName("hajimeapi4j.api.util.CheckServerStatus");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();

            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    constructor.setAccessible(true);
                    Object object = constructor.newInstance();

                    assertNotNull(object);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            fail(e);
        }
    }
}
