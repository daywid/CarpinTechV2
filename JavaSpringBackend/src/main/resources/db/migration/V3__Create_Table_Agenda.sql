CREATE TABLE IF NOT EXISTS `agenda` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `descricao` VARCHAR(80) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `tipo` VARCHAR(20) NOT NULL,
  `funcionario_id` BIGINT NOT NULL,
  CONSTRAINT `fk_funcionario_agenda`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `funcionario` (`id`)
);
