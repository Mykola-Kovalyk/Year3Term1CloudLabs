package ua.iot.kovalyk.mysqlspringlab.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="repairmen")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Repairman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
