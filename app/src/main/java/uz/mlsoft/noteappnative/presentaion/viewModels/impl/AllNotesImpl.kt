package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.domain.NoteRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.AllNotesViewModel
import javax.inject.Inject

@HiltViewModel
class AllNotesImpl @Inject constructor(
    private val repository: NoteRepository
) : ViewModel(), AllNotesViewModel {
    override val allNotesList = MutableLiveData<List<NoteEntity>>()
    override fun serachNotes(title: String) {

    }

    override fun clickBack() {
        backToHome.value = Unit
    }

    override fun updateData() {
        allNotesList.value = repository.getAllNotes()
        showPlaceHolder.value = repository.getAllNotes().isEmpty()
    }

    override val backToHome = MutableLiveData<Unit>()
    override val showPlaceHolder = MutableLiveData<Boolean>()

}