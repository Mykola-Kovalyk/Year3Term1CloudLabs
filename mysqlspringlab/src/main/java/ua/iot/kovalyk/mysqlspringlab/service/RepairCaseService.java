package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceAlreadyExistsException;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;
import ua.iot.kovalyk.mysqlspringlab.repository.PartRepository;
import ua.iot.kovalyk.mysqlspringlab.repository.RepairCaseRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RepairCaseService extends GeneralService<RepairCase, Integer>{

    RepairCaseRepository repairCaseRepository;
    PartRepository partRepository;

    @Autowired
    public RepairCaseService(RepairCaseRepository repository, PartRepository partRepository) {
        this.repository = repairCaseRepository = repository;
        this.partRepository = partRepository;
    }

    @Transactional
    @Override
    public void update(Integer id, RepairCase entity) {
        RepairCase device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //update
        device.setDevice(entity.getDevice());
        device.setRepairman(entity.getRepairman());
        device.setOpened(entity.getOpened());
        device.setClosed(entity.getClosed());
        device.setFailed(entity.getFailed());
        device.setServiceCost(entity.getServiceCost());

        repository.save(device);
    }

    @Transactional
    public List<Part> findReplacedParts(Integer repairCaseId) {
        RepairCase repairCase = repository.findById(repairCaseId)
                .orElseThrow(() -> new ResourceNotFoundException(repairCaseId));
        return repairCase.getReplacedParts().stream().toList();
    }

    @Transactional
    public RepairCase addPartForRepairCase(Integer repairCaseId, Integer partId) {
        RepairCase repairCase = repository.findById(repairCaseId)
                .orElseThrow(() -> new ResourceNotFoundException(repairCaseId));
        repairCaseRepository.addReplacedPart(repairCaseId, partId);
        return repairCase;
    }

    @Transactional
    public RepairCase removePartForRepairCase(Integer repairCaseId, Integer partId) {
        RepairCase repairCase = repository.findById(repairCaseId)
                .orElseThrow(() -> new ResourceNotFoundException(repairCaseId));
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new ResourceNotFoundException(partId));
        if (!repairCase.getReplacedParts().contains(part)) throw new ResourceNotFoundException(partId);
        repairCase.getReplacedParts().remove(part);
        repository.save(repairCase);
        return repairCase;
    }

    @Transactional
    public Float getAverageRepairCost() {
        return repairCaseRepository.getAverageRepairCost();
    }
}
