package com.rockeseat.planner.participant;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository repository;
    
    //Confirma o participante na viagem
    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload ) {
        Optional<Participant> participant = repository.findById(id);
        if (participant.isPresent()) {
            Participant obj = participant.get();
            obj.setConfirmed(true);
            obj.setName(payload.name());

            repository.save(obj);
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.notFound().build();
    }
}
