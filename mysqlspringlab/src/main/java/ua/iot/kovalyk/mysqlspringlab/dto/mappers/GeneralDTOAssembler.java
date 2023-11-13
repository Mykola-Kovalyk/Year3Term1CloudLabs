package ua.iot.kovalyk.mysqlspringlab.dto.mappers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.controller.DeviceController;
import ua.iot.kovalyk.mysqlspringlab.controller.GeneralController;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.dto.DeviceDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public abstract class GeneralDTOAssembler<Entity, DTO extends RepresentationModel<?>> implements RepresentationModelAssembler<Entity, DTO> {

    protected final Class<? extends GeneralController<Entity, DTO, ?>> entityControllerClass;

    public GeneralDTOAssembler(Class<? extends GeneralController<Entity, DTO, ?>> entityControllerClass) {
        this.entityControllerClass = entityControllerClass;
    }

    @Override
    public CollectionModel<DTO> toCollectionModel(Iterable<? extends Entity> entities) {
        Link selfLink = linkTo(methodOn(entityControllerClass).getAllEntities()).withSelfRel();
        return toCollectionModel(entities, selfLink);
    }

    public CollectionModel<DTO> toCollectionModel(Iterable<? extends Entity> entities, Link link) {
        CollectionModel<DTO> bookDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        bookDtos.add(link);
        return bookDtos;
    }
}
