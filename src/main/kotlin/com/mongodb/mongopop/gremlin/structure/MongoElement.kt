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
import org.apache.tinkerpop.gremlin.structure.Element
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.T
import org.apache.tinkerpop.gremlin.structure.util.ElementHelper
import org.bson.Document

abstract class MongoElement protected constructor(protected var document: Document, protected val graph: MongoGraph) : Element {

    abstract val collection : MongoCollection<Document>

    fun save() {
        collection.insertOne(document)
    }

    override fun graph(): Graph {
        return graph
    }

    override fun id(): Any {
        return this.document.get("_id")!!
    }

    override fun label(): String? {
        return document.getString("label")
    }

    override fun remove() {
        collection.deleteOne(Filters.eq(document.get("_id")))
    }

    override fun equals(other: Any?): Boolean {
        return ElementHelper.areEqual(this, other)
    }

    override fun hashCode(): Int {
        return ElementHelper.hashCode(this)
    }
}
