package hajimeapi4j.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hajimeapi4j.internal.datatype.Member;
import jakarta.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

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
          .map(MinimalMember::new)
          .collect(Collectors.toList());
    } else {
      throw new IllegalStateException("Unexpected token: " + p.getCurrentToken());
    }
  }

  static class MinimalMember extends Member {

    public MinimalMember(String name) {
      super(name, null, 0, 0, null, null, null, null);
    }

    /**
     * 名前を取得します。
     *
     * @return 名前
     */
    @Override
    @Nonnull
    public String getName() {
      return super.getName();
    }

    @Override
    @Nonnull
    public String getType() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    @Override
    public int getTaxId() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    @Override
    public int getSongId() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    @NotNull
    @Override
    public String getLink() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    @Override
    @Nonnull
    public String getApi() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    /**
     * このアイドルの所属プロダクションを返します。
     *
     * @return このアイドルの所属プロダクション
     */
    @Override
    public Optional<String> getProduction() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    /**
     * このメンバーの声優を返します。
     *
     * @return 声優
     */
    @NotNull
    @Override
    public String getCv() {
      throw new UnsupportedOperationException("Not Supported for your purpose.");
    }

    @Override
    public String toString() {
      return String.format("MinimalMember(Member[name=%s])", getName());
    }
  }
}
