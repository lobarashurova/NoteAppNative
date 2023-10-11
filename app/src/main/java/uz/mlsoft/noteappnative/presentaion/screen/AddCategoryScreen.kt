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
import uz.mlsoft.noteappnative.databinding.ScreenAddCategoriesBinding
import uz.mlsoft.noteappnative.presentaion.viewModels.AddCategoryViewModel
import uz.mlsoft.noteappnative.presentaion.viewModels.impl.AddCategoryImpl
import uz.mlsoft.noteappnative.utils.finish


@AndroidEntryPoint
class AddCategoryScreen : Fragment(R.layout.screen_add_categories) {
    private lateinit var viewModel: AddCategoryViewModel
    private val binding by viewBinding(ScreenAddCategoriesBinding::bind)
    private var isValidCategoryName = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddCategoryImpl::class.java]
        viewModel.backToHome.observe(this, moveToHomeObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addCategoryBtn.setOnClickListener {
            viewModel.addCategory(binding.inputCategory.text.toString())
        }

        binding.inputCategory.doAfterTextChanged {
            it?.let {
                isValidCategoryName = it.length > 4
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
        binding.addCategoryBtn.isEnabled = isValidCategoryName
    }
}