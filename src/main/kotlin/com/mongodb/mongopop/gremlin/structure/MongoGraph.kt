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

import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.Transaction
import org.apache.tinkerpop.gremlin.structure.Vertex

@Graph.OptIn(Graph.OptIn.SUITE_STRUCTURE_STANDARD)
class MongoGraph : Graph {
    override fun <C : GraphComputer?> compute(graphComputerClass: Class<C>?): C {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compute(): GraphComputer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edges(vararg edgeIds: Any?): MutableIterator<Edge> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addVertex(vararg keyValues: Any?): Vertex {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configuration(): Configuration {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun variables(): Graph.Variables {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun vertices(vararg vertexIds: Any?): MutableIterator<Vertex> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tx(): Transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
