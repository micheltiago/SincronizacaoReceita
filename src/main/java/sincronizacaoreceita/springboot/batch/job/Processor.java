/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizacaoreceita.springboot.batch.job;

import org.springframework.batch.item.ItemProcessor;
import sincronizacaoreceita.ReceitaService;
import sincronizacaoreceita.springboot.batch.entity.LinhaIn;
import sincronizacaoreceita.springboot.batch.entity.LinhaOut;

/**
 * @author tiago.silva@compasso.com.br
 */
public class Processor implements ItemProcessor<LinhaIn, LinhaOut> {

    @Override
    public LinhaOut process(LinhaIn item) throws Exception {
        ReceitaService receitaService = new ReceitaService();
        boolean resposta = receitaService.atualizarConta(item.getAgencia(),
                item.getConta().replaceAll("-", ""),
                Double.parseDouble(item.getSaldo().replaceAll(",", ".")),
                item.getStatus());
        return new LinhaOut(item.getAgencia(),
                item.getConta(),
                item.getSaldo(),
                item.getStatus(),
                resposta);
    }
}
