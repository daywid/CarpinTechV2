package com.api.carpintech.controllers;

import com.api.carpintech.data.vo.AgendaVO;
import com.api.carpintech.models.Agenda;
import com.api.carpintech.repositories.AgendaRepository;
import com.api.carpintech.services.AgendaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController
{
    @Autowired
    private AgendaServices service;
    @GetMapping("/listar")
    public ResponseEntity<PagedModel<EntityModel<AgendaVO>>> findAll
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
    public AgendaVO createAgenda(@RequestBody AgendaVO agenda)
    {
        return service.create(agenda);
    }
    @GetMapping("/encontrar/{id}")
    public AgendaVO findById(@PathVariable Long id)
    {
        return service.findById(id);
    }
    @PutMapping()
    public AgendaVO updateAgenda(@RequestBody AgendaVO agenda)
    {
        return service.update(agenda);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<AgendaVO> deleteAgenda(@PathVariable(value = "id") Long id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}