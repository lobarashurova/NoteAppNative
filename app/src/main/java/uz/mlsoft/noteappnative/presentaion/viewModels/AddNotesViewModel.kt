package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData

interface AddNotesViewModel {
    fun addNoteCategory(categoryId: Int, title: String, note: String)
    val moveToHome: LiveData<Unit>
    fun clickBack()
}