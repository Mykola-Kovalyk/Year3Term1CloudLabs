package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;

public interface RepairCaseRepository extends JpaRepository<RepairCase, Integer> {
    @Query(value= "SELECT AverageCost();", nativeQuery = true)
    public Float getAverageRepairCost();

    @Modifying
    @Query(value= "CALL AddReplacedPart( :case_id , :part_id );", nativeQuery = true)
    public void addReplacedPart(@Param("case_id") Integer repairCaseId, @Param("part_id") Integer partId);
}
