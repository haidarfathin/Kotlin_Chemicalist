package com.projecth.chemicalist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClass
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        imageList = arrayOf(
            R.drawable.astatine,
            R.drawable.calcium,
            R.drawable.cesium,
            R.drawable.copper,
            R.drawable.darmstadtium,
            R.drawable.flerovium,
            R.drawable.fluorine,
            R.drawable.hafnium,
            R.drawable.neon,
            R.drawable.nickel,
            R.drawable.nitrogen,
            R.drawable.phosphorus,
            R.drawable.scandium,
            R.drawable.selenium,
            R.drawable.ytterbium
        )

        titleList = arrayOf(
            "Astatine",
            "Calcium",
            "Cesium",
            "Copper",
            "Darmstadtium",
            "Flerovium",
            "Fluorine",
            "Hafnium",
            "Neon",
            "Nickel",
            "Nitrogen",
            "Phosphorus",
            "Scandium",
            "Selenium",
            "Ytterbium"
        )

        detailImageList = arrayOf(
            R.drawable.astatine_detail,
            R.drawable.calcium_detail,
            R.drawable.cesium_detail,
            R.drawable.copper_detail,
            R.drawable.darmstadtium_detail,
            R.drawable.flerovium_detail,
            R.drawable.fluorine_detail,
            R.drawable.hafnium_detail,
            R.drawable.neon_detail,
            R.drawable.nickel_detail,
            R.drawable.nitrogen_detail,
            R.drawable.phosphorus_detail,
            R.drawable.scandium_detail,
            R.drawable.selenium_detail,
            R.drawable.ytterbium_detail
        )

        descList = arrayOf(
            getString(R.string.astatine),
            getString(R.string.calcium),
            getString(R.string.cesium),
            getString(R.string.copper),
            getString(R.string.darmstadtium),
            getString(R.string.flerovium),
            getString(R.string.fluorine),
            getString(R.string.hafnium),
            getString(R.string.neon),
            getString(R.string.nickel),
            getString(R.string.nitrogen),
            getString(R.string.phosphorus),
            getString(R.string.scandium),
            getString(R.string.selenium),
            getString(R.string.ytterbium)
        )

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText?.toLowerCase(Locale.getDefault()) ?: ""
                if (searchText.isNotEmpty()) {
                    dataList.forEach {
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                } else {
                    searchList.addAll(dataList)
                }
                recyclerView.adapter?.notifyDataSetChanged()
                return false
            }

        })

        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }
}