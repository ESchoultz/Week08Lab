/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.NotesDBException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.NoteServices;

/**
 *
 * @author 669385
 */
public class NoteServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/note.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int noteId;
        String contents;
        noteId = Integer.parseInt(request.getParameter("noteToDelete"));
        NoteServices ns = new NoteServices();

        if (request.getParameter("add") != null) {
            noteId = Integer.parseInt(request.getParameter("upNoteId"));
            contents = request.getParameter("upNoteBody");
            try {
                ns.update(noteId, contents);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("delete") != null) {

            try {
                ns.delete(noteId);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("add") != null) {
            contents = request.getParameter("newNoteBody");

            try {
                ns.insert(contents);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("edit") != null) {
            try {
                request.setAttribute("selectedNote", ns.get(Integer.parseInt(request.getParameter("noteToEdit"))));
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            request.setAttribute("notes", ns.getAll());
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/note.jsp").forward(request, response);
    }
}
