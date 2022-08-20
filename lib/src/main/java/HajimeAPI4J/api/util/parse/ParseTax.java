package hajimeapi4j.api.util.parse;

import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;

import hajimeapi4j.api.HajimeAPI4J;
import hajimeapi4j.api.HajimeAPIBuilder;
import hajimeapi4j.api.util.internal.IParse;

public class ParseTax implements IParse {

    private final JsonNode node;

    public ParseTax(JsonNode node) {
        this.node = node;
    }

    @Override
    public String getName() {
        return node.get("name").asText();
    }

    @Override
    public String getType() {
        return node.get("type").asText();
    }

    @Override
    public String getInternalId() {
        return node.get("tax_id").asText();
    }

    @Override
    public String getLink() {
        return node.get("link").asText();
    }

    @Override
    public String getApi() {
        return node.get("api").asText();
    }

    @Override
    public JsonNode getJsonNode() {
        return node;
    }

    /**
     * {@inheritDoc}
     *
     * この情報では返されるインスタンスは元の情報取得時のものと同じになります。
     */
    @Override
    public HajimeAPI4J getAPIInstance() {
        String id = getInternalId();
        HajimeAPIBuilder builder = HajimeAPIBuilder.createDefault(HajimeAPI4J.Token.TAX);
        builder.addParameter(HajimeAPI4J.Tax_Params.ID, id);
        return builder.build();
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ParseTax) {
            ParseTax anotherList = (ParseTax) another;
            return this.node.equals(anotherList.node);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}
