package HajimeAPI4J.responses.wrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.responses.interfaces.ResponseAdapter;

/**
 * ふじわらはじめAPIで使用されるレスポンス「live」の宣言です。
 * この宣言には {@link HajimeAPI4J.responses.interfaces.ResponseAdapter 独自アダプター} を継承しています。
 * 
 * @author Ranfa
 * @since 1.0.0
 */
public class Live extends ResponseAdapter {
    
    // Declare field
    private String name;
    private String type;
    private int taxId;
    private String link;
    private String api;
    private String date;
    private String place;
    private Unit unit;
    private Member member;

    // getter

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTaxId() {
        return taxId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLink() {
        return link;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getApi() {
        return api;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlace() {
        return place;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Unit getUnit() {
        return unit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Member getMember() {
        return member;
    }

    // setter

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApi(String api) {
        this.api = api;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * パラメータ「date」を取得します。
     * @return パラメータ「date」の値
     * @throws ParseException API側の変更などで日付フォーマットが変わり、parseできない場合
     */
    public Date getDateAsDate() throws ParseException {
        SimpleDateFormat tmpFormat = new SimpleDateFormat(HajimeAPI4J.DATE_FORMAT_STRING);
        return tmpFormat.parse(date);
    }

    /**
     * パラメータ「date」をセットします。
     * @param date セットする date
     */
    public void setDate(Date date) {
        SimpleDateFormat tmpFormat = new SimpleDateFormat(HajimeAPI4J.DATE_FORMAT_STRING);
        this.date = tmpFormat.format(date);
    }

}
