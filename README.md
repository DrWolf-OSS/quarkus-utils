# DrWolf Quarkus Utils

Add repository:

```xml
<repositories>
  <repository>
    <id>repsy</id>
    <url>https://repsy.io/mvn/agea/default</url>
  </repository>
</repositories>
```

Add dependency:
```xml
<dependency>
  <groupId>com.github.drwolf-oss</groupId>
  <artifactId>quarkus-utils</artifactId>
  <version>${quarkus.platform.version}.4</version> <!-- check version!! -->
</dependency>
```

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
