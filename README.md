# SpringCloudStudy
SpringCloud 学习的记录

## Springboot版本
官网：https://spring.io/projects/spring-boot
在官网上 springboot已经更新到最新2.2.6



## Spingcloud版本
官网：https://spring.io/projects/spring-cloud
对于 SpringCloud 来说，版本更新是根据 字母 A-Z 来进行更新，现在已经到了最新的 H版稳定版


在首页中，可以看到相应版本对应的依赖


使用最新 H版 必须要对应的 Boot版本 为 2.2.x 
查看 springboot 和 springcloud 版本依赖：https://start.spring.io/actuator/info
在这里可以看到


最新的 Hoxton.SR4 版本最高能使用 springboot版本为 2.3.0测试版
而在 官网

看到支持的 Boot 版本为 2.2.5

### 环境搭建版本
 - SpringCloud：Hoxton.SR4
 - SpringBoot：2.2.5.RELEASE
 - SpringCloud alibaba：2.2
 - Java：8
 - Maven：3.5以上
 - Mysql：5.7以上


## Cloud组件
### 服务注册中心
#### Eureka：现在已经停更，建议使用以下几种代替（x）
#### Zookeeper：经典的服务注册中心，ZooKeeper是一个分布式的，开放源码的分布式应用程序协调服务。它是一个为分布式应用提供一致性服务的软件，提供的功能包括：配置维护、域名服务、分布式同步、组服务等。
#### Consul：Consul是一个服务网格（微服务间的 TCP/IP，负责服务之间的网络调用、限流、熔断和监控）解决方案，它是一个一个分布式的，高度可用的系统，而且开发使用都很简便。它提供了一个功能齐全的控制平面，主要特点是：服务发现、健康检查、键值存储、安全服务通信、多数据中心。
#### Nacos：Nacos 支持基于 DNS 和基于 RPC 的服务发现（可以作为springcloud的注册中心）、动态配置服务（可以做配置中心）、动态 DNS 服务。


### 服务调用
#### Ribbon：Spring Cloud Ribbon是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现。通过Spring Cloud的封装，可以让我们轻松地将面向服务的REST模版请求自动转换成客户端负载均衡的服务调用。
#### LoadBalancer：LoadBalancer 可以将来自客户端的请求分发到不同的服务器，通过将一系列的请求转发到不同的服务器可以提高服务器的性能，并可以自动地寻找最优的服务器转发请求，这样不仅提高了系统性能，同时达到了负载均衡的目的，满足了用户需求，因此 LoadBalancer 在应用场景中一般处于 web 服务器的前端，用来均衡发到 web 服务器的请求量，均衡负载，提高系统性能。
#### openFeign：常用的几种接口调用方法，Httpclient 易用 灵活，Okhttp 处理网络请求 轻量级 支持多协议，HttpURLConnection 使用复杂，RestTemplate Rest服务的客户端 提供多种便携访问HTTP服务的方法


### 服务降级
#### Resilience4j： Resilience4j是受Netflix的Hysrix项目启发，专门为Java 8 和函数式编程设计的轻量级容错框架
#### Sentinel：Sentinel支持的熔断降级维度更多，可对多种指标进行流控、熔断，且提供了实时监控和控制面板，功能更为强大，推荐使用

### 服务网关
#### GateWay：Spring Cloud Gateway是Spring官方基于Spring 5.0，Spring Boot 2.0和Project Reactor等技术开发的网关，Spring Cloud Gateway旨在为微服务架构提供一种简单而有效的统一的API路由管理方式。Spring Cloud Gateway作为Spring Cloud生态系中的网关，目标是替代ZUUL，其不仅提供统一的路由方式，并且基于Filter链的方式提供了网关基本的功能，例如：安全，监控/埋点，和限流等。

### 服务配置
#### Nacos：Nacos 支持基于 DNS 和基于 RPC 的服务发现（可以作为springcloud的注册中心）、动态配置服务（可以做配置中心）、动态 DNS 服务。

### 服务总线
#### Nacos：Nacos 支持基于 DNS 和基于 RPC 的服务发现（可以作为springcloud的注册中心）、动态配置服务（可以做配置中心）、动态 DNS 服务。
