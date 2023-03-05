package unlp.basededatos.tarjetas.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Bank {

    @MongoId
    private String id;


    private String name;
    private String cuit;
    private String address;

    private String telephone;

	// bi-directional many-to-one association to Card
    // un Banco puede tener muchas Tarjetas(Cards)
    // uno a MUCHOS
//    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
//	private List<Card> cards;


    //Un Banco puede tener muchos clientes (CardHolders)
    //Pero los clientes pueden pertenecer a uno o mas bancos
    //la relacion parece muchos a muchos
    @DBRef
    private List<CardHolder> cardHolders;


    //Y los bancos pueden tener 0 o muchas promociones

    private List<Promotion> promotions;
	
    public Bank(String name, String cuit, String address, String telephone) {
        this.name = name;
        this.cuit = cuit;
        this.address = address;
        this.telephone = telephone;
    }

    public Bank(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//	public List<Card> getCards() {
//		return cards;
//	}
//
//	public void setCards(List<Card> cards) {
//		this.cards = cards;
//	}
//    
//	
//	public void addCard(Card address) {
//		cards.add(address);
//	}
//	
//	public boolean hasCard(Card address) {
//		return cards.contains(address);
//	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CardHolder> getCardHolders() {
		return cardHolders;
	}

	public void setCardHolders(List<CardHolder> cardHolders) {
		this.cardHolders = cardHolders;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public void addPromotion(Promotion promotion) {
		promotions.add(promotion);
	}
	
	public boolean hasPromotion(Promotion promotion) {
		return promotions.contains(promotion);
	}
}
