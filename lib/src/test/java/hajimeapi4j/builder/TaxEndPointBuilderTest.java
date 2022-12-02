package hajimeapi4j.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import hajimeapi4j.api.endpoint.TaxEndPoint;
import hajimeapi4j.internal.builder.TaxEndPointBuilder;
import hajimeapi4j.util.enums.TaxParameter.Order;
import hajimeapi4j.util.enums.TaxParameter.OrderBy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TaxEndPointBuilderTest {

  @Test
  @Disabled("to prevent from calling massive API")
  void withIdTest() {
    TaxEndPointBuilder builder = TaxEndPointBuilder.createWithId(1665);
    builder.setLimit(5)
        .setOrder(Order.ASCENDING)
        .setOrderBy(OrderBy.NAME);
    TaxEndPoint endPoint = builder.build().complete();

    assertNotNull(endPoint);

    assertEquals(1665, endPoint.getTaxId());

  }
}
