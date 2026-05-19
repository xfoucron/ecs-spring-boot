package fr.origisoft.ecsspringboot.infrastructure.controller.response;

import java.util.Date;
import java.util.UUID;

public record FindPostResponse(UUID id, String message, Date createdAt) {
}
