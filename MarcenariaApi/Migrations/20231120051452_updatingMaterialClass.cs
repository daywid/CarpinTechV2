using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MarcenariaApi.Migrations
{
    /// <inheritdoc />
    public partial class updatingMaterialClass : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Estoques",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    quantidade = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Estoques", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Projetos",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    nome = table.Column<string>(type: "TEXT", nullable: true),
                    desc = table.Column<string>(type: "TEXT", nullable: true),
                    valor = table.Column<double>(type: "REAL", nullable: false),
                    status = table.Column<int>(type: "INTEGER", nullable: false),
                    dataCadastro = table.Column<DateTime>(type: "TEXT", nullable: false),
                    dataPrazo = table.Column<DateTime>(type: "TEXT", nullable: false),
                    dataFinalizacao = table.Column<DateTime>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Projetos", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Tarefas",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    nome = table.Column<string>(type: "TEXT", nullable: true),
                    desc = table.Column<string>(type: "TEXT", nullable: true),
                    status = table.Column<int>(type: "INTEGER", nullable: false),
                    dataInicio = table.Column<DateTime>(type: "TEXT", nullable: false),
                    dataFinalizacao = table.Column<DateTime>(type: "TEXT", nullable: false),
                    ProjetoId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Tarefas", x => x.id);
                    table.ForeignKey(
                        name: "FK_Tarefas_Projetos_ProjetoId",
                        column: x => x.ProjetoId,
                        principalTable: "Projetos",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Materiais",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    nome = table.Column<string>(type: "TEXT", nullable: true),
                    custo = table.Column<double>(type: "REAL", nullable: false),
                    estoqueId = table.Column<int>(type: "INTEGER", nullable: false),
                    Tarefaid = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Materiais", x => x.id);
                    table.ForeignKey(
                        name: "FK_Materiais_Estoques_estoqueId",
                        column: x => x.estoqueId,
                        principalTable: "Estoques",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Materiais_Tarefas_Tarefaid",
                        column: x => x.Tarefaid,
                        principalTable: "Tarefas",
                        principalColumn: "id");
                });

            migrationBuilder.CreateIndex(
                name: "IX_Materiais_estoqueId",
                table: "Materiais",
                column: "estoqueId");

            migrationBuilder.CreateIndex(
                name: "IX_Materiais_Tarefaid",
                table: "Materiais",
                column: "Tarefaid");

            migrationBuilder.CreateIndex(
                name: "IX_Tarefas_ProjetoId",
                table: "Tarefas",
                column: "ProjetoId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Materiais");

            migrationBuilder.DropTable(
                name: "Estoques");

            migrationBuilder.DropTable(
                name: "Tarefas");

            migrationBuilder.DropTable(
                name: "Projetos");
        }
    }
}
