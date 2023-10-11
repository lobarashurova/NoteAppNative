package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData
import uz.mlsoft.noteappnative.data.entity.NoteEntity

interface NotesViewModel {
    val notesListLivedata: LiveData<List<NoteEntity>>
    val showPlaceHolderLiveData: LiveData<Boolean>
    val moveToAddFragmentLiveData: LiveData<Int>
    val moveToHomeLivedata: LiveData<Unit>


    fun clickAdd(categoryId: Int)
    fun deleteNotes(notesEntity: NoteEntity)
    fun editNotes(notesEntity: NoteEntity)
    fun setTime(time: Long)
    fun updateList(categoryId: Int)
    fun clickBack()
}