package com.kotlinnote.remirobert.kotlinnote.ProjectsList

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlinnote.remirobert.kotlinnote.Domain.Project
import com.kotlinnote.remirobert.kotlinnote.R
import com.kotlinnote.remirobert.kotlinnote.Tasks.DetailTasksActivity


class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "MainActivity"
    }

    private val recyclerView: RecyclerView by lazy {
        createRecycleView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleView()
    }

    private fun setupRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProjectsAdapter(prepareListTasks(), {project ->  
            showProjectTasksActivity(project)
        })
    }

    private fun prepareListTasks(): ArrayList<Project> {
        val list = ArrayList<Project>()
        list.add(Project("task1"))
        list.add(Project("task2"))
        list.add(Project("task3"))
        return list
    }

    private fun createRecycleView(): RecyclerView = findViewById(R.id.recycle_view_tasks)

    private fun showProjectTasksActivity(project: Project) {
        val intent = DetailTasksActivity.newIntent(this)
        startActivity(intent)
    }
}
