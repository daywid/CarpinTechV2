package com.api.carpintech.services;

import com.api.carpintech.models.Financeiro;

public class FinanceiroService {

    public double calcularLucro(Financeiro f)
    {
        double receitaOperacional = f.getPagamentosClientes();
        double custosOperacionais = f.getCustosMateriais() + f.getSalariosFuncionarios() + f.getDespesasOperacionais();

        if (custosOperacionais > receitaOperacional)
        {
            return 0;
        }
        else
        {
            return receitaOperacional - custosOperacionais;
        }
    }

    // Method to calculate Net Profit
    public Double calcularLucroLiquido(Financeiro f)
    {
        double lucro = calcularLucro(f);
        double impostos = lucro * 0.15; // Example with 15% taxes.
        return lucro - impostos;
    }

}