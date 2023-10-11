package uz.mlsoft.noteappnative.presentaion.viewModels

import androidx.lifecycle.LiveData
import uz.mlsoft.noteappnative.data.entity.NoteEntity


interface EditNotesViewModel {
    val moveToMainFragmentLiveData: LiveData<Unit>
    fun onClickEdit(entity: NoteEntity)
    fun onClickExit()

}