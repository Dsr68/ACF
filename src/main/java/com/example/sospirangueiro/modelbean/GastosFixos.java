package com.example.sospirangueiro.modelbean;

public class GastosFixos extends  Gastos{
    private double valor;
    private int mes;
    private int ano;

    public GastosFixos(){

    }

    public GastosFixos(String id, String descricao, double valor, int mes, int ano) {
        super(id, descricao);
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
