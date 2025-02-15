package br.com.bruno.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    public BigDecimal salario;
    public String funcao;

    public Funcionario(String nome, LocalDate data_nascimento, BigDecimal salario, String funcao) {
        super(nome, data_nascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String toString() {
        return nome + " - " + data_nascimento + " - " + salario + " - " + funcao;
    }
}
