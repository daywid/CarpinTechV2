using MarcenariaApi.Models.Enum;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace MarcenariaApi.Models
{
    public class Tarefa
    {
        [Key]
        public int? id { get; set; }
        public string? nome { get; set; }
        public string? desc { get; set; }
        public Status status { get; set; }
        public DateTime dataInicio { get; set; }
        public DateTime? dataFinalizacao { get; set; }

        public int ProjetoId { get; set; }

        [JsonIgnore]
        public Projeto? Projeto { get; }
    }
}
