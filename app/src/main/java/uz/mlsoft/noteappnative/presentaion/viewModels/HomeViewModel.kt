package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData
import uz.mlsoft.noteappnative.data.entity.CategoryEntity

interface HomeViewModel {
    val moveToUpdateScreen: LiveData<CategoryEntity>
    val moveToAllNotesScreen: LiveData<Unit>
    val moveToNoteScreen: LiveData<CategoryEntity>
    val categoriesListLiveaData: LiveData<List<CategoryEntity>>
    val moveToAddScreenLiveData: LiveData<Unit>
    val showPlaceHolder: LiveData<Boolean>

    fun deleteCategory(categoryId: Int)
    fun loadData()
    fun clickEdit(entity: CategoryEntity)
    fun clickAllNotesButton()
    fun clickNote(entity: CategoryEntity)
    fun clickAdd()


}