package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHour;
import ua.iot.kovalyk.mysqlspringlab.dto.RepairmanDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.WorkingHourDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.RepairmanDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.WorkingHourDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.service.RepairmanService;
import ua.iot.kovalyk.mysqlspringlab.service.WorkingHourService;

import java.util.List;

@RestController
@RequestMapping(value =  "/api/repairmen")
public class RepairmanController extends  GeneralController<Repairman, RepairmanDTO, Integer>{

    WorkingHourService workingHourService;
    WorkingHourDTOAssembler workingHourDTOAssembler;
    RepairmanService repairmanService;

    public RepairmanController(RepairmanService service, WorkingHourService workingHourService, WorkingHourDTOAssembler workingHourDTOAssembler, RepairmanDTOAssembler assembler) {
        this.service = repairmanService = service;
        this.assembler = assembler;
        this.workingHourService = workingHourService;
        this.workingHourDTOAssembler = workingHourDTOAssembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RepairmanDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RepairmanDTO> getEntity(@PathVariable Integer id) {
        return super.getEntity(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<RepairmanDTO> addEntity(@RequestBody Repairman entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@RequestBody Repairman updateEntity, @PathVariable Integer id) {
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        return super.deleteEntity(id);
    }

    @GetMapping(value = "/{repairmanId}/schedule")
    public ResponseEntity<CollectionModel<WorkingHourDTO>> getSchedule(@PathVariable Integer repairmanId) {
        List<WorkingHour> entity = workingHourService.findByRepairman(repairmanId);
        CollectionModel<WorkingHourDTO> entityDto = workingHourDTOAssembler.toCollectionModel(entity);
        return new ResponseEntity(entityDto, HttpStatus.OK);
    }

    @PostMapping(value = "/add_repairmen")
    public void AddRepairmen() {
        repairmanService.addRepairmen();
    }

    @GetMapping(value = "/create_payment_databases")
    public void createPaymentDatabases() {
        repairmanService.createPaymentDatabases();
    }

    @PostMapping(value = "/add_schedule")
    public void addSchedule(@RequestBody WorkingHour workingHour) {
        workingHourService.addSchedule(workingHour);
    }

}
