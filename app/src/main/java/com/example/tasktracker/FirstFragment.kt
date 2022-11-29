package com.example.tasktracker

import android.os.Bundle
//import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FirstFragment : Fragment() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val infoBtn : ImageButton = view.findViewById(R.id.infoButton)
        infoBtn.setOnClickListener{
            val fragment = InfoFragment() //navigoi toiselle fragmentille
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container,fragment)?.commit()
        }

        todoAdapter = TodoAdapter(mutableListOf())
        val rvi : RecyclerView = view.findViewById(R.id.todoitemsRV)
        val btnadd : Button = view.findViewById(R.id.addTodobtn)
        val btndelete : Button = view.findViewById(R.id.deleteTodobtn)
        val et : EditText = view.findViewById(R.id.entertextET)

        rvi.adapter = todoAdapter
        rvi.layoutManager = LinearLayoutManager(context)

        btnadd.setOnClickListener {
            val todoTitle = et.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                et.text.clear()
            }

        }

        btndelete.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }


        return view
    }


}
