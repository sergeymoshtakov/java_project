package com.example.spring_postgres_demo.service.placeservice;

import com.example.spring_postgres_demo.model.Place;

import java.util.List;

public interface IPlaceService {
    void save(Place place) ;

    long[] savePlacesList(List<Place> places) ;

    void update(Place place) ;

    void delete(Place place) ;

    List<Place> findAll() ;

    void deleteAll() ;

    Place findById(long id);

    long findIdByName(String name);
}
