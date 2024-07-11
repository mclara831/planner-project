package com.rockeseat.planner.participant;

import java.io.Serializable;
import java.util.UUID;

import com.rockeseat.planner.trip.Trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Participant implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Participant (String email, Trip trip) {
        this.email = email;
        this.trip = trip;
        this.isConfirmed = false;
        this.name = "";
    }
}
