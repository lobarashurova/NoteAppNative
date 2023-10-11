package uz.mlsoft.noteappnative.presentaion.dilaogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.databinding.DialogDeleteNoteBinding

class DeleteNoteDialog : BottomSheetDialogFragment() {
    private val binding by viewBinding(DialogDeleteNoteBinding::bind)
    private lateinit var deleteListener: () -> Unit
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_delete_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.deleteBtn.setOnClickListener {
            deleteListener.invoke()
        }
        binding.cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }
    }

    fun setDeleteListener(block: (() -> Unit)) {
        this.deleteListener = block
    }
}