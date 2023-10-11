package uz.mlsoft.noteappnative.presentaion.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.databinding.ScreenHomeBinding
import uz.mlsoft.noteappnative.presentaion.adapters.CategoryAdapter
import uz.mlsoft.noteappnative.presentaion.dilaogs.DeleteCategoryDialog
import uz.mlsoft.noteappnative.presentaion.viewModels.HomeViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.HomeViewModelImpl
import uz.mlsoft.noteappnative.utils.startFragment

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private lateinit var viewModel: HomeViewModel
    private val adapter by lazy { CategoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModelImpl::class.java]
        viewModel.moveToAllNotesScreen.observe(this, moveToAllNoteScreenObserver)
        viewModel.moveToNoteScreen.observe(this, moveToNoteScreenObserver)
        viewModel.moveToAddScreenLiveData.observe(this, moveToAddScreenObserver)
        viewModel.moveToUpdateScreen.observe(this, moveToEditScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        onClicks()
        viewModel.loadData()
        binding.homeLayout.addButton.setOnClickListener { viewModel.clickAdd() }
        viewModel.categoriesListLiveaData.observe(viewLifecycleOwner, categoriesListObserver)
        viewModel.showPlaceHolder.observe(viewLifecycleOwner, placeHolderObserver)


    }

    private fun initAdapter() {
        binding.homeLayout.categoryRecycle.adapter = adapter
        adapter.setDialogListener {
            val dialog = DeleteCategoryDialog()
            dialog.setDeleteListener {
                viewModel.deleteCategory(it.id)
                dialog.dismiss()
                viewModel.loadData()
                Toast.makeText(requireContext(), "Category is deleted!", Toast.LENGTH_SHORT).show()
            }

            dialog.setEditListener {
                viewModel.clickEdit(it)
                dialog.dismiss()
                viewModel.loadData()
            }

            dialog.isCancelable = true
            dialog.show(parentFragmentManager, null)
        }

        adapter.setNoteListener {
            viewModel.clickNote(it)
        }
    }

    private val moveToEditScreenObserver = Observer<CategoryEntity> {
        val bundle = Bundle()
        bundle.putSerializable("data", it)
        val fragment = EditCategoryScreen()
        fragment.arguments = bundle
        startFragment(fragment)
    }
    private val moveToAddScreenObserver = Observer<Unit> {
        startFragment(AddCategoryScreen())
    }
    private val moveToNoteScreenObserver = Observer<CategoryEntity> {
        val bundle = Bundle()
        bundle.putSerializable("data", it)
        val fragment = NoteScreen()
        fragment.arguments = bundle
        startFragment(fragment)
    }
    private val moveToAllNoteScreenObserver = Observer<Unit> {
        startFragment(AllNotesScreen())
    }

    private val categoriesListObserver = Observer<List<CategoryEntity>> {
        binding.homeLayout.categoryRecycle.adapter = adapter
        adapter.submitList(it)
    }
    private val placeHolderObserver = Observer<Boolean> {
        binding.homeLayout.placeHolderText.isVisible = it
    }

    private fun onClicks() {
        binding.homeLayout.menuBtn.setOnClickListener {
            toggleLeftDrawer()
        }
        binding.menuLayout.allNotes.setOnClickListener {
            viewModel.clickAllNotesButton()
        }
        binding.menuLayout.infoLinear.setOnClickListener {
            startFragment(InfoScreen())
        }
        binding.menuLayout.rateAppLinear.setOnClickListener {
            rateApp()
        }
        binding.menuLayout.shareAppLinear.setOnClickListener {
            shareProject(requireContext())
        }

        binding.menuLayout.toContactLinear.setOnClickListener {
            gotoLink("https://t.me/astrogirll06")
        }
    }

    private fun gotoLink(s: String) {
        val uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun toggleLeftDrawer() {
        binding.apply {
            if (drawerLayout.isDrawerOpen(menuLayout.root)) {
                drawerLayout.closeDrawer(menuLayout.root)
            } else {
                drawerLayout.openDrawer(menuLayout.root)
            }
        }

    }

    private fun rateApp() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(
            "https://play.google.com/store/apps/details?id={context?applicationContext?.packageName"
        )
        requireActivity().startActivity(i)
    }

    private fun shareProject(context: Context) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Dowloand now!: " + "https://play.google.com/store/apps/details?id=${context.applicationContext?.packageName}"
        )
        sendIntent.type = "text/plain"
        context.startActivity(sendIntent)
    }
}