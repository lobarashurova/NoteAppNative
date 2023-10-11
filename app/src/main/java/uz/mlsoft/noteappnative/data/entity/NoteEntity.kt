package uz.mlsoft.noteappnative.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "notesTable",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        childColumns = ["category_id"],
        parentColumns = ["id"],
        onDelete = ForeignKey.CASCADE,
    )]
)

data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val note_id: Int,
    val category_id: Int,
    val title: String,
    val note: String,
) : Serializable