package commandoengineer.whathehack;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

/**
 * Created by yash on 21/5/17.
 */

public class UserNotifier {

    public void sendSwitchNotification(Context context, String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        final NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle(builder);
        Bitmap picture = BitmapFactory.decodeResource(context.getResources(), R.drawable.special_offers01);
        style.bigPicture(picture);
        Notification notification = builder
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.airtel_logo)
                .setLargeIcon(picture)
                .setStyle(style)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0x1233, notification);
    }
}
