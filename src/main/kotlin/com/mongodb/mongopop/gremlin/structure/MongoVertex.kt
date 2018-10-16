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

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import com.mongodb.client.model.Updates
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.T
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.structure.VertexProperty
import org.bson.Document

class MongoVertex(document: Document, graph: MongoGraph) : MongoElement(document, graph), Vertex {

    override val collection: MongoCollection<Document>
        get() = graph.vertices

    override fun edges(direction: Direction?, vararg edgeLabels: String?): MutableIterator<Edge> {
        // TODO what is direction for? Labels?
        return graph.db.getCollection("edges")
                .find(Filters.`in`(T.label.accessor, edgeLabels))
                .map { MongoEdge(it, graph) }
                .iterator()
    }

    override fun <V : Any?> property(key: String?, value: V): VertexProperty<V> {
        // TODO: REALLY!!!???
        document = collection.findOneAndUpdate(Filters.eq(document.get("_id")),
                Updates.set(key!!, value),
                FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER))!!
        return MongoVertexProperty<V>()
    }

    override fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEdge(label: String?, inVertex: Vertex?, vararg keyValues: Any?): Edge {
        val d = Document()
        keyValues.asList().chunked(2).forEach {
            val key = it[0] as String
            d.append(if (key == T.id.accessor) "_id" else key, it[1])
        }
        d.set(T.label.accessor, label)
        d.set("inVertex", inVertex!!.id())
        d.set("outVertex", this.id())
        graph.db.getCollection("edges").insertOne(d)
        return MongoEdge(d, graph)
    }

    override fun <V : Any?> property(cardinality: VertexProperty.Cardinality?, key: String?, value: V, vararg keyValues: Any?): VertexProperty<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <V : Any?> properties(vararg propertyKeys: String?): MutableIterator<VertexProperty<V>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun vertices(direction: Direction?, vararg edgeLabels: String?): MutableIterator<Vertex> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
