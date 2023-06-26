package com.example.kotlin_demoapp.enums

import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.activity.PlaceholderActivity
import com.example.kotlin_demoapp.architecture.ViewModelActivity
import com.example.kotlin_demoapp.screens.on_cloud.activity.NavigationActivity

enum class Components(val activity: Class<*>) {
    Button(com.example.kotlin_demoapp.activity.components.Button::class.java),
    TextView(com.example.kotlin_demoapp.activity.components.TextView::class.java),
    EditText(com.example.kotlin_demoapp.activity.components.EditText::class.java),
    ImageButton(com.example.kotlin_demoapp.activity.components.ImageButton::class.java),
    RadioButton(com.example.kotlin_demoapp.activity.components.RadioCheck::class.java),
    Switch(com.example.kotlin_demoapp.activity.components.SwitchSlider::class.java),
    Spinner(com.example.kotlin_demoapp.activity.components.Spinner::class.java),
    Toast(com.example.kotlin_demoapp.activity.components.Toast::class.java),
    Chip(com.example.kotlin_demoapp.activity.components.Chip::class.java),
    Calender(com.example.kotlin_demoapp.activity.components.Calender::class.java),
    FAB(com.example.kotlin_demoapp.activity.components.FAB::class.java),
    SnackBar(com.example.kotlin_demoapp.activity.components.SnackBar::class.java),
    TabLayout(com.example.kotlin_demoapp.activity.components.TabLayout::class.java),
    AppBarLayout(com.example.kotlin_demoapp.activity.components.AppBarLayout::class.java),
    RecyclerView(com.example.kotlin_demoapp.activity.components.RecyclerView::class.java),
    ViewPager(com.example.kotlin_demoapp.activity.components.ViewPagerActivity::class.java),
    FragmentBottomNavigation(com.example.kotlin_demoapp.activity.components.FragmentBottomNavigation::class.java),
    ImagePicker(com.example.kotlin_demoapp.activity.components.ImagePickerActivity::class.java),
    DataPassingInFragment(com.example.kotlin_demoapp.activity.components.DataPassingInFragment::class.java),
    Intents(com.example.kotlin_demoapp.activity.components.IntentActivity::class.java),
    WebView(com.example.kotlin_demoapp.activity.components.WebViewActivity::class.java),
    NavigationGraph(NavigationActivity::class.java),
    Timer(ViewModelActivity::class.java),
    Placeholder(PlaceholderActivity::class.java)
}