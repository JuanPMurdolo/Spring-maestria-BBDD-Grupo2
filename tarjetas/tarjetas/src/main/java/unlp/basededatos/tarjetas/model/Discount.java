package unlp.basededatos.tarjetas.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Discount")
public class Discount extends Promotion{

    @Column(name = "discount_percentage")
    private float discountPercentage;

    @Column(name = "price_cap")
    private float priceCap;

    @Column(name = "only_cash")
    private boolean onlyCash;

    public Discount(String code, String promotionTitle, String nameStore, String cuitStore, Date validityStartDate, Date validityEndDate, String comments, float discountPercentage, float priceCap, boolean onlyCash) {
        super(code, promotionTitle, nameStore, cuitStore, validityStartDate, validityEndDate, comments);
        this.discountPercentage = discountPercentage;
        this.priceCap = priceCap;
        this.onlyCash = onlyCash;
    }

    public Discount(){}

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