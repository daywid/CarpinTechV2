using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MarcenariaApi.Data;
using MarcenariaApi.Models;

namespace MarcenariaApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FuncionarioController : ControllerBase
    {
        private readonly MarcenariaDbContext _context;

        public FuncionarioController(MarcenariaDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Funcionario>>> GetFuncionarios()
        {
            if (_context.Funcionarios is null) return NotFound();
            return await _context.Funcionarios.ToListAsync();
        }

        [HttpGet()]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Funcionario>> GetFuncionario(int id)
        {
            if (_context.Funcionarios is null) return NotFound();
            var funcionario = await _context.Funcionarios.FindAsync(id);
            if (funcionario == null) return NotFound();
            return funcionario;
        }

        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult<Funcionario>> PostFuncionario(Funcionario funcionario)
        {
            if (_context.Funcionarios is null) return NotFound();
            _context.Funcionarios.Add(funcionario);
            await _context.SaveChangesAsync();
            return CreatedAtAction("GetFuncionario", new { id = funcionario.Id }, funcionario);
        }

        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<IActionResult> PutFuncionario(int id, Funcionario funcionario)
        {

            if (_context.Funcionarios is null) return NotFound();
            if (id != funcionario.Id) return BadRequest();
            _context.Funcionarios.Update(funcionario);
            await _context.SaveChangesAsync();
            return NoContent();
        }

        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<IActionResult> DeleteFuncionario(int id)
        {
            var funcionario = await _context.Funcionarios.FindAsync(id);
            if (funcionario == null) return NotFound();
            _context.Funcionarios.Remove(funcionario);
            await _context.SaveChangesAsync();
            return NoContent();
        }

    }
}
