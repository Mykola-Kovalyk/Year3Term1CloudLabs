package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {
}
