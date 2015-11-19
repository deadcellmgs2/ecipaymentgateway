/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cosw.paymentgateway.entities;

import java.io.Serializable;

/**
 * Define una cuenta bancaria 
 * @author SEBASTIAN
 */
public class Account implements Serializable{
    
    private Long accountNumber;
    private Boolean isValid; 
    private Double amount; 
    private Double debt; 
    private Long verificationNumber;
    private Integer year;
    private Integer month;

    public Account(Long accountNumber, Boolean isValid, Double amount, Double debt, Long verificationNumber, Integer year, Integer month) {
        this.accountNumber = accountNumber;
        this.isValid = isValid;
        this.amount = amount;
        this.debt = debt;
        this.verificationNumber = verificationNumber;
        this.year = year;
        this.month = month;
    }


    
     public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }
    
    public Long getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(Long verificationNumber) {
        this.verificationNumber = verificationNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
                      builder.append("[ Account Number: "+accountNumber+",");
                      builder.append(" Is_Valid: "+isValid+",");
                      builder.append(" Avalible Amount: $"+String.format("%.0f", amount)+",");
                      builder.append(" Actual Debt: $"+String.format("%.0f", debt)+"]");
        return builder.toString();
    }

    public String discount(Double amount) throws Exception {
       if(isValid){
           if((getAmount()-amount) >= 0){
             setAmount(getAmount()-amount);
             setDebt(getDebt()+amount);
             return StaticAccountHolder.mapSuccess(toString());
           }
           return StaticAccountHolder.mapError(StaticAccountHolder.OUT_OF_FUNDS);
       }
       
       return StaticAccountHolder.mapError(StaticAccountHolder.INVALID_ACCOUNT);
    }

    public String deposit(Double amount) throws Exception {
        if(isValid){
            if((getDebt() - amount)>=0){
                setAmount(getAmount() + amount);
                setDebt(getDebt() - amount);
                return StaticAccountHolder.mapSuccess(toString());
            }
             return StaticAccountHolder.mapError(StaticAccountHolder.MAX_FUNDS_REACHED);
       }
       
       return StaticAccountHolder.mapError(StaticAccountHolder.INVALID_ACCOUNT);
    }

    
    
}
