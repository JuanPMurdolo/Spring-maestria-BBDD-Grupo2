package unlp.basededatos.tarjetas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CashPayment")
public class CashPayment extends Purchase{

    @Column(name = "storeDiscount")
    private float storeDiscount;

    public CashPayment(String paymentVoucher, String store, String cuitStore, float amount, float finalAmount, float storeDiscount) {
        super(paymentVoucher, store, cuitStore, amount, finalAmount);
        this.storeDiscount = storeDiscount;
    }

    public float getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(float storeDiscount) {
        this.storeDiscount = storeDiscount;
    }
}