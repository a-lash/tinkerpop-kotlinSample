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
import org.apache.tinkerpop.gremlin.structure.*
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerProperty
import org.bson.Document
import org.litote.kmongo.findOneById

class MongoEdge(document: Document, graph: MongoGraph) : MongoElement(document, graph), Edge {

    constructor(label: String?, inVertex: Any, outVertex: Any, graph: MongoGraph, vararg keyValues: Any?) : this(Document(), graph) {
        keyValues.asList().chunked(2).forEach {
            val key = it[0].toString()
            document.append(if (key == T.id.accessor) "_id" else key, it[1])
        }
        document.set(T.label.toString(), label)
        document.set("inVertex", inVertex)
        document.set("outVertex", outVertex)

    }

    override val collection: MongoCollection<Document>
        get() = graph.edges

    override fun <V : Any?> properties(vararg propertyKeys: String?): MutableIterator<TinkerProperty<V>> {
        val document = collection.find(Filters.eq(document.get("_id"))).first()
        return document.entries.filter { it.key != "_id" && it.key != "label" && it.key != "inVertex" && it.key != "outVertex"}.map { TinkerProperty(this, it.key, it.value as V) }.toMutableList().iterator()
    }

    override fun vertices(direction: Direction?): MutableIterator<Vertex> {
        // TODO caching
        val ans = mutableListOf<Vertex>()
        if (direction == Direction.IN || direction == Direction.BOTH) {
            ans.addAll(graph.vertices(this.document["inVertex"]).asSequence())
        }
        if (direction == Direction.OUT || direction == Direction.BOTH) {
            ans.addAll(graph.vertices(this.document["outVertex"]).asSequence())
        }
        return ans.iterator()
    }

    override fun <V : Any?> property(key: String?, value: V): Property<V> {
        // TODO: REALLY!!!???
        document = collection.findOneAndUpdate(Filters.eq(document.get("_id")),
                Updates.set(key!!, value),
                FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER))!!
        return MongoProperty<V>() // TODO
    }
}
