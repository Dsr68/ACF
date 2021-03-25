package com.example.sospirangueiro.modelbean;

public class Metas extends Gastos{
    private double limite;

    public Metas(){

    }

    public Metas(int id, String descricao, double limite){
        super(id, descricao);

        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
