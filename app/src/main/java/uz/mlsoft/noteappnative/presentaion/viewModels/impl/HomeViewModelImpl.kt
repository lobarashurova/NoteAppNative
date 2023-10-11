package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.domain.CategoryRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: CategoryRepository
) : HomeViewModel, ViewModel() {


    override val moveToUpdateScreen = MutableLiveData<CategoryEntity>()
    override val moveToAllNotesScreen = MutableLiveData<Unit>()
    override val moveToNoteScreen = MutableLiveData<CategoryEntity>()

    override val categoriesListLiveaData = MutableLiveData<List<CategoryEntity>>()
    override val moveToAddScreenLiveData = MutableLiveData<Unit>()
    override val showPlaceHolder = MutableLiveData<Boolean>()

    override fun deleteCategory(categoryId: Int) {
        repository.deleteCategories(categoryId)
    }


    override fun loadData() {
        categoriesListLiveaData.value = repository.getAllCategories()
        showPlaceHolder.value=repository.getAllCategories().isEmpty()

    }

    override fun clickEdit(entity: CategoryEntity) {
        moveToUpdateScreen.value = entity
    }


    override fun clickAllNotesButton() {
        moveToAllNotesScreen.value = Unit
    }

    override fun clickNote(entity: CategoryEntity) {
        moveToNoteScreen.value = entity
    }


    override fun clickAdd() {
        moveToAddScreenLiveData.value = Unit
    }


}