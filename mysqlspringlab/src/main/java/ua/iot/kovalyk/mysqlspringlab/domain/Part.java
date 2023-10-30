package ua.iot.kovalyk.mysqlspringlab.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="parts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String partNumber;

    @ManyToOne
    @JoinColumn(name = "device", referencedColumnName = "id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    private Manufacturer manufacturer;

    private Integer amount;

    @ManyToMany(mappedBy = "replacedParts")
    private Set<RepairCase> repairCases;

}
