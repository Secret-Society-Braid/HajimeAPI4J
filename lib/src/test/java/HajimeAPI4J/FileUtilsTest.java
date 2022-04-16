package HajimeAPI4J;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.Test;

import HajimeAPI4J.api.util.FileUtils;

public class FileUtilsTest {
    
    @Test
    public void fieldNullCheckTest() {
        final String envFileName = "env.json";
        Field[] field = FileUtils.class.getDeclaredFields();
        for(Field f : field) {
            f.setAccessible(true);
            assertNotNull(f);
            if(f.getName().equals("ENV_FILE_NAME")) {
                try {
                    f.get(new String()).equals(envFileName);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    fail(e);
                }
            }
            if(f.getName().equals("TYPE_REFERENCE")) {
                try {
                    f.get(new String()).equals(new TypeReference<Map<String, String>>() {
                    });
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    fail(e);
                }
            }
        }
    }

    @Test
    public void constructorTest() {
        try {
            Class<?> clazz = Class.forName("HajimeAPI4J.api.util.FileUtils");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            constructors[0].setAccessible(true);

            Object test = constructors[0].newInstance();

            assertNotNull(test);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            fail(e);
        }
    }

    @Test
    public void readFromResourceFolderTest() {
        try {
            List<String> declaredKeys = List.of("version", "suffix", "developer");

            Map<String, String> env = FileUtils.readFilesFromResourceFolder("env.json");

            assertFalse(env.isEmpty());

            assertTrue(env.keySet().containsAll(declaredKeys));
            assertEquals(env.keySet().size(), declaredKeys.size());
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    public void throwExceptionWhenReadEmptyFile() {
        assertDoesNotThrow(() -> {
            FileUtils.readFilesFromResourceFolder("env.json");
        });
    }

}
