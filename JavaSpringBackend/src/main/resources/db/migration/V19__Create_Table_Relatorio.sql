CREATE TABLE IF NOT EXISTS `relatorio` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `conteudo` VARCHAR(100) NOT NULL,
    `data_criacao` TIMESTAMP NOT NULL,
    `funcionario_id` BIGINT NOT NULL,
    status ENUM('CONCLUIDO', 'EM_ANDAMENTO', 'FINALIZADO','CANCELADO') NOT NULL,
    `titulo` VARCHAR(100) NOT NULL,
    CONSTRAINT `fk_funcionario_relatorio`
        FOREIGN KEY (`funcionario_id`)
        REFERENCES `funcionario` (`id`)
);