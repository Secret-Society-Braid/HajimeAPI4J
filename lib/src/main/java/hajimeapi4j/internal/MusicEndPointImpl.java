package hajimeapi4j.internal;

import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.MusicEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Disc;
import hajimeapi4j.internal.datatype.utilizations.Live;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MusicEndPointImpl extends EndPointImpl implements MusicEndPoint {

  protected List<EndPoint> remix;
  protected List<EndPoint> original;
  protected List<EndPoint> lyrics;
  protected List<EndPoint> composer;
  protected List<EndPoint> arrange;
  protected String lyricsUrl;
  protected List<Member> member;
  protected boolean digital;
  protected List<Disc> disc;
  protected List<Live> live;

  protected MusicEndPointImpl(
      String name,
      String type,
      int taxId,
      int songId,
      String link,
      String api,
      String unit,
      List<EndPoint> remix,
      List<EndPoint> original,
      List<EndPoint> lyrics,
      List<EndPoint> composer,
      List<EndPoint> arrange,
      String lyricsUrl,
      List<Member> member,
      boolean digital,
      List<Disc> disc,
      List<Live> live) {
    super(name, type, taxId, songId, link, api);

  }

}
