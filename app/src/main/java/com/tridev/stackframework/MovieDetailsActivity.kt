package com.tridev.stackframework

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.tridev.stackframework.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        initViews()
        onBackPress()
    }

    private fun initViews() {
        var item: Item? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item", Item::class.java)?.let {
                item = it
            }
        } else {
            intent.getParcelableExtra<Item>("item")?.let {
                item = it
            }
        }
        item?.let { bindViews(it) }
    }

    private fun bindViews(item: Item) {
        mBinding.imagePoster.setImageResource(item.backdrop!!)
        mBinding.textName.text = item.name
        mBinding.tvSelectedMovieName.text = item.name
        mBinding.textCategory.text = item.category
        mBinding.textReleaseDate.text = item.releaseDate
        mBinding.ratingBar.rating = item.ratings
        mBinding.mcOffer.visibility = VISIBLE

        mBinding.bt3D.setOnClickListener {
            it.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_outline_select, theme)
        }

        mBinding.bt4DX.setOnClickListener {
            it.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_outline_select, theme)
        }
        mBinding.btIMAX.setOnClickListener {
            it.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_outline_select, theme)
        }

        mBinding.bt3DHindi.setOnClickListener {
            it.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_outline_select, theme)
        }

        mBinding.bt3DKannada.setOnClickListener {
            it.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_outline_select, theme)
        }

        mBinding.btProceed.setOnClickListener {
            Toast.makeText(this, "Ticket Booked Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
        mBinding.ivBack.setOnClickListener {
            Intent(this@MovieDetailsActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun onBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when (mBinding.root.currentState) {
                    R.id.second -> {
                        mBinding.root.transitionToState(R.id.first)
                    }
                    R.id.third -> {
                        mBinding.root.transitionToState(R.id.second)
                    }
                    R.id.fourth -> {
                        mBinding.root.transitionToState(R.id.third)
                    }
                    else -> {
                        Intent(this@MovieDetailsActivity, MainActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                }
            }
        })
    }
}