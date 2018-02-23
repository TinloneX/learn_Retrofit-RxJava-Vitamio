package org.tinlone.demo.rxjavasample;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tinlone.demo.rxjavasample.bean.CallLogBean;
import org.tinlone.demo.rxjavasample.util.adapt.PermissionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallLogRebuildService extends Service {
    public static final String TAG = "CallLogRebuildService";
    public static final String tag_id = "studentId";
    public static final String last_time = "Datetime";

    private List<CallLogBean> callLogs;

    private String studentId;

    private String lastUploadTime;

    private SharedPreferences mSharedPreferences;

    private MyTask task;

    private RequestQueue queue;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init(intent);
        return Service.START_NOT_STICKY;
    }

    private void init(Intent intent) {
        callLogs = new ArrayList<>();
        studentId = intent.getStringExtra(tag_id);
        queue = Volley.newRequestQueue(this);
        if (studentId == null) {
            studentId = "";
        }
        mSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        lastUploadTime = mSharedPreferences.getString(last_time, "");
        Log.i(TAG, "init: "+lastUploadTime);
        task = new MyTask(this);
        task.execute();
    }


    private class MyTask extends AsyncTask<Void, Void, List<CallLogBean>> {
        private Cursor cs;
        private Context mContext;
        List<CallLogBean> calls;

        public MyTask(Context context) {
            mContext = context;
            calls = new ArrayList<>();
        }

        @Override
        protected List< CallLogBean> doInBackground(Void... params) {
            if (cs == null) {
                cs = createCS();
                cs.moveToLast();
                if (null != lastUploadTime && !"".equals(lastUploadTime) && lastUploadTime.equals(cs.getString(3))) {
                    Log.i(TAG, "doInBackground: lastUploadTime.equals(cs.getString(3))");
                    return null;
                } else {
                    mSharedPreferences.edit().putString(last_time, cs.getString(3)).commit();
                    calls.add(readCallLog()) ;
                }
            }
            Log.i(TAG, "doInBackground: while前");
            while (cs.moveToPrevious()){
                Log.i(TAG, "doInBackground: while中");
                calls.add(readCallLog()) ;
            }
            Log.i(TAG, "doInBackground: while后");
            return calls;
        }

        @Override
        protected void onPostExecute(List<CallLogBean> callLogBeens) {
            if (null == callLogBeens) {
                Log.i(TAG, "onPostExecute: 关闭");
                if (cs != null) {
                    cs.close();
                    cs = null;
                    mContext = null;
                }
            } else {
                callLogs.addAll(callLogBeens);
            }

            if (callLogs.size() > 0) {
                sendCallRecord();
            } else {
                stopSelf();
            }
        }

        private Cursor createCS() {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                PermissionUtil.checkAndRequestPermission(mContext, Manifest.permission.READ_CALL_LOG, null);
                return null;
            }
            ContentResolver cr = getContentResolver();
            return cr.query(CallLog.Calls.CONTENT_URI, //系统方式获取通讯录存储地址
                    new String[]{
                            CallLog.Calls.CACHED_NAME,  //姓名
                            CallLog.Calls.NUMBER,    //号码
                            CallLog.Calls.TYPE,  //呼入/呼出(2)/未接
                            CallLog.Calls.DATE,  //拨打时间
                            CallLog.Calls.DURATION   //通话时长
                    }, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        }

        private CallLogBean readCallLog() {
            if (null == cs) return null;
            CallLogBean result = new CallLogBean();
            result.setToName(cs.getString(0));
            result.setToPhone(cs.getString(1));
            result.setCallType(cs.getString(2));
            result.setDateTime(cs.getString(3));
            result.setCallTime(cs.getString(4));
            Log.i(TAG, "readCallLog: " + result.toString());
            return result;
        }
    }

    private void sendCallRecord() {
        String url = "http://192.168.203.136:8080/appPhoneRecord/addRecord";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null) {
                    try {
                        if ("01".equals(new JSONObject(s).getString("code"))) {
                            stopSelf();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        stopSelf();
                    }
                }
                Log.i(TAG, "传送通话记录返回++++++++" + s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("LOG_TAG", volleyError.getMessage(), volleyError);
                stopSelf();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                JSONArray records = new JSONArray();

                map.put("token", "1231");
                map.put("BID", studentId);

                for (int i = 0; i < callLogs.size(); i++) {
                    JSONObject obj = new JSONObject();
                    CallLogBean call = callLogs.get(i);
                    try {
                        obj.put("toName", call.getToName());
                        obj.put("toPhone", call.getToPhone());
                        obj.put("callTime", call.getCallTime());
                        obj.put("callType", call.getCallType());
                        obj.put("dateTime", call.getDateTime());
                        records.put(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                map.put("records", records.toString());
                Log.i(TAG, map.toString());
                return map;
            }
        };
        queue.add(request);
    }
}
