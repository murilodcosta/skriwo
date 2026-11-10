package com.murilodcosta.skriwo.controller;

import com.murilodcosta.skriwo.dto.NoteResponse;
import com.murilodcosta.skriwo.dto.UpdateNoteRequest;
import com.murilodcosta.skriwo.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/pads")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{slug}")
    public NoteResponse getNoteBySlug(@PathVariable String slug) {
        return noteService.getNoteBySlug(slug);
    }

    @PutMapping("/{slug}")
    public NoteResponse upsertNote(
            @PathVariable String slug,
            @Valid @RequestBody(required = false) UpdateNoteRequest request
    ) {
        return noteService.upsertNote(slug, request);
    }

    /**
     * Subscribe to Server-Sent Events (SSE) stream to receive real-time updates for the note.
     */
    @GetMapping(value = "/{slug}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamNoteUpdates(@PathVariable String slug) {
        return noteService.subscribeToNoteStream(slug);
    }
}
