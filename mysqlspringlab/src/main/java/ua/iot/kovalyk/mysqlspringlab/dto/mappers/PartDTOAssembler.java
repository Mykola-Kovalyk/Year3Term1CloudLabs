package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.DeviceController;
import ua.iot.kovalyk.mysqlspringlab.controller.ManufacturerController;
import ua.iot.kovalyk.mysqlspringlab.controller.PartController;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;
import ua.iot.kovalyk.mysqlspringlab.dto.PartDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PartDTOAssembler extends GeneralDTOAssembler<Part, PartDTO> {


    public PartDTOAssembler() {
        super(PartController.class);
    }


    @Override
    public PartDTO toModel(Part entity) {
        PartDTO partDTO = PartDTO.builder()
                .id(entity.getId())
                .device(entity.getDevice().getId())
                .manufacturer(entity.getManufacturer().getId())
                .amount(entity.getAmount())
                .build();

        Link selfLink = linkTo(methodOn(PartController.class).getEntity(partDTO.getId())).withSelfRel();
        Link manufacturerLink = linkTo(methodOn(ManufacturerController.class).getEntity(partDTO.getManufacturer())).withRel("manufacturer");
        Link deviceLink = linkTo(methodOn(DeviceController.class).getEntity(partDTO.getDevice())).withRel("device");

        partDTO.add(selfLink);
        partDTO.add(manufacturerLink);
        partDTO.add(deviceLink);

        return partDTO;
    }

}
