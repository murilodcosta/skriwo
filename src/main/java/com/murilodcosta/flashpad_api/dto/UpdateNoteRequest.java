package com.murilodcosta.flashpad_api.dto;

public record UpdateNoteRequest(
        String content,
        Long version,
        String clientId
) {
    public UpdateNoteRequest(String content) {
        this(content, null, null);
    }
}
