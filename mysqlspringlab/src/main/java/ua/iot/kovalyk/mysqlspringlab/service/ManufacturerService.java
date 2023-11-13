package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;
import ua.iot.kovalyk.mysqlspringlab.repository.ManufacturerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManufacturerService extends GeneralService<Manufacturer, Integer>{

    @Autowired
    public ManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void update(Integer id, Manufacturer entity) {
        Manufacturer device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //update
        device.setName(entity.getName());

        repository.save(device);
    }

    @Transactional
    public List<Device> findDevicesById(Integer id) {
        Manufacturer manufacturer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return manufacturer.getDevices().stream().toList();
    }
}
