package hajimeapi4j.internal.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayResponseRestActionTest {

  static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  @Disabled("This test is for checking the correctness of the mapping.")
  void mappingTest() {

    List<User> users = Collections.emptyList();

    try {
      final URL url = new URL("https://jsonplaceholder.typicode.com/users");
      users = MAPPER.readValue(url, MAPPER.getTypeFactory().constructCollectionLikeType(List.class, User.class));
    } catch (IOException e) {
      fail(e);
    }

    assertFalse(users.isEmpty());

    assertEquals(10, users.size());
    User first = users.get(0);

    assertEquals(1, first.getId());
    assertEquals("Leanne Graham", first.getName());
    assertEquals("Bret", first.getUsername());
    assertEquals("Romaguera-Crona", first.getCompany().getName());
  }

  static class Geo {
    private String lat;
    private String lng;

    public Geo() {
    }

    public String getLat() {
      return this.lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

    public String getLng() {
      return this.lng;
    }

    public void setLng(String lng) {
      this.lng = lng;
    }

    public boolean equals(final Object o) {
      if (o == this) return true;
      if (!(o instanceof Geo)) return false;
      final Geo other = (Geo) o;
      if (!other.canEqual((Object) this)) return false;
      final Object this$lat = this.getLat();
      final Object other$lat = other.getLat();
      if (this$lat == null ? other$lat != null : !this$lat.equals(other$lat)) return false;
      final Object this$lng = this.getLng();
      final Object other$lng = other.getLng();
      if (this$lng == null ? other$lng != null : !this$lng.equals(other$lng)) return false;
      return true;
    }

    protected boolean canEqual(final Object other) {
      return other instanceof Geo;
    }

    public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final Object $lat = this.getLat();
      result = result * PRIME + ($lat == null ? 43 : $lat.hashCode());
      final Object $lng = this.getLng();
      result = result * PRIME + ($lng == null ? 43 : $lng.hashCode());
      return result;
    }

    public String toString() {
      return "ArrayResponseRestActionTest.Geo(lat=" + this.getLat() + ", lng=" + this.getLng() + ")";
    }
  }

  static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address() {
    }

    public String getStreet() {
      return this.street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getSuite() {
      return this.suite;
    }

    public void setSuite(String suite) {
      this.suite = suite;
    }

    public String getCity() {
      return this.city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getZipcode() {
      return this.zipcode;
    }

    public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
    }

    public Geo getGeo() {
      return this.geo;
    }

    public void setGeo(Geo geo) {
      this.geo = geo;
    }

    public boolean equals(final Object o) {
      if (o == this) return true;
      if (!(o instanceof Address)) return false;
      final Address other = (Address) o;
      if (!other.canEqual((Object) this)) return false;
      final Object this$street = this.getStreet();
      final Object other$street = other.getStreet();
      if (this$street == null ? other$street != null : !this$street.equals(other$street)) return false;
      final Object this$suite = this.getSuite();
      final Object other$suite = other.getSuite();
      if (this$suite == null ? other$suite != null : !this$suite.equals(other$suite)) return false;
      final Object this$city = this.getCity();
      final Object other$city = other.getCity();
      if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
      final Object this$zipcode = this.getZipcode();
      final Object other$zipcode = other.getZipcode();
      if (this$zipcode == null ? other$zipcode != null : !this$zipcode.equals(other$zipcode)) return false;
      final Object this$geo = this.getGeo();
      final Object other$geo = other.getGeo();
      if (this$geo == null ? other$geo != null : !this$geo.equals(other$geo)) return false;
      return true;
    }

    protected boolean canEqual(final Object other) {
      return other instanceof Address;
    }

    public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final Object $street = this.getStreet();
      result = result * PRIME + ($street == null ? 43 : $street.hashCode());
      final Object $suite = this.getSuite();
      result = result * PRIME + ($suite == null ? 43 : $suite.hashCode());
      final Object $city = this.getCity();
      result = result * PRIME + ($city == null ? 43 : $city.hashCode());
      final Object $zipcode = this.getZipcode();
      result = result * PRIME + ($zipcode == null ? 43 : $zipcode.hashCode());
      final Object $geo = this.getGeo();
      result = result * PRIME + ($geo == null ? 43 : $geo.hashCode());
      return result;
    }

    public String toString() {
      return "ArrayResponseRestActionTest.Address(street=" + this.getStreet() + ", suite=" + this.getSuite() + ", city=" + this.getCity() + ", zipcode=" + this.getZipcode() + ", geo=" + this.getGeo() + ")";
    }
  }

  static class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getCatchPhrase() {
      return this.catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
      this.catchPhrase = catchPhrase;
    }

    public String getBs() {
      return this.bs;
    }

    public void setBs(String bs) {
      this.bs = bs;
    }

    public boolean equals(final Object o) {
      if (o == this) return true;
      if (!(o instanceof Company)) return false;
      final Company other = (Company) o;
      if (!other.canEqual((Object) this)) return false;
      final Object this$name = this.getName();
      final Object other$name = other.getName();
      if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
      final Object this$catchPhrase = this.getCatchPhrase();
      final Object other$catchPhrase = other.getCatchPhrase();
      if (this$catchPhrase == null ? other$catchPhrase != null : !this$catchPhrase.equals(other$catchPhrase))
        return false;
      final Object this$bs = this.getBs();
      final Object other$bs = other.getBs();
      if (this$bs == null ? other$bs != null : !this$bs.equals(other$bs)) return false;
      return true;
    }

    protected boolean canEqual(final Object other) {
      return other instanceof Company;
    }

    public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final Object $name = this.getName();
      result = result * PRIME + ($name == null ? 43 : $name.hashCode());
      final Object $catchPhrase = this.getCatchPhrase();
      result = result * PRIME + ($catchPhrase == null ? 43 : $catchPhrase.hashCode());
      final Object $bs = this.getBs();
      result = result * PRIME + ($bs == null ? 43 : $bs.hashCode());
      return result;
    }

    public String toString() {
      return "ArrayResponseRestActionTest.Company(name=" + this.getName() + ", catchPhrase=" + this.getCatchPhrase() + ", bs=" + this.getBs() + ")";
    }
  }

  static class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User() {
    }

    public int getId() {
      return this.id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUsername() {
      return this.username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public Address getAddress() {
      return this.address;
    }

    public void setAddress(Address address) {
      this.address = address;
    }

    public String getPhone() {
      return this.phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getWebsite() {
      return this.website;
    }

    public void setWebsite(String website) {
      this.website = website;
    }

    public Company getCompany() {
      return this.company;
    }

    public void setCompany(Company company) {
      this.company = company;
    }

    public boolean equals(final Object o) {
      if (o == this) return true;
      if (!(o instanceof User)) return false;
      final User other = (User) o;
      if (!other.canEqual((Object) this)) return false;
      if (this.getId() != other.getId()) return false;
      final Object this$name = this.getName();
      final Object other$name = other.getName();
      if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
      final Object this$username = this.getUsername();
      final Object other$username = other.getUsername();
      if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
      final Object this$email = this.getEmail();
      final Object other$email = other.getEmail();
      if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
      final Object this$address = this.getAddress();
      final Object other$address = other.getAddress();
      if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
      final Object this$phone = this.getPhone();
      final Object other$phone = other.getPhone();
      if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
      final Object this$website = this.getWebsite();
      final Object other$website = other.getWebsite();
      if (this$website == null ? other$website != null : !this$website.equals(other$website)) return false;
      final Object this$company = this.getCompany();
      final Object other$company = other.getCompany();
      if (this$company == null ? other$company != null : !this$company.equals(other$company)) return false;
      return true;
    }

    protected boolean canEqual(final Object other) {
      return other instanceof User;
    }

    public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      result = result * PRIME + this.getId();
      final Object $name = this.getName();
      result = result * PRIME + ($name == null ? 43 : $name.hashCode());
      final Object $username = this.getUsername();
      result = result * PRIME + ($username == null ? 43 : $username.hashCode());
      final Object $email = this.getEmail();
      result = result * PRIME + ($email == null ? 43 : $email.hashCode());
      final Object $address = this.getAddress();
      result = result * PRIME + ($address == null ? 43 : $address.hashCode());
      final Object $phone = this.getPhone();
      result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
      final Object $website = this.getWebsite();
      result = result * PRIME + ($website == null ? 43 : $website.hashCode());
      final Object $company = this.getCompany();
      result = result * PRIME + ($company == null ? 43 : $company.hashCode());
      return result;
    }

    public String toString() {
      return "ArrayResponseRestActionTest.User(id=" + this.getId() + ", name=" + this.getName() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", address=" + this.getAddress() + ", phone=" + this.getPhone() + ", website=" + this.getWebsite() + ", company=" + this.getCompany() + ")";
    }
  }
}
