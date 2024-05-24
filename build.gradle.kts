plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
   
}

group = "id.ac.ui.cs.advprog.snackventure"
version = "0.0.1-SNAPSHOT"


java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
   useJUnitPlatform()
   finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
   
}
tasks.jacocoTestReport {
   classDirectories.setFrom(files(classDirectories.files.map {
       fileTree(it) { exclude("**/*Application**") }
   }))
   dependsOn(tasks.test) // tests are required to run before generating the report
   reports {
       xml.required.set(false)
       csv.required.set(false)
       html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
   }
}
