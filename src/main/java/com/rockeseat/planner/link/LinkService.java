package com.rockeseat.planner.link;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockeseat.planner.trip.Trip;

@Service
public class LinkService {
    
    @Autowired
    private LinkRepository repository;

    public LinkResponse registerLink(LinkRequestPayload payload, Trip trip) {
        Link newLink = new Link(payload.title(), payload.url(), trip);
        repository.save(newLink);
        return new LinkResponse(newLink.getId());
    }

    public List<LinkData> getAllLinksFromTrip(UUID tripId) {
        return repository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
