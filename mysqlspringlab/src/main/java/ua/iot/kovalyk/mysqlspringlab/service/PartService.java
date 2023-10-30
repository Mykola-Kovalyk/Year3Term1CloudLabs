package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;
import ua.iot.kovalyk.mysqlspringlab.repository.PartRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PartService extends GeneralService<Part, Integer>{

    @Autowired
    public PartService(PartRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void update(Integer id, Part entity) {
        Part part = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //update
        part.setPartNumber(entity.getPartNumber());
        part.setDevice(entity.getDevice());
        part.setManufacturer(entity.getManufacturer());
        part.setAmount(entity.getAmount());

        repository.save(part);
    }

    @Transactional
    public List<RepairCase> findRepairCasesThatUsePart(Integer partId) {
        Part repairCase = repository.findById(partId)
                .orElseThrow(() -> new ResourceNotFoundException(partId));
        return repairCase.getRepairCases().stream().toList();
    }
}
