# spring-mvc-common-processing-demo
Demo application for spring mvc common processing

[![Java CI with Maven](https://github.com/kazuki43zoo/spring-mvc-common-processing-demo/actions/workflows/maven.yml/badge.svg)](https://github.com/kazuki43zoo/spring-mvc-common-processing-demo/actions/workflows/maven.yml)

## pure-spring-demo

Demo for traditional web application(without spring-boot).

```
$ ./mvnw -pl pure-spring-demo clean verify
```

Run with Tomcat 9.0.x using cargo maven plugin.

```
$ ./mvnw -pl pure-spring-demo clean package cargo:run
```


## spring-boot-demo

Demo for spring boot based web application.

```
$ ./mvnw -pl spring-boot-demo clean verify
```
