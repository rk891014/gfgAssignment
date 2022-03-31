package com.example.gfgassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gfgassignment.R
import com.example.gfgassignment.models.Items
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewsViewAdapter(var c: Context, var itemlist: List<Items>)
    : RecyclerView.Adapter<NewsViewAdapter.newsViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_view_details,parent,false);
        
        return newsViewHolder(view);
    }


    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val item = itemlist.get(position)

        if(position==0) {
            holder.enclosercardview.isVisible = true
            holder.simplecardview.isVisible = false
            if(item.enclosure != null && item.enclosure.link != null) {
                val url: String = item.enclosure.link.replace("&amp;", "&")
                Glide.with(c)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.articleImage)
            }
            if(item.pubDate!= null) {
                holder.articledate.text = convertedDate(item.pubDate)
                holder.articletime.text = convertedTime(item.pubDate)
            }
            if(item.title != null) {
                holder.articleTitle.text = item.title
            }
        }else{
            holder.enclosercardview.isVisible = false
            holder.simplecardview.isVisible = true
            if(item.thumbnail != null) {
                val url: String = item.thumbnail.replace("&amp;", "&")
                Glide.with(c)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.articleImage2);
            }
            if(item.pubDate!= null) {
                holder.articledate2.text = convertedDate(item.pubDate)
                holder.articletime2.text = convertedTime(item.pubDate)
            }
            if(item.title != null) {
                holder.articleTitle2.text = item.title
            }

        }
        holder.setIsRecyclable(false)
    }

//    func to convert time to required time format
    private fun convertedTime(dateandtime: String): CharSequence? {

        val time : String = dateandtime.split("\\s".toRegex())[1];


        try {
            val sdf = SimpleDateFormat("hh:mm:ss")
            val sdfs = SimpleDateFormat("hh:mm a")
            val dt: Date = sdf.parse(time)

            return sdfs.format(dt)
        }catch (e: ParseException) {
            e.printStackTrace();
        }
        return ""
    }

    private fun convertedDate(dateandtime: String): CharSequence? {
        val date : String = dateandtime.split("\\s".toRegex())[0];
        try {
            val parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val dtf = DateTimeFormatter.ofPattern("MMM dd, uuuu", Locale.ENGLISH);

            return dtf.format(parsedDate)
        }catch (e: ParseException) {
            e.printStackTrace();
        }
        return ""
    }

    class newsViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val enclosercardview = view.findViewById<CardView>(R.id.enclosercardview);
        val articleTitle = view.findViewById<TextView>(R.id.articleTitle);
        val articledate = view.findViewById<TextView>(R.id.articledate);
        val articleImage = view.findViewById<ImageView>(R.id.articleImage);
        val articletime = view.findViewById<TextView>(R.id.articletime);



        val simplecardview = view.findViewById<CardView>(R.id.simplecardview);
        val articleTitle2 = view.findViewById<TextView>(R.id.articleTitle2);
        val articledate2 = view.findViewById<TextView>(R.id.articledate2);
        val articleImage2 = view.findViewById<ImageView>(R.id.articleImage2);
        val articletime2 = view.findViewById<TextView>(R.id.articletime2);


    }


    override fun getItemCount(): Int {
        return itemlist.size;
    }


}
