package com.rockeseat.planner.activity;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockeseat.planner.trip.Trip;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;
    
    public ActivityResponse registerActivity(ActivityRequestPayload payload, Trip trip) {
        Activity newActivity = new Activity(payload.title(), payload.ocurrs_at(), trip);
        repository.save(newActivity);
        return new ActivityResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivitiesFromTrip(UUID tripId) {
        return repository.findByTripId(tripId).stream().map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOcurrsAt())).toList();
    }
}
