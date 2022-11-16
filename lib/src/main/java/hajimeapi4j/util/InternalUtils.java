package hajimeapi4j.util;

import com.fasterxml.jackson.databind.JsonNode;
import hajimeapi4j.api.endpoint.EndPoint;
import hajimeapi4j.api.endpoint.ListEndPoint;
import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.api.request.RestAction;
import hajimeapi4j.internal.datatype.Member;
import hajimeapi4j.internal.datatype.MemberSolo;
import hajimeapi4j.internal.datatype.Unit;
import hajimeapi4j.internal.datatype.utilizations.Music;
import hajimeapi4j.internal.endpoint.ListEndPointImpl;
import hajimeapi4j.internal.endpoint.TaxEndPointImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalUtils {

  private static final String MESSAGE_METHOD_NOT_ALLOWED = "You cannot invoke this method from Lyrics class";
  private static final String MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED = "raw response must not be null";

  private InternalUtils() { /* do nothing */}

  public static String getMethodNotAllowedMessage() {
    return MESSAGE_METHOD_NOT_ALLOWED;
  }

  public static String getMessageNullRawResponseNotAllowedMessage() {
    return MESSAGE_NULL_RAW_RESPONSE_NOT_ALLOWED;
  }

  public static boolean checkEmpty(EndPoint testee) {
    // String vars will need to check only whether empty or not since we contract this as @Nonnull
    return testee.getName().isEmpty()
        && ((testee.getSongId() == -1) || (testee.getTaxId() == -1))
        && testee.getType().isEmpty()
        && testee.getLink().isEmpty()
        && testee.getApi().isEmpty();
  }

  @Nonnull
  public static RestAction<EndPoint> parseFromUriString(String uri) {
    // parse uri
    // example: https://api.fujiwarahaji.me/v2/music?id=3525
    String shortened = uri.substring(28); // example: v2/music?id=3525
    String[] splitWithSlash = shortened.split("/"); // example: [v2] [music?id=3525]
    log.info("used api version: {}", splitWithSlash[0]);
    String[] splitWithQuestionMark = splitWithSlash[1].split("\\?"); // example: [music] [id=3525]
    log.info("used api endpoint: {}", splitWithQuestionMark[0]);
    Map<String, String> queries = mapFromPlainText(splitWithQuestionMark[1]);
    // TODO: we will need more implementation after completing implementation for builder classes
    return EndPoint.createEmpty(); // FIXME: replace this temporal return to applicable one
  }

  @Nonnull
  static Map<String, String> mapFromPlainText(String plain) {
    Map<String, String> result = new LinkedHashMap<>();
    String[] splitWithAmpersand = plain.split("&");
    Arrays.stream(splitWithAmpersand)
        .forEachOrdered(str -> {
          String[] splitWithEqual = str.split("=");
          result.put(splitWithEqual[0], splitWithEqual[1]);
        });
    return result;
  }

  @Nonnull
  @CheckReturnValue
  public static List<ListEndPoint> generateListEndPointResponse(JsonNode rawResponse) {
    long start = System.currentTimeMillis();
    // check for potentially null access
    if (rawResponse == null) {
      throw new IllegalArgumentException(getMessageNullRawResponseNotAllowedMessage());
    }
    log.debug("attempt to parse data");
    List<ListEndPoint> result = new ArrayList<>();
    Iterator<JsonNode> iteration = rawResponse.elements();
    // adding elements
    while (iteration.hasNext()) {
      JsonNode tmp = iteration.next();
      boolean isMusicType = false;
      boolean isIdolType = false;
      // with idol type query it doesn't have the type parameter in the response,
      // so we locally check whether type parameter exists or not
      if (tmp.get("type") != null) {
        isMusicType = tmp.get("type").asText().equals("music");
      } else {
        isIdolType = true;
      }
      ListEndPoint eachInstance = ListEndPointImpl.createInstance(
          tmp.get("name").asText(),
          isIdolType ? "idol" : tmp.get("type").asText(),
          tmp.get(isMusicType ? "song_id" : "tax_id").asInt(),
          tmp.get("link").asText(),
          tmp.get("api").asText(),
          // music_type will appear when type is music
          isMusicType ? tmp.get("music_type").asText() : null,
          // live will appear when type is live
          (!isIdolType && tmp.get("type").asText().equals("live")) ? tmp.get("date").asText()
              : null,
          // these four params will appear when type is idol
          isIdolType ? tmp.get("production").asText() : null,
          isIdolType ? tmp.get("kana").asText() : null,
          isIdolType ? tmp.get("cv").asText() : null,
          isIdolType ? tmp.get("cvkana").asText() : null
      );
      log.debug("listEndPoint response instance has been created. : {}", eachInstance);
      result.add(eachInstance);
    }
    log.debug("list data parse completed. took {} ms", (System.currentTimeMillis() - start));
    return result;
  }

  @CheckReturnValue
  public static TaxEndPoint generateTaxEndPointResponse(JsonNode rawResponse) {
    long start = System.currentTimeMillis();
    if (rawResponse == null) {
      throw new IllegalArgumentException(getMessageNullRawResponseNotAllowedMessage());
    }
    log.debug("Attempt to parse tax data...");
    // create some subclass instances
    // there are "idol", "unit", "live", "disc" "lyrics", "arrange", "composer" types
    final String type = rawResponse.get("type").asText();
    EndPoint lyrics = null;
    EndPoint composer = null;
    EndPoint arrange = null;
    List<Music> song = new ArrayList<>();
    switch (type) {
      case "idol":

    }
    JsonNode songNode = Objects.requireNonNull(rawResponse.get("song"));
    // parsing member parameter in another thread
    // TODO: implement method for creating thread factory
    CompletableFuture<List<Member>> memberParseFuture = CompletableFuture.supplyAsync(
        () -> parseMemberArray(rawResponse.get("member"));
    for (JsonNode tmp : songNode) {
      List<Unit> unit = new ArrayList<>();
      List<Member> member = new ArrayList<>();
      CompletableFuture<List<Unit>> unitParseFuture = null;
      CompletableFuture<List<Member>> unitMemberFuture = CompletableFuture.supplyAsync(
          () -> InternalUtils.parseMemberArray(rawResponse.get("member")));
      // unit parsing with parallel
      unitParseFuture = CompletableFuture.supplyAsync(() -> {
        // get raw node data from original data
        JsonNode unitArrayNode = tmp.get("unit");
        List<Unit> result = new ArrayList<>();

        // if type is not "disc" or "live", we don't need to do nothing.
        if (!type.equals("disc") || !type.equals("live")) {
          return result;
        }

        // looping for parsing
        for (JsonNode unitNode : unitArrayNode) {

          // since member of unit is array type, we need to create loop again for parsing member of unit
          List<Member> unitMember = new ArrayList<>();

          for (JsonNode unitMemberNode : unitNode.get("member")) {
            Member eachMember = Member.createInstance(
                unitMemberNode.get("name").asText(),
                unitMemberNode.get("type").asText(),
                unitMemberNode.get("tax_id").asInt(),
                unitMemberNode.get("link").asText(),
                unitMemberNode.get("api").asText(),
                unitMemberNode.get("production").asText(),
                unitMemberNode.get("cv").asText()
            );

            // add member data
            unitMember.add(eachMember);
          }

          // create unit data
          Unit each = Unit.createInstance(
              unitNode.get("name").asText(),
              unitNode.get("type").asText(),
              unitNode.get("tax_id").asInt(),
              unitNode.get("link").asText(),
              unitNode.get("api").asText(),
              unitMember
          );

          // add unit data
          result.add(each);
        }
        // return the result
        return result;
      });
      Music music = Music.createInstance(
          tmp.get("name").asText(),
          tmp.get("type").asText(),
          tmp.get("music_type").asText(),
          tmp.get("song_id").asInt(),
          tmp.get("link").asText(),
          tmp.get("api").asText(),
          type.equals("live") ? tmp.get("song_text").asText() : null,
          unitParseFuture.join(),
          type.equals("disc") ? parseMemberSoloArray(tmp.get("member"))
              : type.equals("live") ? parseMemberArray(tmp.get("member")) : null,
          type.equals("live") ? tmp.get("member_text").asText() : null,
          type.equals("idol") ? tmp.get("solo").asBoolean() : null
      );
      log.debug("parse song data: {}", music);
      song.add(music);
    }
    // return
    final boolean isIdolType = type.equals("idol");
    final boolean isLiveType = type.equals("live");
    TaxEndPoint result = TaxEndPointImpl.createInstance(
        rawResponse.get("name").asText(),
        type,
        rawResponse.get("tax_id").asInt(),
        rawResponse.get("link").asText(),
        rawResponse.get("api").asText(),
        (isIdolType || type.equals("unit")) ? rawResponse.get("kana").asText() : null,
        isIdolType ? rawResponse.get("cv").asText() : null,
        isIdolType ? rawResponse.get("cvkana").asText() : null,
        isIdolType ? rawResponse.get("production").asText() : null,
        isLiveType ? rawResponse.get("date").asText() : null,
        isLiveType ? rawResponse.get("place").asText() : null,
        memberParseFuture.join(),
        isLiveType ? rawResponse.get("setList").asBoolean() : null,
        lyrics,
        composer,
        arrange,
        song
    );
    log.debug("tax data parsing complete. took {} ms", (System.currentTimeMillis() - start));
    log.debug("parse tax endpoint data: {}", result);
    return result;
  }

  @CheckReturnValue
  public static List<Member> parseMemberArray(JsonNode memberNode) {
    long start = System.currentTimeMillis();
    List<Member> result = new ArrayList<>();
    for (JsonNode each : memberNode) {
      Member memberData = MemberSolo.createInstance(
          each.get("name").asText(),
          each.get("type").asText(),
          each.get("tax_id").asInt(),
          each.get("link").asText(),
          each.get("api").asText(),
          each.get("production").asText(),
          each.get("cv").asText(),
          each.get("solo") != null ? each.get("solo").asBoolean() : null
      );
      log.debug("member data has been construct: {}", memberData);
      result.add(memberData);
    }
    log.debug("member data parsing complete. took {} ms", (System.currentTimeMillis() - start));
    return result;
  }

  @CheckReturnValue
  public static List<MemberSolo> parseMemberSoloArray(JsonNode memberNode) {
    long start = System.currentTimeMillis();
    List<MemberSolo> result = new ArrayList<>();
    for (JsonNode each : memberNode) {
      MemberSolo memberData = MemberSolo.createInstance(
          each.get("name").asText(),
          each.get("type").asText(),
          each.get("tax_id").asInt(),
          each.get("link").asText(),
          each.get("api").asText(),
          each.get("production").asText(),
          each.get("cv").asText(),
          each.get("solo").asBoolean()
      );
      log.debug("member data has been construct: {}", memberData);
      result.add(memberData);
    }
    log.debug("member data parsing complete. took {} ms", (System.currentTimeMillis() - start));
    return result;
  }
}
