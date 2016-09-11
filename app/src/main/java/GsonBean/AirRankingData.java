package GsonBean;

import java.util.List;

/**
 * Created by haiyuan 1995 on 2016/9/11.
 */

public class AirRankingData {
    /**
     * location : {"id":"W7W3YQKE4QDH","name":"海口","country":"CN","path":"海口,海口,海南,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
     * aqi : 10
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
         * id : W7W3YQKE4QDH
         * name : 海口
         * country : CN
         * path : 海口,海口,海南,中国
         * timezone : Asia/Shanghai
         * timezone_offset : +08:00
         */

        private LocationBean location;
        private String aqi;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
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
    }
}
