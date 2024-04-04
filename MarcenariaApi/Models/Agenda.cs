using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MarcenariaApi.Models
{
    public class Agenda
    {
        [Key]
        public int Id { get; set; }
        public string? Descricao { get; set; }
        public DateTime Data { get; set; }
        public string? Tipo { get; set; }
        
        public int FuncionarioId { get; set; }
    }
}
