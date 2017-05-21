package commandoengineer.whathehack;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by yash on 21/5/17.
 */

public class UserNotifier {

    public void sendSwitchNotification(Context context, String title, String message) {

        Intent intent = new Intent(context, PlanActivity.class);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_perm_phone_msg_white_48dp)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .addAction(R.drawable.ic_account_balance_wallet_grey_600_24dp, "Recharge", PendingIntent.getActivity(context, 0, intent, 0));
        Notification notification = builder.build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0x1233, notification);
    }
}
