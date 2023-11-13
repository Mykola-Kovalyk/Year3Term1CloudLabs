package ua.iot.kovalyk.mysqlspringlab.service;

import com.jayway.jsonpath.internal.path.PredicatePathToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;
import ua.iot.kovalyk.mysqlspringlab.repository.DeviceRepository;

import javax.transaction.Transactional;

@Service
public class DeviceService extends GeneralService<Device, Integer>{

    @Autowired
    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void update(Integer id, Device entity) {
        Device device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //update
        device.setManufacturer(entity.getManufacturer());
        device.setSerialNumber(entity.getSerialNumber());

        repository.save(device);
    }

}
