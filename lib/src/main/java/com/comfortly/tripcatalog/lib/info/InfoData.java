package com.comfortly.tripcatalog.lib.info;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InfoData {

    public static InfoData getInfoData() {
        InfoData data = new InfoData();

        data.setClani(Arrays.asList("dv6510"));
        data.setOpisProjekta("Aplikacija za shranjevanje podatkov o vožnji. Izpolnjevanju vprašalnika po opravljeni " +
                "vožnji in nato analizi vožnje s podatki iz vprašalnika.");
        data.setMikrostoritve(Arrays.asList("http://35.189.96.118:8080/v1/trips"));
        data.setGithub(Arrays.asList("https://github.com/Comfortly2020/trip-catalog"));
        data.setTravis(Collections.emptyList());
        data.setDockerhub(Arrays.asList("https://hub.docker.com/repository/docker/davidunilj/comfortly-trip-data"));
        return data;
    }

    private List<String> clani;

    private String opisProjekta;

    private List<String> mikrostoritve;

    private List<String> github;

    private List<String> travis;

    private List<String> dockerhub;

    public List<String> getClani() {
        return clani;
    }

    public void setClani(List<String> clani) {
        this.clani = clani;
    }

    public String getOpis_projekta() {
        return opisProjekta;
    }

    public void setOpisProjekta(String opisProjekta) {
        this.opisProjekta = opisProjekta;
    }

    public List<String> getMikrostoritve() {
        return mikrostoritve;
    }

    public void setMikrostoritve(List<String> mikrostoritve) {
        this.mikrostoritve = mikrostoritve;
    }

    public List<String> getGithub() {
        return github;
    }

    public void setGithub(List<String> github) {
        this.github = github;
    }

    public List<String> getTravis() {
        return travis;
    }

    public void setTravis(List<String> travis) {
        this.travis = travis;
    }

    public List<String> getDockerhub() {
        return dockerhub;
    }

    public void setDockerhub(List<String> dockerhub) {
        this.dockerhub = dockerhub;
    }
}
