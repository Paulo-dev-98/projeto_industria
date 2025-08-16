# Projeto Java - Gestão de Funcionários

## Descrição
Este projeto em **Java** simula a gestão de funcionários de uma indústria, permitindo:  
- Inserir uma lista de funcionários com atributos como nome, data de nascimento, salário e função.  
- Remover funcionários específicos.  
- Aplicar aumento salarial.  
- Agrupar funcionários por função.  
- Identificar aniversariantes em determinados meses.  
- Determinar o funcionário mais velho.  
- Ordenar a lista de funcionários alfabeticamente.  
- Calcular o total de salários.  
- Calcular quantos salários mínimos cada funcionário ganha.  

O projeto foi desenvolvido como parte de um **teste prático de programação** e segue boas práticas de **POO**, **uso de streams e formatação de dados**.

---

## Tecnologias e Ferramentas Usadas
- **Java 17** – linguagem principal do projeto.  
- **Eclipse IDE** – ambiente de desenvolvimento integrado (IDE) utilizado.  
- **BigDecimal** – para cálculos precisos de valores monetários.  
- **LocalDate** – para manipulação de datas.  
- **DateTimeFormatter** – para formatação de datas no padrão `dd/MM/yyyy`.  
- **NumberFormat** – para formatação de valores monetários no padrão brasileiro (`R$ 1.212,00`).  
- **Streams e Collectors** – para filtragem, ordenação, agrupamento e operações em listas.  
- **Map<String, List<Funcionario>>** – para agrupar funcionários por função.  
- **Optional** – para tratamento seguro de valores que podem não existir (ex: funcionário mais velho).  

---

## Estrutura do Projeto
src/
br.com.industria
Main.java
Pessoa.java
Funcionario.java

- **Pessoa.java**: Classe base com atributos `nome` e `dataNascimento`.  
- **Funcionario.java**: Classe que estende `Pessoa` e adiciona `salario` e `funcao`.  
- **Main.java**: Classe principal que executa todas as operações do teste prático.  

---

## Como Executar
1. Abra o projeto no Eclipse ou em outra IDE compatível com Java.  
2. Compile e execute a classe `Main.java`.  
3. O console exibirá:
   - Lista de funcionários (com formatação de data e salário)  
   - Lista com aumento salarial aplicado  
   - Funcionários agrupados por função  
   - Funcionários aniversariantes nos meses 10 e 12  
   - Funcionário mais velho  
   - Lista ordenada alfabeticamente  
   - Total de salários  
   - Quantos salários mínimos cada funcionário ganha  

---

## Observações
- O projeto utiliza **BigDecimal** para garantir precisão nos cálculos financeiros.  
- Todas as datas são manipuladas com **LocalDate** e formatadas para o padrão brasileiro.  
- Boas práticas de programação orientada a objetos (POO) foram aplicadas.  

---

## Autor
**Paulo Júnio de Souza Nascimento**  
Desenvolvedor Java / Angular  
