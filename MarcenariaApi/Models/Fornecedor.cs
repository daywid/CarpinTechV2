using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
namespace MarcenariaApi.Models
{
    public class Fornecedor
{
    [Key]
    public int Id { get; set; }

    public string? Nome { get; set; }

    public string? Telefone { get; set; }

    public string? Endereco { get; set; }

    // Outras propriedades conforme necessário
}
}