package com.rockeseat.planner.trip;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockeseat.planner.activity.ActivityData;
import com.rockeseat.planner.activity.ActivityRequestPayload;
import com.rockeseat.planner.activity.ActivityResponse;
import com.rockeseat.planner.link.LinkData;
import com.rockeseat.planner.link.LinkRequestPayload;
import com.rockeseat.planner.link.LinkResponse;
import com.rockeseat.planner.participant.ParticipantCreateResponse;
import com.rockeseat.planner.participant.ParticipantData;
import com.rockeseat.planner.participant.ParticipantRequestPayload;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // TRIP

    @PostMapping
    public ResponseEntity<TripCreateResponde> createTrip(@RequestBody TripRequestPayload payload) {
        return ResponseEntity.ok(tripService.createTrip(payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) {
        return ResponseEntity.ok().body(tripService.getTripDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload) {
        return ResponseEntity.ok(tripService.updateTrip(id, payload));
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        return ResponseEntity.ok(tripService.confirmTrip(id));
    }

    // ACTIVITY

    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID id,
            @RequestBody ActivityRequestPayload payload) {
        return ResponseEntity.ok(tripService.registerActivity(id, payload));
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID id) {
        return ResponseEntity.ok(tripService.getAllActivities(id));
    }

    // PARTICIPANT

    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        return ResponseEntity.ok(tripService.inviteParticipant(id, payload));
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantData>> getAllParticipants(@PathVariable UUID id) {
        return ResponseEntity.ok(tripService.getAllParticipants(id));
    }

    // LINKS

    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload) {
        return ResponseEntity.ok(tripService.registerLink(id, payload));
    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID id) {
        return ResponseEntity.ok(tripService.getAllLInks(id));
    }

}
