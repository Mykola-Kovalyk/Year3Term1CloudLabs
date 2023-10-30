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
import ua.iot.kovalyk.mysqlspringlab.service.PartService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value =  "/api/parts")
public class PartController extends  GeneralController<Part, PartDTO, Integer>{

    PartService partService;
    RepairCaseDTOAssembler repairCaseAssembler;

    public PartController(PartService service, PartDTOAssembler assembler, RepairCaseDTOAssembler repairCaseAssembler) {
        this.service = partService = service;
        this.assembler = assembler;
        this.repairCaseAssembler = repairCaseAssembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PartDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PartDTO> getEntity(@PathVariable Integer id) {
        return super.getEntity(id);
    }

    @GetMapping(value = "/{partId}/repair-cases")
    public ResponseEntity<CollectionModel<PartDTO>> getDevicesByManufacturer(@PathVariable Integer partId) {
        List<RepairCase> repairCases = partService.findRepairCasesThatUsePart(partId);
        Link selfLink = linkTo(methodOn(ManufacturerController.class).getDevicesByManufacturer(partId)).withSelfRel();
        CollectionModel<RepairCaseDTO> repairCaseDTOS = repairCaseAssembler.toCollectionModel(repairCases, selfLink);
        return new ResponseEntity(repairCaseDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PartDTO> addEntity(@RequestBody Part entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@RequestBody Part updateEntity, @PathVariable Integer id) {
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        return super.deleteEntity(id);
    }
}
