package com.tridev.stackframework

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.tridev.stackframework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MoviesAdapter.ClickListener {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setUpViewPager()

    }


    private fun setUpViewPager() {
        val list = ArrayList<Item>()
        list.add(Item(R.drawable.ic_avengers,
            "Avengers : End game",
            "Action/Superhero",
            "Released on: 12-10-2018",
            4.5f, R.drawable.ic_avengers_backdrop))
        list.add(Item(R.drawable.ic_batman,
            "Dark Night Rises",
            "Action/Thriller",
            "Released on: 12-10-2014",
            4f,
            R.drawable.ic_batman_backdrop))
        list.add(Item(R.drawable.ic_suicide,
            "Suicide squad",
            "Action/Superhero",
            "Released on: 20-10-2019",
            4f, R.drawable.ic_suicide_backdrop))
        list.add(Item(R.drawable.ic_avatar,
            "Avatar : The Way of Water",
            "Action",
            "Released on: 16-Dec-2022",
            4.5f, R.drawable.ic_avatar_backdrop))
        val adapter = MoviesAdapter(list, mBinding.moviesViewPager, this@MainActivity)


        mBinding.moviesViewPager.clipToPadding = false
        mBinding.moviesViewPager.clipChildren = false
        mBinding.moviesViewPager.offscreenPageLimit = 20
        mBinding.moviesViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        mBinding.moviesViewPager.setPageTransformer(compositePageTransformer)
        mBinding.moviesViewPager.adapter = adapter
//        mBinding.moviesViewPager.setCurrentItem(4, true)

    }

    override fun onClick(item: Item) {
        Intent(this, MovieDetailsActivity::class.java).also {
            it.putExtra("item", item as Parcelable)
            startActivity(it)
        }
    }
}