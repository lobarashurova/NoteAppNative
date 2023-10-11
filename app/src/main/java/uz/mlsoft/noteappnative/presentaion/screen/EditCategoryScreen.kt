package uz.mlsoft.noteappnative.presentaion.screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.databinding.ScreenEditCategoriesBinding
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.EditCategpriesImpl
import uz.mlsoft.noteappnative.utils.finish


@AndroidEntryPoint
class EditCategoryScreen : Fragment(R.layout.screen_edit_categories) {
    private lateinit var viewModel: EditCategpriesImpl
    private val binding by viewBinding(ScreenEditCategoriesBinding::bind)
    private var isValidcategoryName = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditCategpriesImpl::class.java]
        viewModel.moveToMain.observe(this, moveToHomeObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = arguments?.getSerializable("data") as CategoryEntity
        binding.inputCategory.setText(data.category_name)
        binding.editBtn.setOnClickListener {
            viewModel.editCategory(CategoryEntity(data.id, binding.inputCategory.text.toString()))
        }

        binding.inputCategory.doAfterTextChanged {
            it?.let {
                isValidcategoryName = it.length > 4
                check()
            }
        }


        binding.backBtn.setOnClickListener {
            viewModel.clickBack()
        }
    }

    private val moveToHomeObserver = Observer<Unit> {
        finish()
    }

    private fun check() {
        binding.editBtn.isEnabled = isValidcategoryName
    }


}