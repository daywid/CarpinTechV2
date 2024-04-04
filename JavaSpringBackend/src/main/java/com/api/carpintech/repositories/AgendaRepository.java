package com.api.carpintech.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.carpintech.models.Agenda;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {}
