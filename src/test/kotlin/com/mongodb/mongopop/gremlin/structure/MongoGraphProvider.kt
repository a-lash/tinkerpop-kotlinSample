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
import com.mongodb.mongopop.gremlin.structure.MongoGraph.Companion.MONGODB_CONFIG_PREFIX
import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.AbstractGraphProvider
import org.apache.tinkerpop.gremlin.LoadGraphWith
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraphVariables
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerProperty
import java.util.*

class MongoGraphProvider : AbstractGraphProvider() {

    override fun clear(graph: Graph?, configuration: Configuration?) {
        val url = ConnectionString(configuration!!.getString("$MONGODB_CONFIG_PREFIX.connectionUrl"))
        val client = MongoClients.create(url)
        client.getDatabase(url.database!!).drop()
        client.close()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getImplementations(): MutableSet<Class<Any>> {
        return mutableSetOf(
                MongoEdge::class,
                MongoVertex::class,
                MongoElement::class,
                TinkerGraphVariables::class,
                TinkerProperty::class,
                MongoVertexProperty::class,
                MongoGraph::class
        ) as MutableSet<Class<Any>>
    }

    override fun getBaseConfiguration(graphName: String?, test: Class<*>?, testMethodName: String?, loadGraphWith: LoadGraphWith.GraphData?): MutableMap<String, Any> {
        val resource = this::class.java.classLoader.getResource("graph.properties")
        val stream = resource.openStream()
        val props = Properties()
        props.load(stream)

        val configuration = mutableMapOf<String, Any>(Pair(Graph.GRAPH, MongoGraph::class.java.toString()))
        props.keys.filter { (it as String).startsWith(MONGODB_CONFIG_PREFIX) }.forEach{
            configuration.put(it as String, props.getProperty(it))
        }
        return configuration
   }

}
