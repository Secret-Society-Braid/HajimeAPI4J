package hajimeapi4j.internal.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.request.DetailedRequestRestActionImpl;
import hajimeapi4j.util.InternalUtils;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = EndPointImpl.class)
public abstract class AbstructEndPoint implements EndPoint {

  protected String name;
  protected String type;
  protected int taxId;
  protected int songId;
  protected String link;
  protected String api;

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public int getTaxId() {
    return this.getSongId();
  }

  @Override
  public int getSongId() {
    return this.getTaxId();
  }

  @Override
  public String getLink() {
    return this.link;
  }

  @Override
  public String getApi() {
    return this.api;
  }

  @Override
  @Nonnull
  public RestAction<EndPoint> fromApi() {
    return new DetailedRequestRestActionImpl<>(this.getApi(), EndPoint.class);
  }

  @Override
  public boolean checkEmpty() {
    return InternalUtils.checkEmpty(this);
  }
}
