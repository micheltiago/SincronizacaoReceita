/*
Cenário atual de negócio:
Todo dia útil por volta das 8 horas da manhã um colaborador da retaguarda recebe e compila as informações de todas agencias enviadas pelas cooperativas em um arquivos excel. Hoje o Sicredi já possiu mais de 4 milhões de contas ativas.
Esse usuário exporta os dados em um arquivo CSV para ser enviada para a Receita Federal, antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o serviço da receita para processamento automático do arquivo.

Funcionalidade:
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.


Formato CSV:
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

*/
package sincronizacaoreceita;

public class SincronizacaoReceita {

    public static void main(String[] args) throws RuntimeException, InterruptedException {        
        // Exemplo
        //ReceitaService receitaService = new ReceitaService();
        //receitaService.atualizarConta("0101", "123456", 100.50, "A");        
    }
    
}
