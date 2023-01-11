package unlp.basededatos.tarjetas.utils;

public class BankException extends Exception{
    private String message;

    public BankException(String message){
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
