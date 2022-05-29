package org.intelciaitsolution.workercompany.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"org.intelciaitsolution.workercompany"})
public class FeignClientConfiguration {
}
