/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.mongopop.gremlin

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClients
import com.mongodb.mongopop.gremlin.structure.MongoGraph
import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.process.traversal.P.neq
import org.apache.tinkerpop.gremlin.structure.T
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import org.junit.Test


class SmokeTest {

    @Test
    fun `should load some data in the graph`() {
        val graph = GraphFactory.open(this::class.java.classLoader.getResource("graph.properties").path)
        cleanup(graph.configuration())
        val anton = graph.addVertex("Anton")
        val asya = graph.addVertex("Asya")
        val ross = graph.addVertex("Ross")
        val jeff = graph.addVertex("Jeff")
        val craig = graph.addVertex("Craig")


        val mongopop = graph.addVertex(T.label, "Mongopop", "description", "Gremlin Graph query language implementation", "time spent", "2 days")

        anton.addEdge("created", mongopop)
        asya.addEdge("created", mongopop)
        asya.addEdge("authored", mongopop, "motivation", "Many customers ask for MongoDB implementation for Gremlin")
        ross.addEdge("created", mongopop)
        jeff.addEdge("created", mongopop)
        craig.addEdge("consulted", mongopop)

        val edges = graph.edges()
        println("Edges:")
        while (edges.hasNext()) {
            val edge = edges.next()
            println("id: ${edge.id()} (${edge.label()}), from ${edge.outVertex().label()}, to ${edge.inVertex().label()}")
            for (e in edge.properties<Object>()) {
                println("\t${e.key()} -> ${e.value()}")
            }
        }

        println("Who are other guys except Anton who created the Mongopop?")
        val g = graph.traversal()
        g.V().hasLabel("Anton").out("created").label().forEach { println(it) }
//        g.V().hasLabel("Anton").out("created").`in`("created").where(neq("exclude")).values<String>("name")

        /*g.V().hasLabel("Anton").forEach {
            println("id: ${it.id()} (${it.label()})")
            for (e in it.properties<Object>()) {
                println("\t${e.key()} -> ${e.value()}")
            }
        }*/


    }

    fun cleanup(configuration: Configuration) {
        val url = ConnectionString(configuration.getString("${MongoGraph.MONGODB_CONFIG_PREFIX}.connectionUrl"))
        val client = MongoClients.create(url)
        client.getDatabase(url.database!!).drop()
        client.close()
    }
}
