package uz.mlsoft.noteappnative.domain.impl

import uz.mlsoft.noteappnative.data.dao.NoteDao
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.domain.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override fun getAllNotes(): List<NoteEntity> = noteDao.getAllNotes()
    override fun getAllNotesById(category_id: Int): List<NoteEntity> =
        noteDao.getNotesByCategoryId(category_id = category_id)

    override fun insertNotes(notesEntity: NoteEntity) {
        noteDao.insertNotesToCategories(notesEntity = notesEntity)
    }

    override fun updateNotes(notesEntity: NoteEntity) {
        noteDao.insertNotesToCategories(notesEntity = notesEntity)
    }

    override fun deleteNotes(notesEntity: NoteEntity) {
        noteDao.deleteNote(notesEntity)
    }
}