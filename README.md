# Swagger Brake Maven plugin [![Build Status](https://travis-ci.com/redskap/swagger-brake-maven-plugin.svg?branch=master)](https://travis-ci.com/redskap/swagger-brake-maven-plugin)
Maven plugin for [Swagger Brake](https://github.com/redskap/swagger-brake).

The plugin is building on Swagger Brake's latest artifact resolution feature to determine the so 
called `old` version of the API thus it's mandatory to provide the repository URL which will be used
to download the proper artifact. If you want to skip the latest artifact resolution 
you can use a path to the latest version of your API to compare.

There are 2 properties which are mandatory for this reason:
- `mavenRepoUrl` and `mavenSnapshotUrl` or `oldApi`
- `newApi`

An example project can be found [here](https://github.com/redskap/swagger-brake-example/tree/master/swagger-brake-maven-example).

## Usage
```xml
<build>
    <plugins>
        <plugin>
            <groupId>io.redskap</groupId>
            <artifactId>swagger-brake-maven-plugin</artifactId>
            <version>1.0.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>check</goal>
                    </goals>
                    <configuration>
                        <mavenRepoUrl>http://localhost:8081/artifactory/libs-release-local</mavenRepoUrl>
                        <mavenSnapshotRepoUrl>http://localhost:8081/artifactory/libs-snapshot-local</mavenSnapshotRepoUrl>
                        <newApi>${project.build.directory}/generated-sources/swagger/swagger.json</newApi>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Plugin configuration
The following plugin properties are configurable:
- `mavenRepoUrl` - The Maven repository URL where the previous version of the artifact can be found.
- `mavenSnapshotRepoUrl` - The Maven snapshot repository URL where the previous version of the artifact can be found.
- `mavenRepoUsername` - The username for accessing the Maven repository.
- `mavenRepoPassword` - The password for accessing the Maven repository.
- `newApi` - The path of the API file with which the latest version will be compared to
- `groupId` - The groupId of the artifact. Defaults to `project.group`
- `artifactId` - The artifactId. Defaults to `project.name`
- `outputFilePath` - The output where the report will be generated. Defaults to `project.buildDir/swagger-brake`
- `outputFormat` - The format of the report. Defaults to `HTML`. Possible values can be found [here](https://github.com/redskap/swagger-brake#reporting).
- `deprecatedApiDeletionAllowed` - Whether deletion of deprecated APIs should be allowed. More info [here](https://github.com/redskap/swagger-brake#api-deprecation-handling).
- `betaApiExtensionName` - Defines which vendor extension attribute to use for denoting the beta APIs. More info [here](https://github.com/redskap/swagger-brake#beta-api-support).
- `apiFilename` - Specifies the filename of the contract within the downloaded artifact. More info [here](https://github.com/redskap/swagger-brake#latest-artifact-resolution).
- `currentVersion` - Specifies the version of the currently built artifact. Defaults to the project version. More info [here](https://github.com/redskap/swagger-brake#latest-artifact-resolution).

## License
```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```