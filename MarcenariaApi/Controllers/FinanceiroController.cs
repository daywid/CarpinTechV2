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
    public class FinanceiroController : ControllerBase
    {
        private readonly MarcenariaDbContext _context;

        public FinanceiroController(MarcenariaDbContext context)
        {
            _context = context;
        }

        // GET: api/Financeiro/listar
        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Financeiro>>> GetFinanceiros()
        {
            return await _context.Financeiros.ToListAsync();
        }

        // GET: api/Financeiro/buscar/5
        [HttpGet()]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Financeiro>> GetFinanceiro(int id)
        {
            var financeiro = await _context.Financeiros.FindAsync(id);

            if (financeiro == null)
            {
                return NotFound();
            }

            return financeiro;
        }

        // POST: api/Financeiro/cadastrar
        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult<Financeiro>> PostFinanceiro(Financeiro financeiro)
        {
            _context.Financeiros.Add(financeiro);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetFinanceiro", new { id = financeiro.Id }, financeiro);
        }

        // PUT: api/Financeiro/atualizar/5
        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<IActionResult> PutFinanceiro(int id, Financeiro financeiro)
        {
            if (id != financeiro.Id)
            {
                return BadRequest();
            }

            _context.Entry(financeiro).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!FinanceiroExists(id))
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

        // DELETE: api/Financeiro/deletar/5
        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<IActionResult> DeleteFinanceiro(int id)
        {
            var financeiro = await _context.Financeiros.FindAsync(id);
            if (financeiro == null)
            {
                return NotFound();
            }

            _context.Financeiros.Remove(financeiro);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        // GET: api/Financeiro/calculalucro/5
        [HttpGet()]
        [Route("calcularlucro/{id}")]
        public async Task<ActionResult<decimal>> CalculaLucro(int id)
        {
            var financeiro = await _context.Financeiros.FindAsync(id);

            if (financeiro == null)
            {
                return NotFound();
            }

            decimal lucro = financeiro.CalcularLucro();
            return Ok(lucro);
        }

        // GET: api/Financeiro/calculabalanço/5
        [HttpGet()]
        [Route("calcularbalanço/{id}")]
        public async Task<ActionResult<decimal>> CalculaBalanco(int id)
        {
            var financeiro = await _context.Financeiros.FindAsync(id);

            if (financeiro == null)
            {
                return NotFound();
            }

            decimal balanco = financeiro.CalcularBalanco();
            return Ok(balanco);
        }

        // GET: api/Financeiro/calculalucrolíquido/5
        [HttpGet()]
        [Route("calcularlucrolíquido/{id}")]
        public async Task<ActionResult<decimal>> CalculaLucroLiquido(int id)
        {
            var financeiro = await _context.Financeiros.FindAsync(id);

            if (financeiro == null)
            {
                return NotFound();
            }

            decimal lucroLiquido = financeiro.CalcularLucroLiquido();
            return Ok(lucroLiquido);
        }

        private bool FinanceiroExists(int id)
        {
            return _context.Financeiros.Any(e => e.Id == id);
        }
    }
    }