/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.NoteDB;
import database.NotesDBException;
import java.util.Date;
import java.util.List;
import model.Note;

/**
 *
 * @author 669385
 */
public class NoteServices {
    
    private NoteDB noteDB;
    
    public NoteServices() {
        noteDB = new NoteDB();
    }
    
    public Note get(int noteId) throws NotesDBException {
        return noteDB.getNote(noteId);
    }
    
    public List<Note> getAll() throws NotesDBException {
        return noteDB.getAll();
    }
    
    public int update(int noteId, String contents) throws NotesDBException {
        Note note = noteDB.getNote(noteId);
        note.setContents(contents);
        return noteDB.update(note);
    }
    
    public int delete(int noteId) throws NotesDBException {
        Note deletedNote = noteDB.getNote(noteId);
        return noteDB.delete(deletedNote);
    }
    
    public int insert(String contents) throws NotesDBException {
        
        Note newNote = new Note(0, new Date(), contents);
        return noteDB.insert(newNote);
    }

}