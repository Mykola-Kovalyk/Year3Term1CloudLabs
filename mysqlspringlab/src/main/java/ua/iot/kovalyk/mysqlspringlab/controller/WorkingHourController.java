package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHour;
import ua.iot.kovalyk.mysqlspringlab.dto.RepairmanDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.WorkingHourDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.WorkingHourDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.service.WorkingHourService;

@RestController
@RequestMapping(value =  "/api/working_hours")
public class WorkingHourController extends  GeneralController<WorkingHour, WorkingHourDTO, WorkingHour.WorkingHourPK>{

    WorkingHourService workingHourService;

    public WorkingHourController(WorkingHourService service, WorkingHourDTOAssembler assembler) {
        this.service = workingHourService =  service;
        this.assembler = assembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<WorkingHourDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{repairmanId}/{day}")
    public ResponseEntity<WorkingHourDTO> getEntity(@PathVariable Integer repairmanId, @PathVariable String day) {
        WorkingHour.WorkingHourPK id = new WorkingHour.WorkingHourPK(repairmanId, day);
        return super.getEntity(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<WorkingHourDTO> addEntity(@RequestBody WorkingHour entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{repairmanId}/{day}")
    public ResponseEntity<?> updateEntity(@RequestBody WorkingHour updateEntity, @PathVariable Integer repairmanId, @PathVariable String day) {
        WorkingHour.WorkingHourPK id = new WorkingHour.WorkingHourPK(repairmanId, day);
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{repairmanId}/{day}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer repairmanId, @PathVariable String day) {
        WorkingHour.WorkingHourPK id = new WorkingHour.WorkingHourPK(repairmanId, day);
        return super.deleteEntity(id);
    }
}
