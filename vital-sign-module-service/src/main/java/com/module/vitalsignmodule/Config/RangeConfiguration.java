package com.module.vitalsignmodule.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@RefreshScope
@ConfigurationProperties("vitalsign-module-api")
@Getter
@Setter
public class RangeConfiguration {
	private String pulseRange;
	private String bloodPressureRange;
	private String temperatureRange;
	private String bloodSugarRange;
	private String respirationRateRange;
}
