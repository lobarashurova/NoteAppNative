package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData

interface AddCategoryViewModel {
    fun addCategory(name: String)
    val backToHome: LiveData<Unit>
    fun clickBack()
}