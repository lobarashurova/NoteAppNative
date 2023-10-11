package uz.mlsoft.noteappnative.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.databinding.ItemCategoriesBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CategoryAdapter :
    ListAdapter<CategoryEntity, CategoryAdapter.NotesViewHolder>(NotesDiffUtil) {
    private var dialogListener: ((ategory: CategoryEntity) -> Unit)? = null
    private var noteListener: ((category: CategoryEntity) -> Unit)? = null
    private var setRealTime: ((time: Long) -> Unit)? = null

    fun setDialogListener(block: (ategory: CategoryEntity) -> Unit) {
        dialogListener = block
    }


    fun setNoteListener(block: (CategoryEntity) -> Unit) {
        noteListener = block
    }


    object NotesDiffUtil : DiffUtil.ItemCallback<CategoryEntity>() {
        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean =
            oldItem == newItem
    }

    inner class NotesViewHolder(private val binding: ItemCategoriesBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.categoryName.text = getItem(position).category_name
        }

        init {
            binding.root.setOnLongClickListener {
                dialogListener?.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }

            binding.root.setOnClickListener {
                noteListener?.invoke(getItem(adapterPosition))
            }
            //            binding.textHour.text=getItem(adapterPosition).time.toString()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) = holder.bind(position)
}