package com.example.lab5.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Country {
    @SerializedName("name")
    private Name name;
    private String cca3;
    private String region;
    private String[] capital;
    private long population;
    private Map<String, String> languages;
    @SerializedName("flags")
    private Flags flags;

    @SerializedName("maps")
    private Maps map;

    // Getters và Setters

    public String getName() {
        return name.getCommon(); // Lấy tên "common" từ lớp Name
    }

    public void setName(Name name) {
        this.name = name;
    }
    public String getOfficial(){
        return name.getOfficial();
    }
    public void setOfficial(String official){
        name.setOfficial(official);
    }
    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public String getFlags() {
        return flags.getPng();
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public String getMap() {
        return map.googleMaps;
    }

    public void setMap(Maps map) {
        this.map = map;
    }

    // Lớp con Name
    public static class Name {
        private String common; // Tên thường gọi
        private String official; // Tên chính thức

        public String getCommon() {
            return common;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public String getOfficial() {
            return official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }
    }
    public static class Flags{
        String png;
        String svg;

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }

    }

    public static class Maps{
        String googleMaps;
        String openStreetMaps;

        public String getGoogleMaps() {
            return googleMaps;
        }

        public void setGoogleMaps(String googleMaps) {
            this.googleMaps = googleMaps;
        }

        public String getOpenStreetMaps() {
            return openStreetMaps;
        }

        public void setOpenStreetMaps(String openStreetMaps) {
            this.openStreetMaps = openStreetMaps;
        }
    }
}
