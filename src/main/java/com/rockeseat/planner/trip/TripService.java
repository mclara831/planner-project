package com.rockeseat.planner.trip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockeseat.planner.activity.ActivityData;
import com.rockeseat.planner.activity.ActivityRequestPayload;
import com.rockeseat.planner.activity.ActivityResponse;
import com.rockeseat.planner.activity.ActivityService;
import com.rockeseat.planner.link.LinkData;
import com.rockeseat.planner.link.LinkRequestPayload;
import com.rockeseat.planner.link.LinkResponse;
import com.rockeseat.planner.link.LinkService;
import com.rockeseat.planner.participant.ParticipantCreateResponse;
import com.rockeseat.planner.participant.ParticipantData;
import com.rockeseat.planner.participant.ParticipantRequestPayload;
import com.rockeseat.planner.participant.ParticipantService;
import com.rockeseat.planner.trip.exception.DateTimeException;
import com.rockeseat.planner.trip.exception.ResourceNotFoundException;

@Service
public class TripService {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TripRepository repository;

    @Autowired
    private LinkService linkService;

    public TripCreateResponde createTrip(TripRequestPayload payload) {
        LocalDateTime startsAt = LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endsAt = LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
        if (startsAt.isAfter(endsAt)) {
            throw new DateTimeException("A data de início da viagem deve ser anterior à data de término");
        }

        Trip newTrip = new Trip(payload, startsAt, endsAt);
        repository.save(newTrip);

        participantService.registerParticipantsToEvent(payload.email_to_invite(), newTrip);

        return new TripCreateResponde(newTrip.getId());
    }

    public Trip getTripDetails(UUID id) {
        Optional<Trip> trip = repository.findById(id);
        return trip.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Trip updateTrip(UUID id, TripRequestPayload payload) {
        Trip obj = getTripDetails(id);
        LocalDateTime startsAt = LocalDateTime.parse(payload.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endsAt = LocalDateTime.parse(payload.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
        if (startsAt.isAfter(endsAt)) {
            throw new DateTimeException("A data de início da viagem deve ser anterior à data de término");
        }

        obj.setStartsAt(startsAt);
        obj.setEndsAt(endsAt);
        obj.setDestination(payload.destination());
        repository.save(obj);
        return obj;
    }

    public Trip confirmTrip(UUID id) {
        Trip obj = getTripDetails(id);
        obj.setConfirmed(true);
        repository.save(obj);
        participantService.triggerConfirmationEmailToParticipants(id);
        return obj;
    }

    public ActivityResponse registerActivity(UUID id, ActivityRequestPayload payload) {
        Trip trip = getTripDetails(id);

        LocalDateTime ocurrsAt = LocalDateTime.parse(payload.ocurrs_at(), DateTimeFormatter.ISO_DATE_TIME);

        if (ocurrsAt.isAfter(trip.getEndsAt()) || ocurrsAt.isBefore(trip.getStartsAt())) {
            throw new DateTimeException("A data de início da atividade deve estar entre a data de início e término da viagem");
        }
        return activityService.registerActivity(payload, trip);
    }

    public List<ActivityData> getAllActivities(UUID id) {
        return activityService.getAllActivitiesFromTrip(id);
    }

    public ParticipantCreateResponse inviteParticipant(UUID id, ParticipantRequestPayload payload) {
        Trip trip = getTripDetails(id);

        ParticipantCreateResponse participantResponse = participantService.registerParticipantToEvent(payload.email(), trip);

        if (trip.isConfirmed()) {
            participantService.triggerConfirmationEmailToParticipant(payload.email());
        }
        return participantResponse;
    }

    public List<ParticipantData> getAllParticipants(UUID id) {
        return participantService.getAllParticipantsFromTrip(id);
    }

    public LinkResponse registerLink(UUID id, LinkRequestPayload payload) {
        Trip trip = getTripDetails(id);
        return linkService.registerLink(payload, trip);
    }

    public List<LinkData> getAllLInks(UUID id) {
        return linkService.getAllLinksFromTrip(id);
    }
}
