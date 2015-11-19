/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cosw.paymentgateway.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SEBASTIAN
 */
public class StaticAccountHolder {


    
    private HashMap<String,Account> accountHolder; 
    public static final String ACCOUNT_NOT_EXIST= "Account doesn't exist";
    public static final String OUT_OF_FUNDS= "Account is out of funds";
    public static final String OPERATION_ERROR= "The operation is invalid";
    public static final String INVALID_ACCOUNT= "The Account is invalid";
    public static final String MAX_FUNDS_REACHED= "The account has reached the maximum assigned funds";
    
    public static final Integer DISCOUNT = 1;
    public static final Integer DEPOSIT = 2;
    
    
    public StaticAccountHolder (){
        accountHolder= new HashMap<String,Account>();
        accountHolder.put("(102564665,abcdefgh,13245)"
                , new Account(13245l, Boolean.TRUE, 13501574d, 1E6));
        accountHolder.put("(12379899,cosW2015,13246)"
                , new Account(13246l, Boolean.TRUE, 10000000d, 9000000d));
        accountHolder.put("(123456789,contrasena12,12347)"
                , new Account(13247l, Boolean.TRUE, 5000000d, 400000d));
        accountHolder.put("(102564665,sinSaldo,24564)"
                , new Account(24564l, Boolean.TRUE, 0d, 0d));
        accountHolder.put("(123456789,saldoInfinito,11111)"
                , new Account(11111l, Boolean.TRUE, Double.MAX_VALUE, 0d));
        accountHolder.put("(102502315,inactiva145,123450)"
                , new Account(132450l, Boolean.FALSE, 0d, 0d));
     }
    
    public String getAccountStatus(String user, String password, Long accountNumber) throws Exception{
        String key = "("+user+","+password+","+accountNumber+")";
        
        if(accountHolder.containsKey(key)){
           return accountHolder.get(key).toString();
        }else{
           return mapError(ACCOUNT_NOT_EXIST);
        }
    }
    
    public String executeTransaction(String user, String password, Long accountNumber,Double amount,Integer operation) throws Exception{
        String key = "("+user+","+password+","+accountNumber+")";
        switch (operation){
            case 1: 
                return discount(key,amount);
            case 2:   
                return deposit(key,amount);
        }
        return mapError(user);
    }

    public static String mapError(String literal) throws Exception {
        throw new Exception( "Error : "+ literal);
    }

    private String discount(String key, Double amount) throws Exception {
       if(accountHolder.containsKey(key)) 
        return accountHolder.get(key).discount(amount);
       else 
        return mapError(ACCOUNT_NOT_EXIST);
    }

    private String deposit(String key, Double amount) throws Exception {
       if(accountHolder.containsKey(key)) 
        return accountHolder.get(key).deposit(amount);
       else 
        return mapError(ACCOUNT_NOT_EXIST);
    
    }
    
    public static String mapSuccess(String account) {
        return "Success: "+account;
    }
    
}
