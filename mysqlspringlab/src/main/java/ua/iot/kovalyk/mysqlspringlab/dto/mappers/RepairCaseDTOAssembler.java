package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.DeviceController;
import ua.iot.kovalyk.mysqlspringlab.controller.RepairCaseController;
import ua.iot.kovalyk.mysqlspringlab.controller.RepairmanController;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;
import ua.iot.kovalyk.mysqlspringlab.dto.RepairCaseDTO;

import java.sql.Timestamp;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RepairCaseDTOAssembler extends GeneralDTOAssembler<RepairCase, RepairCaseDTO> {


    public RepairCaseDTOAssembler() {
        super(RepairCaseController.class);
    }


    @Override
    public RepairCaseDTO toModel(RepairCase entity) {
        RepairCaseDTO deviceDTO = RepairCaseDTO.builder()
                .id(entity.getId())
                .device(entity.getDevice().getId())
                .repairman(entity.getRepairman().getId())
                .opened(entity.getOpened())
                .closed(entity.getClosed())
                .failed(entity.getFailed())
                .serviceCost(entity.getServiceCost())
                .build();

        Link selfLink = linkTo(methodOn(RepairCaseController.class).getEntity(deviceDTO.getId())).withSelfRel();
        Link repairmanLink = linkTo(methodOn(RepairmanController.class).getEntity(deviceDTO.getRepairman())).withRel("repairman");
        Link deviceLink = linkTo(methodOn(DeviceController.class).getEntity(deviceDTO.getDevice())).withRel("device");

        deviceDTO.add(selfLink);
        deviceDTO.add(repairmanLink);
        deviceDTO.add(deviceLink);

        return deviceDTO;
    }

}
