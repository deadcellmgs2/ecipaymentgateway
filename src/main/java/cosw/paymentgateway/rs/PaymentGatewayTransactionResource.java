package cosw.paymentgateway.rs;

import cosw.paymentgateway.entities.StaticAccountHolder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SEBASTIAN
 */
@Component
@Path("/transactiongateway")
public class PaymentGatewayTransactionResource {
    /**
     * Log del recurso REST
     */
    private static final Logger LOG = Logger.getLogger(PaymentGatewayTransactionResource.class);
    private StaticAccountHolder accountHolder = new StaticAccountHolder();
    private Integer DISCOUNT = 1;
    private Integer DEPOSIT = 2;
            
    
    /**
     * Obtiene los estados de cuenta dada la informacion de cliente
     *
     * @param idUser
     * @param password
     * @param accountNumber
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/getAccountStatus/{idUser}/{password}/{accountNumber}/{verificationNumber}/{year}/{month}")
    public Response getAccountStatus(@PathParam("idUser") String idUser, @PathParam("password") String password 
          , @PathParam("accountNumber") Long accountNumber, @PathParam("verificationNumber") Long verificationNumber
          , @PathParam("year") Integer year, @PathParam("month") Integer month) {
         Response response = null;
         try {
             response = Response.status(Response.Status.OK)
                     .entity(accountHolder.getAccountStatus(idUser, password, accountNumber, verificationNumber, year, month))
                     .build();
         }catch(Exception e){
             response = Response.status(Response.Status.OK)
                     .entity(e.getMessage())
                     .build();
         }
         
         return response;
    }
    
    
    /**
     * Simula una transacción de pago de cuenta dada la informacion de cliente
     *
     * @param idUser
     * @param password
     * @param accountNumber
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/doPayment/{idUser}/{password}/{accountNumber}/{verificationNumber}/{year}/{month}/{amount}")
    public Response doPayment(@PathParam("idUser") String idUser, @PathParam("password") String password 
          , @PathParam("accountNumber") Long accountNumber, @PathParam("verificationNumber") Long verificationNumber
          , @PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("amount") Double amount) {
         Response response = null;
         try {
             response = Response.status(Response.Status.OK)
                     .entity(accountHolder.executeTransaction(idUser, password, accountNumber, verificationNumber, year, month, amount, DISCOUNT))
                     .build();
         }catch(Exception e){
             response = Response.status(Response.Status.OK)
                     .entity(e.getMessage())
                     .build();
         }
         
         return response;
    }
    
  /**
     * Simula una transacción de deposito de cuenta dada la informacion de cliente
     *
     * @param idUser
     * @param password
     * @param accountNumber
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    @Path("/doDeposit/{idUser}/{password}/{accountNumber}/{verificationNumber}/{year}/{month}/{amount}")
    public Response doDeposit(@PathParam("idUser") String idUser, @PathParam("password") String password 
          , @PathParam("accountNumber") Long accountNumber, @PathParam("verificationNumber") Long verificationNumber
          , @PathParam("year") Integer year, @PathParam("month") Integer month, @PathParam("amount") Double amount) {
         Response response = null;
         try {
             response = Response.status(Response.Status.OK)
                     .entity(accountHolder.executeTransaction(idUser, password, accountNumber, verificationNumber, year, month, amount, DEPOSIT))
                     .build();
         }catch(Exception e){
             response = Response.status(Response.Status.OK)
                     .entity(e.getMessage())
                     .build();
         }
         
         return response;
    }
}
