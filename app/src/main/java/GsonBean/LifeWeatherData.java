package GsonBean;

import java.util.List;

/**
 * Created by admin on 2016/9/6.
 * 生活提示的Data Bean类
 */
public class LifeWeatherData {


    /**
     * location : {"id":"WTW3SJ5ZBJUY","name":"上海","country":"CN","path":"上海,上海,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
     * suggestion : {"ac":{"brief":"较少开启","details":"您将感到很舒适，一般不需要开启空调。"},"air_pollution":{"brief":"较差","details":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"},"airing":{"brief":"不太适宜","details":"天气阴沉，不利于水分的迅速蒸发，不太适宜晾晒。若需要晾晒，请尽量选择通风的地点。"},"allergy":{"brief":"极不易发","details":"天气条件极不易诱发过敏，可放心外出，享受生活。"},"beer":{"brief":"较不适宜","details":"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。"},"boating":{"brief":"较适宜","details":"白天较适宜划船，但天气阴沉，气温稍低，请注意加衣，小心着凉。"},"car_washing":{"brief":"不宜","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"chill":{"brief":"凉","details":"感觉有点凉，室外活动注意适当增减衣物。"},"comfort":{"brief":"较舒适","details":"白天天气阴沉，会感到有点儿凉，但大部分人完全可以接受。"},"dating":{"brief":"较适宜","details":"虽然天空有些阴沉，但情侣们可以放心外出，不用担心天气来调皮捣乱而影响了兴致。"},"dressing":{"brief":"较冷","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"fishing":{"brief":"较适宜","details":"较适合垂钓，但天气稍凉，会对垂钓产生一定的影响。"},"flu":{"brief":"较易发","details":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},"hair_dressing":{"brief":"一般","details":"注意防晒，洗发不宜太勤，建议选用保湿防晒型洗发护发品。出门请戴上遮阳帽或打遮阳伞。"},"kiteflying":{"brief":"不宜","details":"天气不好，不适宜放风筝。"},"makeup":{"brief":"保湿","details":"皮肤易缺水，用润唇膏后再抹口红，用保湿型霜类化妆品。"},"mood":{"brief":"较差","details":"天气阴沉，会感觉莫名的压抑，情绪低落，此时将所有的悲喜都静静地沉到心底，在喧嚣的尘世里，感受片刻恬淡的宁静。"},"morning_sport":{"brief":"不宜","details":"阴天，早晨天气寒冷，请尽量避免户外晨练，若坚持室外晨练请注意保暖防冻，建议年老体弱人群适当减少晨练时间。"},"night_life":{"brief":"较不适宜","details":"有降水，会给您的出行带来很大的不便，建议就近或最好在室内进行夜生活。"},"road_condition":{"brief":"干燥","details":"阴天，路面比较干燥，路况较好。"},"shopping":{"brief":"适宜","details":"阴天，在这种天气里去逛街，省去了涂防晒霜，打遮阳伞的麻烦，既可放松身心，又会有很多意外收获。"},"sport":{"brief":"较适宜","details":"阴天，较适宜进行各种户内外运动。"},"sunscreen":{"brief":"弱","details":"属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},"traffic":{"brief":"良好","details":"阴天，路面干燥，交通气象条件良好，车辆可以正常行驶。"},"travel":{"brief":"适宜","details":"天气较好，温度适宜，总体来说还是好天气哦，这样的天气适宜旅游，您可以尽情地享受大自然的风光。"},"umbrella":{"brief":"不带伞","details":"阴天，但降水概率很低，因此您在出门的时候无须带雨伞。"},"uv":{"brief":"最弱","details":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
     * last_update : 2015-11-28T14:10:48+08:00
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * ac : {"brief":"较少开启","details":"您将感到很舒适，一般不需要开启空调。"}
         * air_pollution : {"brief":"较差","details":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"}
         * airing : {"brief":"不太适宜","details":"天气阴沉，不利于水分的迅速蒸发，不太适宜晾晒。若需要晾晒，请尽量选择通风的地点。"}
         * allergy : {"brief":"极不易发","details":"天气条件极不易诱发过敏，可放心外出，享受生活。"}
         * beer : {"brief":"较不适宜","details":"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。"}
         * boating : {"brief":"较适宜","details":"白天较适宜划船，但天气阴沉，气温稍低，请注意加衣，小心着凉。"}
         * car_washing : {"brief":"不宜","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
         * chill : {"brief":"凉","details":"感觉有点凉，室外活动注意适当增减衣物。"}
         * comfort : {"brief":"较舒适","details":"白天天气阴沉，会感到有点儿凉，但大部分人完全可以接受。"}
         * dating : {"brief":"较适宜","details":"虽然天空有些阴沉，但情侣们可以放心外出，不用担心天气来调皮捣乱而影响了兴致。"}
         * dressing : {"brief":"较冷","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
         * fishing : {"brief":"较适宜","details":"较适合垂钓，但天气稍凉，会对垂钓产生一定的影响。"}
         * flu : {"brief":"较易发","details":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"}
         * hair_dressing : {"brief":"一般","details":"注意防晒，洗发不宜太勤，建议选用保湿防晒型洗发护发品。出门请戴上遮阳帽或打遮阳伞。"}
         * kiteflying : {"brief":"不宜","details":"天气不好，不适宜放风筝。"}
         * makeup : {"brief":"保湿","details":"皮肤易缺水，用润唇膏后再抹口红，用保湿型霜类化妆品。"}
         * mood : {"brief":"较差","details":"天气阴沉，会感觉莫名的压抑，情绪低落，此时将所有的悲喜都静静地沉到心底，在喧嚣的尘世里，感受片刻恬淡的宁静。"}
         * morning_sport : {"brief":"不宜","details":"阴天，早晨天气寒冷，请尽量避免户外晨练，若坚持室外晨练请注意保暖防冻，建议年老体弱人群适当减少晨练时间。"}
         * night_life : {"brief":"较不适宜","details":"有降水，会给您的出行带来很大的不便，建议就近或最好在室内进行夜生活。"}
         * road_condition : {"brief":"干燥","details":"阴天，路面比较干燥，路况较好。"}
         * shopping : {"brief":"适宜","details":"阴天，在这种天气里去逛街，省去了涂防晒霜，打遮阳伞的麻烦，既可放松身心，又会有很多意外收获。"}
         * sport : {"brief":"较适宜","details":"阴天，较适宜进行各种户内外运动。"}
         * sunscreen : {"brief":"弱","details":"属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
         * traffic : {"brief":"良好","details":"阴天，路面干燥，交通气象条件良好，车辆可以正常行驶。"}
         * travel : {"brief":"适宜","details":"天气较好，温度适宜，总体来说还是好天气哦，这样的天气适宜旅游，您可以尽情地享受大自然的风光。"}
         * umbrella : {"brief":"不带伞","details":"阴天，但降水概率很低，因此您在出门的时候无须带雨伞。"}
         * uv : {"brief":"最弱","details":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
         */

        private SuggestionBean suggestion;
        private String last_update;

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public static class SuggestionBean {
            /**
             * brief : 较少开启
             * details : 您将感到很舒适，一般不需要开启空调。
             */

            private AcBean ac;
            /**
             * brief : 较差
             * details : 气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。
             */

            private AirPollutionBean air_pollution;
            /**
             * brief : 不太适宜
             * details : 天气阴沉，不利于水分的迅速蒸发，不太适宜晾晒。若需要晾晒，请尽量选择通风的地点。
             */

            private AiringBean airing;
            /**
             * brief : 极不易发
             * details : 天气条件极不易诱发过敏，可放心外出，享受生活。
             */

            private AllergyBean allergy;
            /**
             * brief : 较不适宜
             * details : 您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。
             */

            private BeerBean beer;
            /**
             * brief : 较适宜
             * details : 白天较适宜划船，但天气阴沉，气温稍低，请注意加衣，小心着凉。
             */

            private BoatingBean boating;
            /**
             * brief : 不宜
             * details : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
             */

            private CarWashingBean car_washing;
            /**
             * brief : 凉
             * details : 感觉有点凉，室外活动注意适当增减衣物。
             */

            private ChillBean chill;
            /**
             * brief : 较舒适
             * details : 白天天气阴沉，会感到有点儿凉，但大部分人完全可以接受。
             */

            private ComfortBean comfort;
            /**
             * brief : 较适宜
             * details : 虽然天空有些阴沉，但情侣们可以放心外出，不用担心天气来调皮捣乱而影响了兴致。
             */

            private DatingBean dating;
            /**
             * brief : 较冷
             * details : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
             */

            private DressingBean dressing;
            /**
             * brief : 较适宜
             * details : 较适合垂钓，但天气稍凉，会对垂钓产生一定的影响。
             */

            private FishingBean fishing;
            /**
             * brief : 较易发
             * details : 天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。
             */

            private FluBean flu;
            /**
             * brief : 一般
             * details : 注意防晒，洗发不宜太勤，建议选用保湿防晒型洗发护发品。出门请戴上遮阳帽或打遮阳伞。
             */

            private HairDressingBean hair_dressing;
            /**
             * brief : 不宜
             * details : 天气不好，不适宜放风筝。
             */

            private KiteflyingBean kiteflying;
            /**
             * brief : 保湿
             * details : 皮肤易缺水，用润唇膏后再抹口红，用保湿型霜类化妆品。
             */

            private MakeupBean makeup;
            /**
             * brief : 较差
             * details : 天气阴沉，会感觉莫名的压抑，情绪低落，此时将所有的悲喜都静静地沉到心底，在喧嚣的尘世里，感受片刻恬淡的宁静。
             */

            private MoodBean mood;
            /**
             * brief : 不宜
             * details : 阴天，早晨天气寒冷，请尽量避免户外晨练，若坚持室外晨练请注意保暖防冻，建议年老体弱人群适当减少晨练时间。
             */

            private MorningSportBean morning_sport;
            /**
             * brief : 较不适宜
             * details : 有降水，会给您的出行带来很大的不便，建议就近或最好在室内进行夜生活。
             */

            private NightLifeBean night_life;
            /**
             * brief : 干燥
             * details : 阴天，路面比较干燥，路况较好。
             */

            private RoadConditionBean road_condition;
            /**
             * brief : 适宜
             * details : 阴天，在这种天气里去逛街，省去了涂防晒霜，打遮阳伞的麻烦，既可放松身心，又会有很多意外收获。
             */

            private ShoppingBean shopping;
            /**
             * brief : 较适宜
             * details : 阴天，较适宜进行各种户内外运动。
             */

            private SportBean sport;
            /**
             * brief : 弱
             * details : 属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
             */

            private SunscreenBean sunscreen;
            /**
             * brief : 良好
             * details : 阴天，路面干燥，交通气象条件良好，车辆可以正常行驶。
             */

            private TrafficBean traffic;
            /**
             * brief : 适宜
             * details : 天气较好，温度适宜，总体来说还是好天气哦，这样的天气适宜旅游，您可以尽情地享受大自然的风光。
             */

            private TravelBean travel;
            /**
             * brief : 不带伞
             * details : 阴天，但降水概率很低，因此您在出门的时候无须带雨伞。
             */

            private UmbrellaBean umbrella;
            /**
             * brief : 最弱
             * details : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
             */

            private UvBean uv;

            public AcBean getAc() {
                return ac;
            }

            public void setAc(AcBean ac) {
                this.ac = ac;
            }

            public AirPollutionBean getAir_pollution() {
                return air_pollution;
            }

            public void setAir_pollution(AirPollutionBean air_pollution) {
                this.air_pollution = air_pollution;
            }

            public AiringBean getAiring() {
                return airing;
            }

            public void setAiring(AiringBean airing) {
                this.airing = airing;
            }

            public AllergyBean getAllergy() {
                return allergy;
            }

            public void setAllergy(AllergyBean allergy) {
                this.allergy = allergy;
            }

            public BeerBean getBeer() {
                return beer;
            }

            public void setBeer(BeerBean beer) {
                this.beer = beer;
            }

            public BoatingBean getBoating() {
                return boating;
            }

            public void setBoating(BoatingBean boating) {
                this.boating = boating;
            }

            public CarWashingBean getCar_washing() {
                return car_washing;
            }

            public void setCar_washing(CarWashingBean car_washing) {
                this.car_washing = car_washing;
            }

            public ChillBean getChill() {
                return chill;
            }

            public void setChill(ChillBean chill) {
                this.chill = chill;
            }

            public ComfortBean getComfort() {
                return comfort;
            }

            public void setComfort(ComfortBean comfort) {
                this.comfort = comfort;
            }

            public DatingBean getDating() {
                return dating;
            }

            public void setDating(DatingBean dating) {
                this.dating = dating;
            }

            public DressingBean getDressing() {
                return dressing;
            }

            public void setDressing(DressingBean dressing) {
                this.dressing = dressing;
            }

            public FishingBean getFishing() {
                return fishing;
            }

            public void setFishing(FishingBean fishing) {
                this.fishing = fishing;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public HairDressingBean getHair_dressing() {
                return hair_dressing;
            }

            public void setHair_dressing(HairDressingBean hair_dressing) {
                this.hair_dressing = hair_dressing;
            }

            public KiteflyingBean getKiteflying() {
                return kiteflying;
            }

            public void setKiteflying(KiteflyingBean kiteflying) {
                this.kiteflying = kiteflying;
            }

            public MakeupBean getMakeup() {
                return makeup;
            }

            public void setMakeup(MakeupBean makeup) {
                this.makeup = makeup;
            }

            public MoodBean getMood() {
                return mood;
            }

            public void setMood(MoodBean mood) {
                this.mood = mood;
            }

            public MorningSportBean getMorning_sport() {
                return morning_sport;
            }

            public void setMorning_sport(MorningSportBean morning_sport) {
                this.morning_sport = morning_sport;
            }

            public NightLifeBean getNight_life() {
                return night_life;
            }

            public void setNight_life(NightLifeBean night_life) {
                this.night_life = night_life;
            }

            public RoadConditionBean getRoad_condition() {
                return road_condition;
            }

            public void setRoad_condition(RoadConditionBean road_condition) {
                this.road_condition = road_condition;
            }

            public ShoppingBean getShopping() {
                return shopping;
            }

            public void setShopping(ShoppingBean shopping) {
                this.shopping = shopping;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public SunscreenBean getSunscreen() {
                return sunscreen;
            }

            public void setSunscreen(SunscreenBean sunscreen) {
                this.sunscreen = sunscreen;
            }

            public TrafficBean getTraffic() {
                return traffic;
            }

            public void setTraffic(TrafficBean traffic) {
                this.traffic = traffic;
            }

            public TravelBean getTravel() {
                return travel;
            }

            public void setTravel(TravelBean travel) {
                this.travel = travel;
            }

            public UmbrellaBean getUmbrella() {
                return umbrella;
            }

            public void setUmbrella(UmbrellaBean umbrella) {
                this.umbrella = umbrella;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class AcBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class AirPollutionBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class AiringBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class AllergyBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class BeerBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class BoatingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class CarWashingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class ChillBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class ComfortBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class DatingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class DressingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class FishingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class FluBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class HairDressingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class KiteflyingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class MakeupBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class MoodBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class MorningSportBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class NightLifeBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class RoadConditionBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class ShoppingBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class SportBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class SunscreenBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class TrafficBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class TravelBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class UmbrellaBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

            public static class UvBean {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }
        }
    }
}
