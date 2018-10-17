plugins {
    kotlin("jvm") version "1.2.71"
    java
    maven
}

group = "com.mongodb.mongopop"
version = "1.0.0"

apply(plugin="maven")

val kotlinVersion: String by project
val tinkerpopVersion: String by project
val kmongoVersion: String by project
val junitVersion: String by project

dependencies {
    api(kotlin("stdlib"))
    api("org.litote.kmongo:kmongo:$kmongoVersion")
    api("org.apache.tinkerpop:tinkergraph-gremlin:$tinkerpopVersion")

    testApi("org.apache.tinkerpop:gremlin-test:$tinkerpopVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("junit:junit:$junitVersion")
}

repositories {
    jcenter()
    mavenCentral()
}

