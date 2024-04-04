package com.api.carpintech.unittests.services;

import com.api.carpintech.models.Financeiro;
import com.api.carpintech.services.FinanceiroService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinanceiroServiceTest
{

    @Test
    void testCalcularLucro()
    {
        Financeiro f = new Financeiro(1000, 500, 300, 200);
        FinanceiroService service = new FinanceiroService();
        double expected = 1000 - (500 + 300 + 200);
        double actual = service.calcularLucro(f);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcularLucroWhenCustosOperacionaisGreaterThanReceitaOperacional()
    {
        Financeiro f = new Financeiro(500, 600, 300, 200);
        FinanceiroService service = new FinanceiroService();
        double expected = 0;
        double actual = service.calcularLucro(f);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcularLucroLiquidoWhenCustosOperacionaisGreaterThanReceitaOperacional()
    {
        Financeiro f = new Financeiro(500, 600, 300, 200);
        FinanceiroService service = new FinanceiroService();
        Double expected = 0.0; // Profit is zero
        Double actual = service.calcularLucroLiquido(f);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcularLucroLiquido()
    {
        Financeiro f = new Financeiro(1000, 500, 300, 200);
        FinanceiroService service = new FinanceiroService();
        Double expected = (1000 - (500 + 300 + 200)) * 0.85; // 15% taxes
        Double actual = service.calcularLucroLiquido(f);
        assertEquals(expected, actual);
    }

}