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
import org.apache.tinkerpop.gremlin.AbstractGraphProvider
import org.apache.tinkerpop.gremlin.LoadGraphWith
import org.apache.tinkerpop.gremlin.structure.Graph

class MongoGraphProvider : AbstractGraphProvider() {
    override fun clear(graph: Graph?, configuration: Configuration?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getImplementations(): MutableSet<Class<Any>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseConfiguration(graphName: String?, test: Class<*>?, testMethodName: String?, loadGraphWith: LoadGraphWith.GraphData?): MutableMap<String, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
