package uz.mlsoft.noteappnative.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.mlsoft.noteappnative.data.entity.CategoryEntity


@Dao
interface CategoryDao {
    @Query("SELECT * FROM categoriesTable")
    fun getAllCategories():List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(entity: CategoryEntity)


    @Query("delete from categoriesTable where id=:category_id ")
    fun deleteNotesByCategoryId(category_id: Int)
}