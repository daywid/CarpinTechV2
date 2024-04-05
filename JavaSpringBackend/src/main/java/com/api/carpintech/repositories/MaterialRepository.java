package com.api.carpintech.repositories;

import com.api.carpintech.models.Cliente;
import com.api.carpintech.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {}
