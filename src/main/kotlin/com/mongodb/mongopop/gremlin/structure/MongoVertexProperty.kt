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

import org.apache.tinkerpop.gremlin.structure.Property
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.structure.VertexProperty

class MongoVertexProperty<T>(private val vertex: MongoVertex, private val key: String, private val value: T) : VertexProperty<T> {
    override fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun value(): T {
        return value
    }

    override fun isPresent(): Boolean {
        return true
    }

    override fun key(): String {
        return key
    }

    override fun element(): Vertex {
        return vertex
    }

    override fun id(): Any {
        return ""
    }

    override fun <V : Any?> property(key: String?, value: V): Property<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <U : Any?> properties(vararg propertyKeys: String?): MutableIterator<Property<U>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
