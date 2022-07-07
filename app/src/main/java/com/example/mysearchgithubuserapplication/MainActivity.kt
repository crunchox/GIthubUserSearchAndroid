package com.example.mysearchgithubuserapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        hideProgress()
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty())
                    Toast.makeText(applicationContext, "Tidak boleh kosong", Toast.LENGTH_SHORT)
                        .show()
                else {
                    showProgress()
                    searchUser(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

        })

        searchAdapter = SearchAdapter(applicationContext)
        recycle_view.layoutManager = LinearLayoutManager(applicationContext)
        recycle_view.adapter = searchAdapter
        recycle_view.setHasFixedSize(true)
    }

    private fun showProgress(){
        progress_circular.visibility = View.VISIBLE
        recycle_view.visibility = View.GONE
    }

    private fun hideProgress(){
        progress_circular.visibility = View.GONE
        recycle_view.visibility = View.VISIBLE
    }

    private fun searchUser(query: String) {
        val searchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)
        searchViewModel.setSearchUser(query)
        searchViewModel.getResultList().observe(
            this
        ) { items ->
            hideProgress()
            if (items.size != 0) {
                searchAdapter.setSearchUserList(items)
            } else
                Toast.makeText(applicationContext, "Pengguna Tidak Ditemukan!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}