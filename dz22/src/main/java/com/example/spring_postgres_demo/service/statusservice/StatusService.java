package com.example.spring_postgres_demo.service.statusservice;

import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    // Сохранение нового или обновленного объекта Status
    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    // Сохранение списка объектов Status
    @Override
    public long[] saveStatusesList(List<Status> statuses) {
        statusRepository.saveAll(statuses);
        // Возвращаем массив идентификаторов сохраненных статусов
        return statuses.stream().mapToLong(Status::getId).toArray();
    }

    // Обновление существующего объекта Status
    @Override
    public void update(Status status) {
        if (status.getId() != 0) {
            Optional<Status> existingStatus = statusRepository.findById(status.getId());
            if (existingStatus.isPresent()) {
                statusRepository.save(status);
            }
        }
    }

    // Удаление объекта Status
    @Override
    public void delete(Status status) {
        statusRepository.delete(status);
    }

    // Поиск всех объектов Status
    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    // Удаление всех объектов Status
    @Override
    public void deleteAll() {
        statusRepository.deleteAll();
    }

    // Поиск объекта Status по идентификатору
    @Override
    public Status findById(long id) {
        return statusRepository.findById(id).orElse(null);
    }

    // Поиск идентификатора объекта Status по самому объекту (по имени статуса)
    @Override
    public long findIdByStatus(Status status) {
        List<Long> ids = statusRepository.findIdByName(status.getName());
        if (!ids.isEmpty()) {
            return ids.get(0); // Возвращаем первый найденный ID
        }
        throw new EntityNotFoundException("No Status found with name: " + status.getName());
    }
}
