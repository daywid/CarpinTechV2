CREATE TABLE IF NOT EXISTS `tarefa`(
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `descricao` VARCHAR(100) NOT NULL,
    status ENUM('CONCLUIDO', 'EM_ANDAMENTO','FINALIZADO','CANCELADO') NOT NULL,
    `data_inicio` TIMESTAMP NOT NULL,
    `data_termino` TIMESTAMP NOT NULL,
    `projeto_id` BIGINT NOT NULL,
    CONSTRAINT `fk_projeto_tarefa`
        FOREIGN KEY (`projeto_id`)
        REFERENCES `projeto` (`id`)
);
