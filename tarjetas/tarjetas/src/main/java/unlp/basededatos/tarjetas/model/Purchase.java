package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import unlp.basededatos.tarjetas.enums.PurchaseType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY,
		property = "purchaseType",
		visible = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = MonthlyPayments.class, name = "monthly"),
		@JsonSubTypes.Type(value = CashPayment.class, name = "cash"),
})

@Entity
@Table(name = "Purchase")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    
    @Column(name = "purchaseType")
    private PurchaseType purchaseType;

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
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
}