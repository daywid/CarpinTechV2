﻿// <auto-generated />
using System;
using MarcenariaApi.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

#nullable disable

namespace MarcenariaApi.Migrations
{
    [DbContext(typeof(MarcenariaDbContext))]
    [Migration("20231120051452_updatingMaterialClass")]
    partial class updatingMaterialClass
    {
        /// <inheritdoc />
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder.HasAnnotation("ProductVersion", "7.0.11");

            modelBuilder.Entity("MarcenariaApi.Models.Agenda", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<DateTime>("Data")
                        .HasColumnType("TEXT");

                    b.Property<string>("Descricao")
                        .HasColumnType("TEXT");

                    b.Property<int>("FuncionarioId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Tipo")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Agendas");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Cliente", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Endereco")
                        .HasColumnType("TEXT");

                    b.Property<string>("Nome")
                        .HasColumnType("TEXT");

                    b.Property<string>("Telefone")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Clientes");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Estoque", b =>
                {
                    b.Property<int>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<int>("quantidade")
                        .HasColumnType("INTEGER");

                    b.HasKey("id");

                    b.ToTable("Estoques");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Financeiro", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<decimal>("CustosMateriais")
                        .HasColumnType("TEXT");

                    b.Property<decimal>("DespesasOperacionais")
                        .HasColumnType("TEXT");

                    b.Property<decimal>("PagamentosClientes")
                        .HasColumnType("TEXT");

                    b.Property<decimal>("SalariosFuncionarios")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Financeiros");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Fornecedor", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Endereco")
                        .HasColumnType("TEXT");

                    b.Property<string>("Nome")
                        .HasColumnType("TEXT");

                    b.Property<string>("Telefone")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Fornecedores");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Funcionario", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Funcao")
                        .HasColumnType("TEXT");

                    b.Property<int>("HorasTrabalhadas")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Nome")
                        .HasColumnType("TEXT");

                    b.Property<decimal>("Salario")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Funcionarios");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Material", b =>
                {
                    b.Property<int>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<int?>("Tarefaid")
                        .HasColumnType("INTEGER");

                    b.Property<double>("custo")
                        .HasColumnType("REAL");

                    b.Property<int>("estoqueId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("nome")
                        .HasColumnType("TEXT");

                    b.HasKey("id");

                    b.HasIndex("Tarefaid");

                    b.HasIndex("estoqueId");

                    b.ToTable("Materiais");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Projeto", b =>
                {
                    b.Property<int>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<DateTime>("dataCadastro")
                        .HasColumnType("TEXT");

                    b.Property<DateTime>("dataFinalizacao")
                        .HasColumnType("TEXT");

                    b.Property<DateTime>("dataPrazo")
                        .HasColumnType("TEXT");

                    b.Property<string>("desc")
                        .HasColumnType("TEXT");

                    b.Property<string>("nome")
                        .HasColumnType("TEXT");

                    b.Property<int>("status")
                        .HasColumnType("INTEGER");

                    b.Property<double>("valor")
                        .HasColumnType("REAL");

                    b.HasKey("id");

                    b.ToTable("Projetos");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Relatorio", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Conteudo")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<DateTime>("DataCriacao")
                        .HasColumnType("TEXT");

                    b.Property<int>("FuncionarioId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Status")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("Titulo")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.ToTable("Relatorios");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Tarefa", b =>
                {
                    b.Property<int>("id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<int>("ProjetoId")
                        .HasColumnType("INTEGER");

                    b.Property<DateTime>("dataFinalizacao")
                        .HasColumnType("TEXT");

                    b.Property<DateTime>("dataInicio")
                        .HasColumnType("TEXT");

                    b.Property<string>("desc")
                        .HasColumnType("TEXT");

                    b.Property<string>("nome")
                        .HasColumnType("TEXT");

                    b.Property<int>("status")
                        .HasColumnType("INTEGER");

                    b.HasKey("id");

                    b.HasIndex("ProjetoId");

                    b.ToTable("Tarefas");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Material", b =>
                {
                    b.HasOne("MarcenariaApi.Models.Tarefa", null)
                        .WithMany("Materiais")
                        .HasForeignKey("Tarefaid");

                    b.HasOne("MarcenariaApi.Models.Estoque", null)
                        .WithMany("materiais")
                        .HasForeignKey("estoqueId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("MarcenariaApi.Models.Tarefa", b =>
                {
                    b.HasOne("MarcenariaApi.Models.Projeto", null)
                        .WithMany("Tarefas")
                        .HasForeignKey("ProjetoId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("MarcenariaApi.Models.Estoque", b =>
                {
                    b.Navigation("materiais");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Projeto", b =>
                {
                    b.Navigation("Tarefas");
                });

            modelBuilder.Entity("MarcenariaApi.Models.Tarefa", b =>
                {
                    b.Navigation("Materiais");
                });
#pragma warning restore 612, 618
        }
    }
}