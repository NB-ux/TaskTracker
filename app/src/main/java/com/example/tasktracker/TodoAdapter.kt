package com.example.tasktracker

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todoitem, parent, false))
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{
            todo ->  todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(todotitleTV: TextView, isChecked: Boolean){
        if (isChecked){
            todotitleTV.paintFlags = todotitleTV.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            todotitleTV.paintFlags = todotitleTV.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.findViewById<TextView>(R.id.todotitleTV).text = curTodo.title
        holder.itemView.findViewById<CheckBox>(R.id.DoneCB).isChecked = curTodo.isChecked
        toggleStrikeThrough(holder.itemView.findViewById(R.id.todotitleTV), curTodo.isChecked)
        holder.itemView.findViewById<CheckBox>(R.id.DoneCB).setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.itemView.findViewById(R.id.todotitleTV), isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}