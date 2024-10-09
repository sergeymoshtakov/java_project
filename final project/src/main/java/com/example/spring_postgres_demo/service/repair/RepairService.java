package com.example.spring_postgres_demo.service.repair;

import com.example.spring_postgres_demo.dao.repair.RepairRepository;
import com.example.spring_postgres_demo.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService implements IRepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Override
    public void save(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public int[] saveStudentsList(List<Repair> repairs) {
        repairRepository.saveAll(repairs);
        return repairs.stream().mapToInt(Repair::getId).toArray();
    }

    @Override
    public void update(Repair repair) {
        if (repair.getId() != 0) {
            Optional<Repair> existingRepair = repairRepository.findById(repair.getId());
            if (existingRepair.isPresent()) {
                repairRepository.save(repair);
            }
        }
    }

    @Override
    public void delete(Repair repair) {
        repairRepository.delete(repair);
    }

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    @Override
    public void deleteAll() {
        repairRepository.deleteAll();
    }

    @Override
    public Repair findById(int id) {
        return repairRepository.findById(id).orElse(null);
    }
}
