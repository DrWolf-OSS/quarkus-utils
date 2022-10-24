# DrWolf Quarkus Utils

Add repository:

```xml
<repositories>
  <repository>
    <id>drwolf maven public</id>
    <url>https://maven.drwolf.it</url>
  </repository>
</repositories>
```

Add dependency:
```xml
<dependency>
  <groupId>com.github.drwolf-oss</groupId>
  <artifactId>quarkus-utils</artifactId>
  <!-- check available tags: https://github.com/DrWolf-OSS/quarkus-utils/tags -->
  <version>${quarkus.platform.version}.3</version> 
</dependency>
```

## Exception Handling

The library provides
[CustomExceptionHandler](https://github.com/DrWolf-OSS/quarkus-utils/blob/main/src/main/java/it/drwolf/base/utils/CustomExceptionHandler.java) 
which will catch and log any `Exception`, if you throw subclasses of [WebApplicationException](https://docs.oracle.com/javaee/7/api/javax/ws/rs/package-tree.html) the handle will return the proper status code


## GIT info

Add git-info plugin to pom.xml

```xml
<plugin>
    <groupId>io.github.git-commit-id</groupId>
    <artifactId>git-commit-id-maven-plugin</artifactId>
    <version>5.0.0</version>
    <executions>
        <execution>
            <id>get-the-git-infos</id>
            <goals>
                <goal>revision</goal>
            </goals>
            <phase>initialize</phase>
        </execution>
    </executions>
    <configuration>
        <generateGitPropertiesFile>true</generateGitPropertiesFile>
        <generateGitPropertiesFilename>${project.build.outputDirectory}/git.json
        </generateGitPropertiesFilename>
        <excludeProperties>
            <excludeProperty>git.commit.user.*</excludeProperty>
            <excludeProperty>git.build.user.*</excludeProperty>
        </excludeProperties>
        <format>json</format>
        <commitIdGenerationMode>full</commitIdGenerationMode>
    </configuration>
</plugin>
```

@ Startup do

```java
GitResource.loadInfo(this.getClass().getClassLoader().getResourceAsStream("git.json"));
```

## Entities:

```java
public class YourEntity extends BaseEntity<Long> {
 ...
}
```

## Resources:

```java
public class YourResource extends CrudResource<YourRepository, YourEntity, Long> {
  ...
}}
```
