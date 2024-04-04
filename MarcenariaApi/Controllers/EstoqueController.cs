using MarcenariaApi.Data;
using MarcenariaApi.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace MarcenariaApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EstoqueController : ControllerBase
    {
        private MarcenariaDbContext _dbContext;
        public EstoqueController(MarcenariaDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Estoque>>> Listar()
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Estoques is null) return NotFound();
            return await _dbContext.Estoques.Include(e => e.materiais).ToListAsync();
        }

        [HttpGet]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Estoque>> Buscar(int id)
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Estoques is null) return NotFound();
            var estoqueTemp = await _dbContext.Estoques.FindAsync(id);
            if (estoqueTemp is null) return NotFound();
            return estoqueTemp;
        }

        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult> Cadastrar(Estoque estoque)
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Estoques is null) return NotFound();

            await _dbContext.AddAsync(estoque);
            await _dbContext.SaveChangesAsync();
            return Created("Estoque cadastrado com sucesso!", estoque);
        }

        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<ActionResult> Alterar(int id, Estoque estoque)
        {
            if (_dbContext is null) return BadRequest();
            if (_dbContext.Estoques is null) return BadRequest();
            var estoqueTemp = await _dbContext.Estoques.FindAsync(id);
            if (estoqueTemp is null) return BadRequest();
            estoqueTemp.quantidade = estoque.quantidade;
            await _dbContext.SaveChangesAsync();
            return Ok();
        }

        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<ActionResult> Excluir(int id)
        {
            if (_dbContext is null) return NotFound();
            if (_dbContext.Estoques is null) return NotFound();
            var estoqueTemp = await _dbContext.Estoques.FindAsync(id);
            if (estoqueTemp is null) return NotFound();
            _dbContext.Remove(estoqueTemp);
            await _dbContext.SaveChangesAsync();
            return Ok();
        }

        private async Task<bool> EstoqueExists(int id)
        {
            if (_dbContext is null) return false;
            var estoqueTemp = await _dbContext.Estoques.FindAsync(id);
            if (estoqueTemp is null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
}
