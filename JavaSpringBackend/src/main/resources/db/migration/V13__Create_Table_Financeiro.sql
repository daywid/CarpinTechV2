CREATE TABLE IF NOT EXISTS `financeiro`(
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `custos_materiais` DECIMAL(10,2) NOT NULL,
    `salarios_funcionarios` DECIMAL(10,2) NOT NULL,
    `pagamentos_clientes` DECIMAL(10,2) NOT NULL,
    `despesas_operacionais` DECIMAL(10,2) NOT NULL
);