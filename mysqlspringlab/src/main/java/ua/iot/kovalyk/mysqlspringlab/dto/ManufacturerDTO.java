package ua.iot.kovalyk.mysqlspringlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "manufacturer", collectionRelation = "manufacturers")
public class ManufacturerDTO extends RepresentationModel<ManufacturerDTO> {
    private Integer id;
    private String name;
}
