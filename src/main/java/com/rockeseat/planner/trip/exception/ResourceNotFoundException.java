package com.rockeseat.planner.trip.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(UUID id) {
        super("Could not find a resource with this id: " + id);
    }
}
