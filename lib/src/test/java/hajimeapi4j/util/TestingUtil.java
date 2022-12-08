package hajimeapi4j.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestingUtil {

  private static ObjectMapper mapperInstance;

  public static synchronized ObjectMapper getMapperInstance() {
    if (mapperInstance == null) {
      mapperInstance = new ObjectMapper();
    }
    return mapperInstance;
  }

}
