package hajimeapi4j.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.utilizations.Song;
import hajimeapi4j.internal.endpoint.TaxEndPointImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaxEndPointDeserializer extends JsonDeserializer<TaxEndPoint> {

  @Override
  public TaxEndPoint deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p.readValueAsTree();
    if (!node.hasNonNull("member")) {
      return p.readValueAs(TaxEndPoint.class);
    }
    String name = node.get("name").asText();
    String type = node.get("type").asText();
    int id = node.get("id").asInt();
    String link = node.get("link").asText();
    String api = node.get("api").asText();
    String kana = node.get("kana").asText();
    String cv = node.get("cv").asText();
    String cvKana = node.get("cvkana").asText();
    String production = node.get("production").asText();
    String date = node.get("date").asText();
    String place = node.get("place").asText();
    boolean setlist = node.get("setlist").asBoolean();
    EndPoint lyrics = node.get("lyrics").traverse().readValueAs(EndPoint.class);
    EndPoint composer = node.get("composer").traverse().readValueAs(EndPoint.class);
    EndPoint arrange = node.get("arrange").traverse().readValueAs(EndPoint.class);
    List<Song> song = node.get("song").traverse().readValueAs(new TypeReference<List<Song>>() {
    });

    List<Member> member = null;

    if (node.get("member").isObject()) {
      member = node.get("member").traverse().readValueAs(new TypeReference<List<Member>>() {
      });
    } else if (node.get("member").isTextual()) {
      List<String> memberName = Arrays.stream(node.get("member").asText().split(",")).collect(
          Collectors.toList());
      member = memberName.parallelStream().map(Member::createInstance).collect(Collectors.toList());
    }
    return TaxEndPointImpl.createInstance(name, type, id, link, api, kana, cv, cvKana, production,
        date, place, member, setlist, lyrics, composer, arrange, song);
  }
}
