package edu.wit.mobileapp.fridge101;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BroadCast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        try {
            String date = "date";
            Date d = new Date();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            if (dateFormat.equals(date)) {
                Intent it = new Intent(context, MainActivity.class);
                createNotification(context, it, "Message", null, null);
            }
        } catch (Exception e){
            Log.v("date", e.getMessage());
        }
    }

    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence description){
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setContentIntent(p);

        Notification n = builder.build();
        n.vibrate = new long[]{150,300,150,400};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(0, n);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }
}
