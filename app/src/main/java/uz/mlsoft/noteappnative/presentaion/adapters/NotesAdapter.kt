package uz.mlsoft.noteappnative.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.mlsoft.noteappnative.data.entity.NoteEntity
import uz.mlsoft.noteappnative.databinding.ItemNotesBinding
import uz.mlsoft.noteappnative.utils.setHtml
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.NotesViewHolder>(NotesDiffUtil) {
    private var editListener: ((note: NoteEntity) -> Unit)? = null
    private var deleteListener: ((note: NoteEntity) -> Unit)? = null
    private var setRealTime: ((time: Long) -> Unit)? = null

    fun setEditListener(block: (NoteEntity) -> Unit) {
        editListener = block
    }

    fun setDeleteListener(block: (NoteEntity) -> Unit) {
        deleteListener = block
    }

    object NotesDiffUtil : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem.note_id == newItem.note_id

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem == newItem
    }

    inner class NotesViewHolder(private val binding: ItemNotesBinding) : ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.noteText.setHtml(getItem(position).note)
            binding.titleNote.text = (getItem(position).title)
        }

        init {
            binding.root.setOnLongClickListener {
                deleteListener?.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }
            binding.root.setOnClickListener {
                editListener?.invoke(getItem(adapterPosition))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) = holder.bind(position)
}