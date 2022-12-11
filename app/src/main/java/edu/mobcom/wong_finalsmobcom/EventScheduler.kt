package edu.mobcom.wong_finalsmobcom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.mobcom.wong_finalsmobcom.databinding.ActivityEventSchedulerBinding
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.provider.CalendarContract
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class EventScheduler : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityEventSchedulerBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_scheduler)


        binding = ActivityEventSchedulerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.backEvent.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

        binding.sTime.setOnClickListener{
            openTimePicker(binding.sTime)
        }

        binding.eTime.setOnClickListener {
            openTimePicker(binding.eTime)
        }

        binding.btnLogoutEvent.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            uLabel(calendar)
        }
        binding.tvDateEvent.setOnClickListener {
            DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.btnScheduleEvent.setOnClickListener {
            val recipients = binding.recipients.text.toString()
            val eventName = binding.eventName.text.toString()
            val date = binding.etEventDate.text.toString()
            val startTime = binding.sTime.text.toString()
            val endTime = binding.eTime.text.toString()
            val venue = binding.venue.text.toString()


            if (checkEmpty(recipients, eventName, date, startTime, endTime, venue)) {
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show()
            } else {
                //title, location, date, begin, end, recipients
                saveEvent(recipients, eventName, date, startTime, endTime, venue)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getmSeconds(date: String, time: String): Long {
        return try {
            val myFormat = "dd-MM-yyyy h:mm a"
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            val date1 = sdf.parse("$date $time")
            val cal1 = Calendar.getInstance()
            date1.also { cal1.time = it }
            val beginCal = Calendar.getInstance()
            beginCal[cal1[Calendar.YEAR], cal1[Calendar.MONTH], cal1[Calendar.DAY_OF_MONTH], cal1[Calendar.HOUR_OF_DAY]] =
                cal1[Calendar.MINUTE]
            beginCal.timeInMillis
        } catch (e: Exception) {
            Date().time
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveEvent(
        title: String,
        location: String,
        date: String,
        begin: String,
        end: String,
        recipients: String
    ) {
        val i = Intent(Intent.ACTION_INSERT).apply {//Used for creating a new Calendar event
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, getmSeconds(date, begin))
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, getmSeconds(date, end))
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipients))
        }
        try {
            startActivity(i)
            Toast.makeText(applicationContext, "$title Saved", Toast.LENGTH_SHORT).show()
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "Content error", Toast.LENGTH_LONG).show()
        }
    }
    private fun checkEmpty(
        recipients: String,
        eventName: String,
        date: String,
        startTime: String,
        endTime: String,
        venue: String
    ): Boolean {
        return recipients.isEmpty() || eventName.isEmpty() || date.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || venue.isEmpty()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun uLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.tvDateEvent.setText(sdf.format(myCalendar.time))
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun openTimePicker(textE: EditText){
        val currentTime = Calendar.getInstance()
        val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentTime.get(Calendar.MINUTE)
        TimePickerDialog(this,
            { _, hour, minute ->
                var hr = hour
                val ampm:String
                // determines if input time is AM or PM
                when {hour == 0 -> {
                    hr += 12
                    ampm = "AM"
                }
                    hour == 12 -> ampm = "PM"
                    hour > 12 -> {
                        hr -= 12
                        ampm = "PM"
                    }
                    else -> ampm = "AM"
                }
                textE.setText("$hour:$minute $ampm") }, startHour, startMinute, false).show()

    }
}