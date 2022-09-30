package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;

@Service
public class    NotesService {
    @Autowired
    NoteMapper noteMapper;
    
    /**
     * @param note
     * @return user id  if note is added successfully
     */
    public int addNote(Note note) {

       return noteMapper.addNote(note);
    }   


    /**
     * @param noteId
     * @return note if note is found
     */
    public Note getNoteById(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public Integer updateNoteById(Note note) {
        return noteMapper.updateNote(note);
    }


    /**
     * @param id
     * @return user id if note is deleted successfully
     */
    public int deleteNoteById(Integer id) {
        return noteMapper.deleteNote(id);
    }


    public List<Note> getNotesByUserId(Integer userId) {
        return noteMapper.getNotesByUserId(userId);
    }


    public void displayNotes() {
        List<Note> notes = noteMapper.getNotes();
        for (Note note : notes) {
            System.out.println(note);
        }
    }


}
