package com.example.gfgassignment

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gfgassignment.adapters.NewsViewAdapter
import com.example.gfgassignment.models.Items
import com.example.gfgassignment.viewmodel.MainViewModel
import android.net.ConnectivityManager




class MainActivity : AppCompatActivity() {
    lateinit var context : Context
    lateinit var mainviewmodel : MainViewModel
    var refreshdata : Boolean = true;
    lateinit var wpprogressBar : ProgressBar
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity
        mainviewmodel = ViewModelProvider(this).get(MainViewModel :: class.java)

        val itemsswipetorefresh = findViewById<SwipeRefreshLayout>(R.id.itemsswipetorefresh)
        wpprogressBar= findViewById(R.id.wpprogressBar)
        recyclerView = findViewById(R.id.recyclerView)
        if(checkConnection(context)){
            loaddata()
        }else{
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
        }


        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.teal_200))
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

        itemsswipetorefresh.setOnRefreshListener {
            if(checkConnection(context)){
                refreshdata = true;
                loaddata()
                Toast.makeText(context, "Page Refreshed Successfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
            }
            itemsswipetorefresh.isRefreshing = false

        }
    }

    private fun loaddata() {

        wpprogressBar.isVisible = true

        mainviewmodel.getNews()!!.observe(this, Observer { Newslist ->

            if(refreshdata){
                wpprogressBar.isVisible = false


                if(Newslist.status == "ok"){
                    val itemlist : List<Items> = Newslist.items

                    prepareadapter(itemlist)
                }else{
                    Toast.makeText(context,"Technical Error",Toast.LENGTH_LONG).show()
                }
                refreshdata = false

            }

        })
    }

    private fun prepareadapter(itemlist: List<Items>) {

        val obj_adapter = NewsViewAdapter(this,itemlist)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = obj_adapter
    }


        fun checkConnection(context: Context): Boolean {
            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null
        }

}