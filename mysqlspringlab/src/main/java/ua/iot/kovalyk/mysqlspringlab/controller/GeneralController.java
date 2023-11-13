package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.iot.kovalyk.mysqlspringlab.service.GeneralService;

import java.util.List;


public abstract class GeneralController<Entity, DTO extends RepresentationModel<?>, ID> {


    protected GeneralService<Entity, ID> service;

    protected RepresentationModelAssembler<Entity, DTO> assembler;


    public ResponseEntity<CollectionModel<DTO>> getAllEntities() {
        List<Entity> entity = service.findAll();
        CollectionModel<DTO> entityDtos = assembler.toCollectionModel(entity);
        return new ResponseEntity(entityDtos, HttpStatus.OK);
    }

    public ResponseEntity<DTO> getEntity(ID id) {
        Entity entity = service.findById(id);
        DTO entityDto = assembler.toModel(entity);
        return new ResponseEntity(entityDto, HttpStatus.OK);
    }

    public ResponseEntity<DTO> addEntity(Entity entity) {
        Entity newEntity = service.create(entity);
        DTO entityDto = assembler.toModel(newEntity);
        return new ResponseEntity<>(entityDto, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateEntity(Entity updateEntity, ID id) {
        service.update(id, updateEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteEntity(ID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
