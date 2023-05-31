package com.example.kotlin_demoapp.screens.on_cloud.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin_demoapp.databinding.FragmentOnCloudPinnedBinding
import com.example.kotlin_demoapp.screens.on_cloud.OnCloudConstants
import com.example.kotlin_demoapp.screens.on_cloud.callback.OnFragmentChange
import com.example.kotlin_demoapp.screens.on_cloud.model.TransferDataModel

class OnCloudPinnedFragment : Fragment() {

    private lateinit var binding: FragmentOnCloudPinnedBinding

    // here parent Fragment is NavHostFragment and its parent is actual parent
    private val onFragmentChange by lazy { parentFragment?.parentFragment as OnFragmentChange }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnCloudPinnedBinding.inflate(layoutInflater)

        binding.btnSend.setOnClickListener {
            val data = TransferDataModel(
                binding.etName.text.toString().ifEmpty { "NAME" },
                binding.etSurname.text.toString().ifEmpty { "SURNAME" }
            )
            val action = OnCloudPinnedFragmentDirections.navigateToFilesFragment(data)
            findNavController().navigate(action)
            onFragmentChange.changeTab(OnCloudConstants.FILES)
        }
        return binding.root
    }
}