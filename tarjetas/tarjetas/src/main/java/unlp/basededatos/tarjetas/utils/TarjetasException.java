package unlp.basededatos.tarjetas.utils;

public class TarjetasException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
