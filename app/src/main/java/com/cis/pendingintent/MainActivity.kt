package com.cis.pendingintent

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { view ->
            val builder = getNotificationBuilder("pending", "pending intent")
            builder.setContentTitle("notification title!")
            builder.setContentText("노티 메시지")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setAutoCancel(true)

            val intent1 = Intent(this, TestActivity::class.java)
            val pending = PendingIntent.getActivity(this, 0, intent1, 0)
            builder.setContentIntent(pending)

            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        btn2.setOnClickListener { view ->
            var builder = getNotificationBuilder("pending2", "pending intent2")
            builder.setContentTitle("notification title 2")
            builder.setContentText("노티 메시지 2")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setAutoCancel(true)

            var intent2 = Intent(this, Test2Activity::class.java)
            intent2.putExtra("stringSend", "data sending!!")
            intent2.putExtra("intSend", 11)
            var pending = PendingIntent.getActivity(this, 20, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            // getActivity의 파라미터들 중에 flags 를 PendingIntent.FLAG_UPDATE_CURRENT 를 넣어줘야만 intent가 제대로 전달된다.

            builder.setContentIntent(pending)

            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }

        btn3.setOnClickListener { view ->
            var builder = getNotificationBuilder("pending3", "pending intent3")
            builder.setContentTitle("notification title 3")
            builder.setContentText("노티 메시지 3")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setAutoCancel(true)

            var intent2 = Intent(this, Test2Activity::class.java)
            intent2.putExtra("stringSend", "data sending3")
            intent2.putExtra("intSend", 333)
            var pending = PendingIntent.getActivity(this, 30, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            // getActivity의 파라미터들 중에 requestCode 를 다르게 주면 같은 엑티비티가 실행되지만 마지막 내용으로 덮어지게 되는것이 아니라
            // 각각의 noti에 해당 데이터를 전달해줄 수 있다.

            builder.setContentIntent(pending)

            val intent3 = Intent(this, Test2Activity::class.java)
            intent3.putExtra("stringData3", "Test3Activity 실행")
            val pending3 = PendingIntent.getActivity(this, 40, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder2 = NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_alert, "Action 1", pending3)
            val action1 = builder2.build()
            builder.addAction(action1)

            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(30, notification)
        }
    }

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder {
        var builder : NotificationCompat.Builder? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, id)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        return builder
    }
}
