/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizacaoreceita.springboot.batch.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tiago.silva@compasso.com.br
 */
@Getter
@Setter
public class LinhaOut {

    private String agencia;
    private String conta;
    private String saldo;
    private String status;
    private boolean retorno;

    public LinhaOut() {
    }

    public LinhaOut(String agencia, String conta, String saldo, String status, boolean retorno) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
        this.retorno = retorno;
    }   
}
