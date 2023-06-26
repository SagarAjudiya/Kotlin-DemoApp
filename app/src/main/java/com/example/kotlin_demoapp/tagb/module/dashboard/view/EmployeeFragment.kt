package com.example.kotlin_demoapp.tagb.module.dashboard.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.net.Uri
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentEmployeeBinding
import com.example.kotlin_demoapp.tagb.adapter.EmployeeRecyclerAdapter
import com.example.kotlin_demoapp.tagb.base_classes.BaseFragment
import com.example.kotlin_demoapp.tagb.base_classes.observeEvent
import com.example.kotlin_demoapp.tagb.helper.dateToUTC
import com.example.kotlin_demoapp.tagb.helper.setWindowTouchable
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo
import com.example.kotlin_demoapp.tagb.module.dashboard.viewModel.EmployeeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

class EmployeeFragment : BaseFragment<FragmentEmployeeBinding, EmployeeViewModel>() {

    private lateinit var adapter: EmployeeRecyclerAdapter
    private val employeeList = mutableListOf<EmployeeInfo>()
    private var previousSearch: Job? = null
    override fun setViewModel(): EmployeeViewModel =
        ViewModelProvider(this)[EmployeeViewModel::class.java]

    override fun getResId(): Int = R.layout.fragment_employee

    override fun setUpView() {
        adapter =
            EmployeeRecyclerAdapter(
                this::editEmployee,
                this::deleteEmployee,
                this::showEmployee
            )
        binding.employeeRecycler.adapter = adapter
        binding.callback = this
        addQueryListener()
        setLoadingState(false)
        binding.shimmerLayout.startShimmer()
        viewModel.getEmployeeList()
        addObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addObservers() {
        // fetch employee API success
        viewModel.fetchEmployeeListSuccess.observeEvent(viewLifecycleOwner) { newEmployeeList ->
            employeeList.clear()
            employeeList.addAll(newEmployeeList.toMutableList())
            adapter.submitList(employeeList)
            checkEmptyList()
            binding.shimmerLayout.apply {
                stopShimmer()
                visibility = View.GONE
            }
        }
        // fetch employee API failure
        viewModel.fetchEmployeeListFailure.observeEvent(viewLifecycleOwner) {
            view?.let { Snackbar.make(it, "Fetch Employee Error", Snackbar.LENGTH_SHORT).show() }
            setLoadingState(false)
        }
        // update employee API success
        viewModel.updateEmployeeSuccess.observeEvent(viewLifecycleOwner) {
            employeeList[it.first] = it.second
            adapter.submitList(employeeList)
            view?.let { view ->
                Snackbar.make(view, "Update Employee Success", Snackbar.LENGTH_SHORT).show()
            }
            setLoadingState(false)
        }
        // update employee API failure
        viewModel.updateEmployeeFailure.observeEvent(viewLifecycleOwner) {
            setLoadingState(false)
            view?.let { Snackbar.make(it, "Update Employee Error", Snackbar.LENGTH_SHORT).show() }
        }
        // add employee API success
        viewModel.addEmployeeSuccess.observeEvent(viewLifecycleOwner) {
            employeeList.add(it)
            adapter.submitList(employeeList)
            setLoadingState(false)
            view?.let { view ->
                Snackbar.make(view, "Add Employee Success", Snackbar.LENGTH_SHORT).show()
            }
            checkEmptyList()
        }
        // add employee API failure
        viewModel.addEmployeeFailure.observeEvent(viewLifecycleOwner) {
            setLoadingState(false)
            view?.let { view ->
                Snackbar.make(view, "Add Employee Error", Snackbar.LENGTH_SHORT).show()
            }
        }
        // show employee API success
        viewModel.viewEmployeeSuccess.observeEvent(viewLifecycleOwner) {
            ShowEmployeeFragment(it).show(childFragmentManager, "Dialog")
        }
        // show employee API failure
        viewModel.viewEmployeeFailure.observeEvent(viewLifecycleOwner) {
            view?.let { Snackbar.make(it, "Show Employee Error", Snackbar.LENGTH_SHORT).show() }
        }
        // delete employee API success
        viewModel.deleteEmployeeSuccess.observeEvent(viewLifecycleOwner) {
            employeeList.removeAt(it)
            adapter.submitList(employeeList)
            setLoadingState(false)
            view?.let { view ->
                Snackbar.make(view, "Delete Employee Success", Snackbar.LENGTH_SHORT).show()
            }
            checkEmptyList()
        }
        // delete employee API failure
        viewModel.deleteEmployeeFailure.observeEvent(viewLifecycleOwner) {
            setLoadingState(false)
            view?.let {
                Snackbar.make(it, "Delete Employee Error", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.uploadImageFailure.observeEvent(viewLifecycleOwner) {
            setLoadingState(false)
            view?.let {
                Snackbar.make(it, "Image Upload Error", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun addQueryListener() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                previousSearch?.cancel()
                val newJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(300)
                    viewModel.getEmployeeList(newText ?: "")
                }
                previousSearch = newJob
                return true
            }
        })
    }

    fun addEmployee() {
        ModificationEmployeeFragment("Add Employee") { name, image: Uri? ->
            setLoadingState(true)
            //Image upload
            if (image != null) {
                viewModel.uploadImageAndAdd(image, name)
            } else {
                binding.emptyEmployee.visibility = View.GONE
                viewModel.addEmployee(
                    hashMapOf(
                        "id" to "null",
                        "name" to name,
                        "createdAt" to dateToUTC(Calendar.getInstance().time),
                        "avatar" to "null"
                    )
                )
            }
        }.show(childFragmentManager, "Dialog")
    }

    private fun checkEmptyList() {
        if (employeeList.isEmpty()) {
            binding.emptyEmployee.visibility = View.VISIBLE
        } else {
            binding.emptyEmployee.visibility = View.GONE
        }
    }

    private fun editEmployee(item: EmployeeInfo, position: Int) {
        ModificationEmployeeFragment("Edit Profile", item.name, item.image) { name, image: Uri? ->
            setLoadingState(true)
            //Image upload
            if (image != null) {
                viewModel.uploadImageAndUpdate(image, name, position, item.id)
            } else {
                viewModel.updateEmployee(
                    hashMapOf(
                        "id" to "null",
                        "name" to name,
                        "createdAt" to item.createdDate,
                        "avatar" to item.image
                    ),
                    position, item.id
                )
            }
        }.show(childFragmentManager, "Dialog")
    }

    private fun deleteEmployee(id: String, position: Int) {
        AlertDialog.Builder(activity).apply {
            setTitle("Confirm Delete")
            setMessage("Are you sure you want to delete this profile?")
            setIcon(R.drawable.icon_info)
            setPositiveButton("Confirm") { _, _ ->
                setLoadingState(true)
                viewModel.deleteEmployee(id, position)
            }
            setNegativeButton("Cancel", null)
        }.show()
    }

    private fun showEmployee(id: String) {
        viewModel.viewEmployee(id)
    }

    private fun setLoadingState(show: Boolean) {
        binding.progressGroup.visibility = if (show) View.VISIBLE else View.GONE
        setWindowTouchable(!show)
    }
}
