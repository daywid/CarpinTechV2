using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MarcenariaApi.Data;
using MarcenariaApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MarcenariaApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RelatorioController : ControllerBase
    {
        private readonly MarcenariaDbContext _context;

        public RelatorioController(MarcenariaDbContext context)
        {
            _context = context;
        }

        // GET: api/Relatorio/listar
        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Relatorio>>> GetRelatorios()
        {
            return await _context.Relatorios.ToListAsync();
        }

        // GET: api/Relatorio/buscar/5
        [HttpGet()]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Relatorio>> GetRelatorio(int id)
        {
            var relatorio = await _context.Relatorios.FindAsync(id);

            if (relatorio == null)
            {
                return NotFound();
            }

            return relatorio;
        }

        // POST: api/Relatorio/cadastrar
        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult<Relatorio>> PostRelatorio(Relatorio relatorio)
        {
            // Verificar se o FuncionarioId existe na tabela Funcionario
            if (!_context.Funcionarios.Any(f => f.Id == relatorio.FuncionarioId))
            {
                return BadRequest("O FuncionarioId especificado não existe na tabela Funcionario.");
            }



            _context.Relatorios.Add(relatorio);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetRelatorio", new { id = relatorio.Id }, relatorio);
        }

        // PUT: api/Relatorio/atualizar/5
        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<IActionResult> PutRelatorio(int id, Relatorio relatorio)
        {
            if (id != relatorio.Id)
            {
                return BadRequest();
            }

            // Verificar se o FuncionarioId existe na tabela Funcionario
            if (!_context.Funcionarios.Any(f => f.Id == relatorio.FuncionarioId))
            {
                return BadRequest("O FuncionarioId especificado não existe na tabela Funcionario.");
            }

            _context.Entry(relatorio).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!RelatorioExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // DELETE: api/Relatorio/deletar/5
        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<IActionResult> DeleteRelatorio(int id)
        {
            var relatorio = await _context.Relatorios.FindAsync(id);
            if (relatorio == null)
            {
                return NotFound();
            }

            _context.Relatorios.Remove(relatorio);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool RelatorioExists(int id)
        {
            return _context.Relatorios.Any(e => e.Id == id);
        }
    }
}
