package com.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * This class provides the bootstrap for the Spring Boot application that is 
 * executed in the main method. You need to pass the class that is executed.
 */ 
@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class RestApplication {

	public static void main(String[] args) {
		usingSpringApplication(args);


	}

	private static void usingSpringApplication(String[] args) {
		// SpringApplication.run(RestApplication.class, args);
		// Or, alternative approach below
		SpringApplication app = new SpringApplication(RestApplication.class);

		// customizing banner programmatically
		// app.setBanner(new Banner() {
		// 	@Override
		// 	public void printBanner(Environment environment, Class<?>
		// 	sourceClass, PrintStream out) {
		// 	out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
		// 	}
		// 	});
			
		app.setBanner((environment, sourceClass, out) -> { 
			out.print("\n\n\tThis is my own banner!\n\n".toUpperCase()); 
		});
		// app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	/**
	 * Demonstrates different use cases and examples of SpringApplicationBuilder
	 * @param args
	 */
	private static void viaSpringApplicationBuilder(String[] args) {
		new SpringApplicationBuilder()
			.bannerMode(Banner.Mode.CONSOLE)
			.sources(RestApplication.class)
			.run(args);
		
		/* 
		1. We can define hierarchy when creating a spring app.
		2. If we have a web configuration, make sure that it's being declared as a child.
		3. Also parent and child must share the same org.springframework.core.env.Environment interface
		   	(this represents the environment in which the current application is running; it
			 is related to profiles and properties declarations)			 
		*/
		new SpringApplicationBuilder(RestApplication.class)
			.bannerMode(Banner.Mode.CONSOLE)
			//.child(OtherConfig.class)
			.run(args);

		// We can log the information at startup. By default it is set to true
		new SpringApplicationBuilder(RestApplication.class)
			.logStartupInfo(false)
			//.child(OtherConfig.class)
			.run(args);

		// We can activate/de-activate profiles
		new SpringApplicationBuilder(RestApplication.class)
			.profiles("prod", "cloud")
			//.child(OtherConfig.class)
			.run(args);
			
		// we can attach listeners for some of the ApplicationEvent events
		Logger logger = LoggerFactory.getLogger(RestApplication.class);
		new SpringApplicationBuilder(RestApplication.class)
			// .listeners(new ApplicationListener<ApplicationEvent>() {
			// 	@Override
			// 	public void onApplicationEvent(ApplicationEvent event) {
			// 		logger.info("#### > " + event.getClass().getCanonicalName());
			// 	}
			// })
			.listeners(event -> logger.info("#### > " + event.getClass().getCanonicalName()))
			//.child(OtherConfig.class)
			.run(args);

		// We can remove any web environment auto-configuration from happening.
		// Spring boot guesses which kind of app we are running based on the classpath
		// For a web app, the algorithm is very simple WebApplicationType.SERVLET or WebApplicationType.REACTIVE; but imagine that you are using
		// libraries that actually run without a web environment, and your app is not a web app, but Spring Boot configures it as shown below.
		new SpringApplicationBuilder(RestApplication.class)
			.web(WebApplicationType.NONE)
			//.child(OtherConfig.class)
			.run(args);
	}

	/**
	 * Spring Boot allows you to get the arguments passed to the application. 
	 * When you have SpringApplication.run(SpringBootSimpleApplication.class, args), 
	 * you have access to the args in you beans. See MyComponent below
	 * @param args
	 */
	private static void usingApplicationArguments(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}

// Using ApplicationAruments class
@Component
class MyComponent {
	private static final Logger log = LoggerFactory.getLogger(MyComponent.class);
	
	@Autowired
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if(enable)
			log.info("## > You are enabled!");
		List<String> _args = args.getNonOptionArgs();
		log.info("## > extra args ...");
		if(!_args.isEmpty())
			_args.forEach(file -> log.info(file));
	}
}
