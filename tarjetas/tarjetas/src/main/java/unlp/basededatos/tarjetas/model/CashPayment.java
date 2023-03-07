package unlp.basededatos.tarjetas.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cashpayment")
public class CashPayment extends Purchase{
    private float storeDiscount;
  
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