package br.com.bruno;

import br.com.bruno.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Criar um array de usuários
        List<Funcionario> funcionarios = new ArrayList<>(); //Se fosse apenas a tabela do problema, seria possível usar um hashmap

        List<Funcionario> inputDoUsuario = Arrays.asList(
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
        );

        for(Funcionario func : inputDoUsuario) {
            funcionarios.add(func);
        }

        //Remover o funcionário João
        for(int i = 0; i < funcionarios.size(); i++) {
            if(funcionarios.get(i).nome.equals("João")) {
                funcionarios.remove(i);
                i--;
            }
        }

        //Exibir todos
        System.out.println("Exibir todos");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.toString());
        }

        //Atualizar lista na parte de salário em 10%
        for(int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            BigDecimal salario_reajuste = BigDecimal.valueOf(10).divide(BigDecimal.valueOf(100));
            funcionario.salario = funcionario.salario.multiply(salario_reajuste);
        }

        Map<String, List<Funcionario>> grupo_funcao = new HashMap();
        //Agrupar funcionários por função em um hashlist (MAP) key (funcao)
        for(Funcionario funcionario : funcionarios) {
            grupo_funcao.computeIfAbsent(funcionario.funcao, k -> new ArrayList<>())
                    .add(funcionario);
        }

        //Imprimir os funcionários por funcao
        System.out.println("Por função:");
        for (Map.Entry<String, List<Funcionario>> entry : grupo_funcao.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().stream()
                    .map(f -> f.nome)
                    .toList());
        }

        //Imprimir quem faz aniversario mês 10 e 12
        System.out.println("Aniversário mês 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.data_nascimento.getMonthValue();
            if(mes == 10 || mes == 12) {
                System.out.println(funcionario.toString());
            }
        }

        //Imprimir o mais velho, mostrando nome e idade
        Funcionario maisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.data_nascimento.isBefore(maisVelho.data_nascimento)) {
                maisVelho = funcionario;
            }
        }
        System.out.println("O funcionário mais velho é " + maisVelho.nome + " com " +
                Period.between(maisVelho.data_nascimento, LocalDate.now()).getYears() + " anos");

        //Imprimir em ordem alfabética
        System.out.println("Ordem alfabética:");
        List<Funcionario> funcionarios_ordenados = new ArrayList<>(funcionarios);
        // Ordena pelo nome em ordem alfabética (A-Z)
        funcionarios_ordenados.sort(Comparator.comparing(f -> f.nome));

        // Imprime em ordem alfabética
        System.out.println("Ordem alfabética:");
        for (Funcionario funcionario : funcionarios_ordenados) {
            System.out.println(funcionario);
        }

        //Imprimir o total dos salários
        BigDecimal total_salarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            total_salarios = total_salarios.add(funcionario.salario);
        }
        System.out.println("Total dos salários: " + total_salarios);


        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        //Imprimir quantos salários mínimos ganha cada funcionário
        for (Funcionario funcionario : funcionarios) {
            BigDecimal qtdSalarios = funcionario.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(10))
                    .setScale(1, RoundingMode.HALF_UP);

            System.out.println(funcionario.nome + " ganha " + qtdSalarios + " salários mínimos.");
        }
    }
}

//Se for um INPUT do usuário e ele escolher a operação, será mais legal.