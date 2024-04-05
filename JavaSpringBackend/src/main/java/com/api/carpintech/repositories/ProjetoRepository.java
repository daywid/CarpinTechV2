package com.api.carpintech.repositories;

import com.api.carpintech.models.Agenda;
import com.api.carpintech.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {}
