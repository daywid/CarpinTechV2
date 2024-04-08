package com.api.carpintech.services;

import com.api.carpintech.controllers.AgendaController;
import com.api.carpintech.controllers.FuncionarioController;
import com.api.carpintech.data.vo.AgendaVO;
import com.api.carpintech.data.vo.FuncionarioVO;
import com.api.carpintech.exceptions.RequiredObjectIsNullException;
import com.api.carpintech.exceptions.ResourceNotFoundException;
import com.api.carpintech.mapper.DozerMapper;
import com.api.carpintech.repositories.FuncionarioRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FuncionarioService
{
    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    @Autowired
    FuncionarioRepository repository;

    @Autowired
    PagedResourcesAssembler<FuncionarioVO> assembler;

    public FuncionarioVO create(FuncionarioVO funcionarioVO)
    {
        logger.info("Creating a funcionario!");

        if(funcionarioVO == null) throw new RequiredObjectIsNullException();

        var entity = DozerMapper.parseObject(funcionarioVO, FuncionarioVO.class);
        var vo = DozerMapper.parseObject(entity, FuncionarioVO.class);
        vo.add(linkTo(methodOn(AgendaController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PagedModel <EntityModel<FuncionarioVO>> findAll(Pageable pageable)
    {
        logger.info("Find all funcionarios");

        var funcionarioPage = repository.findAll(pageable);
        var funcionarioVOPage = funcionarioPage.map(p -> DozerMapper.parseObject(p, FuncionarioVO.class));
        funcionarioVOPage.map(
                p -> p.add(
                        linkTo(methodOn(FuncionarioController.class)
                                .findById(p.getKey())).withSelfRel())
        );
        Link link = linkTo(
                methodOn(FuncionarioController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")).withSelfRel();
        return assembler.toModel(funcionarioVOPage, link);
    }

    public FuncionarioVO findById(Long id) {

        logger.info("Finding a funcionario by id");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, FuncionarioVO.class);
        vo.add(linkTo(methodOn(AgendaController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public FuncionarioVO update(FuncionarioVO funcionario)
    {

        if(funcionario == null) throw new RequiredObjectIsNullException();

        return funcionario;
    }

    public void delete(Long id) {
    }
}
