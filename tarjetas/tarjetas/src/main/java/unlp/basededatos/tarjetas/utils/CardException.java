package unlp.basededatos.tarjetas.utils;

public class CardException extends Exception{
    private String message;

    public CardException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
