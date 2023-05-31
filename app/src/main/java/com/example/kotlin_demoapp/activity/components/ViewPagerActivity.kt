package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ViewPagerRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityViewPagerBinding
import com.example.kotlin_demoapp.model.CityDetails

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    private lateinit var cityList: ArrayList<CityDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cityList = arrayListOf(
            CityDetails(
                R.drawable.img_newyork,
                "New York",
                "New York is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDetails(
                R.drawable.img_dubai,
                "Dubai",
                "Dubai is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDetails(
                R.drawable.img_jersey,
                "New Jersey",
                "New Jersey is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDetails(
                R.drawable.img_la,
                "Los Angels",
                "Los Angels is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDetails(
                R.drawable.img_thailand,
                "Thailand",
                "Thailand is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            ),
            CityDetails(
                R.drawable.img_torrento,
                "Torrento",
                "Torrento is a large city beside the Tapi River in the west Indian state of Gujarat. Once known for silk weaving, Surat remains a commercial center for textiles, and the New Textile Market area is lined with fabric shops. Overlooking the river, Surat Castle was built in the 1500s to defend the city against Portuguese colonists. Nearby, the Dutch, Armenian and English cemeteries contain elaborate colonial-era tombs."
            )
        )

        binding.mainViewPager.adapter = ViewPagerRecyclerAdapter(cityList)
    }
}