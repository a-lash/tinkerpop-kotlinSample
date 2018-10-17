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

import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import org.junit.Test

class SmokeTest {

    @Test
    fun `should load some data in the graph`() {
        val graph = GraphFactory.open(this::class.java.classLoader.getResource("graph.properties").path)
        val v1 = graph.addVertex("h")
        val v2 = graph.addVertex("g")

        v1.addEdge("edge1", v2, "1", "one", "2", "two")

        val edges = graph.edges()
        while (edges.hasNext()) {
            val edge = edges.next()
            println(edge.id().toString() + " " + edge.label())
        }


        val vertices = graph.vertices(v1.id(), v2.id())
        while (vertices.hasNext()) {
            val v = vertices.next()
            //      System.out.println(v.id() + " " + v.label());
        }

    }
}
