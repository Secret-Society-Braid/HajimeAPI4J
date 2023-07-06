package hajimeapi4j.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hajimeapi4j.internal.datatype.Member;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDeserializer extends StdDeserializer<List<Member>> {

  private final static ObjectMapper MAPPER = new ObjectMapper();

  protected MemberDeserializer() {
    super(Member.class);
  }

  @Override
  public List<Member> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.getCurrentToken() == JsonToken.START_ARRAY) {
      p.setCodec(MAPPER);
      return MAPPER.readValue(p,
          MAPPER.getTypeFactory().constructCollectionType(List.class, Member.class));
    } else if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
      return Arrays.stream(p.getValueAsString().split(","))
          .map(String::trim)
          .map(Member::createInstance)
          .collect(Collectors.toList());
    } else {
      throw new IllegalStateException("Unexpected token: " + p.getCurrentToken());
    }
  }
}
