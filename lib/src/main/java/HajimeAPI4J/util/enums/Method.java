package hajimeapi4j.util.enums;

public enum Method {

  LIST,
  TAX,
  MUSIC;

  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
