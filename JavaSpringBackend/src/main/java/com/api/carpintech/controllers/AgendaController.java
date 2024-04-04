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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController
{
    @Autowired
    private AgendaServices service;
    private final AgendaRepository agendaRepository;
    public AgendaController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<PagedModel<EntityModel<AgendaVO>>> findAll
    (
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "limit", defaultValue = "12") Integer limit,
        @RequestParam(value = "direction", defaultValue = "asc") String direction
    )
    {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? "desc" : "asc";
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.valueOf(sortDirection), "id");
        return ResponseEntity.ok(service.findAll(pageable));
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/cadastrar")
    public ResponseEntity<Agenda> createAgenda(@RequestBody Agenda agenda)
    {
        Agenda createdAgenda = agendaRepository.save(agenda);
        return new ResponseEntity<>(createdAgenda, HttpStatus.CREATED);
    }

    @GetMapping("/encontrar/{id}")
    public AgendaVO findById(@PathVariable Long id)
    {
//        if(!agendaRepository.existsById(id))
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        Agenda agenda = agendaRepository.findById(id).get();
//        return new ResponseEntity<>(agenda, HttpStatus.OK);
        return null;
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Agenda> updateAgenda(@PathVariable Long id, @RequestBody Agenda agenda)
    {
        if(!agendaRepository.existsById(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        agenda.setId(id);
        Agenda updatedAgenda = agendaRepository.save(agenda);
        return new ResponseEntity<>(updatedAgenda, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Agenda> deleteAgenda(@PathVariable Long id)
    {

    //

        return null;
    }





}
