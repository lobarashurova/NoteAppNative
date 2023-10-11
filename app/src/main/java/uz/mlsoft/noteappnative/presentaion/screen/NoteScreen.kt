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
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.databinding.ScreenNotesBinding
import uz.mlsoft.noteappnative.presentaion.adapters.NotesAdapter
import uz.mlsoft.noteappnative.presentaion.dilaogs.DeleteNoteDialog
import uz.mlsoft.noteappnative.presentaion.viewModels.NotesViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.NotesViewModelImpl
import uz.mlsoft.noteappnative.utils.finish
import uz.mlsoft.noteappnative.utils.myTimber
import uz.mlsoft.noteappnative.utils.startFragment


@AndroidEntryPoint
class NoteScreen : Fragment(R.layout.screen_notes) {
    private val binding by viewBinding(ScreenNotesBinding::bind)
    private val viewModel: NotesViewModel by viewModels<NotesViewModelImpl>()
    private lateinit var data: CategoryEntity
    private val adapter by lazy { NotesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.moveToAddFragmentLiveData.observe(this, moveToAddContactPageObserver)
        viewModel.moveToHomeLivedata.observe(this, moveToHomeObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        data = arguments?.getSerializable("data") as CategoryEntity
        viewModel.updateList(data.id)
        initObservers()
        initAdapter()
        binding.button.setOnClickListener {
            viewModel.clickAdd(data.id)
        }
        binding.backBtn.setOnClickListener {
            viewModel.clickBack()
        }
        binding.categoryName.text = data.category_name
        viewModel.showPlaceHolderLiveData.observe(viewLifecycleOwner, showPlaceHolderObserver)

    }

    private fun initObservers() {
        viewModel.apply {
            notesListLivedata.observe(viewLifecycleOwner, notesListObserver)
            showPlaceHolderLiveData.observe(viewLifecycleOwner, showPlaceHolderObserver)
        }
    }

    private fun initAdapter() {
        binding.recyclerNotes.adapter = adapter
        adapter.setDeleteListener {
            val dialog = DeleteNoteDialog()
            dialog.setDeleteListener {
                viewModel.deleteNotes(it)
                viewModel.updateList(data.id)
                dialog.dismiss()
            }
            dialog.isCancelable = false
            dialog.show(parentFragmentManager, null)
        }


        adapter.setEditListener {
            viewModel.editNotes(it)
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            val fragment = EditNotesScreen()
            fragment.arguments = bundle
            startFragment(fragment)
        }
    }


    private val notesListObserver = Observer<List<NoteEntity>> {
        adapter.submitList(it)
        myTimber("screen list:${it.size}")
    }
    private val showPlaceHolderObserver = Observer<Boolean> {
        binding.placeHolderText.isVisible = it
    }
    private val moveToAddContactPageObserver = Observer<Int> {
        val bundle = Bundle()
        bundle.putInt("id", it)
        val fragment = AddNoteScreen()
        fragment.arguments = bundle
        startFragment(fragment)
    }
    private val moveToHomeObserver = Observer<Unit> {
        finish()
    }
}