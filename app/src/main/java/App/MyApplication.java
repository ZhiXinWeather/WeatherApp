package App;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by haiyuan 1995 on 2016/8/29.
 */

public class MyApplication extends Application {
    //弟弟的账号
    public static final String Key = "b2cusgfnnsthc4sz";
    public static final String Api_id = "UB4E270B6C";


    private static RequestQueue mRequestQueue;
    public static int status_bar_height;



    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this);//写完这里之后记得去Manifests中修改application
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            status_bar_height = getResources().getDimensionPixelSize(resourceId);
        }

    }

    public static RequestQueue getRequestQueue()
    {
        return mRequestQueue;
    }


}
