using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MarcenariaApi.Models
{
    public class Financeiro
    {

        [Key]
        public int Id { get; set; }
        public decimal CustosMateriais{ get; set; }
        public decimal SalariosFuncionarios { get; set; }
        public decimal PagamentosClientes { get; set; }
        public decimal DespesasOperacionais { get; set; }


        // Método para calcular o Lucro
        public decimal CalcularLucro()
        {
            if (CustosMateriais + SalariosFuncionarios + DespesasOperacionais > PagamentosClientes)
            {
                return 0;
            }
            return PagamentosClientes - (CustosMateriais + SalariosFuncionarios + DespesasOperacionais);
        }

        // Método para calcular o Balanço
        public decimal CalcularBalanco()
        {
            return CustosMateriais + SalariosFuncionarios + DespesasOperacionais - PagamentosClientes;
        }

        // Método para calcular o Lucro Líquido
        public decimal CalcularLucroLiquido()
        {
            return CalcularLucro() - CalcularBalanco();
        }


    }
}