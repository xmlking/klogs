import com.palantir.gradle.docker.DockerExtension
import org.gradle.jvm.tasks.Jar

val springCloudStreamVersion by project

apply {
    plugin("com.palantir.docker")
}

val jar: Jar by tasks
docker {
    name = "${group}/${jar.baseName}:${jar.version}"
    files(jar.outputs)
    setDockerfile(file("src/main/docker/Dockerfile"))
    buildArgs(mapOf(
            "JAR_NAME" to jar.archiveName,
            "PORT"   to  "8080",
            "JAVA_OPTS" to "-Xms64m -Xmx128m"
    ))
    pull(true)
    dependsOn(tasks.findByName("build"))
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-stream-dependencies:$springCloudStreamVersion")

    }
}

dependencies {
    compile(project(":shared"))
    compile("org.springframework.cloud:spring-cloud-stream-binder-kstream")
    compile("org.springframework.kafka:spring-kafka:2.1.0.RELEASE") //FIXME : remove
}

/**
 * Configures the [docker][DockerExtension] project extension.
 */
val Project.docker get() = extensions.getByName("docker") as DockerExtension

fun Project.docker(configure: DockerExtension.() -> Unit): Unit = extensions.configure("docker", configure)
