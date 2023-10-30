package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.RepairmanController;
import ua.iot.kovalyk.mysqlspringlab.controller.WorkingHourController;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHour;
import ua.iot.kovalyk.mysqlspringlab.dto.WorkingHourDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WorkingHourDTOAssembler extends GeneralDTOAssembler<WorkingHour, WorkingHourDTO> {


    public WorkingHourDTOAssembler() {
        super(WorkingHourController.class);
    }


    @Override
    public WorkingHourDTO toModel(WorkingHour entity) {
        WorkingHourDTO deviceDTO = WorkingHourDTO.builder()
                .repairman(entity.getId().getRepairman())
                .day(entity.getId().getDay())
                .end(entity.getEnd())
                .start(entity.getStart())
                .build();

        Link selfLink = linkTo(methodOn(WorkingHourController.class).getEntity(deviceDTO.getRepairman(), deviceDTO.getDay())).withSelfRel();
        Link repairmanLink = linkTo(methodOn(RepairmanController.class).getEntity(deviceDTO.getRepairman())).withRel("repairman");

        deviceDTO.add(selfLink);
        deviceDTO.add(repairmanLink);

        return deviceDTO;
    }

}
