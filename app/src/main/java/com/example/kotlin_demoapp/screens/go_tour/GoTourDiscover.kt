package com.example.kotlin_demoapp.screens.go_tour

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityGoTourDiscoverBinding
import com.google.android.material.chip.Chip


class GoTourDiscover : AppCompatActivity() {

    private lateinit var binding: ActivityGoTourDiscoverBinding
    private lateinit var months: ArrayList<Button>
    private lateinit var region: ArrayList<Button>
    private lateinit var user: ArrayList<Button>
    private lateinit var days: ArrayList<Button>
    private var selectedRegion = mutableSetOf<Chip>()
    private var selectedUser = mutableSetOf<Chip>()
    private var selectedMonth = mutableSetOf<Chip>()
    private var selectedDays = mutableSetOf<Chip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoTourDiscoverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        region =
            arrayListOf(binding.btnAsia, binding.btnAfrica, binding.btnEurope, binding.btnAmerica)
        user = arrayListOf(
            binding.btnPopular,
            binding.btnBestChoice,
            binding.btnLastTrip,
            binding.btnFiveStar
        )
        months = arrayListOf(
            binding.btnJan,
            binding.btnFeb,
            binding.btnMarch,
            binding.btnApril,
            binding.btnMay,
            binding.btnJune,
            binding.btnJuly
        )
        days = arrayListOf(
            binding.btnThirtyOrLess,
            binding.btnFifteenToSeven,
            binding.btnSevenToFour,
            binding.btnFiveToTwo,
            binding.btnTwoToOne
        )

        binding.scrollChoice.isVerticalScrollBarEnabled = false
        binding.scrollChipGroup.isHorizontalScrollBarEnabled = false
        binding.scrollPerson.isHorizontalScrollBarEnabled = false
        binding.scrollTime.isHorizontalScrollBarEnabled = false

        region.forEach { deSelectChoice(it) }
        user.forEach { deSelectChoice(it) }
        months.forEach { deSelectChoice(it) }
        days.forEach { deSelectChoice(it) }
        binding.imgBack.setOnClickListener { finish() }
    }

    fun selectImage(view: View) {
        var btn = view as? Button
        btn = btn ?: return
        when (btn) {
            in region -> {
                region.forEach { deSelectChoice(it) }
                selectedRegion.forEach {
                    binding.filterChipGroup.removeView(it)
                }
                selectedRegion.clear()
                selectChoice(btn)
                selectedRegion.add(addChip(btn.text.toString()))
            }

            in user -> {
                user.forEach { deSelectChoice(it) }
                selectedUser.forEach {
                    binding.filterChipGroup.removeView(it)
                }
                selectedUser.clear()
                selectChoice(btn)
                selectedUser.add(addChip(btn.text.toString()))
            }

            in months -> {
                months.forEach { deSelectChoice(it) }
                selectedMonth.forEach {
                    binding.filterChipGroup.removeView(it)
                }
                selectedMonth.clear()
                selectChoice(btn)
                selectedMonth.add(addChip(btn.text.toString()))
            }

            in days -> {
                days.forEach { deSelectChoice(it) }
                selectedDays.forEach {
                    binding.filterChipGroup.removeView(it)
                }
                selectedDays.clear()
                selectChoice(btn)
                selectedDays.add(addChip(btn.text.toString()))
            }
        }
    }

    private fun selectChoice(view: Button) {
        var buttonDrawable: Drawable = view.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable)
        DrawableCompat.setTint(buttonDrawable, getColor(R.color.orange))
        view.background = buttonDrawable
        view.setTextColor(getColor(R.color.white))
    }

    private fun deSelectChoice(view: Button) {
        var buttonDrawable: Drawable = view.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable)
        DrawableCompat.setTint(buttonDrawable, getColor(R.color.light_grey))
        view.background = buttonDrawable
        view.setTextColor(getColor(R.color.black))
    }

    private fun addChip(title: String): Chip {
        val chip = Chip(this)
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10f,
            resources.displayMetrics
        ).toInt()
        chip.apply {
            chipBackgroundColor = ColorStateList.valueOf(getColor(R.color.orange))
            setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
            text = title
            setCloseIconResource(R.drawable.icon_cancel)
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                binding.filterChipGroup.removeView(chip)
            }
        }
        binding.filterChipGroup.addView(chip)
        return chip
    }
}