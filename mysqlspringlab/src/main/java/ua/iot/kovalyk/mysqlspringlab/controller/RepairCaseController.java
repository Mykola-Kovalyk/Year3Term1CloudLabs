package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;
import ua.iot.kovalyk.mysqlspringlab.dto.PartDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.RepairCaseDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.PartDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.RepairCaseDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.service.RepairCaseService;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value =  "/api/repair-cases")
public class RepairCaseController extends  GeneralController<RepairCase, RepairCaseDTO, Integer>{

    RepairCaseService repairCaseService;
    PartDTOAssembler deviceAssembler;

    public RepairCaseController(RepairCaseService service, RepairCaseDTOAssembler assembler, PartDTOAssembler deviceAssembler) {
        this.service = repairCaseService = service;
        this.assembler = assembler;
        this.deviceAssembler = deviceAssembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RepairCaseDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RepairCaseDTO> getEntity(@PathVariable Integer id) {
        return super.getEntity(id);
    }

    @GetMapping(value = "/{repairCaseId}/parts")
    public ResponseEntity<CollectionModel<PartDTO>> getReplacedPartsByCase(@PathVariable Integer repairCaseId) {
        List<Part> parts = repairCaseService.findReplacedParts(repairCaseId);
        Link selfLink = linkTo(methodOn(ManufacturerController.class).getDevicesByManufacturer(repairCaseId)).withSelfRel();
        CollectionModel<PartDTO> partDTOS = deviceAssembler.toCollectionModel(parts, selfLink);
        return new ResponseEntity(partDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{repairCaseId}/parts/{partId}")
    public ResponseEntity<RepairCaseDTO> addReplacedPartToCase(@PathVariable Integer repairCaseId, @PathVariable Integer partId) {
        RepairCase repairCase = repairCaseService.addPartForRepairCase(repairCaseId, partId);
        RepairCaseDTO dto =  assembler.toModel(repairCase);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{repairCaseId}/parts/{partId}")
    public ResponseEntity<RepairCaseDTO> removeReplacedPartFromCase(@PathVariable Integer repairCaseId, @PathVariable Integer partId) {
        RepairCase repairCase = repairCaseService.removePartForRepairCase(repairCaseId, partId);
        RepairCaseDTO dto =  assembler.toModel(repairCase);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<RepairCaseDTO> addEntity(@RequestBody RepairCase entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@RequestBody RepairCase updateEntity, @PathVariable Integer id) {
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        return super.deleteEntity(id);
    }

    @GetMapping(value = "/average_cost")
    public ResponseEntity<Float> getAverageRepairCost() {
        Float avg = repairCaseService.getAverageRepairCost();
        System.out.println(avg);
        return new ResponseEntity(avg, HttpStatus.OK);
    }
}
