/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author User
 */
public class PairRR {
    private Integer BurstTime;
    private Integer Identificador;
    private Integer Residuo;

    public Integer getBurstTime() {
        return BurstTime;
    }

    public void setBurstTime(Integer BurstTime) {
        this.BurstTime = BurstTime;
    }

    public Integer getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(Integer Identificador) {
        this.Identificador = Identificador;
    }

    public Integer getResiduo() {
        return Residuo;
    }

    public void setResiduo(Integer Residuo) {
        this.Residuo = Residuo;
    }
    
    public PairRR() {
        this.BurstTime = null;
        this.Identificador = null;
        this.Residuo = null;
    }
    
    public PairRR(Integer BurstTime, Integer Identificador, Integer Residuo) {
        this.BurstTime = BurstTime;
        this.Identificador = Identificador;
        this.Residuo = Residuo;
    }

    
}
