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
import com.mongodb.client.MongoClients
import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.AbstractGraphProvider
import org.apache.tinkerpop.gremlin.LoadGraphWith
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraphVariables

class MongoGraphProvider : AbstractGraphProvider() {
    override fun clear(graph: Graph?, configuration: Configuration?) {
        val url = ConnectionString(configuration!!.getString("connectionUrl"))
        val client = MongoClients.create(url)
        val db = client.getDatabase(url.database!!)
        for (collection in db.listCollectionNames()) {
            db.getCollection(collection).drop()
        }
    }

    override fun getImplementations(): MutableSet<Class<Any>> {
        return mutableSetOf(
                MongoEdge::class.java as Class<Any>,
                MongoVertex::class.java as Class<Any>,
                MongoElement::class.java as Class<Any>,
                TinkerGraphVariables::class.java as Class<Any>,
                MongoProperty::class.java as Class<Any>,
                MongoVertexProperty::class.java as Class<Any>,
                MongoGraph::class.java as Class<Any>
        )
    }

    override fun getBaseConfiguration(graphName: String?, test: Class<*>?, testMethodName: String?, loadGraphWith: LoadGraphWith.GraphData?): MutableMap<String, Any> {
        return mutableMapOf(
                Pair(Graph.GRAPH, MongoGraph::class.java.toString()),
                Pair("connectionUrl", "mongodb+srv://tpop:TinkerPop3@cluster0-hakmv.mongodb.net/graph")
        )
    }

}
