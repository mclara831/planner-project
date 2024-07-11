package com.rockeseat.planner.trip;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "trips")
public class Trip implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;
    
    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    
    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    public Trip(TripRequestPayload payload) {
        this.destination = payload.destination();
        this.isConfirmed = false;
        this.ownerName = payload.owner_name();
        this.ownerEmail = payload.owner_email();
        this.startsAt = LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
    }

    public Trip(TripRequestPayload payload, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.destination = payload.destination();
        this.isConfirmed = false;
        this.ownerName = payload.owner_name();
        this.ownerEmail = payload.owner_email();
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }
}
