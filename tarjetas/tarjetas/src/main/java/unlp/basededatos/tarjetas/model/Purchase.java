package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "REF_TYPE")
@Table(name = "Purchase")
public abstract class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_purchase")
    private Long id;

    @Column(name = "paymentVoucher")
    private String paymentVoucher;

    @Column(name = "store")
    private String store;

    @Column(name = "cuitStore")
    private String cuitStore;

    @Column(name = "amount")
    private float amount;

    @Column(name = "finalAmount")
    private float finalAmount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name="PromotionID")
    private Promotion promotion;

    public Purchase(String paymentVoucher, String store, String cuitStore, float amount, float finalAmount) {
        this.paymentVoucher = paymentVoucher;
        this.store = store;
        this.cuitStore = cuitStore;
        this.amount = amount;
        this.finalAmount = finalAmount;
    }

    public Purchase(){}

    public String getPaymentVoucher() {
        return paymentVoucher;
    }

    public void setPaymentVoucher(String paymentVoucher) {
        this.paymentVoucher = paymentVoucher;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCuitStore() {
        return cuitStore;
    }

    public void setCuitStore(String cuitStore) {
        this.cuitStore = cuitStore;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(float finalAmount) {
        this.finalAmount = finalAmount;
    }
}