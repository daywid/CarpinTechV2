package com.api.carpintech.repositories;

import com.api.carpintech.models.Agenda;
import com.api.carpintech.models.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {}
