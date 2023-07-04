package hajimeapi4j.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeserializationHelper {

  public static <T> T findAndSetValue(Class<T> clazz, T instance, String fieldName, Object value) {
    try {
      clazz.getDeclaredField(fieldName).set(instance, value);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      log.warn("cannot find field {}, which will be ignored", fieldName, e);
    }
    return instance;
  }
}
