package com.api.carpintech.repositories;

import com.api.carpintech.models.Cliente;
import com.api.carpintech.models.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {}
