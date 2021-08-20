### MariaDB

    Hostname: i5a508.p.ssafy.io
    port: 3306
    Username: root
    Password: root
    version: 10.3.31-MariaDB-0ubuntu0.20.04.1

- Auth-Server

```
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.data.web.pageable.one-indexed-parameters=true
spring.datasource.url=jdbc:mariadb://i5a508.p.ssafy.io:3306/bojang_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.hikari.username=root
spring.datasource.hikari.password=root
spring.datasource.platform=postgresql
```

- Item-Server

```
 spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.data.web.pageable.one-indexed-parameters=true
spring.datasource.url=jdbc:mariadb://i5a508.p.ssafy.io:3306/bojang_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.hikari.username=root
spring.datasource.hikari.password=root
spring.datasource.platform=postgresql
```

- Billing-Server

```
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.data.web.pageable.one-indexed-parameters=true
spring.datasource.url=jdbc:mariadb://i5a508.p.ssafy.io:3306/bojang_billing_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.hikari.username=root
spring.datasource.hikari.password=root
spring.datasource.platform=postgresql
```

- Store-Server

```
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.data.web.pageable.one-indexed-parameters=true
spring.datasource.url=jdbc:mariadb://i5a508.p.ssafy.io:3306/bojang_billing_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.hikari.username=root
spring.datasource.hikari.password=root
spring.datasource.platform=postgresql
```
