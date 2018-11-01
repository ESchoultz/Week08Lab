/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Note;

/**
 *
 * @author 669385
 */
public class NoteDB {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
       
        try {
            em.persist(note);
            et.commit();
        }catch (Exception e) {
            System.out.println(e);
            et.rollback();
        }finally {
            em.close();
        }
        
        return 1;
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        
        try {
            em.merge(note);
            trans.commit();
        }catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        }finally {
            em.close();
        }
        return 1;
    }

    public List<Note> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;
        }finally {
            em.close();
        }
    }

    public Note getNote(int noteId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteId);
            return note;
        }finally {
            em.close();
        }
    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(note));
            trans.commit();
        }catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        }finally {
            em.close();
        }
        return 1;
    }
}
