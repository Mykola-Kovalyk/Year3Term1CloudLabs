package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;
import ua.iot.kovalyk.mysqlspringlab.dto.DeviceDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.ManufacturerDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.DeviceDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.ManufacturerDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.service.ManufacturerService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping(value =  "/api/manufacturers")
public class ManufacturerController extends  GeneralController<Manufacturer, ManufacturerDTO, Integer>{

    final ManufacturerService manufacturerService;
    final ManufacturerDTOAssembler manufacturerAssembler;
    final DeviceDTOAssembler deviceAssembler;

    public ManufacturerController(ManufacturerService service, ManufacturerDTOAssembler assembler, DeviceDTOAssembler deviceAssembler) {
        this.service = manufacturerService = service;
        this.assembler = manufacturerAssembler = assembler;
        this.deviceAssembler = deviceAssembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ManufacturerDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ManufacturerDTO> getEntity(@PathVariable Integer id) {
        return super.getEntity(id);
    }

    @GetMapping(value = "/{manufacturerId}/devices")
    public ResponseEntity<CollectionModel<DeviceDTO>> getDevicesByManufacturer(@PathVariable Integer manufacturerId) {
        List<Device> devices = manufacturerService.findDevicesById(manufacturerId);
        Link selfLink = linkTo(methodOn(ManufacturerController.class).getDevicesByManufacturer(manufacturerId)).withSelfRel();
        CollectionModel<DeviceDTO> deviceDTOS = deviceAssembler.toCollectionModel(devices, selfLink);
        return new ResponseEntity(deviceDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ManufacturerDTO> addEntity(@RequestBody Manufacturer entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@RequestBody Manufacturer updateEntity, @PathVariable Integer id) {
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        return super.deleteEntity(id);
    }
}
