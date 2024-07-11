package com.rockeseat.planner.participant;

import java.util.UUID;

public record ParticipantData(UUID is, String name, String email, boolean isConfirmed) {
}
