package com.example.kotlin_demoapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.kotlin_demoapp.callbacks.ChangeNameListner
import com.example.kotlin_demoapp.databinding.FragmentRightIntentBinding

class RightIntentFragment : Fragment() {

    private lateinit var binding: FragmentRightIntentBinding
    private val TAG = "LIFECYCLE"
    private lateinit var changeNameListner: ChangeNameListner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRightIntentBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView")
        changeNameListner = activity as ChangeNameListner

        setFragmentResultListener(
            "LeftFragment"
        ) { _, result ->
            binding.txtName.text = result.getString("name")
            binding.txtSurname.text = result.getString("surname")
        }

        binding.btnClear.setOnClickListener {
            binding.editName.text = null
            binding.editSurname.text = null
        }

        binding.editName.doOnTextChanged { text, _, _, _ ->
            binding.txtName.text = text
        }

        binding.editSurname.doOnTextChanged { text, _, _, _ ->
            binding.txtSurname.text = text
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val result = Bundle()
        changeNameListner.nameEvent(binding.editName.text.toString().ifEmpty {""}, binding.editSurname.text.toString().ifEmpty {""})
        result.putString("name", binding.editName.text.toString())
        result.putString("surname", binding.editSurname.text.toString())
        setFragmentResult("RightFragment", result)
        Log.d(TAG, "onPause Fragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach Fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Fragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach Fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView Fragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Fragment")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Fragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated Fragment")
    }
}