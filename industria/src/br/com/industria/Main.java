package br.com.industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

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
		
	    funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	    
	    System.out.println("lista normal ja com a data e o salario formatado.");
		
		for (Funcionario f : funcionarios) {
		    String dataFormatada = f.getDataNascimento().format(formatter);
		    String salarioFormatado = nf.format(f.getSalario());
			System.out.println("----------------------------------------------\n" +
					f.getNome() + " | " + dataFormatada + " | " + salarioFormatado + " | " + f.getFuncao());		   
		}
		
		BigDecimal aumento = new BigDecimal("0.10");

		for (Funcionario f : funcionarios) {
		    BigDecimal salarioAtual = f.getSalario();
		    BigDecimal novoSalario = salarioAtual.add(salarioAtual.multiply(aumento));
		    
		    // Arredonda para 2 casas decimais.
		    novoSalario = novoSalario.setScale(2, RoundingMode.HALF_UP);
		    
		    f.setSalario(novoSalario);
		}
		
		System.out.println("lista com o acrescimo salarial");
		
		for (Funcionario f : funcionarios) {
		    String dataFormatada = f.getDataNascimento().format(formatter);
		    String salarioFormatado = nf.format(f.getSalario());
			System.out.println("----------------------------------------------\n" +
					f.getNome() + " | " + dataFormatada + " | " + salarioFormatado + " | " + f.getFuncao());		   
		}
		
		System.out.println(); // quebra de linha;
		
		Map<String, List<Funcionario>> funcionariosPorFuncao = 
		        funcionarios.stream()
		                    .collect(Collectors.groupingBy(Funcionario::getFuncao));
		
		System.out.println("lista formatada por função:");
		
		for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
		    System.out.println("Função: " + entry.getKey());
		    for (Funcionario f : entry.getValue()) {
		        System.out.println("  - " + f.getNome());
		    }
		}
		
		List<Funcionario> aniversariantes = funcionarios.stream()
		        .filter(f -> {
		            int mes = f.getDataNascimento().getMonthValue();
		            return mes == 10 || mes == 12;
		        })
		        .collect(Collectors.toList());
		
		System.out.println("\nlista dos funcionarios que fazem aniversario no mês 10 e 12 \n");


		for (Funcionario f : aniversariantes) {
		    System.out.println(f.getNome() + " | " 
		                       + f.getDataNascimento().format(formatter) 
		                       + " | " + f.getFuncao());
		}

	}

}
