package net.damoang.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//todo remove db exclude
@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
)
public class DmgNgBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmgNgBeApplication.class, args);
	}

}
