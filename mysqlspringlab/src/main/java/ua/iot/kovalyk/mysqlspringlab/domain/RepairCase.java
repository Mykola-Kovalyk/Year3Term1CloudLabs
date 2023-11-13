package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="repair_cases")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RepairCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "device", referencedColumnName = "id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "repairman", referencedColumnName = "id")
    private Repairman repairman;

    private Timestamp opened;
    private Timestamp closed;
    private Boolean failed;
    private Float serviceCost;

    @ManyToMany
    @JoinTable(name = "replaced_parts",
            joinColumns = @JoinColumn(name = "repair_case", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "replaced_part", referencedColumnName = "id", nullable = false))
    private Set<Part> replacedParts;
}
