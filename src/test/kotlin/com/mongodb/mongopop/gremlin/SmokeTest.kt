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
import org.apache.tinkerpop.gremlin.structure.T
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import org.junit.Test
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource



class SmokeTest {

    @Test
    fun `should load some data in the graph`() {
        val graph = GraphFactory.open(this::class.java.classLoader.getResource("graph.properties").path)
        cleanup(graph.configuration())
        val v1 = graph.addVertex("a")
        val v2 = graph.addVertex(T.label, "b", "prop1", "1")
        val v3 = graph.addVertex(T.label, "c", "prop1", "2")

        v1.addEdge("edge1", v2, "1", "one", "2", "two")

        v2.addEdge("edge2", v3)

        val edges = graph.edges()
        println("Edges:")
        while (edges.hasNext()) {
            val edge = edges.next()
            println("id: ${edge.id()} (${edge.label()}), from ${edge.outVertex().label()}, to ${edge.inVertex().label()}")
            for (e in edge.properties<Object>()) {
                println("\t${e.key()} -> ${e.value()}")
            }
        }

        println("Vertices:")
        val g = graph.traversal()
        g.V().hasLabel("a", "b").forEach {
            println("id: ${it.id()} (${it.label()})")
            for (e in it.properties<Object>()) {
                println("\t${e.key()} -> ${e.value()}")
            }
        }


        val vertices = graph.vertices(v1.id(), v2.id())
        while (vertices.hasNext()) {
            val v = vertices.next()
            //      System.out.println(v.id() + " " + v.label());
        }
    }

    fun cleanup(configuration: Configuration) {
        val url = ConnectionString(configuration.getString("${MongoGraph.MONGODB_CONFIG_PREFIX}.connectionUrl"))
        val client = MongoClients.create(url)
        val db = client.getDatabase(url.database!!)
        for (collection in db.listCollectionNames()) {
            db.getCollection(collection).drop()
        }
    }
}
