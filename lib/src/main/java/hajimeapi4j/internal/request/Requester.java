package hajimeapi4j.internal.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Requester {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private static final String API_BASE_URL = "https://api.fujiwarahaji.me/v2"; // TODO: update API version to v2.1
  private static final String USER_AGENT = "HajimeAPI4J java wrapper developed by @hizumiaoba";

  private CompiledRoute route;

  public Requester() {
    log.info("api requester has been created");
  }

  public void setRoute(CompiledRoute route) {
    this.route = route;
  }

  public JsonNode sendRequest() throws IOException {
    URL url = new URL(API_BASE_URL + route.getCompiledRouteString());
    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.addRequestProperty("User-Agent", USER_AGENT);
    log.info("opening connection with following data");
    log.info("Route data: {}", route);
    log.info("Request method: GET");
    log.info("User Agent: {}", USER_AGENT);
    log.info("API Version: v2");
    connection.connect();
    return MAPPER.readTree(connection.getInputStream());
  }

}
