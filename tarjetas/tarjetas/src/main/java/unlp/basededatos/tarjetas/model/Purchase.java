package unlp.basededatos.tarjetas.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "payment_voucher")
    private String paymentVoucher;

    @Column(name = "store")
    private String store;

    @Column(name = "cuit_store")
    private String cuitStore;

    @Column(name = "amount")
    private float amount;

    @Column(name = "final_amount")
    private float finalAmount;

    //@ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "purchase_promotion",
            joinColumns = { @JoinColumn(name = "id_purchase") },
            inverseJoinColumns = { @JoinColumn(name = "id_promotion") })
    @JsonIgnore
    private List<Promotion> promotions;
    
    //Una compra tiene asociada una sola tarjeta
    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name="id_card")
    private Card card;
    
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

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}


}