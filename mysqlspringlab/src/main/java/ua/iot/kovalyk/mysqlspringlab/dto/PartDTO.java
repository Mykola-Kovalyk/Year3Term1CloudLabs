package ua.iot.kovalyk.mysqlspringlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "part", collectionRelation = "parts")
public class PartDTO extends RepresentationModel<PartDTO> {
    private Integer id;
    private String partNumber;
    private Integer device;
    private Integer manufacturer;
    private Integer amount;
}
