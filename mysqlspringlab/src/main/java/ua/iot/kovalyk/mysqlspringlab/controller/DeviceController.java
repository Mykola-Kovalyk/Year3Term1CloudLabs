package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.dto.DeviceDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.DeviceDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.service.DeviceService;

@RestController
@RequestMapping(value =  "/api/devices")
public class DeviceController extends GeneralController<Device, DeviceDTO, Integer>{

    public DeviceController(DeviceService service, DeviceDTOAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DeviceDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getEntity(@PathVariable Integer id) {
        return super.getEntity(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<DeviceDTO> addEntity(@RequestBody Device entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@RequestBody Device updateEntity, @PathVariable Integer id) {
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        return super.deleteEntity(id);
    }
}
