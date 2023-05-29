package com.tripnow.ecommerce.Dto;
import com.tripnow.ecommerce.models.FormaPago;
import java.time.LocalDateTime;

public class PagoDTO {

    private FormaPago tipoTc;
    private String numeroTarjeta;
    private int numeroCVV;
    private String email;
    private double monto;
    private String descripcion;
    private LocalDateTime fechaPago;

    public PagoDTO() {
    }

    public PagoDTO(FormaPago tipoTc, String numeroTarjeta, int numeroCVV, String email, double monto, String descripcion, LocalDateTime fechaPago) {
        this.tipoTc = tipoTc;
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCVV = numeroCVV;
        this.email = email;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fechaPago = fechaPago;
    }

    //getter y setter:

    public FormaPago getTipoTc() {
        return tipoTc;
    }

    public void setTipoTc(FormaPago tipoTc) {
        this.tipoTc = tipoTc;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getNumeroCVV() {
        return numeroCVV;
    }

    public void setNumeroCVV(int numeroCVV) {
        this.numeroCVV = numeroCVV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}