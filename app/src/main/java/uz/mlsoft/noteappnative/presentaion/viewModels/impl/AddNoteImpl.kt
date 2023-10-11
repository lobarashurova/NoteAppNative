package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.domain.NoteRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.AddNotesViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(), AddNotesViewModel {
    override val moveToHome = MutableLiveData<Unit>()
    override fun addNoteCategory(categoryId: Int, title: String, note: String) {
        noteRepository.insertNotes(
            NoteEntity(
                note_id = 0,
                category_id = categoryId, title = title, note = note
            )
        )
        moveToHome.value = Unit
    }

    override fun clickBack() {
        moveToHome.value = Unit
    }
}