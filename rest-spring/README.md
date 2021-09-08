# Spring Framework
RESTful API exploration of spring core, boot etc.

<div style="text-align: justify">

## 1. **Brief History about Java EE**

**Jarkarta EE** (formerly known as **Java EE**) is an Eclipse Foundation Project and **TomEE** (Apache foundation) shortly called as "Tommy" is an Jakarta EE certified application server. Oracle donated/contributed Java EE to Eclipse foundation (Java EE/JEE became open-source) and it is now rebranded as Jakarta EE. The major difference between Jakarta EE and Java EE/JEE is **governance model**. Eclipse Enterprise for Java (**EE4J**) is an top-level project for Jakarta EE.  

## 2. **Spring Framework**

The Spring framework was created in 2003 by Rod Johnson. Spring is a complimentary technology to Java EE or Jakarta EE but not a replacement. The Spring framework supports the *devependency injection* and *common annotation* make development easier and it integrates several technologies such as Servlet API, WebSocket API, concurrency utilities, JSON binding API, bean validation, JPA, JMS and JTA/JCA.

Spring along with Java, it also offers excellent support of **Groovy** and **Kotlin** as alternative languages on the JVM.

**Principles of Spring Framework**:

- *Provide choice at every level*: Spring lets you defer design decisions at late as possible. e.g. we can switch persistance providers through configuration without changing our code.
- *Accomodate diverse perspectives*: Spring embraces flexibility and is not opinionated about how things should be done.
- *Maintains strong backward compatibility*: Spring supports carefully chosen range of JDK versions and third party libraries to facilitate maintenance of applications and libraries that depend on Spring.
- *Care about API design*: The Spring team puts a lot of thought and time into making APIs that are intuitive and that hold up across many versions and many years.
- *Set high standards for code quality*: The Spring Framework puts a strong emphasis on meaningful, current, and accurate Javadocs and can claim clean code structure with no circular dependencies between packages.

The Spring framework can be considered as a collection of sub-frameworks also called as layers such as Spring Core, Spring AOP, Spring Web MVC, Spring DAO, Spring ORM, Spring Context and Spring WebFlow.

### 2.1 **Spring Core and IoC container**

Spring Core module is the core component of Spring framework and provides **Inversion of Control (IoC) container**.

IoC container does the following:

- Creates the objects, configures, assembles their dependencies and manages their entire life cycle.
- It uses **Dependency Injection (DI)** to manage the components that makeup the application. It gets the information about the objects from a configuration file(XML) or Java Code or Java Annotations and Java POJO class. These objects are called **Beans**. Since the Controlling of Java objects and their lifecycle is not done by the developers, hence the name **Inversion Of Control (IoC)**.

#### **Inversion of Control (IoC) and Dependency Injection (DI)**

**IoC is also known as dependency injection (DI)**. It is a process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes or a mechanism such as the ***Service Locator*** pattern.

There are **two types of Spring IoC Containers**:

**BeanFactory Container**: This is the simplest container providing the basic support for **Dependency Injection (DI)** and it  is defined by `org.springframework.beans.factory.BeanFactory` interface. The BeanFactory and related interfaces, such as *BeanFactoryAware*, *InitializingBean*, *DisposableBean*, are still present in Spring for the purpose of backward compatibility with a large number of third-party frameworks that integrate with Spring.

**ApplicationContext Container**: This container adds more enterprise specific functionality such as the ability to resolve textual messages from a properties file and the ability to publish application events to interested event listeners. This container is defined by the `org.springframework.context.ApplicationContext` interface. The *ApplicationContext* container includes all functionality of the *BeanFactory* container, so it is generally recommended over *BeanFactory*. *BeanFactory* can still be used for lightweight applications like mobile devices or applet-based applications where data volume and speed is significant.

### 2.2 **Spring Context**

Spring works with Plain Old Java Objects (POJO) by making it easy to exend. We need to add a configuration to wire up all dependencies and inject what's needed to create Spring ***beans*** to execute an application.

Spring Context that creates all the Spring beans using a configuration (XML or annotation or Java Config) that references all our classes which makes our application run.

<img src="https://github.com/srikanthkakumanu/apis/blob/main/rest-spring/spring-context.png" width=500 height=300></img>


### 2.3 **Spring WebFlux**

The **Spring WebFlux** module was introduced in version 5 with a fully non-blocking stack that supports Reactive Streams back-pressure and runs on servers such as Netty, Undertow and Servlet 3.1+ containers. It is aimed for a non-blocking stack that handles concurrency with a small number of threads that can scale with less hardware.

The WebFlux module depends on another Spring project known as **Project Reactor** (https://projectreactor.io).

**Reactor** is the reactive library of choice for Spring WebFlux. Reactor is a fourth-generation reactive library based on Reactive Streams specification for building *non-blocking backpressure-ready applications on JVM*.

Reactor offers two reactive and composable APIs called **Mono and Flux**. The *Mono* and *Flux* types are used to work on data sequences of 0..1 and 0..N through a rich set of operators aligned with the *ReactiveX* vocabulary of operators. Reactor has a strong focus on server-side Java. It is developed in close collaboration with Spring.

## 3. **Spring boot**

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

With Spring Boot:

- Create stand-alone Spring applications.

- Embed **Tomcat** (by default), **Jetty** or **Undertow** directly (no need to deploy WAR files) or **Netty** (If we are using the new **web-reactive modules**).

- Provide opinionated 'starter' dependencies to simplify your build configuration.

- Automatically configure Spring and 3rd party libraries whenever possible.

- Provide production-ready features such as metrics, health checks, and externalized configuration.

- Absolutely no code generation and no requirement for XML configuration (favors *convention over configuration*).

### 3.1 **Spring Boot features**

Spring Boot is highly customizable, from the auto-configuration that sets up our application (based on the *classpath*) to customizing how it starts, what to show, and what to enable or disable based on its own properties.

Some of the features below given:

- Auto-configuration
- SpringApplication class
- SpringApplicationBuilder class

There are two important Spring Boot components:

**`@SpringBootApplication` annotation**: This annotation is combination of **`@ComponentScan`**, **`@Configuration`** and **`@EnableAutoConfiguration`** annotations.

**SpringApplication**: This class provides the bootstrap for the Spring Boot application that is executed in the main() method. We need to pass the class that is executed.

e.g.

```java
@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
	// SpringApplication.run(RestApplication.class, args);
	// Or, alternative approach below
      SpringApplication app = 
              new SpringApplication(RestApplication.class);
		app.run(args);
	}
}    
```

### 3.1.1 **Auto-Configuration**

**Auto-configuration** is one of the important features in Spring Boot because it configures our Spring Boot application according to our classpath, annotations, any other configuration declarations such as JavaConfig classes or XML.

Spring Boot won't generate any source code but it adds some on the fly.

- Spring Boot starts by importing missing dependencies, such as the **`org.springframework.web.bind.annotation.RestController`** annotation, among other imports.
- Next, it identifies that you need a **`spring-boot-starter-web`** because you marked your class and method with the `@RestController` and the `@GetMapping` annotations.
- Next, it adds the necessary annotation that triggers auto-configuration, the **`@EnableAutoConfiguration`** annotation.
- Then it adds the main method that is the entry point for the application.

The **`@SpringBootApplication`** annotation, which is one of the main components of a Spring Boot app. This annotation is equivalent to declaring the **`@Configuration`**, **`@ComponentScan`**, and **`@EnableAutoConfiguration`** annotations (**`@SpringBootApplication`** inherits all three).

#### **Disable or exclude specific Auto-configuration**

You can disable a specific auto-configuration by adding the **`exclude`** parameter by using either the **`@EnableAutoConfiguration`** or the **`@SpringBootApplication`** annotations in your class.

This is a **very useful technique** when we want Spring Boot to skip certain and unnecessary auto-configurations.

e.g.

```java
@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class,DataSourceAutoConfiguration.class})
```

#### **@EnableAutoConfiguration and @Enable(Technology) annotations**

The Spring framework and some of its modules such as SpringData, SpringAMQP and SpringIntegration provide @Enable<Technology> annotations e.g. ***`@EnableTransactionManagement`***, ***`@EnableRabbit`*** and ***`@EnableIntegration`*** etc.

We can use these annotations to follow the ***convention over configuration*** pattern. They help making our apps easy to develop without worrying about its configuration.

As discussed above, ***@EnableAutoConfiguration*** annotation to do the auto-configuration.

*spring-boot-autoconfigure-<version>.jar/META-INF/**spring.factories*** contains all the auto-configuration classes that are used to setup any configuration that our application needs for running.

Useful Link: https://devwithus.com/customize-spring-boot-auto-configuration/ </br>

### 3.1.2 **SpringApplication class**

We can have more advanced configuration using the **`SpringApplication`** such as custom banner, banner mode etc. It allows us to configure the way our app behaves and we have control over the main **`ApplicationContext` (Spring IoC container)** where all our beans are used.

### 3.1.3 **SpringApplicationBuilder class**

The **`SpringApplicationBuilder`** class provides a fluent API and is a builder for the `SpringApplication` and the `ApplicationContext` instances.

Using **`SpringApplicationBuilder`** we can do:

- Define and run **`SpringApplication`** using **`SpringApplicationBuilder.sources()`** method. We can also get current SpringApplication instance using `application()`.
- We can define a hierarchy when creating a Spring application using `.child()` method.
  - If we have a web configuration, make sure that it's being declared as a child.
  - Also parent and child must share the same `org.springframework.core.env.Environment` interface (this represents the environment in which the current application is running; it is related to profiles and properties declarations)
- We can log the information at startup. By default it is set to true e.g. `.logStartupInfo()`.
- We can activate/de-activate profiles e.g. `.profiles("prod", "cloud")`.
- we can also attach listeners for some of the **`ApplicationEvent`** events. e.g. `.listeners(event -> logger.info("#### > " + event.getClass().getCanonicalName()))`. We can also have these additional events.
  - **`ApplicationEvent`**: generic application event.
  - **`ApplicationStartedEvent`**: This is sent at the start.
  - **`ApplicationEnvironmentPreparedEvent`**: This is sent when the environment is known.
  - **`ApplicationPreparedEvent`**: This is sent after the bean definitions.
  - **`ApplicationReadyEvent`**: This is sent when the application is ready.
  - **`ApplicationFailedEvent`**: This is sent incase of exception during the startup.
- We can remove any web environment auto-configuration from happening. e.g. `.web(WebApplicationType.NONE)` or `.web(WebApplicationType.SERVLET)` or `.web(WebApplicationType.REACTIVE)`.

</div>