package unlp.basededatos.tarjetas.utils;

public class TarjetasException extends Exception{
    private String message;

    public TarjetasException(String message){
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
