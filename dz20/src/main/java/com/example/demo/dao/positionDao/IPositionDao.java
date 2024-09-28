package com.example.demo.dao.positionDao;

import com.example.demo.model.Position;

import java.util.List;

public interface IPositionDao {
    void save(Position position);
    void update(Position position);
    void delete(int positionId);
    List<Position> findAll();
    Position findById(int positionId);
    void deleteAll();
}
