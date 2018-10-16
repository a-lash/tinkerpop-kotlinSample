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

package com.mongodb.mongopop.gremlin.structure

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.T
import org.apache.tinkerpop.gremlin.structure.Transaction
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.structure.util.GraphFactoryClass
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraphVariables
import org.bson.Document

@Graph.OptIn(Graph.OptIn.SUITE_STRUCTURE_STANDARD)
@GraphFactoryClass(MongoGraph.MongoGraphFactory::class)
class MongoGraph(val conf: Configuration) : Graph {
    private val client: MongoClient
    private val vertices: MongoCollection<Document>
    private val edges: MongoCollection<Document>
    private var db: MongoDatabase
    private val variables: TinkerGraphVariables

    init {
        val url = ConnectionString(conf.getString("connectionUrl"))
        client = MongoClients.create(url)
        db = client.getDatabase(url.database!!)
        vertices = db.getCollection("vertices")
        edges = db.getCollection("edges")
        variables = TinkerGraphVariables()
    }

    override fun <C : GraphComputer?> compute(graphComputerClass: Class<C>?): C {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compute(): GraphComputer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edges(vararg edgeIds: Any?): MutableIterator<Edge> {
        return edges.find(Filters.`in`("_id", edgeIds)).
                map {
                    MongoEdge(it, this)
                }.iterator()
    }

    override fun addVertex(vararg keyValues: Any?): Vertex {
        val d = Document()
        keyValues.asList().chunked(2).forEach {
            val key = it[0] as String
            d.append(if (key == T.id.accessor) "_id" else key, it[1])
        }
        vertices.insertOne(d)

        return MongoVertex(d, this)
    }

    override fun configuration(): Configuration {
        return conf
    }

    override fun variables(): Graph.Variables {
        return variables
    }

    override fun vertices(vararg vertexIds: Any?): MutableIterator<Vertex> {
        return vertices.find(Filters.`in`("_id", vertexIds)).
                map {
                   MongoVertex(it, this)
                }.iterator()
    }

    override fun close() {
        client.close()
    }

    override fun tx(): Transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    object MongoGraphFactory {
        fun open(conf: Configuration): Graph {
            return MongoGraph(conf)
        }
    }

}
