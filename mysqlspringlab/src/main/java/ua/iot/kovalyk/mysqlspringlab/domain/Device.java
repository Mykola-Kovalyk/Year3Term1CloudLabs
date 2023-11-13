package ua.iot.kovalyk.mysqlspringlab.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="devices")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    private Manufacturer manufacturer;
    private String serialNumber;
}
