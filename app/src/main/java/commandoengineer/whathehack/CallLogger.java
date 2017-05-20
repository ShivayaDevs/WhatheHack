package commandoengineer.whathehack;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.Date;

/**
 * Created by yash on 20/5/17.
 */

public class CallLogger {

    public String readCallLogs(Context context) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
            return "Permission Denied.";

        Cursor mCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        int number = mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type_index = mCursor.getColumnIndex(CallLog.Calls.TYPE);
        int duration_index = mCursor.getColumnIndex(CallLog.Calls.DURATION);
        int date_index = mCursor.getColumnIndex(CallLog.Calls.DATE);
        int name_index = mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int sim_name_index = mCursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_COMPONENT_NAME);
        int sim_no_index = mCursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID);

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        while (mCursor.moveToNext() && counter < 2000) {

            String phoneNumber = mCursor.getString(number);
            String callType = mCursor.getString(type_index);
            String caller = mCursor.getString(name_index);
            String callDuration = mCursor.getString(duration_index);
            String callDate = mCursor.getString(date_index);
            String sim_name = mCursor.getString(sim_name_index);
            String sim_no = mCursor.getString(sim_no_index);
            Date cDate = new Date(Long.valueOf(callDate.trim()));
            String completeDate = cDate.toString();


            if(callType.equals("2")) {
                int network = 1;
                if(counter % 4 == 0){
                    network = 0;
                }
                int std = 0;
                if(counter % 3 == 0){
                    std = 1;
                }

                sb.append(phoneNumber)
                        .append(",")
                        .append(Integer.parseInt(sim_no) - 1)
                        .append(",")
                        .append(network)
                        .append(",")
                        .append(callDuration)
                        .append(",")
                        .append(std)
                        .append(",")
                        .append(completeDate.substring(11,13))
                        .append('\n');
            }
            counter++;
        }
        Log.e("s", "Pushed " + counter + "logs");
        return sb.toString();
    }



}
