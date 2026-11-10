package com.murilodcosta.skriwo.repository;

import com.murilodcosta.skriwo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findBySlug(String slug);
}
