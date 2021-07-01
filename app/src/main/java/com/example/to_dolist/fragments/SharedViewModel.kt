package com.example.to_dolist.fragments

import android.app.Application
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.to_dolist.R
import com.example.to_dolist.data.models.Priority
import com.example.to_dolist.data.models.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(todoData: List<ToDoData>) {
        emptyDatabase.value = todoData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object:
        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position) {
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(Color.RED)}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(Color.MAGENTA )}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(Color.GREEN)}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when(priority) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> Priority.LOW
        }
    }

    fun parsePriorityToInt(priority: Priority): Int = when (priority) {
        Priority.HIGH -> 0
        Priority.MEDIUM -> 1
        Priority.LOW -> 2
    }
}