package com.github.cagatayalt.plainnotes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.cagatayalt.plainnotes.databinding.RowViewBinding
import com.github.cagatayalt.plainnotes.room.NotesTable


class RvAdapter(val context: Context, val list: List<NotesTable>, val listener:MyInterface) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    class ViewHolder(binding: RowViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.titleTv
        val desc = binding.descriptionTv
        val delete = binding.deleteIconIV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_view,parent,false)
        return ViewHolder(RowViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list.get(position)
        holder.title.text = model.title
        holder.desc.text = model.description

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(context, AddNotesActivity::class.java).putExtra("type","Update")
                .putExtra("id",model.id)
                .putExtra("title",model.title)
                .putExtra("desc",model.description)
            )
        }

        holder.delete.setOnClickListener {
            listener.onClick(model.id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}