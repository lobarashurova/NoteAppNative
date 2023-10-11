package uz.mlsoft.noteappnative.domain

import uz.mlsoft.noteappnative.data.entity.NoteEntity

interface NoteRepository {
    fun getAllNotes(): List<NoteEntity>
    fun getAllNotesById(category_id: Int): List<NoteEntity>
    fun insertNotes(notesEntity: NoteEntity)
    fun updateNotes(notesEntity: NoteEntity)
    fun deleteNotes(notesEntity: NoteEntity)
}