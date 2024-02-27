package com.notes.Notes.Project.model.notes;

import com.notes.Notes.Project.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "notas")
@Table(name = "notas")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String content;

    @Enumerated(EnumType.STRING)
    private NotesType notesType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notes(){}
}
