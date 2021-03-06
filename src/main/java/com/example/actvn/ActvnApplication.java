package com.example.actvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		ActvnApplication.class,
		Jsr310JpaConverters.class,
})
@EnableSwagger2
public class ActvnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActvnApplication.class, args);
	}

}
