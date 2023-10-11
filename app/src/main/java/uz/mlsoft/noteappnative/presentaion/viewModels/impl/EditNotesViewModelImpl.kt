package uz.mlsoft.noteappnative.presentaion.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.domain.NoteRepository
import uz.mlsoft.noteappnative.presentaion.viewModels.EditNotesViewModel
import javax.inject.Inject

@HiltViewModel
class EditNotesViewModelImpl @Inject constructor(val repository: NoteRepository) : ViewModel(),
    EditNotesViewModel {

    override val moveToMainFragmentLiveData = MutableLiveData<Unit>()
    override fun onClickEdit(entity: NoteEntity) {
        repository.updateNotes(entity)
        moveToMainFragmentLiveData.value=Unit
    }


    override fun onClickExit() {
        moveToMainFragmentLiveData.value = Unit
    }

}