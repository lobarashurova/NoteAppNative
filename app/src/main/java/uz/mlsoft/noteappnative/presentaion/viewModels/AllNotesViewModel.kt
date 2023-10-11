package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData
import uz.mlsoft.noteappnative.data.entity.NoteEntity

interface AllNotesViewModel {
    val allNotesList: LiveData<List<NoteEntity>>
    val backToHome: LiveData<Unit>
    val showPlaceHolder: LiveData<Boolean>


    fun serachNotes(title: String)
    fun clickBack()
    fun updateData()
}