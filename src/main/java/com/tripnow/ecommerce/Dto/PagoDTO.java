package com.tripnow.ecommerce.Dto;

import com.tripnow.ecommerce.models.FormaPago;
import com.tripnow.ecommerce.models.Orden;

public class PagoDTO {

    private String number;
    private int cvv;
    private double amount;
    private String description;
    private FormaPago typeCard;
    private String email;

    public PagoDTO() {
    }

    //getter y setter:
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FormaPago getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(FormaPago typeCard) {
        this.typeCard = typeCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PagoDTO(String number, int cvv, double amount, String description, FormaPago typeCard, String email){
        this.number=number;
        this.cvv=cvv;
        this.description=description;
        this.typeCard=typeCard;
        this.email=email;

    }
}