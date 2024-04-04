namespace MarcenariaApi.Models
{
    public class Estoque
    {
        public int? id { get; set; }
        public ICollection<Material>? materiais { get; set; }
        public int quantidade { get; set; }
    }
}
