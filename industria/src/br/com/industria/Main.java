package br.com.industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        
        /* Comtempla a parte: informação de data deve ser exibido no formato dd/mm/aaaa */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        /* Comtempla a parte: informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.*/ 
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        /* Comtempla a parte:  Inserir todos os funcionários, na mesma ordem e informações da tabela acima. */
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        /* Imprime a lista de Funcionarios com o João */
        System.out.println("Lista de funcionários com o João:");
        imprimirFuncionarios(funcionarios, formatter, nf);

        /* Comtempla a parte: Remover o funcionário “João” da lista. */ 
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        /* Imprime a lista de Funcionarios sem o João */
        System.out.println("Lista de funcionários sem o João:");
        imprimirFuncionarios(funcionarios, formatter, nf);

        /*Comtempla a parte: Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor. */
        BigDecimal aumento = new BigDecimal("0.10");
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario()
                    .add(f.getSalario().multiply(aumento))
                    .setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }

        /* Imprime a lista de funcionarios com o valor do aumento de 10% ajustado*/
        System.out.println("\nLista de funcionários com aumento de 10%:");
        imprimirFuncionarios(funcionarios, formatter, nf);

        /* Comtempla a parte: Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. */
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        /* Imprime a lista de funcionarios agrupada por funções */
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });

        /* Comtempla a parte: Imprimir os funcionários que fazem aniversário no mês 10 e 12. */ 
        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(formatter) + " | " + f.getFuncao()));

        /* Comtempla a parte: Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.*/
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(f -> {
                    int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
                    System.out.println("\nFuncionário mais velho: " + f.getNome() + " | Idade: " + idade);
                });

        /* Comtempla a parte: Imprimir a lista de funcionários por ordem alfabética. */
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(formatter) + " | " +
                        nf.format(f.getSalario()) + " | " + f.getFuncao()));

        /* Comtempla a parte: Imprimir o total dos salários dos funcionários. */
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + nf.format(totalSalarios));

        /* Comtempla a parte: Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.*/
        System.out.println("\nQuantos salários mínimos cada funcionário ganha:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalMin = f.getSalario().divide(salarioMinimo, 1, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtdSalMin + " salários mínimos.");
        });
    }

    // Método auxiliar para imprimir funcionários formatados
    private static void imprimirFuncionarios(List<Funcionario> lista, DateTimeFormatter formatter, NumberFormat nf) {
        lista.forEach(f -> System.out.println(f.getNome() + " | " +
                f.getDataNascimento().format(formatter) + " | " +
                nf.format(f.getSalario()) + " | " + f.getFuncao()));
    }
}
