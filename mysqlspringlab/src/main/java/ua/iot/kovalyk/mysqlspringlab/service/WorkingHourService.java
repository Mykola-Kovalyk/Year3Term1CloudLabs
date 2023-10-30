package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHour;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;
import ua.iot.kovalyk.mysqlspringlab.repository.RepairmanRepository;
import ua.iot.kovalyk.mysqlspringlab.repository.WorkingHourRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WorkingHourService extends GeneralService<WorkingHour,  WorkingHour.WorkingHourPK>{

    WorkingHourRepository workingHourRepository;
    RepairmanRepository repairmanRepository;

    @Autowired
    public WorkingHourService(WorkingHourRepository repository, RepairmanRepository repairmanRepository) {
        this.repository = workingHourRepository = repository;
        this.repairmanRepository = repairmanRepository;
    }

    @Transactional
    @Override
    public void update(WorkingHour.WorkingHourPK id, WorkingHour entity) {
        WorkingHour device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //update
        device.setId(entity.getId());
        device.setStart(entity.getStart());
        device.setEnd(entity.getEnd());

        repository.save(device);
    }

    @Transactional
    public List<WorkingHour> findByRepairman(Integer repairmanId) {
        return workingHourRepository.findByRepairman(repairmanId);
    }

    @Transactional
    public void addSchedule(WorkingHour workingHour) {
        String name =  repairmanRepository.findById(workingHour.getId().getRepairman())
                .orElseThrow(() -> new ResourceNotFoundException(workingHour.getId().getRepairman())).getName();
        workingHourRepository.addSchedule(name, workingHour.getId().getDay(), workingHour.getStart(), workingHour.getEnd());
    }
}
