package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="manufacturers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy="manufacturer")
    private Set<Device> devices;

    @OneToMany(mappedBy="manufacturer")
    private Set<Part> parts;
}
