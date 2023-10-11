package uz.mlsoft.noteappnative.presentaion.screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.ITextFormat
import org.wordpress.aztec.toolbar.IAztecToolbarClickListener
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.databinding.ScreenEditNotesBinding
import uz.mlsoft.noteappnative.presentaion.viewModels.EditNotesViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.EditNotesViewModelImpl
import uz.mlsoft.noteappnative.utils.finish
import uz.mlsoft.noteappnative.utils.setHtml


@AndroidEntryPoint
class EditNotesScreen : Fragment(R.layout.screen_edit_notes) {
    private val binding by viewBinding(ScreenEditNotesBinding::bind)
    private lateinit var viewModel: EditNotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditNotesViewModelImpl::class.java]
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModels()

        binding.exitBtn.setOnClickListener {
            showFinishDialog()
        }
        binding.visual.textSize = 23f
        binding.visual.setPadding(10, 10, 10, 10)
        binding.visual.setBackgroundColor(resources.getColor(R.color.colorPrimaryLight))
        val edit = arguments?.getSerializable("data") as NoteEntity
        binding.visual.setHtml(edit.note)
        binding.titleInput.setText(edit.title)


        binding.saveBtn.setOnClickListener {
            if (binding.visual.text.isNotEmpty() && binding.titleInput.text!!.length > 3) {
                viewModel.onClickEdit(
                    NoteEntity(
                        edit.note_id, edit.category_id,
                        binding.titleInput.text.toString(),
                        binding.visual.toFormattedHtml()
                    )
                )
            } else if (binding.visual.text.isEmpty()) {
                binding.visual.error = "Note should not be empty"
            } else if (binding.titleInput.text!!.length < 3) {
                binding.titleInput.error = "title length should be longer than 3"
            }
        }


        binding.apply {
            Aztec.with(visual, tools, object : IAztecToolbarClickListener {
                override fun onToolbarCollapseButtonClicked() {}

                override fun onToolbarExpandButtonClicked() {}

                override fun onToolbarFormatButtonClicked(
                    format: ITextFormat, isKeyboardShortcut: Boolean
                ) {
                }

                override fun onToolbarHeadingButtonClicked() {}

                override fun onToolbarHtmlButtonClicked() {}

                override fun onToolbarListButtonClicked() {}

                override fun onToolbarMediaButtonClicked(): Boolean {
                    return false
                }

            })
        }
    }

    private fun initModels() {
        viewModel.apply {
            moveToMainFragmentLiveData.observe(viewLifecycleOwner) {
                finish()
            }
        }
    }

    private fun showFinishDialog() {
        val dialog_correct = Dialog(requireContext())
        dialog_correct.setContentView(R.layout.diallog_finish)
        dialog_correct.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog_correct.setCancelable(false)
        dialog_correct.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog_correct.show()

        val finishBtn = dialog_correct.findViewById<AppCompatButton>(R.id.exit_btn_finish)
        val nextBtn = dialog_correct.findViewById<AppCompatButton>(R.id.cancel_btn_finish)

        nextBtn.setOnClickListener {
            dialog_correct.dismiss()
        }

        finishBtn.setOnClickListener {
            finish()
            dialog_correct.dismiss()
        }
    }

}