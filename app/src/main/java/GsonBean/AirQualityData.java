package GsonBean;

import java.util.List;

/**
 * Created by haiyuan 1995 on 2016/9/10.
 */

public class AirQualityData {
    /**
     * location : {"id":"WX4FBXXFKE4F","name":"北京","country":"CN","path":"北京,北京,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
     * air : {"city":{"aqi":"26","pm25":"11","pm10":"26","so2":"4","no2":"29","co":"0.433","o3":"41","last_update":"2016-09-10T11:00:00+08:00","quality":"优"},"stations":[{"aqi":"17","pm25":"8","pm10":"15","so2":"2","no2":"33","co":"0.5","o3":"49","last_update":"2016-09-10T11:00:00+08:00","station":"万寿西宫","latitude":"39.865927","longitude":"116.359805"},{"aqi":"42","pm25":"13","pm10":"42","so2":"12","no2":"17","co":"0.5","o3":"49","last_update":"2016-09-10T11:00:00+08:00","station":"定陵","latitude":"40.285142","longitude":"116.163759"},{"aqi":"19","pm25":"7","pm10":"12","so2":"2","no2":"37","co":"0.5","o3":"44","last_update":"2016-09-10T11:00:00+08:00","station":"东四","latitude":"39.950804","longitude":"116.427767"},{"aqi":"24","pm25":"10","pm10":"24","so2":"2","no2":"44","co":"0.4","o3":"42","last_update":"2016-09-10T11:00:00+08:00","station":"天坛","latitude":"39.873111","longitude":"116.427790"},{"aqi":"28","pm25":"19","pm10":null,"so2":"3","no2":"32","co":"0.4","o3":"51","last_update":"2016-09-10T11:00:00+08:00","station":"农展馆","latitude":"39.970288","longitude":"116.466866"},{"aqi":"20","pm25":"12","pm10":"20","so2":"3","no2":"34","co":"0.5","o3":"2","last_update":"2016-09-10T11:00:00+08:00","station":"官园","latitude":"39.941134","longitude":"116.354797"},{"aqi":"25","pm25":"17","pm10":"24","so2":"6","no2":"37","co":"0.5","o3":"41","last_update":"2016-09-10T11:00:00+08:00","station":"海淀区万柳","latitude":"39.992122","longitude":"116.308900"},{"aqi":"13","pm25":"5","pm10":"13","so2":"2","no2":"15","co":"0.2","o3":null,"last_update":"2016-09-10T11:00:00+08:00","station":"顺义新城","latitude":"40.142587","longitude":"116.713935"},{"aqi":"21","pm25":"10","pm10":"21","so2":"2","no2":"4","co":"0.3","o3":"48","last_update":"2016-09-10T11:00:00+08:00","station":"怀柔镇","latitude":"40.392563","longitude":"116.638102"},{"aqi":"45","pm25":"12","pm10":"45","so2":"9","no2":"33","co":"0.6","o3":"45","last_update":"2016-09-10T11:00:00+08:00","station":"昌平镇","latitude":"40.193956","longitude":"116.223904"},{"aqi":"26","pm25":"8","pm10":"26","so2":"2","no2":"28","co":"0.2","o3":"49","last_update":"2016-09-10T11:00:00+08:00","station":"奥体中心","latitude":"40.001692","longitude":"116.400748"},{"aqi":"51","pm25":"18","pm10":"51","so2":"9","no2":"37","co":"0.6","o3":"37","last_update":"2016-09-10T11:00:00+08:00","station":"古城","latitude":"39.926664","longitude":"116.218942"}]}
     * last_update : 2016-09-10T11:00:00+08:00
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
         * id : WX4FBXXFKE4F
         * name : 北京
         * country : CN
         * path : 北京,北京,中国
         * timezone : Asia/Shanghai
         * timezone_offset : +08:00
         */

        private LocationBean location;
        /**
         * city : {"aqi":"26","pm25":"11","pm10":"26","so2":"4","no2":"29","co":"0.433","o3":"41","last_update":"2016-09-10T11:00:00+08:00","quality":"优"}
         * stations : [{"aqi":"17","pm25":"8","pm10":"15","so2":"2","no2":"33","co":"0.5","o3":"49","last_update":"2016-09-10T11:00:00+08:00","station":"万寿西宫","latitude":"39.865927","longitude":"116.359805"},{"aqi":"42","pm25":"13","pm10":"42","so2":"12","no2":"17","co":"0.5","o3":"49","last_update":"2016-09-10T11:00:00+08:00","station":"定陵","latitude":"40.285142","longitude":"116.163759"},{"aqi":"19","pm25":"7","pm10":"12","so2":"2","no2":"37","co":"0.5","o3":"44","last_update":"2016-09-10T11:00:00+08:00","station":"东四","latitude":"39.950804","longitude":"116.427767"},{"aqi":"24","pm25":"10","pm10":"24","so2":"2","no2":"44","co":"0.4","o3":"42","last_update":"2016-09-10T11:00:00+08:00","station":"天坛","latitude":"39.873111","longitude":"116.427790"},{"aqi":"28","pm25":"19","pm10":null,"so2":"3","no2":"32","co":"0.4","o3":"51","last_update":"2016-09-10T11:00:00+08:00","station":"农展馆","latitude":"39.970288","longitude":"116.466866"},{"aqi":"20","pm25":"12","pm10":"20","so2":"3","no2":"34","co":"0.5","o3":"2","last_update":"2016-09-10T11:00:00+08:00","station":"官园","latitude":"39.941134","longitude":"116.354797"},{"aqi":"25","pm25":"17","pm10":"24","so2":"6","no2":"37","co":"0.5","o3":"41","last_update":"2016-09-10T11:00:00+08:00","station":"海淀区万柳","latitude":"39.992122","longitude":"116.308900"},{"aqi":"13","pm25":"5","pm10":"13","so2":"2","no2":"15","co":"0.2","o3":null,"last_update":"2016-09-10T11:00:00+08:00","station":"顺义新城","latitude":"40.142587","longitude":"116.713935"},{"aqi":"21","pm25":"10","pm10":"21","so2":"2","no2":"4","co":"0.3","o3":"48","last_update":"2016-09-10T11:00:00+08:00","station":"怀柔镇","latitude":"40.392563","longitude":"116.638102"},{"aqi":"45","pm25":"12","pm10":"45","so2":"9","no2":"33","co":"0.6","o3":"45","last_update":"2016-09-10T11:00:00+08:00","station":"昌平镇","latitude":"40.193956","longitude":"116.223904"},{"aqi":"26","pm25":"8","pm10":"26","so2":"2","no2":"28","co":"0.2","o3":"49","last_update":"2016-09-10T11:00:00+08:00","station":"奥体中心","latitude":"40.001692","longitude":"116.400748"},{"aqi":"51","pm25":"18","pm10":"51","so2":"9","no2":"37","co":"0.6","o3":"37","last_update":"2016-09-10T11:00:00+08:00","station":"古城","latitude":"39.926664","longitude":"116.218942"}]
         */

        private AirBean air;
        private String last_update;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public AirBean getAir() {
            return air;
        }

        public void setAir(AirBean air) {
            this.air = air;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public static class LocationBean {
            private String id;
            private String name;
            private String country;
            private String path;
            private String timezone;
            private String timezone_offset;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getTimezone_offset() {
                return timezone_offset;
            }

            public void setTimezone_offset(String timezone_offset) {
                this.timezone_offset = timezone_offset;
            }
        }

        public static class AirBean {
            /**
             * aqi : 26
             * pm25 : 11
             * pm10 : 26
             * so2 : 4
             * no2 : 29
             * co : 0.433
             * o3 : 41
             * last_update : 2016-09-10T11:00:00+08:00
             * quality : 优
             */

            private CityBean city;
            /**
             * aqi : 17
             * pm25 : 8
             * pm10 : 15
             * so2 : 2
             * no2 : 33
             * co : 0.5
             * o3 : 49
             * last_update : 2016-09-10T11:00:00+08:00
             * station : 万寿西宫
             * latitude : 39.865927
             * longitude : 116.359805
             */

            private List<StationsBean> stations;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public List<StationsBean> getStations() {
                return stations;
            }

            public void setStations(List<StationsBean> stations) {
                this.stations = stations;
            }

            public static class CityBean {
                private String aqi;
                private String pm25;
                private String pm10;
                private String so2;
                private String no2;
                private String co;
                private String o3;
                private String last_update;
                private String quality;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getLast_update() {
                    return last_update;
                }

                public void setLast_update(String last_update) {
                    this.last_update = last_update;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }
            }

            public static class StationsBean {
                private String aqi;
                private String pm25;
                private String pm10;
                private String so2;
                private String no2;
                private String co;
                private String o3;
                private String last_update;
                private String station;
                private String latitude;
                private String longitude;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getLast_update() {
                    return last_update;
                }

                public void setLast_update(String last_update) {
                    this.last_update = last_update;
                }

                public String getStation() {
                    return station;
                }

                public void setStation(String station) {
                    this.station = station;
                }

                public String getLatitude() {
                    return latitude;
                }

                public void setLatitude(String latitude) {
                    this.latitude = latitude;
                }

                public String getLongitude() {
                    return longitude;
                }

                public void setLongitude(String longitude) {
                    this.longitude = longitude;
                }
            }
        }
    }
}
