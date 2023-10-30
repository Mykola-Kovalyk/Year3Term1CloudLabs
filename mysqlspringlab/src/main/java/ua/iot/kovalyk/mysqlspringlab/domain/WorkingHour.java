package ua.iot.kovalyk.mysqlspringlab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;



@Entity
@Table(name="working_hours")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WorkingHour {
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class WorkingHourPK implements Serializable {
        private Integer repairman;
        private String day;
    }

    @Id
    public WorkingHourPK id;

    private Time start;
    private Time end;
}
