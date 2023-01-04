package com.example.acronyms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.acronyms.R
import com.example.acronyms.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val acronymViewModel by viewModels<AcronymViewModel>()
    private lateinit var adapter: AcronymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = AcronymAdapter()
        binding.also {
            it.lifecycleOwner = this@MainActivity
            it.viewModel = acronymViewModel
            it.adapter = adapter
            it.acronymRv.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        acronymViewModel.dataState.observe(this) { state ->
            if (state.error != null) {
                Toast.makeText(this, state.error, Toast.LENGTH_LONG).show()
                acronymViewModel.onErrorShown()
            }
            if (state.data?.isNotEmpty() == true) {
                adapter.submitList(state.data[0].lfs)
            }
        }
    }
}