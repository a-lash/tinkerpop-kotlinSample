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

package com.mongodb.mongopop.gremlin.structure;

import java.util.Iterator;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;


public class Test {
  public static void main(final String[] args) {

    // assumes args[0] is a configuration file location
    Graph graph = GraphFactory.open("/Users/alisovenko/graph.properties");
    final Vertex v1 = graph.addVertex("h");
    final Vertex v2 = graph.addVertex("g");

    v1.addEdge("edge1", v2, "1", "one", "2", "two");

    final Iterator<Edge> edges = graph.edges();
    while (edges.hasNext()) {
      final Edge edge = edges.next();
      System.out.println(edge.id() + " " + edge.label());
    }


    final Iterator<Vertex> vertices = graph.vertices(v1.id(), v2.id());
    while (vertices.hasNext()) {
      final Vertex v = vertices.next();
//      System.out.println(v.id() + " " + v.label());
    }
  }
}
