package com.dev.scaffdone;

import com.dev.scaffdone.scaffold.ScaffoldService;
import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import com.dev.scaffdone.scaffold.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication()
public class ScaffDoneApplication implements CommandLineRunner {

	@Autowired
	public ScaffoldService service;

	public static void main(String[] args) {


		SpringApplication.run(ScaffDoneApplication.class, args);
		ScaffoldData scaffoldData = new ScaffoldData(1L, Size.A,true,1.0f,Size.ONEFRAME,"Owi");
		System.out.println(scaffoldData.username());

	}


	@Override
	public void run(String... args) throws Exception {
		service.printData();
	}
}
