package fr.origisoft.ecsspringboot.application.command;

import fr.origisoft.ecsspringboot.domain.model.PostId;

public record DeletePostCommand(PostId postId) {
}
