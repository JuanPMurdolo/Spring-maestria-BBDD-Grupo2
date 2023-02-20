package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.*;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITarjetasService {

    // 1- Agregar una nueva promoción de tipo descuento a un banco dado
	public Promotion addNewPromotion(Promotion promotion, Long id) throws TarjetasException;
    
    // 2- Editar las fecha de vencimiento de un pago con cierto código.
    public List<Payment> updatePaymentsExpiration(String code, Date first, Date second) throws TarjetasException;

    // 3- Generar el total de pago de un mes dado, informando las compras correspondientes
    public String getTotalByMonth(String month) throws TarjetasException;

    // 4- Obtener el listado de tarjetas que vencen en los siguientes 30 días.
    public List<Card> getCardSoonExpiration() throws TarjetasException;

    // 5-Obtener la información de una compra, incluyendo el listado de cuotas si esta posee.
    public String getPurchaseInfo(Long id) throws TarjetasException;

    // 6-Eliminar una promoción a traves de su código (tener en cuenta que esta puede haber sido aplicada alguna compra)
	public Promotion deletePromotion(String code) throws TarjetasException;

    // 7-Obtener el precio total a pagar de una compra en cuotas (tener en cuenta que pueden existir promociones aplicadas)
    public float totalQuota(Long id) throws TarjetasException;
    
    // 8-Obtener el listado de las promociones disponibles de un local entre dos fechas
    public List<Promotion> promotionListBetweenDates(String cuit, Date date, Date date1) throws TarjetasException;

    // 9-Obtener los titulares de las 10 tarjetas con más compras.
    public List<CardHolder> get10CardHolersWithMorePurchases() throws TarjetasException;

    // 10-Obtener la promoción mas utilizada en las compras registradas
    public Optional<Promotion> getPromotionMostUsed() throws TarjetasException;

    // 11-Obtener el nombre y cuit del local, que mas facturo en cierto mes
    public String getInfoFromBusiness(String month) throws TarjetasException;

    // 12-Obtener el banco que registre la mayor sumatoria de los importes en pagos con su tarjeta.
	public Bank getBankMostImportByCard(String month) throws TarjetasException;

}
