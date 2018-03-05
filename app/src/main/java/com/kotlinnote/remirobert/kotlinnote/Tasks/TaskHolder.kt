package com.kotlinnote.remirobert.kotlinnote.Tasks

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.kotlinnote.remirobert.kotlinnote.Domain.Task
import com.kotlinnote.remirobert.kotlinnote.R

/**
 * Created by remirobert on 04/03/2018.
 */

class TaskHolder(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(task: Task) = with(itemView) {
        val nameTextView = findViewById<TextView>(R.id.task_name)
        val checkBox = findViewById<CheckBox>(R.id.checkbox_completed)

        nameTextView.text = task.name
        checkBox.isChecked = task.isCompleted
    }
}