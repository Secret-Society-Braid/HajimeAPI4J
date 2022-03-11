package HajimeAPI4J.api.util.parse;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.api.HajimeAPIBuilder;
import HajimeAPI4J.api.util.datatype.Member;
import HajimeAPI4J.api.util.datatype.MemberSolo;
import HajimeAPI4J.api.util.datatype.Unit;
import HajimeAPI4J.api.util.internal.IParse;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class ParseMusic implements IParse {

    private Logger logger = LoggerFactory.getLogger(ParseMusic.class);

    private JsonNode node;

    public ParseMusic(JsonNode node) {
        this.node = node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return node.get("name").asText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return node.get("type").asText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInternalId() {
        return node.get("music_id").asText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLink() {
        return node.get("link").asText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getApi() {
        return node.get("api").asText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonNode getJsonNode() {
        return node;
    }

    /**
     * 作詞者情報をList形式で返します。
     * 配列の長さが1の場合でも、配列を返します。
     *
     * @return 作詞者情報
     */
    public List<Map<String, String>> getLyric() {
        List<Map<String, String>> resList = new ArrayList<>();
        JsonNode lyricNode = node.get("lyric");
        if(lyricNode.isArray()) {
            for(JsonNode node : lyricNode) {
                Map<String, String> resMap = new HashMap<>();
                resMap.put("name", node.get("name").asText());
                resMap.put("link", node.get("link").asText());
                resMap.put("api", node.get("api").asText());
                resMap.put("tax_id", node.get("tax_id").asText());
                resMap.put("type", node.get("type").asText());
                resList.add(resMap);
            }
        } else {
            /* This won't be happened. */
            throw new RuntimeException("Lyric data must be array.");
        }
        return resList;
    }

    /**
     * 作曲者情報をList形式で返します。
     * 配列の長さが1の場合でも、配列を返します。
     *
     * @return 作曲者情報
     */
    public List<Map<String, String>> getComposer() {
        List<Map<String, String>> resList = new ArrayList<>();
        JsonNode compositionNode = node.get("composer");
        if(compositionNode.isArray()) {
            for(JsonNode node : compositionNode) {
                Map<String, String> resMap = new HashMap<>();
                resMap.put("name", node.get("name").asText());
                resMap.put("link", node.get("link").asText());
                resMap.put("api", node.get("api").asText());
                resMap.put("tax_id", node.get("tax_id").asText());
                resMap.put("type", node.get("type").asText());
                resList.add(resMap);
            }
        } else {
            /* This won't be happened. */
            throw new RuntimeException("composer data must be array.");
        }
        return resList;
    }

    /**
     * 編曲者情報をList形式で返します。
     * 配列の長さが1の場合でも、配列を返します。
     *
     * @return 編曲者情報
     */
    public List<Map<String, String>> getArrange() {
        List<Map<String, String>> resList = new ArrayList<>();
        JsonNode arrangementNode = node.get("arrange");
        if(arrangementNode.isArray()) {
            for(JsonNode node : arrangementNode) {
                Map<String, String> resMap = new HashMap<>();
                resMap.put("name", node.get("name").asText());
                resMap.put("link", node.get("link").asText());
                resMap.put("api", node.get("api").asText());
                resMap.put("tax_id", node.get("tax_id").asText());
                resMap.put("type", node.get("type").asText());
                resList.add(resMap);
            }
        } else {
            /* This won't be happened. */
            throw new RuntimeException("arranger data must be array.");
        }
        return resList;
    }

    /**
     * 歌詞サイトのURLを返します。
     *
     * @return 歌詞サイトのURL
     */
    public String getLyricURL() {
        return node.get("lyrics_url").asText();
    }

    /**
     * CD、配信、ゲーム内出演メンバー情報をList形式で返します。
     * 使用しているmember型は {@link Member} にて実装されている型です。
     *
     * @return CD、配信、ゲーム内出演メンバー情報
     */
    public List<Member> getMember() {
        JsonNode memberNode = node.get("member");
        List<Member> resList = new ArrayList<>();
        if(memberNode.isArray()) {
            for(JsonNode node : memberNode) {
                Member member = new Member();
                member.setName(node.get("name").asText());
                member.setLink(node.get("link").asText());
                member.setApi(node.get("api").asText());
                member.setTax_id(node.get("tax_id").asInt());
                member.setType(node.get("type").asText());
                member.setProduction(node.get("production").asText());
                member.setCv(node.get("cv").asText());
                resList.add(member);
            }
        } else {
            /* This won't be happened. */
            throw new RuntimeException("member data must be array.");
        }
        return resList;
    }

    static class Disc {
        private String name;
        private String type;
        private int tax_id;
        private String link;
        private String api;
        private Unit unit;
        private MemberSolo member;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTax_id() {
            return tax_id;
        }

        public void setTax_id(int tax_id) {
            this.tax_id = tax_id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public Unit getUnit() {
            return unit;
        }

        public void setUnit(Unit unit) {
            this.unit = unit;
        }

        public MemberSolo getMember() {
            return member;
        }

        public void setMember(MemberSolo member) {
            this.member = member;
        }
    }

    /**
     * CD情報を配列で返します。
     *
     * @return CD情報
     */
    public List<Disc> getDisc() {
        List<Disc> resList = new ArrayList<>();
        JsonNode discNode = node.get("disc");
        if(discNode.isArray()) {
            for(JsonNode node : discNode) {
                Disc disc = new Disc();
                disc.setName(node.get("name").asText());
                disc.setType(node.get("type").asText());
                disc.setTax_id(node.get("tax_id").asInt());
                disc.setLink(node.get("link").asText());
                disc.setApi(node.get("api").asText());
                disc.setUnit(new Unit(node.get("unit")));
                disc.setMember(new MemberSolo(node.get("member")));
                resList.add(disc);
            }
        }
        return resList;
    }

    static class Live {
        private String name;
        private String type;
        private int tax_id;
        private String link;
        private String api;
        private String date;
        private String place;
        private Unit unit;
        private Member member;

        private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTax_id() {
            return tax_id;
        }

        public void setTax_id(int tax_id) {
            this.tax_id = tax_id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public Unit getUnit() {
            return unit;
        }

        public void setUnit(Unit unit) {
            this.unit = unit;
        }

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }
    }

    /**
     * ライブ情報を配列で返します。
     *
     * @return ライブ情報
     */
    public List<Live> getLive() {
        JsonNode liveNode = node.get("live");
        List<Live> resList = new ArrayList<>();
        if(liveNode.isArray()) {
            for( JsonNode node : liveNode) {
                Live tmp = new Live();
                tmp.setName(node.get("name").asText());
                tmp.setType(node.get("type").asText());
                tmp.setTax_id(node.get("tax_id").asInt());
                tmp.setLink(node.get("link").asText());
                tmp.setApi(node.get("api").asText());
                tmp.setDate(node.get("date").asText());
                tmp.setPlace(node.get("place").asText());
                tmp.setUnit(new Unit(node.get("unit")));
                tmp.setMember(new MemberSolo(node.get("member")));
                resList.add(tmp);
            }
        } else {
            throw new RuntimeException("This won't be happened.");
        }
        return resList;
    }

    /**
     * {[@inheritDoc]}
     *
     * この情報では返されるインスタンスは元の情報取得時のものと同じになります。
     */
    @Override
    public HajimeAPI4J getAPIInstance() {
        String id = getInternalId();
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.MUSIC);
        builder.addParameter(HajimeAPI4J.Music_Params.ID, id);
        return builder.build();
    }
}
