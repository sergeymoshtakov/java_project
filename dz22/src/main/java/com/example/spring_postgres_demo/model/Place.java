package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "Place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="address")
    private String address;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Event> events;

    @Override
    public int hashCode() {
        return Objects.hash(id, address, name, events);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return place.id.equals(id)
                && place.address.equals(address)
                && place.name.equals(name)
                && events.equals(place.events);
    }

    @Override
    public String toString() {
        return String.format("Place{id=%d, address=%s, name=%s}", id, address, name);
    }
}
