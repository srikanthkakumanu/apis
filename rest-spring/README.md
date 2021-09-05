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

### 2.1 **Spring Context**

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

### 3.1 **Auto-Configuration**

**Auto-configuration** is one of the important features in Spring Boot because it configures our Spring Boot application according to our classpath, annotations, any other configuration declarations such as JavaConfig classes or XML.

Spring Boot won't generate any source code but it adds some on the fly.

- Spring Boot starts by importing missing dependencies, such as the **`org.springframework.web.bind.annotation.RestController`** annotation, among other imports.
- Next, it identifies that you need a **`spring-boot-starter-web`** because you marked your class and method with the `@RestController` and the `@GetMapping` annotations.
- Next, it adds the necessary annotation that triggers auto-configuration, the **`@EnableAutoConfiguration`** annotation.
- Then it adds the main method that is the entry point for the application.

The **`@SpringBootApplication`** annotation, which is one of the main components of a Spring Boot app. This annotation is equivalent to declaring the **`@Configuration`**, **`@ComponentScan`**, and **`@EnableAutoConfiguration`** annotations (**`@SpringBootApplication`** inherits all three).

You can disable a specific auto-configuration by adding the **`exclude`** parameter by using either the **`@EnableAutoConfiguration`** or the **`@SpringBootApplication`** annotations in your class.

This is a **very useful technique** when we want Spring Boot to skip certain and unnecessary auto-configurations.

e.g.

```
@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class,DataSourceA
utoConfiguration.class})
```

Useful Link: https://devwithus.com/customize-spring-boot-auto-configuration/ </br>

</div>