package br.com.industria;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    	
    	/* Comtempla a parte: Inserir todos os funcion�rios, na mesma ordem e informa��es da tabela acima. */
        List<Funcionario> funcionarios = Utils.criarFuncionarios();

        /* Comtempla a parte: Imprimir todos os funcion�rios com todas suas informa��es, sendo que:
         � informa��o de data deve ser exibido no formato dd/mm/aaaa;
         � informa��o de valor num�rico deve ser exibida no formatado com separador de milhar como ponto e decimal como v�rgula. */
        System.out.println("Lista de funcion�rios com o Jo�o:");
        Utils.imprimirFuncionarios(funcionarios);

        /* Comtempla a parte: Remover o funcion�rio �Jo�o� da lista. */
        Utils.removerFuncionario(funcionarios, "Jo�o");
        System.out.println("\nLista de funcion�rios sem o Jo�o:");
        Utils.imprimirFuncionarios(funcionarios);
        
        /* Comtempla a parte: Os funcion�rios receberam 10% de aumento de sal�rio, atualizar a lista de funcion�rios com novo valor. */
        Utils.aplicarAumento(funcionarios, new BigDecimal("0.10"));
        System.out.println("\nLista de funcion�rios com aumento de 10%:");
        Utils.imprimirFuncionarios(funcionarios);
        
        /* Comtempla a parte: Agrupar os funcion�rios por fun��o em um MAP, sendo a chave a �fun��o� e o valor a �lista de funcion�rios�. 
           Imprimir os funcion�rios, agrupados por fun��o.*/
        Utils.agruparPorFuncao(funcionarios);
        
        /* Comtempla a parte: Imprimir os funcion�rios que fazem anivers�rio no m�s 10 e 12. */
        Utils.imprimirAniversariantes(funcionarios, 10, 12);

        /* Comtempla a parte:  Imprimir o funcion�rio com a maior idade, exibir os atributos: nome e idade. */
        Utils.imprimirFuncionarioMaisVelho(funcionarios);

        /* Comtempla a parte: Imprimir a lista de funcion�rios por ordem alfab�tica. */
        Utils.imprimirOrdemAlfabetica(funcionarios);

        /* Comtempla a parte: Imprimir o total dos sal�rios dos funcion�rios. */
        Utils.imprimirTotalSalarios(funcionarios);

        /* Comtempla a parte: Imprimir quantos sal�rios m�nimos ganha cada funcion�rio, considerando que o sal�rio m�nimo � R$1212.00. */
        Utils.imprimirQtdSalariosMinimos(funcionarios);
    }
}
