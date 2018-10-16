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

import com.mongodb.client.MongoDatabase
import org.apache.tinkerpop.gremlin.structure.*

class MongoVertex(id: Any, label: String, val db: MongoDatabase) : MongoElement(id, label), Vertex {
    override fun edges(direction: Direction?, vararg edgeLabels: String?): MutableIterator<Edge> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEdge(label: String?, inVertex: Vertex?, vararg keyValues: Any?): Edge {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun graph(): Graph {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <V : Any?> property(cardinality: VertexProperty.Cardinality?, key: String?, value: V, vararg keyValues: Any?): VertexProperty<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <V : Any?> properties(vararg propertyKeys: String?): MutableIterator<VertexProperty<V>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun vertices(direction: Direction?, vararg edgeLabels: String?): MutableIterator<Vertex> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
