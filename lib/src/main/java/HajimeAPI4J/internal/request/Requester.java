package hajimeapi4j.internal.request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Requester {

  private static final String API_BASE_URL = "https://api.fujiwarahaji.me/v2";
  private static final String USER_AGENT = "HajimeAPI4J java wrapper developed by @hizumiaoba";

  private Route route;

  public Requester() {
    log.info("api requester has been created");
  }

  public void setRoute(Route route) {
    this.route = route;
  }

  public

}
