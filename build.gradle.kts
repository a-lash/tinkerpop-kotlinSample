plugins {
    application
    kotlin("jvm") version "1.2.71"
}

application {
    mainClassName = "samples.HelloWorldKt"
}

dependencies {
    api(kotlin("stdlib"))

    api("org.apache.tinkerpop:tinkergraph-gremlin:3.3.3")
}

repositories {
    jcenter()
    mavenCentral()
}
