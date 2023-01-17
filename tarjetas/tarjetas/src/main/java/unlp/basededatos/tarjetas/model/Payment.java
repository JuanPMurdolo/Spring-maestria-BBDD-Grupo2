package unlp.basededatos.tarjetas.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_payment")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "firstExpiration")
    private Date firstExpiration;

    @Column(name = "secondExpiration")
    private Date secondExpiration;

    @Column(name = "purchase")
    private float purchase;

    @Column(name = "totalPrice")
    private float totalPrice;

    public Payment(String code, String month, String year, Date firstExpiration, Date secondExpiration, float purchase, float totalPrice) {
        this.code = code;
        this.month = month;
        this.year = year;
        this.firstExpiration = firstExpiration;
        this.secondExpiration = secondExpiration;
        this.purchase = purchase;
        this.totalPrice = totalPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getFirstExpiration() {
        return firstExpiration;
    }

    public void setFirstExpiration(Date firstExpiration) {
        this.firstExpiration = firstExpiration;
    }

    public Date getSecondExpiration() {
        return secondExpiration;
    }

    public void setSecondExpiration(Date secondExpiration) {
        this.secondExpiration = secondExpiration;
    }

    public float getPurchase() {
        return purchase;
    }

    public void setPurchase(float purchase) {
        this.purchase = purchase;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}