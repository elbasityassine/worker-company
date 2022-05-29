package org.intelciaitsolution.workercompany.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.intelciaitsolution.workercompany"},
        exclude = {DataSourceAutoConfiguration.class})
public class WorkerCompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkerCompanyApplication.class, args);
    }
}
