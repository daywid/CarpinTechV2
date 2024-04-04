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
    public class FornecedorController : ControllerBase
    {
        private readonly MarcenariaDbContext _context;

        public FornecedorController(MarcenariaDbContext context)
        {
            _context = context;
        }

        // GET: api/Fornecedor/listar
        [HttpGet]
        [Route("listar")]
        public async Task<ActionResult<IEnumerable<Fornecedor>>> GetFornecedores()
        {
            return await _context.Fornecedores.ToListAsync();
        }

        // GET: api/Fornecedor/buscar/5
        [HttpGet()]
        [Route("buscar/{id}")]
        public async Task<ActionResult<Fornecedor>> GetFornecedor(int id)
        {
            var fornecedor = await _context.Fornecedores.FindAsync(id);

            if (fornecedor == null)
            {
                return NotFound();
            }

            return fornecedor;
        }

        // POST: api/Fornecedor/cadastrar
        [HttpPost]
        [Route("cadastrar")]
        public async Task<ActionResult<Fornecedor>> PostFornecedor(Fornecedor fornecedor)
        {
            _context.Fornecedores.Add(fornecedor);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetFornecedor", new { id = fornecedor.Id }, fornecedor);
        }

        // PUT: api/Fornecedor/atualizar/5
        [HttpPut()]
        [Route("atualizar/{id}")]
        public async Task<IActionResult> PutFornecedor(int id, Fornecedor fornecedor)
        {
            if (id != fornecedor.Id)
            {
                return BadRequest();
            }

            _context.Entry(fornecedor).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!FornecedorExists(id))
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

        // DELETE: api/Fornecedor/deletar/5
        [HttpDelete()]
        [Route("deletar/{id}")]
        public async Task<IActionResult> DeleteFornecedor(int id)
        {
            var fornecedor = await _context.Fornecedores.FindAsync(id);
            if (fornecedor == null)
            {
                return NotFound();
            }

            _context.Fornecedores.Remove(fornecedor);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool FornecedorExists(int id)
        {
            return _context.Fornecedores.Any(e => e.Id == id);
        }
    }
}
