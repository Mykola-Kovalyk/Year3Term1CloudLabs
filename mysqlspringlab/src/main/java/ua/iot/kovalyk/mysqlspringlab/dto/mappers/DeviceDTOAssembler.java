package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.DeviceController;
import ua.iot.kovalyk.mysqlspringlab.controller.GeneralController;
import ua.iot.kovalyk.mysqlspringlab.controller.ManufacturerController;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.dto.DeviceDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DeviceDTOAssembler extends GeneralDTOAssembler<Device, DeviceDTO> {


    public DeviceDTOAssembler() {
        super(DeviceController.class);
    }

    @Override
    public DeviceDTO toModel(Device entity) {
        DeviceDTO deviceDTO = DeviceDTO.builder()
                .id(entity.getId())
                .manufacturer(entity.getManufacturer().getId())
                .serialNumber(entity.getSerialNumber())
                .build();

        Link selfLink = linkTo(methodOn(DeviceController.class).getEntity(deviceDTO.getId())).withSelfRel();
        Link manufacturerLink = linkTo(methodOn(ManufacturerController.class).getEntity(deviceDTO.getManufacturer())).withRel("manufacturer");

        deviceDTO.add(selfLink);
        deviceDTO.add(manufacturerLink);

        return deviceDTO;
    }

}
