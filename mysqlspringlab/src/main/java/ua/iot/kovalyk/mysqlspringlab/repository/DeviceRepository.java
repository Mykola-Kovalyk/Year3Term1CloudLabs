package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
