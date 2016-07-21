package cn.zhangxd.trip.service.provider.thirdapi.geolocation;

import com.google.gson.annotations.SerializedName;

public class BaiduGeoBean {

    @Override
    public String toString() {
        return "BaiduGeoBean{" +
                "result=" + result +
                '}';
    }

    @SerializedName("status")
    private int status;

    @SerializedName("result")
    private BaiduGeoResult result;

    public class BaiduGeoResult {

        @Override
        public String toString() {
            return "BaiduGeoResult{" +
                    ", location=" + location +
                    ", formatted_address=" + formattedAddress +
                    ", business=" + business +
                    ", addressComponent=" + addressComponent +
                    ", cityCode=" + cityCode +
                    '}';
        }

        @SerializedName("location")
        private BaiduGeoLocation location;

        public class BaiduGeoLocation {

            @Override
            public String toString() {
                return "BaiduGeoLocation{" +
                        ", lng=" + lng +
                        ", lat=" + lat +
                        '}';
            }

            @SerializedName("lng")
            private Double lng;
            @SerializedName("lat")
            private Double lat;

            public Double getLng() {
                return lng;
            }

            public void setLng(Double lng) {
                this.lng = lng;
            }

            public Double getLat() {
                return lat;
            }

            public void setLat(Double lat) {
                this.lat = lat;
            }
        }

        @SerializedName("formatted_address")
        private String formattedAddress;

        @SerializedName("business")
        private String business;

        @SerializedName("addressComponent")
        private AddressComponent addressComponent;

        public class AddressComponent {

            @Override
            public String toString() {
                return "AddressComponent{" +
                        ", adcode=" + adcode +
                        ", country=" + country +
                        ", direction=" + direction +
                        ", province=" + province +
                        ", street=" + street +
                        ", streetNumber=" + streetNumber +
                        ", countryCode=" + countryCode +
                        '}';
            }

            @SerializedName("adcode")
            private String adcode;
            @SerializedName("city")
            private String city;
            @SerializedName("country")
            private String country;
            @SerializedName("direction")
            private String direction;
            @SerializedName("province")
            private String province;
            @SerializedName("street")
            private String street;
            @SerializedName("street_number")
            private String streetNumber;
            @SerializedName("country_code")
            private Integer countryCode;

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreetNumber() {
                return streetNumber;
            }

            public void setStreetNumber(String streetNumber) {
                this.streetNumber = streetNumber;
            }

            public Integer getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(Integer countryCode) {
                this.countryCode = countryCode;
            }
        }

        @SerializedName("cityCode")
        private int cityCode;

        public BaiduGeoLocation getLocation() {
            return location;
        }

        public void setLocation(BaiduGeoLocation location) {
            this.location = location;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public AddressComponent getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponent addressComponent) {
            this.addressComponent = addressComponent;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaiduGeoResult getResult() {
        return result;
    }

    public void setResult(BaiduGeoResult result) {
        this.result = result;
    }

}