package com.rockeseat.planner.participant;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockeseat.planner.trip.Trip;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip) {
        Participant participant = new Participant(email, trip);
        repository.save(participant);
        return new ParticipantCreateResponse(participant.getId());
    }
    
    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip){
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();

        repository.saveAll(participants);
        System.out.println(participants.get(0).getId());
    }
    public void triggerConfirmationEmailToParticipants(UUID id){}

    public void triggerConfirmationEmailToParticipant(String email){}

    public List<ParticipantData> getAllParticipantsFromTrip(UUID id) {
        return repository.findByTripId(id).stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.isConfirmed())).toList();
    }
    
}
