package com.example.sospirangueiro.modelbean;

public class GastosFixos extends  Gastos{
    private double valor;

    public GastosFixos(){

    }

    public GastosFixos(int id, String descricao, double valor){
        super(id, descricao);

        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
