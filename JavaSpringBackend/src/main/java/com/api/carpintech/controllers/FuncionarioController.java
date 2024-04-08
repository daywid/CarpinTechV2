package com.api.carpintech.controllers;

import com.api.carpintech.data.vo.FuncionarioVO;
import com.api.carpintech.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController
{
    @Autowired
    private FuncionarioService service;
    @GetMapping("/listar")
    public ResponseEntity<PagedModel<EntityModel<FuncionarioVO>>> findAll
    (
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "limit", defaultValue = "12") Integer limit,
        @RequestParam(value = "direction", defaultValue = "asc") String direction
    )
    {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "firstName"));
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping("/cadastrar")
    public FuncionarioVO createFuncionario(@RequestBody FuncionarioVO funcionario)
    {
        return service.create(funcionario);
    }

    @GetMapping("/encontrar/{id}")
    public FuncionarioVO findById(@PathVariable Long id)
    {
        return service.findById(id);
    }

    @PutMapping()
    public FuncionarioVO updateFuncionario(@RequestBody FuncionarioVO funcionario)
    {
        return service.update(funcionario);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<FuncionarioVO> deleteFuncionario(@PathVariable(value = "id") Long id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}