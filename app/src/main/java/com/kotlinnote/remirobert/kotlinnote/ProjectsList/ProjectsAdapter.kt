package com.kotlinnote.remirobert.kotlinnote.ProjectsList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlinnote.remirobert.kotlinnote.Domain.Project
import com.kotlinnote.remirobert.kotlinnote.R

/**
 * Created by remirobert on 03/03/2018.
 */
class ProjectsAdapter(private val projects: ArrayList<Project>, private val context: Context):
        RecyclerView.Adapter<ProjectHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProjectHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.project_row, parent, false)
        return ProjectHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectHolder?, position: Int) {
        holder?.bind(projects[position])
    }

    override fun getItemCount(): Int {
        return projects.count()
    }
}
