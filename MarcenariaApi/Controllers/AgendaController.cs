using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MarcenariaApi.Data;
using MarcenariaApi.Models;

namespace MarcenariaApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AgendaController : ControllerBase
    {
        private readonly MarcenariaDbContext _context;

        public AgendaController(MarcenariaDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Agenda>>> GetAgendas()
        {
            if (_context.Agendas is null) return NotFound();
            return await _context.Agendas.ToListAsync();
        }

        [HttpGet()]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Agenda>> GetAgenda(int id)
        {
            if (_context.Agendas is null) return NotFound();
            var agenda = await _context.Agendas.FindAsync(id);
            if (agenda == null) return NotFound();
            return agenda;
        }

        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult<Agenda>> PostAgenda(Agenda agenda)
        {
            if (_context.Agendas is null) return NotFound();
            if (!_context.Funcionarios.Any(f => f.Id == agenda.FuncionarioId))
            {
                return BadRequest("O FuncionarioId especificado não existe na tabela Funcionario.");
            }
            _context.Agendas.Add(agenda);
            await _context.SaveChangesAsync();
            return CreatedAtAction("GetAgenda", new { id = agenda.Id }, agenda);
        }

        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<IActionResult> PutAgenda(int id, Agenda agenda)
        {
            if (_context.Agendas is null) return NotFound();
            if (id != agenda.Id) return BadRequest();
            if (!_context.Funcionarios.Any(f => f.Id == agenda.FuncionarioId)) 
            return BadRequest("O FuncionarioId especificado não existe na tabela Funcionario.");
            _context.Agendas.Update(agenda);
             await _context.SaveChangesAsync();
             return NoContent();
           
        }

        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<IActionResult> DeleteAgenda(int id)
        {
            if (_context.Agendas is null) return NotFound();
            var agenda = await _context.Agendas.FindAsync(id);
            if (agenda == null) return NotFound();
            _context.Agendas.Remove(agenda);
            await _context.SaveChangesAsync();
            return NoContent();
        }
    }
}
