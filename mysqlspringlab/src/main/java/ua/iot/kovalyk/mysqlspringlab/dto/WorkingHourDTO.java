package ua.iot.kovalyk.mysqlspringlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Time;



@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "repairman", collectionRelation = "repairmen")
public class WorkingHourDTO extends RepresentationModel<WorkingHourDTO> {
    private Integer repairman;
    private String day;
    private Time start;
    private Time end;
}
