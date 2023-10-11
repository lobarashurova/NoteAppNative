package uz.mlsoft.noteappnative.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.mlsoft.noteappnative.data.dao.CategoryDao
import uz.mlsoft.noteappnative.data.dao.NoteDao
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.data.entity.NoteEntity


@Database(entities = [NoteEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase :RoomDatabase() {
    abstract fun getNotesDao(): NoteDao
    abstract fun getCategoryDao(): CategoryDao
}