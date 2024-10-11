package com.example.spring_postgres_demo.service.placeservice;

import com.example.spring_postgres_demo.dao.place.PlaceRepository;
import com.example.spring_postgres_demo.model.Place;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService implements IPlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // Сохранение нового или обновленного объекта Place
    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }

    // Сохранение списка объектов Place
    @Override
    public long[] savePlacesList(List<Place> places) {
        placeRepository.saveAll(places);
        return places.stream().mapToLong(Place::getId).toArray();
    }

    // Обновление существующего объекта Place
    @Override
    public void update(Place place) {
        if (place.getId() != 0) {
            Optional<Place> existingPlace = placeRepository.findById(place.getId());
            if (existingPlace.isPresent()) {
                placeRepository.save(place);
            }
        }
    }

    // Удаление объекта Place
    @Override
    public void delete(Place place) {
        placeRepository.delete(place);
    }

    // Поиск всех объектов Place
    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    // Удаление всех объектов Place
    @Override
    public void deleteAll() {
        placeRepository.deleteAll();
    }

    // Поиск объекта Place по идентификатору
    @Override
    public Place findById(long id) {
        return placeRepository.findById(id).orElse(null);
    }

    // Поиск идентификатора объекта Place по имени
    @Override
    public long findIdByName(String name) {
        List<Long> ids = placeRepository.findIdByName(name);
        if (!ids.isEmpty()) {
            return ids.get(0); // Возвращаем первый найденный ID
        }
        throw new EntityNotFoundException("No Place found with name: " + name);
    }
}
