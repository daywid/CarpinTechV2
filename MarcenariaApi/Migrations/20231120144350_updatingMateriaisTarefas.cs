using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MarcenariaApi.Migrations
{
    /// <inheritdoc />
    public partial class updatingMateriaisTarefas : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Materiais_Tarefas_Tarefaid",
                table: "Materiais");

            migrationBuilder.DropIndex(
                name: "IX_Materiais_Tarefaid",
                table: "Materiais");

            migrationBuilder.DropColumn(
                name: "Tarefaid",
                table: "Materiais");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "Tarefaid",
                table: "Materiais",
                type: "INTEGER",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Materiais_Tarefaid",
                table: "Materiais",
                column: "Tarefaid");

            migrationBuilder.AddForeignKey(
                name: "FK_Materiais_Tarefas_Tarefaid",
                table: "Materiais",
                column: "Tarefaid",
                principalTable: "Tarefas",
                principalColumn: "id");
        }
    }
}
