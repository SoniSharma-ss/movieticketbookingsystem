package com.example.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
		 
		final String uri = "http://localhost:8080/pay?price=10";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);

	    System.out.println(result);
		/* AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.booking");
        context.refresh();
  
        MovieRepository repository = context.getBean(MovieRepository.class);
  
        new MovieEntity();
		// testing the store method
        repository.save(MovieEntity.builder().id(1).name("sharsoni").language(MovieLanguage.ENGLISH).releaseDate( LocalDate.now()).build());
       
        // testing the retrieve method
        List<MovieEntity> movieEntity = repository.findAll();
        System.out.println(movieEntity);
         
        
        TheaterRepository repository = context.getBean(TheaterRepository.class);
        List<TheaterEntity> t = repository.findAll();
        System.out.println(t);
       
        // close the spring context
        context.close(); */
	}

}
