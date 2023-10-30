package ua.iot.kovalyk.mysqlspringlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "repair_case", collectionRelation = "repair_cases")
public class RepairCaseDTO extends RepresentationModel<RepairCaseDTO> {
    private Integer id;
    private Integer device;
    private Integer repairman;
    private Timestamp opened;
    private Timestamp closed;
    private Boolean failed;
    private Float serviceCost;
}
