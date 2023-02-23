package unlp.basededatos.tarjetas.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_bank")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

	// bi-directional many-to-one association to Card
    // un Banco puede tener muchas Tarjetas(Cards)
    // uno a MUCHOS
//    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
//	private List<Card> cards;


    //Un Banco puede tener muchos clientes (CardHolders)
    //Pero los clientes pueden pertenecer a uno o mas bancos
    //la relacion parece muchos a muchos
    @JsonIgnore 
    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "banks_cardholders",
        joinColumns = @JoinColumn(name = "id_bank"),
        inverseJoinColumns = @JoinColumn(name = "id_cardholder")
    )
    private List<CardHolder> cardHolders;


    //Y los bancos pueden tener 0 o muchas promociones
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
