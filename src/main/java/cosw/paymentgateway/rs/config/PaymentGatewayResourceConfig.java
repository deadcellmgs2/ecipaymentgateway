/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cosw.paymentgateway.rs.config;

import java.util.Set;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author SEBASTIAN
 */
public class PaymentGatewayResourceConfig extends ResourceConfig{
  /**
     * Log de la clase
     */
    private static final Logger LOG = Logger.getLogger(PaymentGatewayResourceConfig.class);
    
    public PaymentGatewayResourceConfig(){
        super();
        LOG.info("Beginning of the application settings.");

        LOG.info("Recording resources...");
        // Recursos REST
        register(cosw.paymentgateway.rs.PaymentGatewayTransactionResource.class);
    
    }
    
     /**
     * Constructor indicando conjunto de clases
     * @param classes clases
     */
    public PaymentGatewayResourceConfig(Set<Class<?>> classes) {
        super(classes);
    }

    /**
     * Constuctor 
     * @param classes clase
     */
    public PaymentGatewayResourceConfig(Class<?>... classes) {
        super(classes);
    }

    /**
     * Constructor 
     * @param original resourceConfig
     */
    public PaymentGatewayResourceConfig(ResourceConfig original) {
        super(original);
    }

}
