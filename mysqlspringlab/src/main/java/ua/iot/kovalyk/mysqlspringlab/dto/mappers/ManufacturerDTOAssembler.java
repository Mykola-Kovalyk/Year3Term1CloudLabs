package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.DeviceController;
import ua.iot.kovalyk.mysqlspringlab.controller.ManufacturerController;
import ua.iot.kovalyk.mysqlspringlab.controller.GeneralController;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;
import ua.iot.kovalyk.mysqlspringlab.dto.ManufacturerDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ManufacturerDTOAssembler extends GeneralDTOAssembler<Manufacturer, ManufacturerDTO> {


    public ManufacturerDTOAssembler() {
        super(ManufacturerController.class);
    }


    @Override
    public ManufacturerDTO toModel(Manufacturer entity) {
        ManufacturerDTO deviceDTO = ManufacturerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

        Link selfLink = linkTo(methodOn(ManufacturerController.class).getEntity(deviceDTO.getId())).withSelfRel();

        deviceDTO.add(selfLink);

        return deviceDTO;
    }

}
