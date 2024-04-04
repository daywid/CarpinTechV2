using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MarcenariaApi.Models
{
    public class Funcionario
    {
        [Key]
        public int Id { get; set; }
        public string? Nome { get; set; }
        public string? Funcao { get; set; }
        public decimal Salario { get; set; }
        public int HorasTrabalhadas { get; set; } 
    }
}