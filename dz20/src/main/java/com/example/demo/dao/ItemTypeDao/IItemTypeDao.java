package com.example.demo.dao.ItemTypeDao;

import com.example.demo.model.ItemType;

import java.util.List;

public interface IItemTypeDao {
    void save(ItemType itemType);
    void update(ItemType itemType);
    void delete(int itemTypeId);
    List<ItemType> findAll();
    ItemType findById(int itemTypeId);
    void deleteAll();
}
