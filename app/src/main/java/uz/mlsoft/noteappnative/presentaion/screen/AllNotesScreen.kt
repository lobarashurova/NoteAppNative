package uz.mlsoft.noteappnative.presentaion.screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.databinding.ScreenAllNotesBinding
import uz.mlsoft.noteappnative.presentaion.adapters.NotesAdapter
import uz.mlsoft.noteappnative.presentaion.dilaogs.DeleteNoteDialog
import uz.mlsoft.noteappnative.presentaion.viewModels.AllNotesViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.NotesViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.AllNotesImpl
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.NotesViewModelImpl
import uz.mlsoft.noteappnative.utils.finish
import uz.mlsoft.noteappnative.utils.startFragment

@AndroidEntryPoint
class AllNotesScreen : Fragment(R.layout.screen_all_notes) {
    private val binding by viewBinding(ScreenAllNotesBinding::bind)
    private val viewModel: AllNotesViewModel by viewModels<AllNotesImpl>()
    private val viewModelNote: NotesViewModel by viewModels<NotesViewModelImpl>()
    private val adapter by lazy { NotesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backToHome.observe(this, backObsever)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        viewModel.updateData()
        viewModel.allNotesList.observe(viewLifecycleOwner, listObserver)
        viewModel.showPlaceHolder.observe(viewLifecycleOwner, placeHolderObserver)
        binding.backBtn.setOnClickListener {
            viewModel.clickBack()
        }

    }

    private fun initAdapter() {
        binding.allNotesRecyler.adapter = adapter
        adapter.setDeleteListener {
            val dialog = DeleteNoteDialog()
            dialog.setDeleteListener {
                viewModelNote.deleteNotes(it)
                viewModel.updateData()
                dialog.dismiss()
            }
            dialog.isCancelable = false
            dialog.show(parentFragmentManager, null)
        }


        adapter.setEditListener {
            viewModelNote.editNotes(it)
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            val fragment = EditNotesScreen()
            fragment.arguments = bundle
            startFragment(fragment)
        }
    }

    private val listObserver = Observer<List<NoteEntity>> {
        adapter.submitList(it)
    }

    private val backObsever = Observer<Unit> {
        finish()
    }
    private val placeHolderObserver = Observer<Boolean> {
        binding.placeHolderText.isVisible = it
    }
}