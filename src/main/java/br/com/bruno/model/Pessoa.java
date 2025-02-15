package br.com.bruno.model;

import java.time.LocalDate;

public class Pessoa {
    public String nome;
    public LocalDate data_nascimento;

    public Pessoa(String nome, LocalDate data_nascimento) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
    }
}
