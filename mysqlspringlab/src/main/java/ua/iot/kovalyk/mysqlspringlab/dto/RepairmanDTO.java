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
@Relation(itemRelation = "repairman", collectionRelation = "repairmen")
public class RepairmanDTO extends RepresentationModel<RepairmanDTO> {
    private Integer id;
    private String name;
}
