using MarcenariaApi.Data;
using MarcenariaApi.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace MarcenariaApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MaterialController : ControllerBase
    {
        private MarcenariaDbContext _dbContext;
        public MaterialController(MarcenariaDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Material>>> Listar()
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Materiais is null) return NotFound();
            return await _dbContext.Materiais.ToListAsync();
        }

        [HttpGet]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Material>> Buscar(int id)
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Materiais is null) return NotFound();
            var materialTemp = await _dbContext.Materiais.FindAsync(id);
            if (materialTemp is null) return NotFound();
            return materialTemp;
        }

        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult> Cadastrar(Material material)
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Materiais is null) return NotFound();
            if (_dbContext.Estoques is null) return NotFound("Não há estoques cadastrados!");

            var estoqueTemp = await _dbContext.Estoques.FindAsync(material.estoqueId);
            if (estoqueTemp is null) return NotFound("Estoque não encontrado!");

            await _dbContext.AddAsync(material);
            await _dbContext.SaveChangesAsync();
            return Created("Material cadastrado com sucesso!", material);
        }

        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<ActionResult> Alterar(int id, Material material)
        {
            if (_dbContext is null) return BadRequest();
            if (_dbContext.Materiais is null) return BadRequest();
            var materialTemp = await _dbContext.Materiais.FindAsync(id);
            if (materialTemp is null) return BadRequest();
            materialTemp.nome = material.nome;
            materialTemp.custo = material.custo;
            materialTemp.estoqueId = material.estoqueId;
            await _dbContext.SaveChangesAsync();
            return Ok();
        }

        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<ActionResult> Excluir(int id)
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Materiais is null) return NotFound();
            var materialTemp = await _dbContext.Materiais.FindAsync(id);
            if (materialTemp is null) return NotFound();
            _dbContext.Remove(materialTemp);
            await _dbContext.SaveChangesAsync();
            return Ok();
        }

        private bool MaterialExists(int id)
        {
            return _dbContext.Materiais.Any(e => e.id == id);
        }
    }
}
