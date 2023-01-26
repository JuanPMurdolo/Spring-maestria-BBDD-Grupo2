package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.*;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Date;
import java.util.List;

public interface ITarjetasService {

    // Agregar una nueva promoción de tipo descuento a un banco dado
	public Promotion addNewPromotion(Promotion promotion, Long id) throws TarjetasException;
    
    // Editar las fecha de vencimiento de un pago con cierto código.
    public List<Payment> updatePaymentsExpiration(String code, Date first, Date second) throws TarjetasException;

    //Generar el total de pago de un mes dado, informando las compras correspondientes

    //Obtener el listado de tarjetas que vencen en los siguientes 30 días.
    public List<Card> getCardSoonExpiration() throws TarjetasException;

    //Obtener la información de una compra, incluyendo el listado de cuotas si esta posee.
    public Purchase getPurchaseInfo(Long id) throws TarjetasException;

    //Eliminar una promoción a traves de su código (tener en cuenta que esta puede haber sido aplicada alguna compra)

    //Obtener el precio total a pagar de una compra en cuotas (tener en cuenta que pueden existir promociones aplicadas)
    
    //Obtener el listado de las promociones disponibles de un local entre dos fechas

    //Obtener los titulares de las 10 tarjetas con más compras.

    //Obtener la promoción mas utilizada en las compras registradas

    //Obtener el nombre y cuit del local, que mas facturo en cierto mes

    //Obtener el banco que registre la mayor sumatoria de los importes en pagos con su tarjeta.
	
}
