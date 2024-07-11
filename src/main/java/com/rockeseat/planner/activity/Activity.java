package com.rockeseat.planner.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@Table(name = "activities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Activity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ocurrs_at", nullable = false)
    private LocalDateTime ocurrsAt;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Activity(String title, String ocurrsAt, Trip trip) {
        this.title = title;
        this.ocurrsAt = LocalDateTime.parse(ocurrsAt, DateTimeFormatter.ISO_DATE_TIME);
        this.trip = trip;
    }

    
}
