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
