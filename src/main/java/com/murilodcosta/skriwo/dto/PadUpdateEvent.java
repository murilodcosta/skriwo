package com.murilodcosta.skriwo.dto;

public record PadUpdateEvent(
        String content,
        Long version,
        String clientId
) {
}
