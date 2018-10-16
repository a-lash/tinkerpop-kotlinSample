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
import org.apache.tinkerpop.gremlin.structure.Property
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.bson.Document

class MongoEdge(document: Document, graph: MongoGraph) : MongoElement(document, graph), Edge {
    override val collection: MongoCollection<Document>
        get() = graph.edges

    override fun <V : Any?> properties(vararg propertyKeys: String?): MutableIterator<Property<V>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun vertices(direction: Direction?): MutableIterator<Vertex> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <V : Any?> property(key: String?, value: V): Property<V> {
        // TODO: REALLY!!!???
        document = collection.findOneAndUpdate(Filters.eq(document.get("_id")),
                Updates.set(key!!, value),
                FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER))!!
        return MongoProperty<V>() // TODO
    }
}
