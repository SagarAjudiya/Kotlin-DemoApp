package com.example.kotlin_demoapp.screens.go_tour

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityGoTourUpcomingBinding


class GoTourUpcoming : AppCompatActivity() {

    private lateinit var binding: ActivityGoTourUpcomingBinding
    private lateinit var locationTypes: ArrayList<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoTourUpcomingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        locationTypes = arrayListOf<ImageButton>(binding.btnSun, binding.btnBike, binding.btnBoat, binding.btnBus, binding.btnPlane)

        binding.scrollHistory.isVerticalScrollBarEnabled = false

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    fun selectImage(view: View) {
        var  imgButton = view as? ImageButton
        imgButton = imgButton ?: return
        locationTypes.forEach {
            deSelectLocation(it)
        }
        selectLocation(imgButton)
    }

    private fun selectLocation(view: ImageButton){
        var buttonDrawable: Drawable = view.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable)
        DrawableCompat.setTint(buttonDrawable, getColor(R.color.orange))
        view.background = buttonDrawable
        view.imageTintList = ColorStateList.valueOf(getColor(R.color.white))
    }

    private fun deSelectLocation(view: ImageButton){
        var buttonDrawable: Drawable = view.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable)
        DrawableCompat.setTint(buttonDrawable, getColor(R.color.light_grey))
        view.background = buttonDrawable
        view.imageTintList = ColorStateList.valueOf(getColor(R.color.black))
    }
}