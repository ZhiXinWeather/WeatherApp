package WeatherApiURL;

/**
 * Created by haiyuan 1995 on 2016/8/29.
 * url地址
 */

public class Url {

/**
 * key
 你的API密钥
 location
 所查询的位置
 参数值范围：
 城市ID 例如：location=WX4FBXXFKE4F
 城市中文名 例如：location=北京
 城市拼音/英文名 例如：location=beijing
 经纬度 例如：location=39.93:116.40 （注意纬度前经度在后，冒号分隔）
 IP地址 例如：location=220.181.111.86
 “ip”两个字母 自动识别请求IP地址，例如：location=ip
 language
 语言 (可选)
 参数值范围：
 zh-Hans 简体中文
 zh-Hant 繁体中文
 en 英文
 ja 日语
 de 德语
 fr 法语
 hi 印地语（印度官方语言之一）
 id 印度尼西亚语
 ru 俄语
 th 泰语
 默认值：zh-Hans
 unit
 单位 (可选)
 参数值范围：
 c 当参数为c时，温度c、风速km/h、能见度km、气压mb
 f 当参数为f时，温度f、风速mph、能见度mile、气压inch
 默认值：c
 返回结果  200
 *
 * */
    public static final String Weather_Now_Url="https://api.thinkpage.cn/v3/weather/now.json";
/**
 *  获取今天和未来4天的预报
 *
 * */
    public static final String Weather_Daily_Url="https://api.thinkpage.cn/v3/weather/daily.json";

    /**
     * 城市的天气搜索
     *根据城市ID、中文、英文、拼音、IP、经纬度搜索匹配的城市。
     *
     * **/
    public static final String Weather_SearchCity_Url="https://api.thinkpage.cn/v3/location/search.json";


/**
 * 空气质量
 *
 * **/
    public static final String Weather_Air_Url="https://api.thinkpage.cn/v3/air/now.json";



    /**
     * 空气质量排行榜
     *
     * **/
    public static final String Weather_Air_Ranking_Url="https://api.thinkpage.cn/v3/air/ranking.json";



    /**
     * 生活指数
     *
     * 获取指定城市的基本、交通、生活、运动、健康5大类共27项生活指数。目前仅支持中国城市。

     基本类：穿衣、紫外线强度、洗车、旅游、感冒、运动

     交通类：交通、路况

     生活类：晾晒、雨伞、空调开启、啤酒、逛街、夜生活、约会

     运动类：晨练、钓鱼、划船、放风筝

     健康类：过敏、美发、化妆、风寒、防晒、空气污染扩散条件、舒适度、心情
     *
     * **/

     public static String Weather_Life_Url="https://api.thinkpage.cn/v3/life/suggestion.json";

}
