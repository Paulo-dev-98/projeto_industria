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
        
        /* Comtempla a parte: informa��o de data deve ser exibido no formato dd/mm/aaaa */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        /* Comtempla a parte: informa��o de valor num�rico deve ser exibida no formatado com separador de milhar como ponto e decimal como v�rgula.*/ 
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        /* Comtempla a parte:  Inserir todos os funcion�rios, na mesma ordem e informa��es da tabela acima. */
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Jo�o", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Helo�sa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        /* Imprime a lista de Funcionarios com o Jo�o */
        System.out.println("Lista de funcion�rios com o Jo�o:");
        imprimirFuncionarios(funcionarios, formatter, nf);

        /* Comtempla a parte: Remover o funcion�rio �Jo�o� da lista. */ 
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("Jo�o"));

        /* Imprime a lista de Funcionarios sem o Jo�o */
        System.out.println("Lista de funcion�rios sem o Jo�o:");
        imprimirFuncionarios(funcionarios, formatter, nf);

        /*Comtempla a parte: Os funcion�rios receberam 10% de aumento de sal�rio, atualizar a lista de funcion�rios com novo valor. */
        BigDecimal aumento = new BigDecimal("0.10");
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario()
                    .add(f.getSalario().multiply(aumento))
                    .setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }

        /* Imprime a lista de funcionarios com o valor do aumento de 10% ajustado*/
        System.out.println("\nLista de funcion�rios com aumento de 10%:");
        imprimirFuncionarios(funcionarios, formatter, nf);

        /* Comtempla a parte: Agrupar os funcion�rios por fun��o em um MAP, sendo a chave a �fun��o� e o valor a �lista de funcion�rios�. */
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        /* Imprime a lista de funcionarios agrupada por fun��es */
        System.out.println("\nFuncion�rios agrupados por fun��o:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Fun��o: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });

        /* Comtempla a parte: Imprimir os funcion�rios que fazem anivers�rio no m�s 10 e 12. */ 
        System.out.println("\nFuncion�rios com anivers�rio em outubro e dezembro:");
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(formatter) + " | " + f.getFuncao()));

        /* Comtempla a parte: Imprimir o funcion�rio com a maior idade, exibir os atributos: nome e idade.*/
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(f -> {
                    int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
                    System.out.println("\nFuncion�rio mais velho: " + f.getNome() + " | Idade: " + idade);
                });

        /* Comtempla a parte: Imprimir a lista de funcion�rios por ordem alfab�tica. */
        System.out.println("\nFuncion�rios em ordem alfab�tica:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(formatter) + " | " +
                        nf.format(f.getSalario()) + " | " + f.getFuncao()));

        /* Comtempla a parte: Imprimir o total dos sal�rios dos funcion�rios. */
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos sal�rios: " + nf.format(totalSalarios));

        /* Comtempla a parte: Imprimir quantos sal�rios m�nimos ganha cada funcion�rio, considerando que o sal�rio m�nimo � R$1212.00.*/
        System.out.println("\nQuantos sal�rios m�nimos cada funcion�rio ganha:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalMin = f.getSalario().divide(salarioMinimo, 1, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtdSalMin + " sal�rios m�nimos.");
        });
    }

    // M�todo auxiliar para imprimir funcion�rios formatados
    private static void imprimirFuncionarios(List<Funcionario> lista, DateTimeFormatter formatter, NumberFormat nf) {
        lista.forEach(f -> System.out.println(f.getNome() + " | " +
                f.getDataNascimento().format(formatter) + " | " +
                nf.format(f.getSalario()) + " | " + f.getFuncao()));
    }
}
