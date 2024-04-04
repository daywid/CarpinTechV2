using MarcenariaApi.Models.Enum;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace MarcenariaApi.Models
{
    public class Projeto
    {
        [Key]
        public int? id { get; set; }
        public string? nome { get; set; }
        public string? desc { get; set; }
        public Double valor { get; set; }

        public ICollection<Tarefa>? Tarefas { get; set; }
        public Status status { get; set; }
        public DateTime dataCadastro { get; set;  }
        public DateTime dataPrazo { get; set; }

        public DateTime? dataFinalizacao { get; set; }

    }
}
