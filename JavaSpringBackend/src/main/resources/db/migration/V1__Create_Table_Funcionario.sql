CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `funcao` VARCHAR(100) NOT NULL,
  `salario` DECIMAL(10, 2) NOT NULL,
  `horasTrabalhadas` DECIMAL(10, 2)
);
