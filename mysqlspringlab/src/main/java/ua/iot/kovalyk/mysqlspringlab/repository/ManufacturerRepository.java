package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
}
