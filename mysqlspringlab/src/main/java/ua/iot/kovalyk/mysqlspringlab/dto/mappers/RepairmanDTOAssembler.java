package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.RepairmanController;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.dto.RepairmanDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RepairmanDTOAssembler extends GeneralDTOAssembler<Repairman, RepairmanDTO> {


    public RepairmanDTOAssembler() {
        super(RepairmanController.class);
    }


    @Override
    public RepairmanDTO toModel(Repairman entity) {
        RepairmanDTO deviceDTO = RepairmanDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

        Link selfLink = linkTo(methodOn(RepairmanController.class).getEntity(deviceDTO.getId())).withSelfRel();

        deviceDTO.add(selfLink);

        return deviceDTO;
    }

}
