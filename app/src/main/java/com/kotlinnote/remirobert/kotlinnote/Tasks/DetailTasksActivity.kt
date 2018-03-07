package com.kotlinnote.remirobert.kotlinnote.Tasks

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlinnote.remirobert.kotlinnote.LoginActivity
import com.kotlinnote.remirobert.kotlinnote.R

class DetailTasksActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DetailTasksActivity::class.java)
        }
    }

    private val recyclerView: RecyclerView by lazy {
        createRecycleView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tasks)
        setupRecycleView()
    }

    private fun createRecycleView() = findViewById<RecyclerView>(R.id.recycle_view_tasks)

    private fun setupRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
