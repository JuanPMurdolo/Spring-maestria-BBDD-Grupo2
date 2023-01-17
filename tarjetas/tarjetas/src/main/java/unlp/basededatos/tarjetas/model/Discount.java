package unlp.basededatos.tarjetas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Discount")
public class Discount extends Promotion{

    @Column(name = "discountPercentage")
    private float discountPercentage;

    @Column(name = "priceCap")
    private float priceCap;

    @Column(name = "onlyCash")
    private boolean onlyCash;

    public Discount(String code, String promotionTitle, String nameStore, String cuitStore, Date validityStartDate, Date validityEndDate, String comments, float discountPercentage, float priceCap, boolean onlyCash) {
        super(code, promotionTitle, nameStore, cuitStore, validityStartDate, validityEndDate, comments);
        this.discountPercentage = discountPercentage;
        this.priceCap = priceCap;
        this.onlyCash = onlyCash;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getPriceCap() {
        return priceCap;
    }

    public void setPriceCap(float priceCap) {
        this.priceCap = priceCap;
    }

    public boolean isOnlyCash() {
        return onlyCash;
    }

    public void setOnlyCash(boolean onlyCash) {
        this.onlyCash = onlyCash;
    }
}