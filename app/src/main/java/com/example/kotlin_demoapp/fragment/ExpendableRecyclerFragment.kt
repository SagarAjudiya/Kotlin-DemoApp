package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ExpendableRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentExpendableRecyclerViewBinding
import com.example.kotlin_demoapp.databinding.FragmentSearchRecyclerViewBinding
import com.example.kotlin_demoapp.model.CityDescription

class ExpendableRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentExpendableRecyclerViewBinding
    private lateinit var cityList: ArrayList<CityDescription>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExpendableRecyclerViewBinding.inflate(layoutInflater)

        cityList = arrayListOf(
            CityDescription(
                "Surat",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Ahmedabad",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Anand",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Bhavnagar",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Baroda",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Dwarka",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Vapi",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Valsad",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Nadiad",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDescription(
                "Kheda",
                "Surat is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            )
        )
        binding.expendableRecycler.layoutManager = LinearLayoutManager(container?.context)
        binding.expendableRecycler.adapter = ExpendableRecyclerAdapter(cityList)

        return binding.root
    }
}