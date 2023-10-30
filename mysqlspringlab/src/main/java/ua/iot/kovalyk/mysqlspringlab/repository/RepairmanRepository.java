package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;

public interface RepairmanRepository extends JpaRepository<Repairman, Integer> {
    @Modifying
    @Query(value= "CALL AddRepairmen();", nativeQuery = true)
    public void addRepairmen();

    @Modifying
    @Query(value= "CALL CreatePaymentDatabases();", nativeQuery = true)
    public void createPaymentDatabases();
}
