package com.example.kotlin_demoapp.helper

import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.model.CityDescription
import com.example.kotlin_demoapp.model.CityDetails
import com.example.kotlin_demoapp.model.ParkingModel

object Helper {

    fun getCityList() = arrayListOf(
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

    fun getMonth() =  arrayListOf("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec")

    fun getCountryName() = arrayOf("India", "China", "Australia", "Portugle", "America", "New Zealand")

    fun getCityListWithImageAndDescription() = arrayListOf(
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

    fun getCityStaggeredList() = arrayListOf("London", "New York\n\n\n\n\n\nNey York", "Surat\n\n\n\n\n\nSurat", "Delhi\n\nDelhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad\n\n\n\n\nAhmedabad")

    fun getCityNameList() = arrayListOf("London", "New York", "Surat", "Delhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad")

    fun getCityArray() = arrayListOf("London", "New York", "Surat", "Delhi", "Tokyo\n\n\n\n\n\n\nTokyo", "Dubai", "Mumbai", "Ahmedabad", "London", "New York", "Surat", "Delhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad")

    fun getSiteList() = arrayOf("google", "yahoo", "gmail", "youtube", "apple", "facebook", "twitter", "instagram")

    fun getParkingCitationData() = listOf<ParkingModel>(
        ParkingModel(
            "RUCHIT",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            true,
            40,
            false
        ),
        ParkingModel(
            "PARTH",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            false
        ),
        ParkingModel(
            "TOSIF",
            "2434 - 179 W Washington St., Valet",
            "Oct 25, 2021",
            true,
            40,
            true
        ),
        ParkingModel(
            "MEETRAJ",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            true
        ),
        ParkingModel(
            "RAHUL",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            true
        ),
        ParkingModel(
            "ANIKET",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            true,
            40,
            false
        ),
        ParkingModel(
            "RUCHIT",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            false
        ),
        ParkingModel(
            "PARTH",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            true
        ),
        ParkingModel(
            "TOSIF",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            false
        )
    )
}