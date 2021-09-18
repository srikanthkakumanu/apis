# **Designing Web APIs and Paradigms**

---

<div style="text-align: justify">

This repo explores different frameworks for Web APIs design and paradigms.

## **Table of contents**

---

What is an API

Why do we need APIs

API Design considerations

API Paradigms

Request __ Response APIs

Representational State Transfer (REST)

Remote Procedure Call (RPC)

GraphQL

Request __ Response APIs – Summary

Event Driven APIs

Polling approach and its Limitations

WebHooks

WebSockets

HTTP streaming

Event Driven APIs – Summary

API Documentation

API Security

## 1. **What is an API**

API (In short, Application Programming interface) is the interface that a software program presents to other programs or humans or world.

In computing terms, It is a set of functions and procedures allowing the creation of applications that access the features or data of an Operating System, application or other service.

Or, It is a connection between computers or between computer programs. It is a type of software interface offering a service to other pieces of software.

## 2. **Why do we need APIs**

APIs have emerged out of a need to exchange information with providers of data who are equipped to solve specific problems. APIs enable businesses to develop unique products quickly. Rather than reinventing the wheel, companies are able to differentiate their product offerings while taking advantage of existing technologies and tapping into other systems.

When designing an API, It is important to understand who your targeted users (developers are like customers) are, their needs and why they are using your API. Because changing an API’s design is very difficult and the cost of switching from one API to another is extremely high for most developers.
An API must be aligned with the core business as is the case with many SaaS (Software-As-A-Service) companies e.g. GitHub, Stripe, Salesforce.

Many companies structured their API development:

- API for internal developers first, external developers second
- API for external developers first, internal developers second
- API as the product

Disadvantage or risk of first approach is that the needs of external developers may drift apart from the needs of internal developers. e.g. Slack

Advantage of second approach is that the API can be customized to serve external developers rather than stradling two audiences. e.g. GitHub GraphQL API.

In case of API as the product, entire business aligns behind building a seamless interface for customers and it is the most straight forward approach possible. e.g. Stripe, Twilio

Good APIs offer:

- clarity (of purpose, design and context)
- flexibility (ability to be adopted to different use cases)
- power (completeness of the solution offered)
- Usability
- Scalability
- Performance
- hackability (ability to pickup quickly through iteration and experimentation)
- documentation and developer resources (important to setting users up for success)

## 3. **API Design considerations**

Key goals to consider when designing an API is:

- Coupling: tight coupling (strong abstraction), loose coupling
- Chattiness: High network overhead or Low latency and very low network overhead
- Client complexity
- Cognitive complexity
- Caching
- Discoverability
- Versioning

<img src="https://github.com/srikanthkakumanu/apis/blob/main/Design_considerations.png" width=500 height=300></img> </br>

## 4. **API Paradigms**

An API paradigm defines the interface exposing back-end data of a service to other applications. Therefore, picking the right API paradigm is important.

If not, there may not be enough room built-in to add the features we want later on. This can also happen when organisation or product changes over time. To save time, effort, headaches and to leave room for new and existing features it’s important to give some thought to protocols, patterns and best practices before developing an API. It will help us to design an API that allows us to make the changes we want in the future.
API paradigms broadly categorised into two:

- **Request - Response APIs**
  - REST (Representational State Transfer)
  - RPC (Remote Procedure Call)
  - GraphQL (Query Language)
- **Event Driven APIs**
  - WebHooks
  - WebSockets
  - HTTP streaming

## 5. **Request and Response APIs**

Request - Response APIs typically expose an interface through HTTP based web server. APIs define set of endpoints. Clients make HTTP requests for data to those endpoints and server returns responses. The response is typically sent back as JSON or XML.

## 5.1 **Representational State Transfer**

REST is an architectural style and it is most popular choice for API development. REST is all about resources. A resource is an entity that can be identified, named, addressed or handled on the web. REST APIs expose data as resources and use standard HTTP methods to represent CRUD (Create, Read, Update and Delete) transactions against these resources. e.g. users, customers, charges, balance, events, orders etc.

Fundamental Unit is: ***resource***

The ultimate goal of REST is Hypertext As The Engine Of Application State (HATEOAS). HATEOAS goal is to decouple client and server.

The cutting edge of REST APIs today is making that hyper text model easier by using JSON schemas such as:

- JSON Hypertext Application Language (HAL)
- JSON-API: A specification for building APIs in JSON
- Ion: An intuitive JSON based hypermedia type for REST

General rules for REST APIs:

- Resources are URI and it is part of URLs e.g. /users
- Nouns are used instead of verbs for resources. e.g. use /users/123 instead of /getUserInfo/123
- For each resource, two URLs are generally implemented – one for collection (e.g. /users) and one for specific element (e.g. /users/123).
- HTTP methods (GET, POST, UPDATE, DELETE) inform the server about the action to be performed.


|Operation  |HTTP *verb*  |URL:/users  |URL:/users/123  |Description  |
|---------|---------|---------|---------|---------|
|Create     |POST         |Create a new user         |Not applicable         |         |
|Read     |GET         |List all users         |Retrieve user 123         |GET is idempotent and has read-only semantic. GET requests never, ever change the state of a resource. They have no side-effects. </br> We can cache the results.         |
|Update     |PUT or PATCH         |Batch update users         |Update user 123         |Use PUT for replacing a resource </br> and PATCH for partial updates for existing resources.        |
|Delete     |DELETE         |         |Delete all users         |Delete user 123         |

- Standard HTTP response codes are returned by the server indicating success or failure.
  - 2XX – range indicate success.
  - 3XX – indicate a resource has moved.
  - 4XX – indicate a client-side error (e.g. missing parameter or too many requests).
  - 5XX – range indicate server-side errors.
- REST APIs may return JSON or XML responses.
- **Relationships** – A resource that exists within another resource can be better represented as a subresource instead of top-level resource in the URL. This make the relationship clarity to developers using the API. e.g. GET /repos/:owner/:repo/issues/:number
- **Non-CRUD operations** – Beyond the standard CRUD operations, REST may sometimes need to represent Non-CRUD operations and the following approaches commonly used in those scenerios:
  - Render an action as part of a field of a resource. e.g. use an request/input parameter to represent an action.
  - Treat an action like a subresource. e.g. locking and unlocking PUT /repos/:owner/:repo/issues/:number/lock
  - Some operations such as search are difficult to fit in REST style. A best practice in that case is to use just the action verb in the API URL. e.g. GET /search/code?q=:query:

**Benefits:**

- Uses standard method names, arguments and status codes.
- Utilises HTTP features
- Easy to maintain
- Good layer of abstraction (decoupled client and server)
- API can evolve over time

**Drawbacks:**

- Big payloads
- Multiple HTTP round-trips – multiple calls to get different resources leads to network overhead
- No single specification on how REST APIs should be built

**Best fit:** REST is best for APIs that expose CRUD like operations. It is best fit for APIs that focus on objects or resources, many varied clients, discoverability and documentation.

## 5.2 **Remote Procedure Call**

Remote Procedure Call (RPC) is one of the simplest API paradigms in which a client executes a block of code on another server.

Fundamental Unit is: ***action*** or ***command***

- RPC is about actions rather than resources. Clients typically pass a method name and arguments to a server and receive back JSON or XML.
- The endpoints contain name of the operation to be executed.
- API calls are made with the HTTP verb e.g. GET for read-only requests and POST for others.
- RPC works great for APIs that expose variety of actions that have more nuances and complications than CRUD operations. It accomodate complicated resource models or actions upon multiple types of resources.
- RPC-style APIs are not exclusive to HTTP. There are other high-performance protocols that are available for RPC-style APIs, including Apache Thrift, gRPC and Twirp (twitchtv).

It may be a good fit when we have tons of micro services and communicates heavily among them. Rather than making calls over REST which leads to network overhead, perhaps it is easy and good choice to use RPC mechanism to communicate among internal micro services.

**Benefits:**

- Simple and Easy to understand
- Lightweight payloads (low overhead on network)
- High performance

**Drawbacks:**

- Tight coupling (high coupling to underlying system)
- Discovery is difficult
- Limited standardization
- Leads to function explosion (too many functions i.e. actions)

**Best fit:** RPC is best for APIs exposing several actions. It is best fit for action oriented and simple interactions, internal micro services where it offers high message rate and low overhead.

## 5.3 **GraphQL**

GraphQL is a query language for APIs that has gained significant focus recently. GraphQL allows clients to define the structure of data required and server returns exactly that structure.

Fundamental Unit is: ***query***

Unlike REST and RPC, GraphQL APIs need only single URL endpoint.

We do not need different HTTP *verbs* to describe the operation. Instead we indicate in the JSON body whether we are performing a query or mutation.

GraphQL APIs support GET and POST verbs. It offers several benefits over REST and RPC.

GraphQL combines some ideas of RPC and REST.

**Benefits:**

- **Low network overhead and saves multiple roundtrips:** GraphQL enables clients to nest queries and fetch data across resources in a single request. Without GraphQL, this might require multiple HTTP calls to the server. This means mobile applications using GraphQL can be quick, even on slow network connections.
- **Avoids Versioning:** You can add new fields and types to a GraphQL API without affecting existing queries. Similarly, deprecating existing fields is easier. By doing log analysis, an API provider can figure out which clients are using a field. You can hide deprecated fields from tools and remove them when no clients are using them. With REST and RPC APIs, it’s harder to figure out which clients are using a deprecated field, making removal more difficult.
- **Smaller payload size:** REST and RPC APIs often end up responding with data that clients might not ever use. With GraphQL, because clients can exactly specify what they need, the payload sizes can be smaller. GraphQL queries return predictable results while giving clients control over the data that is returned.
- **Typed Schema i.e. Strongly typed:** GraphQL is strongly typed. At development time, GraphQL type checking helps in ensuring that a query is syntactically correct and valid. This makes building high-quality, less error-prone clients easy.
- **Introspection:** Although there are external solutions like Swagger that help make exploring REST APIs easy, GraphQL is natively discoverable. It comes with GraphiQL, an in-browser IDE for exploring GraphQL. It lets users write, validate, and test GraphQL queries in a browser.

**Drawbacks:**

- **Added complexity:** The server needs to do additional processing to parse complex queries and verify parameters.
- **Caching support:** It does not use HTTP caching semantics and custom caching is complex to implement.
- **Optimizing performance is difficult:** Optimizing performance of GraphQL queries can be difficult. It’s easy to predict the use cases and debug performance bottlenecks within a company. But when working with external developers, those use cases become difficult to understand and optimize.
- Too complicated for simple APIs

**Best fit:** GraphQL APIs are best when we need querying flexibility. It is best fit for data or mobile client APIs and data is graph-like, optimize for high latency.

## 5.4 **Request __ Response APIs Summary**

|  |REST  |RPC  |GraphQL  |
|---------|---------|---------|---------|
|**What?**     |Exposes data as ***resources*** and uses standard HTTP methods to represent CRUD operations.         |Exposes ***action*** based API methods - clients pass method name and arguments.         |A ***query*** language for APIs - clients define the structure of response.         |
|**Example services**     |Stripe, GitHub, Twitter, Google         |Slack, Flickr         |Facebook, GitHub, Yelp         |
|**Example usage**     |**`GET /users/<id>`**         |**`GET /users.get?id=<id>`**         |**`query ($id:String!) {user(login: $id) {name company createdAt }}`**         |
|HTTP ***verbs*** used     |GET, POST, PUT, PATCH, DELETE         |GET, POST         |GET, POST         |
|**Pros**     |– Standard method name, arguments format, and status codes. </br> – Utilises HTTP features. </br> – Easy to maintain.         |– Easy to understand </br>– Lightweight payloads </br>– High performance         |– Saves multiple round trips.</br>– Avoids versioning</br>– Smaller payload size</br>– Strongly typed</br>– Built-in introspection         |
|**Cons**     |– Big payloads</br>– Multiple HTTP round-trips         |– Discovery is difficult </br> – Limited standardisation </br>– Can lead to function explosion         |– Requires additional query parsing </br>– Back-end performance optimisation is difficult </br>– Too complicated for simple APIs         |
|**When to use?**     |For APIs doing CRUD like operations         |For APIs exposing several actions         |When we need querying flexibility; great for providing querying flexibility and maintaining consistency         |

## 6. **Event Driven APIs**

To share data about events real time. An event-driven architecture consists of ***event producers*** that generate a stream of events, and ***event consumers*** that listen for the events.

In case of Request - Response APIs, for services with constantly changing data, the response can quickly become stale. Developers who want to stay up to date with the changes in data often end up polling the API. With ***polling***, developers constantly query API endpoints at a predetermined frequency and look for new data. If developers poll at a ***low frequency***, their apps will not have data about all the events (like a resource being created, updated, or deleted) that occurred since the last poll. However, polling at a ***high frequency*** would lead to a huge waste of resources, as most API calls will not return any new data.

## 6.1 **Polling approach and its limitations**

In case of Request - Response APIs, for services with constantly changing data, the response can quickly become stale. Developers who want to stay up to date with the changes in data often end up polling the API.

With **polling**, developers constantly query API endpoints at a predetermined frequency and look for new data. If developers poll at a ***low frequency***, their apps will not have data about all the events (like a resource being created, updated, or deleted) that occurred since the last poll. However, polling at a ***high frequency*** would lead to a huge waste of resources, as most API calls will not return any new data.

## 6.2 **WebHooks**

A WebHook is a URL that accepts an HTTP POST (or GET/PUT/DELETE). An API provider implementing WebHooks will simply POST a message to the configured URL when something happens.

Unlike Request – Response APIs, we can receive real-time updates with WebHooks. WebHooks are great for easily sharing real-time data from one server to another server. WebHooks are easy to implement because we simply create a HTTP endpoint to receive events.

WebHooks adds **some complexities:**

- **Failures and re-tries:** To ensure WebHooks are delivered successfully, its important to build a system that will re-try WebHook delivery on errors.
- **Security:** With WebHooks, the onus is on developer to ensure that they received a legitimate WebHook. e.g. checksum, public/private key encryption etc.
- **Firewalls:** Applications running behind firewalls can access APIs over HTTP, but they are unable to receive inbound traffic. For such applications, utilizing WebHooks is difficult and often not possible.
- **Noise:** Typically each WebHook represents one single event. When there are thousands of events, happening in a short time that need to be sent via single WebHook, it can be noisy.

## 6.3 **WebSocket**

WebSocket is a protocol used to establish a two-way streaming communication channel over a single TCP connection. Although the protocol is generally used between a client and a server, sometimes it is used for server-to-server communication as well.

WebSockets enable full-duplex communication – server and client can communicate with each other simultaneously at low overhead. They also work well with firewalls that block ports because WebSockets work over port 80 or 443. WebSockets are best fit for fast, live streaming data and long lived connections.

WebSockets have **some complexities:**

- **Spotty connections:** WebSockets on mobile devices or in regions where connectivity can be spotty. Clients are supposed to keep connections alive. If connection dies, the client needs to re-initiate it.
- **Scalability:** Developers using a WebSocket API must establish a connection for each team that uses their app. This means if the app is installed on 5,000 devices, the developers would be responsible for maintaining 5,000 connections between their servers and apps.

## 6.4 **HTTP Streaming**

With HTTP request – response APIs, clients send an HTTP request and the server returns a HTTP response of a finite length. Now there is a possibility to make the length of this **response indefinite**.

With HTTP streaming, the server can continue to push new data in a single long-lived connection opened by the client. HTTP streaming is easy to consume.

To transmit the data over a persistent connection from a server to client, **there are two options**:

- **The first option** is for server to set the Transfer-Encoding header to chunked. It indicates to clients that data will be arriving in chunks of newline-delimited strings.
- **The second option** is to stream data via server-side-events (SSE). This option is best choice for clients consuming these events in a browser because they can use the standarized EventSource API.

HTTP streaming is easy to consume but there are **few complexities** such as:

- **Buffering:** Clients and proxies often have buffer limits. They might not start rendering the data to application until a threshold is met.
- **Frequent changes and re-connections:** If clients want to frequently change what kind of events they listen to, HTTP streaming may not be an ideal because it requires re-connections.

## 6.5 **Event driven APIs Summary**

|  |WebHooks  |WebSockets  |HTTP streaming  |
|---------|---------|---------|---------|
|**What?**    |Event notification via HTTP callback         |Two-way streaming connections over TCP         |Long-lived connection over HTTP         |
|**Example services**     |Slack, Stripe, GitHub, Zapier, Google         |Slack, Trello, Blockchain         |Twitter, Facebook         |
|**Benefits**     |– Easy server-to-server communication.</br> – Uses HTTP protocol         |– Two-way streaming communication.</br> – Native browser support</br> – Can bypass firewalls         |– Can stream over simple HTTP</br> – Native browser support</br> – Can bypass firewalls         |
|**Drawbacks**     |– Do not work across firewalls or in browsers</br> – Handling failures, retries, security is hard.         |– Need to maintain a persistant connection</br> – Not HTTP         |– Bi-directional communication is difficult</br> – Reconnections required to receive different events         |
|**When to use?**     |To trigger the server to serve real-time events         |For Two-way, real-time communication between browsers and servers         |For one-way communication over simple HTTP         |

## 7. **API Documentation**

We can use markup languages such as reStructuredText and Markdown. For simple cases, we can rely on https://readthedocs.io or GitHub pages.

## 8. **API Security**

Security is a critical element of any web application. To ensure that an application is secure there are many things that we need to do such as:

- Input validation
- Using SSL everywhere
- validating content-types
- maintaining audit logs
- protect against cross-site request forgery (CSRF)
- protect against cross-site scripting (XSS)

Beyond the above, there are some more web application security practices and techniques which have to be applied when we expose web APIs to outside world.

### 8.1 **Authentication and Authorization**

**Authentication** and **Authorization** are two foundational elements of security.

- **Authentication**: The process of verifying who you are. Web applications accomplish this by asking user to login with a username and password to ensure the request is authentic.
- **Authorization**: The process of verifying that you are permitted to do what you are trying to do.

#### 8.1.1 **Basic authentication**

Its the simplest technique used to enfore access control on the web. The clients send HTTP requests with an **`Authorization`** header which consists of the word **"Basic"** followed by a space and a string generated by combining username and password with a colon (*username:password*) and encoding it with base64.

e.g. `Authorization: Basic dXNlcjpwYXNzd29yZA==`

But it offers least amount of security. If you use Basic Authentication for your API, to use a third-party developer’s application, your users might need to share their username and password credentials with them. That has several disadvantages.

- Applications are required to store these credentials in clear text or in a way that they can decrypt them.
- Users cannot revoke access to a single application without revoking access to all the applications by changing the password.
- Applications get full access to user accounts. Users cannot limit access to selected resources.

#### 8.1.2 **oAuth**

To address issues faced by Basic Authentication and other prevalent authentication and authorization mechanisms, **OAuth** was introduced in 2007.

**OAuth** is an open standard that allows users to grant access to applications **without sharing passwords** with them. The second benefit of OAuth is that it allows API providers’ users to **grant selective permission**. Each application has different requirements of what data it needs from an API provider. The OAuth framework allows API providers to grant access to one or more resources.







</div>

NSQ
Kicklock