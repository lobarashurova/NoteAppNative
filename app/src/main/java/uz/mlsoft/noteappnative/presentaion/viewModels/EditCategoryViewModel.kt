package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData
import uz.mlsoft.noteappnative.data.entity.CategoryEntity

interface EditCategoryViewModel {
    fun editCategory(entity: CategoryEntity)
    val  moveToMain: LiveData<Unit>
    fun clickBack()
}