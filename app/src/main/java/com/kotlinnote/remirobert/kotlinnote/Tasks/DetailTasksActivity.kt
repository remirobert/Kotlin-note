package com.kotlinnote.remirobert.kotlinnote.Tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlinnote.remirobert.kotlinnote.R

class DetailTasksActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        createRecycleView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tasks)
    }

    private fun createRecycleView() = findViewById<RecyclerView>(R.id.recycle_view_tasks)

    private fun setupRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}
