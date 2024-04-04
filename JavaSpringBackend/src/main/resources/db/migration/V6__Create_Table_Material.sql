CREATE TABLE IF NOT EXISTS `material` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `custo` DOUBLE NOT NULL,
  `estoque_id` BIGINT NOT NULL,
  FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`)
);
