using System;
using System.ComponentModel.DataAnnotations;

namespace MarcenariaApi.Models
{
    public class Relatorio
    {
        [Key]
        public int Id { get; set; }
        
        [Required]
        public string Conteudo { get; set; }
        
        [Required]
        public DateTime DataCriacao { get; set; }

        [Required]        
        public int FuncionarioId { get; set; }

        // Adicione uma propriedade para status (por exemplo, 'Concluído', 'Em Progresso', 'Pendente', etc.).
        public string Status { get; set; }

        [Required]
        public string Titulo { get; set; }
    }
}
