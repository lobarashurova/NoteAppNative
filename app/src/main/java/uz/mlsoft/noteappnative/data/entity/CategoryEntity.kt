package uz.mlsoft.noteappnative.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "categoriesTable")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category_name: String,
):Serializable