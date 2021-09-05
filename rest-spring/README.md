# Spring Framework
RESTful API exploration of spring core, boot etc.

<div class="text-align: justify">

## **Brief History about Java EE**

**Jarkarta EE** (formerly known as **Java EE**) is an Eclipse Foundation Project and **TomEE** (Apache foundation) shortly called as "Tommy" is an Jakarta EE certified application server. Oracle donated/contributed Java EE to Eclipse foundation (Java EE/JEE became open-source) and it is now rebranded as Jakarta EE. The major difference between Jakarta EE and Java EE/JEE is **governance model**. Eclipse Enterprise for Java (**EE4J**) is an top-level project for Jakarta EE.  

## **Introduction**

The Spring framework was created in 2003 by Rod Johnson. Spring is a complimentary technology to Java EE or Jakarta EE but not a replacement. The Spring framework supports the *devependency injection* and *common annotation* make development easier and it integrates several technologies such as Servlet API, WebSocket API, concurrency utilities, JSON binding API, bean validation, JPA, JMS and JTA/JCA.

**Principles of Spring Framework**:

- *Provide choice at every level*: Spring lets you defer design decisions at late as possible. e.g. we can switch persistance providers through configuration without changing our code.
- *Accomodate diverse perspectives*: Spring embraces flexibility and is not opinionated about how things should be done.
- *Maintains strong backward compatibility*: Spring supports carefully chosen range of JDK versions and third party libraries to facilitate maintenance of applications and libraries that depend on Spring.
- *Care about API design*: The Spring team puts a lot of thought and time into making APIs that are intuitive and that hold up across many versions and many years.
- *Set high standards for code quality*: The Spring Framework puts a strong emphasis on meaningful, current, and accurate Javadocs and can claim clean code structure with no circular dependencies between packages.

### **Spring Context**

Spring works with Plain Old Java Objects (POJO) by making it easy to exend. We need to add a configuration to wire up all dependencies and inject what's needed to create Spring ***beans*** to execute an application.

Spring Context that creates all the Spring beans using a configuration (XML or annotation or Java Config) that references all our classes which makes our application run.


</div>