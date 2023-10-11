package uz.mlsoft.noteappnative.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.mlsoft.noteappnative.data.entity.NoteEntity


@Dao
interface NoteDao {
    @Query("SELECT * FROM notesTable")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notesTable where category_id=:category_id")
    fun getNotesByCategoryId(category_id: Int): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotesToCategories(notesEntity: NoteEntity)

    @Delete
    fun deleteNote(notesEntity: NoteEntity)


}