## Brief Introduction

## Overview

<img src="/_media/logo.png" style="zoom: 80%;" /> <img src="/_media/itcast.png" style="zoom: 80%;" />


Eaglemap is a powerful, simple and easy-to-use middle platform service system that supports multiple map service providers. Its design goal is to simplify the use of map services in business systems. At present, it has connected with two mainstream map service providers in China: Baidu map and Gaode map. In the future, it is considered to connect Tencent map.

## Characteristic

- Eaglemap has a highly scalable design, which can easily expand and connect with three-party map service providers.
- After using eaglemap, the business system can quickly integrate map services.
- Unify the service interfaces of multiple map manufacturers to reduce the integration cost.
- For the same service, eaglemap can intelligently select service providers to provide services.
- For map service provider interface call, retry mechanism is used in case of exception.
- Localized storage is made for trajectory data to reduce the interface requests of service providers.
- Provide multi terminal integration solutions: Java SDK, spring boot starter and JS SDK.
- Eaglemap provides two operation modes, base and complete, which can be selected by the business system according to the needs.
- Eaglemap provides a visual management system for management.

## Version Release History

?> 1.0-SNAPSHOT  -> Published in 2022.3.26

## Download

### Source Address

| Project                      | Source code warehouse address                                |
| ---------------------------- | ------------------------------------------------------------ |
| EagleMap                     |                                                              |
| EagleMap-sdk                 | https://gitee.com/itcastopen/EagleMap-sdk.git<br/>https://github.com/itcastopen/EagleMap-sdk.git |
| eaglemap-spring-boot-starter | https://gitee.com/itcastopen/eaglemap-spring-boot-starter.git<br />https://github.com/itcastopen/eaglemap-spring-boot-starter.git |

### Maven Dependency

~~~xml
<!--java sdk-->
<dependency>
    <groupId>com.itheima.em</groupId>
    <artifactId>EagleMap-sdk</artifactId>
    <version>{version}</version>
</dependency>

<!--springboot starter-->
<dependency>
    <groupId>com.itheima.em</groupId>
    <artifactId>eaglemap-spring-boot-starter</artifactId>
    <version>{version}</version>
</dependency>

<!-- If it is a SNAPSHOT version, for example, to be in the POM Introducing snapshot source into XML file -->
<repositories>
    <repository>
        <id>sonatypeSnapshots</id>
        <name>Sonatype Snapshots</name>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
</repositories>
~~~

## Architecture Design

### Design Idea

The reason why such a middle platform service is designed is that there will be many scenarios using map services in enterprise projects. Whether using Baidu map or Gaode map, you need to write various HTTP requests, data analysis and other codes. In this way, the same code will appear in multiple systems, and once the map provider is switched, the code will have to be rewritten in order to make the business system simpler Fast integration of map services, so it is necessary to use a Chinese Taiwanese map service.

### System Composition

Eaglemap consists of two parts: server side and SDK side.

**server side：**

The server side is responsible for the connection with the map service provider and mainly realizes two functions: basic map service and track service. The track service will store track points and other data in MySQL. Externally, it provides interface services through restful, and provides a visual management system.

**sdk side：**

The SDK side communicates with the server side through HTTP protocol. In order to facilitate integration, the SDK is divided into two types: Java SDK and spring boot starter.

### Architecture Diagram

<img src="/_media/framework.png" style="zoom: 80%;" />