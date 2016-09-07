package Utils;

import com.example.haiyuan1995.myapplication.R;

/**
 * Created by haiyuan 1995 on 2016/8/30.
 */

public class SelectWeatherImage {


    public static int selectImageView(String weatherCode){
        int resourceID = R.drawable.cloudy;//默认多云
        switch (weatherCode){
            case "0": resourceID=R.drawable.sunny;
                break;
            case "1": resourceID=R.drawable.clear;
                break;
            case "2": resourceID=R.drawable.fair;
                break;
            case "3": resourceID=R.drawable.fair_night;
                break;
            case "4": resourceID=R.drawable.cloudy;
                break;
            case "5": resourceID=R.drawable.partly_cloudy;
                break;
            case "6": resourceID=R.drawable.partly_cloudy_night;
                break;
            case "7": resourceID=R.drawable.mostly_cloudy;
                break;
            case "8": resourceID=R.drawable.mostly_cloudy_night;
                break;
            case "9": resourceID=R.drawable.overcast;
                break;
            case "10": resourceID=R.drawable.shower;
                break;
            case "11": resourceID=R.drawable.thundershower;
                break;
            case "12": resourceID=R.drawable.thunder_shower_with_hail;
                break;
            case "13": resourceID=R.drawable.light_rain;
                break;
            case "14": resourceID=R.drawable.moderate_rain;
                break;
            case "15": resourceID=R.drawable.heavy_rain;
                break;
            case "16": resourceID=R.drawable.storm;
                break;
            case "17": resourceID=R.drawable.heavy_storm;
                break;
            case "18": resourceID=R.drawable.severe_storm;
                break;
            case "19": resourceID=R.drawable.ice_rain;
                break;
            case "20": resourceID=R.drawable.sleet;
                break;
            case "21": resourceID=R.drawable.snow_flurry;
                break;
            case "22": resourceID=R.drawable.light_snow;
                break;
            case "23": resourceID=R.drawable.moderate_snow;
                break;
            case "24": resourceID=R.drawable.heavy_snow;
                break;
            case "25": resourceID=R.drawable.snowstorm;
                break;
            case "26": resourceID=R.drawable.dust;
                break;
            case "27": resourceID=R.drawable.sand;
                break;
            case "28": resourceID=R.drawable.duststorm;
                break;
            case "29": resourceID=R.drawable.sandstorm;
                break;
            case "30": resourceID=R.drawable.foggy;
                break;
            case "31": resourceID=R.drawable.haze;
                break;
            case "32": resourceID=R.drawable.windy;
                break;
            case "33": resourceID=R.drawable.blustery;
                break;
            case "34": resourceID=R.drawable.hurricane;
                break;
            case "35": resourceID=R.drawable.tropical_storm;
                break;
            case "36": resourceID=R.drawable.tornado;
                break;
            case "37": resourceID=R.drawable.cold;
                break;
            case "38": resourceID=R.drawable.hot;
                break;
            case "99": resourceID=R.drawable.unknown;
                break;
        }
        return resourceID;
    }
    public static int selectBackground(String weatherCode){
        int resourceID = R.mipmap.monday;//默认多云
        switch (weatherCode) {
            case "星期一":
                resourceID = R.mipmap.monday;
                break;
            case "星期二":
                resourceID =R.mipmap.tuesday;
                break;
            case "星期三":
                resourceID = R.mipmap.wednesday;
                break;
            case "星期四":
                resourceID = R.mipmap.thursday;
                break;
            case "星期五":
                resourceID = R.mipmap.friday;
                break;
            case "星期六":
                resourceID = R.mipmap.saturday;
                break;
            case "星期日":
                resourceID = R.mipmap.sunday;
                break;
        }
        return resourceID;
    }

}
