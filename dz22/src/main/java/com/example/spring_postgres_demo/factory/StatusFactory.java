package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.enums.Statuses;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class StatusFactory implements IFactory {

    @Override
    public Status getRandomElement() {
        Statuses randomStatusEnum = RandomElements.getRandomElement(Statuses.values());
        Status status = new Status();
        status.setId(randomStatusEnum.getId());
        status.setName(randomStatusEnum.getName());
        return status;
    }
}
