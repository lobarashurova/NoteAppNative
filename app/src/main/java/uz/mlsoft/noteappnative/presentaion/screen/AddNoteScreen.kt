package uz.mlsoft.noteappnative.presentaion.screen

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.ITextFormat
import org.wordpress.aztec.toolbar.IAztecToolbarClickListener
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.databinding.ScreenAddNotesBinding
import uz.mlsoft.noteappnative.presentaion.viewModels.AddNotesViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.AddNoteImpl
import uz.mlsoft.noteappnative.utils.finish

@AndroidEntryPoint
class AddNoteScreen : Fragment(R.layout.screen_add_notes) {
    private val binding by viewBinding(ScreenAddNotesBinding::bind)
    private val viewModel: AddNotesViewModel by viewModels<AddNoteImpl>()
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.moveToHome.observe(this, moveToHomeObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = arguments?.getInt("id")!!
        binding.visual.textSize = 23f
        binding.visual.setPadding(10, 10, 10, 10)
        binding.visual.setBackgroundColor(resources.getColor(R.color.colorPrimaryLight))
        binding.saveBtn.setOnClickListener {
            if (binding.visual.text.isNotEmpty() && binding.titleInput.text!!.length > 3) {
                viewModel.addNoteCategory(
                    id,
                    binding.titleInput.text.toString(),
                    binding.visual.toFormattedHtml()
                )
            } else if (binding.visual.text.isEmpty()) {
                binding.visual.error = "Note should not be empty"
            } else if (binding.titleInput.text!!.length < 3) {
                binding.titleInput.error = "title length should be longer than 3"
            }
        }
        binding.exitBtn.setOnClickListener {
            showFinishDialog()
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

    private val moveToHomeObserver = Observer<Unit> {
        finish()
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
            viewModel.clickBack()
            dialog_correct.dismiss()
        }
    }


}