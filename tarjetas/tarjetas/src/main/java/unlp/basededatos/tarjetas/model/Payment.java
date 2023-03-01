package unlp.basededatos.tarjetas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String month;

    private String year;

    @Column(name = "first_expiration")
    private Date firstExpiration;

    @Column(name = "second_expiration")
    private Date secondExpiration;

    private float purchase;

    @Column(name = "total_price")
    private float totalPrice;

    //falta la relacion e/Quota y Payment
    @JsonIgnore
    @OneToMany( cascade = CascadeType.MERGE, fetch = FetchType.EAGER )
    private List<Quota> quota;

    //Falta la relacion /CashPayment y Payment
	@OneToMany
	private List<CashPayment> cashpayment;
	
	public Payment(String code, String month, String year, Date firstExpiration, Date secondExpiration, float purchase, float totalPrice) {
        this.code = code;
        this.month = month;
        this.year = year;
        this.firstExpiration = firstExpiration;
        this.secondExpiration = secondExpiration;
        this.purchase = purchase;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CashPayment> getCashpayments() {
		return cashpayment;
	}

	public void setCashpayments(List<CashPayment> cashpayments) {
		this.cashpayment = cashpayments;
	}

    public Payment(){}

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

    public List<Quota> getQuotas() {
        return quota;
    }

    public void setQuotas(List<Quota> quotas) {
        this.quota = quotas;
    }
}