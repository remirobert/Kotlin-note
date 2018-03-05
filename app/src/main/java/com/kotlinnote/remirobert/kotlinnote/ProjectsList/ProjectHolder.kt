package com.kotlinnote.remirobert.kotlinnote.ProjectsList

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.kotlinnote.remirobert.kotlinnote.Domain.Project
import com.kotlinnote.remirobert.kotlinnote.R

/**
 * Created by remirobert on 04/03/2018.
 */

class ProjectHolder(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(project: Project) = with(itemView) {
        val nameTextView = findViewById<TextView>(R.id.task_name)
        nameTextView.text = project.name
    }
}