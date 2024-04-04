package com.api.carpintech.services;
import com.api.carpintech.controllers.AgendaController;
import com.api.carpintech.data.vo.AgendaVO;
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
import com.api.carpintech.controllers.AgendaController.*;
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

    public PagedModel<EntityModel<AgendaVO>> findAll(Pageable pageable)
    {
        logger.info("Find all agendas");

        var agendaPage = agendaRepository.findAll(pageable);
        var agendaVOPage = agendaPage.map(p -> {
            AgendaVO agendaVO = DozerMapper.parseObject(p, AgendaVO.class);
            return agendaVO.add(
                    linkTo(methodOn(AgendaController.class).findById(agendaVO.getId())).withSelfRel()
            );
        });
        Link link = linkTo(
                methodOn(AgendaController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")).withSelfRel();

        return assembler.toModel(agendaVOPage, link);
    }


}
