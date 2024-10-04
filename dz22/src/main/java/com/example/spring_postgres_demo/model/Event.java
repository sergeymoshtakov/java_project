package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private LocalDateTime eventDate;

    @Column(name="event_date")
    private String name;



    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Override
    public int hashCode() {
        return Objects.hash(id, eventDate, name, tickets, place);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id.equals(event.id)
                && eventDate.equals(event.eventDate)
                && name.equals(event.name)
                && place.equals(event.place)
                && tickets.equals(event.tickets);
    }

    @Override
    public String toString() {
        return String.format("Event{id=%d, eventDate=%s, name=%s}", id, eventDate, name);
    }
}
