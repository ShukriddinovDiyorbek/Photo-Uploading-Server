package com.example.thecatapi.adapter

import android.annotation.SuppressLint
import android.graphics.Color.blue
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.thecatapi.R
import com.example.thecatapi.model.ResponseItem
import com.google.android.material.imageview.ShapeableImageView

class SearchAdapter(var items:ArrayList<ResponseItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickitemhome:((word:String)-> Unit) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_search,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val home = items[position]

        if (holder is HomeViewHolder) {
            val tv_title = holder.tv_title
            val iv_photo = holder.iv_photo
            val item = holder.item

            tv_title.text = home.originalFilename

            iv_photo.loadImageUrl(home.url)
        }
    }
    fun ImageView.loadImageUrl(url: String?) {
        Glide.with(this).load(url).placeholder(getCircularProgressDrawable())
            .error(R.drawable.error).into(this)
    }

    private fun ImageView.getCircularProgressDrawable() =
        CircularProgressDrawable(this.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            colorFilter = PorterDuffColorFilter(
                ResourcesCompat.getColor(resources, R.color.blue, null),
                PorterDuff.Mode.ADD
            )
            start()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("CutPasteId")
    class HomeViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_title: TextView
        var iv_photo: ShapeableImageView
        var item: FrameLayout

        init {
            tv_title = view.findViewById(R.id.textView)
            iv_photo = view.findViewById(R.id.imageView)
            item = view.findViewById(R.id.item_home)
        }
    }
}