package com.example.kotlin_demoapp.activity.intent

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.broadcast.AlarmReceiver
import com.example.kotlin_demoapp.databinding.ActivityExplicitIntentBinding
import com.example.kotlin_demoapp.model.PersonDetails

class ExplicitIntent : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityExplicitIntentBinding
    private var getData =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != RESULT_OK) return@registerForActivityResult
            val person: PersonDetails? = it.data?.getParcelableExtra("person")
            person?.let { safePerson -> binding.person = safePerson }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExplicitIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
        binding.btnLaunch.setOnClickListener(this)
        binding.btnFilterLaunch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnGo.id -> {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("Title", binding.etTitle.text.toString().ifEmpty { "Enter Details" })
                    putExtra(
                        "person", PersonDetails(
                            binding.etName.text.toString(),
                            binding.etSurname.text.toString(),
                            binding.etEmail.text.toString(),
                            binding.etPhone.text.toString()
                        )
                    )
                }
                getData.launch(intent)
            }

            binding.btnClear.id -> {
                binding.apply {
                    txtResultName.visibility = View.GONE
                    txtResultSurname.visibility = View.GONE
                    txtResultEmail.visibility = View.GONE
                    txtResultPhone.visibility = View.GONE
                    etName.visibility = View.VISIBLE
                    etSurname.visibility = View.VISIBLE
                    etEmail.visibility = View.VISIBLE
                    etPhone.visibility = View.VISIBLE
                    etTitle.visibility = View.VISIBLE
                }
            }

            binding.btnLaunch.id -> {
                val delay = binding.etDelay.text.toString().ifEmpty { "1" }.toInt()
                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent =
                    PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                (getSystemService(ALARM_SERVICE) as? AlarmManager)?.apply {
                    set(
                        AlarmManager.RTC,
                        System.currentTimeMillis() + (delay * 1000),
                        pendingIntent
                    )
                }
                Toast.makeText(this, "Pending Intent Set", Toast.LENGTH_SHORT).show()
            }

            binding.btnFilterLaunch.id -> {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(
                        Intent.EXTRA_TEXT,
                        binding.etText.text.toString().ifEmpty { "DEFAULT" })
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(intent, "Choose App"))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            txtResultName.visibility = View.VISIBLE
            txtResultSurname.visibility = View.VISIBLE
            txtResultEmail.visibility = View.VISIBLE
            txtResultPhone.visibility = View.VISIBLE
            etName.visibility = View.GONE
            etSurname.visibility = View.GONE
            etEmail.visibility = View.GONE
            etPhone.visibility = View.GONE
            etTitle.visibility = View.GONE
        }
    }
}