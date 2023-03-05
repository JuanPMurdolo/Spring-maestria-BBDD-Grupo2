package unlp.basededatos.tarjetas.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;
import unlp.basededatos.tarjetas.enums.PromotionType;

import java.util.Date;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.EXISTING_PROPERTY,
		property = "promoType",
		visible = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = Discount.class, name = "discount"),
		@JsonSubTypes.Type(value = Financing.class, name = "financing"),
})

@Document
public abstract class Promotion {
    @MongoId
    private String id;

    private String code;

    private String promotionTitle;

    private String nameStore;

    private String cuitStore;

    private Date validityStartDate;
    private Date validityEndDate;
    private String comments;
    private PromotionType promoType;
    private Boolean borrado = false;

    //Una promocion puede pertenecer a muchos bancos
    //Y los bancos pueden tener 0 o muchas promociones
	/*
	 * @ManyToMany()
	 * 
	 * @JoinTable( name = "Bank_Promotions", joinColumns = @JoinColumn(name =
	 * "bankID"), inverseJoinColumns = @JoinColumn(name = "promotionID") ) private
	 * List<Bank> banks;
	 */
    @DocumentReference(lazy = true)
    private List<Purchase> purchase;


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}

	public Promotion(String code, String promotionTitle, String nameStore, String cuitStore, Date validityStartDate, Date validityEndDate, String comments) {
        this.code = code;
        this.promotionTitle = promotionTitle;
        this.nameStore = nameStore;
        this.cuitStore = cuitStore;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.comments = comments;
    }

    public Promotion(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getCuitStore() {
        return cuitStore;
    }

    public void setCuitStore(String cuitStore) {
        this.cuitStore = cuitStore;
    }

    public Date getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(Date validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    public Date getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(Date validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    public String getComments() {
        return comments;
    }
    
	public void setComments(String comments) {
        this.comments = comments;
    }
    public PromotionType getPromoType() {
		return promoType;
	}

	public void setPromoType(PromotionType promoType) {
		this.promoType = promoType;
	}

	public Boolean getBorrado() {
		return borrado;
	}

	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}


}