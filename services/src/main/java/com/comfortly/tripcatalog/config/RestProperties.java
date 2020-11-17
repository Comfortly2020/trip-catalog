package com.comfortly.tripcatalog.config;

import javax.enterprise.context.ApplicationScoped;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

@ConfigBundle("rest-properties.config")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(value = "maintenance-mode", watch = true)
    private Boolean maintenanceMode;

    public Boolean getMaintenanceMode() {
        return this.maintenanceMode;
    }

    public void setMaintenanceMode(final Boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }
}
