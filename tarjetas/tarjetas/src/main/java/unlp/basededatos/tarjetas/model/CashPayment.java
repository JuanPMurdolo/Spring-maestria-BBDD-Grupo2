package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Cash")
public class CashPayment extends Purchase{

    @Column(name = "storeDiscount")
    private float storeDiscount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_payment")
    private Payment payment;
    
    public CashPayment(String paymentVoucher, String store, String cuitStore, float amount, float finalAmount, float storeDiscount) {
        super(paymentVoucher, store, cuitStore, amount, finalAmount);
        this.storeDiscount = storeDiscount;
    }
    public CashPayment(){
        super();
    }

    public float getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(float storeDiscount) {
        this.storeDiscount = storeDiscount;
    }
}