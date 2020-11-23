package com.comfortly.tripcatalog.config;

import javax.enterprise.context.ApplicationScoped;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

@ConfigBundle("rest-properties.config")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(value = "broken", watch = true)
    private Boolean broken;

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }
}
