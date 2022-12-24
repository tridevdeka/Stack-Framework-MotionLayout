package com.tridev.stackframework

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tridev.stackframework.databinding.ItemContainerMovieBinding
import kotlinx.parcelize.Parcelize

class MoviesAdapter(
    private val list: ArrayList<Item>,
    private val viewPager2: ViewPager2,
    private val listener: ClickListener,
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(val mBinding: ItemContainerMovieBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemContainerMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = list[position]
        holder.mBinding.imagePoster.setImageResource(item.image)
        holder.mBinding.textName.text = item.name
        holder.mBinding.textCategory.text = item.category
        holder.mBinding.textReleaseDate.text = item.releaseDate
        holder.mBinding.ratingBar.rating = item.ratings

        if (position == list.size - 2) {
            viewPager2.post(runnable)
        }
        holder.mBinding.mcDetails.setOnClickListener {
            listener.onClick(item)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    private val runnable = Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }


    interface ClickListener {
        fun onClick(item: Item)
    }

}

@Parcelize
data class Item(
    val image: Int,
    val name: String,
    val category: String,
    val releaseDate: String,
    val ratings: Float,
    val backdrop:Int?=null
) : Parcelable