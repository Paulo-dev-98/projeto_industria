package br.com.industria;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    	
    	/* Comtempla a parte: Inserir todos os funcionários, na mesma ordem e informações da tabela acima. */
        List<Funcionario> funcionarios = Utils.criarFuncionarios();

        /* Comtempla a parte: Imprimir todos os funcionários com todas suas informações, sendo que:
         • informação de data deve ser exibido no formato dd/mm/aaaa;
         • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula. */
        System.out.println("Lista de funcionários com o João:");
        Utils.imprimirFuncionarios(funcionarios);

        /* Comtempla a parte: Remover o funcionário “João” da lista. */
        Utils.removerFuncionario(funcionarios, "João");
        System.out.println("\nLista de funcionários sem o João:");
        Utils.imprimirFuncionarios(funcionarios);
        
        /* Comtempla a parte: Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor. */
        Utils.aplicarAumento(funcionarios, new BigDecimal("0.10"));
        System.out.println("\nLista de funcionários com aumento de 10%:");
        Utils.imprimirFuncionarios(funcionarios);
        
        /* Comtempla a parte: Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. 
           Imprimir os funcionários, agrupados por função.*/
        Utils.agruparPorFuncao(funcionarios);
        
        /* Comtempla a parte: Imprimir os funcionários que fazem aniversário no mês 10 e 12. */
        Utils.imprimirAniversariantes(funcionarios, 10, 12);

        /* Comtempla a parte:  Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade. */
        Utils.imprimirFuncionarioMaisVelho(funcionarios);

        /* Comtempla a parte: Imprimir a lista de funcionários por ordem alfabética. */
        Utils.imprimirOrdemAlfabetica(funcionarios);

        /* Comtempla a parte: Imprimir o total dos salários dos funcionários. */
        Utils.imprimirTotalSalarios(funcionarios);

        /* Comtempla a parte: Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00. */
        Utils.imprimirQtdSalariosMinimos(funcionarios);
    }
}
