package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cost")
    private double cost;

    @Column(name="number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Override
    public int hashCode() {
        return Objects.hash(id, number, customer, event, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id
                && Double.compare(ticket.cost, cost) == 0
                && number == ticket.number
                && customer.equals(ticket.customer)
                && event.equals(ticket.event)
                && cost == ticket.cost
                && status == ticket.status;
    }

    @Override
    public String toString() {
        return String.format("Ticket{id=%d, cost=%f, number=%d, customer=%s, event=%s, status=%s}", id, cost, number, customer, event.toString(), status.toString());
    }
}
