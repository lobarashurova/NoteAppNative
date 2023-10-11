package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.domain.NoteRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.NotesViewModel
import uz.mlsoft.noteappnative.utils.myTimber
import javax.inject.Inject


@HiltViewModel
class NotesViewModelImpl @Inject constructor(
    private val repository: NoteRepository
) : NotesViewModel, ViewModel() {

    override val notesListLivedata = MutableLiveData<List<NoteEntity>>()

    override val showPlaceHolderLiveData = MutableLiveData<Boolean>()

    override val moveToAddFragmentLiveData = MutableLiveData<Int>()
    override val moveToHomeLivedata = MutableLiveData<Unit>()

    override fun clickAdd(categoryId: Int) {
        moveToAddFragmentLiveData.value = categoryId
    }

    override fun deleteNotes(notesEntity: NoteEntity) {
        repository.deleteNotes(notesEntity)
    }

    override fun editNotes(notesEntity: NoteEntity) {
        repository.updateNotes(notesEntity)
    }

    override fun setTime(time: Long) {

    }

    override fun updateList(categoryId: Int) {
        val notesList = repository.getAllNotesById(categoryId)
        notesListLivedata.value = notesList
        myTimber("viewModel size${notesList.size}")
        showPlaceHolderLiveData.value = notesList.isEmpty()
    }

    override fun clickBack() {
        moveToHomeLivedata.value = Unit
    }
}