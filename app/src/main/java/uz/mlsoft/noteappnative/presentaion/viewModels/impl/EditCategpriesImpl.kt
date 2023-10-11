package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.domain.CategoryRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.EditCategoryViewModel
import javax.inject.Inject


@HiltViewModel
class EditCategpriesImpl @Inject constructor(
    private val repository: CategoryRepository
) :
    EditCategoryViewModel, ViewModel() {
    override fun editCategory(entity: CategoryEntity) {
        repository.updateCategories(entity)
        moveToMain.value = Unit
    }

    override val moveToMain = MutableLiveData<Unit>()

    override fun clickBack() {
        moveToMain.value = Unit
    }

}