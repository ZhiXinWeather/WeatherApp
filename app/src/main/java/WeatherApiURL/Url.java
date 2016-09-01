package WeatherApiURL;

/**
 * Created by haiyuan 1995 on 2016/8/29.
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

}
