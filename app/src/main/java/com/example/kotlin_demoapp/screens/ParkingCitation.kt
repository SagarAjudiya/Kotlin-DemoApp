package com.example.kotlin_demoapp.screens

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ParkingCitationAdapter
import com.example.kotlin_demoapp.databinding.ActivityParkingCitationBinding
import com.example.kotlin_demoapp.helper.Helper
import com.google.android.material.snackbar.Snackbar

class ParkingCitation : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityParkingCitationBinding
    private var adapter = ParkingCitationAdapter(this::selectParkingTicket, this::changeStatus)
    private var parkingList = Helper.getParkingCitationData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_parking_citation)
        binding.btnBack.setOnClickListener(this)
        adapter.submitList(parkingList)
        binding.parkingCitationRecycler.adapter = adapter

        binding.parkingCitationRecycler.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        binding.btnFilter.setOnClickListener(this)
        searchData()

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
            (currentFocus as? EditText)?.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnBack.id -> finish()
            binding.btnFilter.id -> {
                val paid = parkingList.filter { it.status }
                val unpaid = parkingList.filter { !it.status }
                parkingList = paid + unpaid
                adapter.submitList(parkingList)
            }
        }
    }

    private fun searchData() {
        binding.searchField.doOnTextChanged { text, _, _, _ ->
            val searchText = text.toString()
            val result = parkingList.filter {
                it.citationID.lowercase().contains(searchText) || it.dueDate.lowercase()
                    .contains(searchText)
            }
            adapter.submitList(result)
            if (result.isEmpty()) {
                Snackbar.make(binding.root, "No Data Found", Snackbar.LENGTH_SHORT)
                    .setAction("OK") {}
                    .show()
            }
        }
    }

    private fun selectParkingTicket(position: Int, isChecked: Boolean) {
        parkingList[position].checked = isChecked
    }

    private fun changeStatus(position: Int) {
        parkingList[position].status = !parkingList[position].status
        adapter.notifyItemChanged(position)
    }
}
