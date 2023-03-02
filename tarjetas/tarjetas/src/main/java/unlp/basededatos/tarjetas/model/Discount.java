package unlp.basededatos.tarjetas.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
public class Discount extends Promotion{
    private float discountPercentage;
    private float priceCap;

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