package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHour;

import java.sql.Time;
import java.util.List;

public interface WorkingHourRepository extends JpaRepository<WorkingHour,  WorkingHour.WorkingHourPK> {
    @Query(value= "SELECT * FROM kovalyk.working_hours WHERE repairman = :repairman_id ;", nativeQuery = true)
    public List<WorkingHour> findByRepairman(@Param("repairman_id") Integer repairmanId);

    @Modifying
    @Query(value= "CALL AddSchedule( :repairman_name , :work_day , :start_time , :end_time );", nativeQuery = true)
    void addSchedule(@Param("repairman_name") String repairman_name, @Param("work_day") String day, @Param("start_time") Time start, @Param("end_time") Time end);
}
