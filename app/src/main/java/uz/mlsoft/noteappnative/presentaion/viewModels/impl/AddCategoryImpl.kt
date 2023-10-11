package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.domain.CategoryRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.AddCategoryViewModel
import javax.inject.Inject

@HiltViewModel
class AddCategoryImpl @Inject constructor(private val repository: CategoryRepository) :
    AddCategoryViewModel, ViewModel() {

    override fun addCategory(name: String) {
        repository.insertCategories(CategoryEntity(0, name))
        backToHome.value = Unit
    }

    override val backToHome = MutableLiveData<Unit>()
    override fun clickBack() {
        backToHome.value = Unit
    }
}