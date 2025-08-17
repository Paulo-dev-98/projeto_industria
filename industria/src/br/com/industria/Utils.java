package br.com.industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
	
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NF = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
	
    public static List<Funcionario> criarFuncionarios() {
        return new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    public static void imprimirFuncionarios(List<Funcionario> lista) {
        lista.forEach(f -> System.out.println(f.getNome() + " | " +
                f.getDataNascimento().format(FORMATTER) + " | " +
                NF.format(f.getSalario()) + " | " + f.getFuncao()));
    }

    public static void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public static void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> {
            BigDecimal novoSalario = f.getSalario()
                    .add(f.getSalario().multiply(percentual))
                    .setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        });
    }

    public static void agruparPorFuncao(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários agrupados por função:");
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });
    }

    public static void imprimirAniversariantes(List<Funcionario> funcionarios, int... meses) {
        Set<Integer> mesesSet = Arrays.stream(meses).boxed().collect(Collectors.toSet());
        System.out.println("\nFuncionários com aniversário nos meses " + mesesSet + ":");
        funcionarios.stream()
                .filter(f -> mesesSet.contains(f.getDataNascimento().getMonthValue()))
                .forEach(f -> System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(FORMATTER) + " | " + f.getFuncao()));
    }

    public static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(f -> {
                    int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
                    System.out.println("\nFuncionário mais velho: " + f.getNome() + " | Idade: " + idade);
                });
    }

    public static void imprimirOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(FORMATTER) + " | " +
                        NF.format(f.getSalario()) + " | " + f.getFuncao()));
    }

    public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + NF.format(totalSalarios));
    }

    public static void imprimirQtdSalariosMinimos(List<Funcionario> funcionarios) {
        System.out.println("\nQuantos salários mínimos cada funcionário ganha:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalMin = f.getSalario().divide(SALARIO_MINIMO, 1, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtdSalMin + " salários mínimos.");
        });
    }
}
