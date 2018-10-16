plugins {
    application
    kotlin("jvm") version "1.2.71"
}

application {
    mainClassName = "samples.HelloWorldKt"
}

dependencies {
    api(kotlin("stdlib"))

    api("org.mongodb:mongodb-driver-sync:3.8.2")
    api("org.apache.tinkerpop:tinkergraph-gremlin:3.3.3")
}

repositories {
    jcenter()
    mavenCentral()
}
