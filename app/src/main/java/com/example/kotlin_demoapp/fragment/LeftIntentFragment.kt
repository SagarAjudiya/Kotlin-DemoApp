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
import com.example.kotlin_demoapp.callbacks.ChangeNameListener
import com.example.kotlin_demoapp.databinding.FragmentLeftIntentBinding

class LeftIntentFragment : Fragment() {

    private lateinit var binding: FragmentLeftIntentBinding
    private val TAG = "LIFECYCLE"
    private lateinit var changeNameListener: ChangeNameListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLeftIntentBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView Left Fragment")
        changeNameListener = activity as ChangeNameListener

        setFragmentResultListener(
            "RightFragment"
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
        changeNameListener.nameEvent(binding.editName.text.toString().ifEmpty {""}, binding.editSurname.text.toString().ifEmpty {""})
        val result = Bundle()
        result.putString("name", binding.editName.text.toString())
        result.putString("surname", binding.editSurname.text.toString())
        setFragmentResult("LeftFragment", result)

        Log.d(TAG, "onPause Left Fragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach Left Fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Left Fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Left Fragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach Left Fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView Left Fragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Left Fragment")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Left Fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Left Fragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated Left Fragment")
    }
}