package ua.iot.kovalyk.mysqlspringlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// run data.sql and then schema.sql after the application is started, 
// to fill DB with data and create triggers/functions/procedures
// the files are in resources folder 
