package unlp.basededatos.tarjetas.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Card {
    @Id
    private Long id;
    private String number;

    private String ccv;

    private String cardholderNameInCard;

    private Date since;

    private Date expirationDate;

	//bi-directional many-to-one association to Bank
    // muchas tarjetas solo puede pertenecer a un Banco (bank)
    // MUCHOS a uno
    private Bank bank;

    //Una tarjeta puede tener solo un owner(CardHolder)
    //Muchos a uno
    private CardHolder owner;
    
    public Card(String number, String ccv, String cardholderNameInCard, Date since, Date expirationDate) {
        this.number = number;
        this.ccv = ccv;
        this.cardholderNameInCard = cardholderNameInCard;
        this.since = since;
        this.expirationDate = expirationDate;
    }

	public Card() {

    }
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getCardholderNameInCard() {
        return cardholderNameInCard;
    }

    public void setCardholderNameInCard(String cardholderNameInCard) {
        this.cardholderNameInCard = cardholderNameInCard;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
    
    public CardHolder getOwner() {
		return owner;
	}

	public void setOwner(CardHolder owner) {
		this.owner = owner;
	}

    
}
