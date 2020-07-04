package com.dakusuno.dakusunogua.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.dakusuno.dakusunogua.MainActivity
import com.dakusuno.dakusunogua.R
import timber.log.Timber
import java.util.*

class AlarmReceiver : BroadcastReceiver(){
    companion object{
        private const val ALARM = 100
    }

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("message")
        Timber.d("Alarm Recieved")
        showAlarmNotif(context,message, ALARM)
    }
    fun setRepeatingAlarm(context: Context,message : String){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("message",message)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY,9)
            set(Calendar.MINUTE,0)
            set(Calendar.SECOND,0)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, ALARM,intent,0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)
        Timber.d(calendar.toString())
        Toast.makeText(context, "Repeating alarm diaktifkan", Toast.LENGTH_SHORT).show()

    }
    private fun showAlarmNotif(context: Context,message: String,notifId:Int){

        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, ALARM,intent,0)
        Timber.d("ALARM DUDE")
        val CHANNEL = "GUA"
        val CHANNEL_NAME = "Github User App"
        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL)
            .setSmallIcon(R.drawable.ic_baseline_tag_faces_12)
            .setContentIntent(pendingIntent)
            .setContentTitle("GUA")
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL)

            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notif = builder.build()
        notificationManagerCompat.notify(notifId,notif)
    }
    fun cancelAlarm(context: Context){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, ALARM, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "Repeating alarm dibatalkan", Toast.LENGTH_SHORT).show()
    }
}