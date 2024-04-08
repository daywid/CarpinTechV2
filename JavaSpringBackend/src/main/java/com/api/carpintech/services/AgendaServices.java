package com.api.carpintech.services;
import com.api.carpintech.controllers.AgendaController;
import com.api.carpintech.data.vo.AgendaVO;
import com.api.carpintech.exceptions.RequiredObjectIsNullException;
import com.api.carpintech.exceptions.ResourceNotFoundException;
import com.api.carpintech.mapper.DozerMapper;
import com.api.carpintech.repositories.AgendaRepository;
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
public class AgendaServices
{

    private Logger logger = Logger.getLogger(AgendaServices.class.getName());

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    PagedResourcesAssembler<AgendaVO> assembler;

    public AgendaVO create(AgendaVO agendaVO)
    {
        if(agendaVO == null) throw new RequiredObjectIsNullException();

        logger.info("Creating an agenda!");

        var entity = DozerMapper.parseObject(agendaVO, AgendaVO.class);
        var vo = DozerMapper.parseObject(entity, AgendaVO.class);
        vo.add(linkTo(methodOn(AgendaController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PagedModel<EntityModel<AgendaVO>> findAll(Pageable pageable)
    {
        logger.info("Find all agendas");

        var agendaPage = agendaRepository.findAll(pageable);
        var agendaVOPage = agendaPage.map(p -> DozerMapper.parseObject(p, AgendaVO.class));
        agendaVOPage.map(
                p -> p.add(
                        linkTo(methodOn(AgendaController.class)
                                .findById(p.getKey())).withSelfRel())
        );
        Link link = linkTo(
                methodOn(AgendaController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")).withSelfRel();
        return assembler.toModel(agendaVOPage, link);
    }

    public AgendaVO findById(Long id)
    {
        logger.info("Find agenda by id");
        var entity = agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, AgendaVO.class);
        vo.add(linkTo(methodOn(AgendaController.class).findById(id)).withSelfRel());
        return vo;
    }


    public AgendaVO update(AgendaVO agenda)
    {
        if(agenda == null) throw new RequiredObjectIsNullException();
        logger.info("Updating agenda");

        var entity = agendaRepository.findById(agenda.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setDescricao(agenda.getDescricao());
        entity.setDate(agenda.getDate());
        entity.setTipo(agenda.getTipo());
        entity.setFuncionario(agenda.getFuncionario());

        var vo = DozerMapper.parseObject(agendaRepository.save(entity), AgendaVO.class);
        vo.add(linkTo(methodOn(AgendaController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id)
    {
        logger.info("Delete agenda");
        var entity = agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        agendaRepository.delete(entity);
    }
}
