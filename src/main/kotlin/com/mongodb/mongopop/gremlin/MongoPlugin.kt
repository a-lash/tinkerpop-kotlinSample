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

package com.mongodb.mongopop.gremlin

import com.mongodb.mongopop.gremlin.structure.*
import org.apache.tinkerpop.gremlin.jsr223.AbstractGremlinPlugin
import org.apache.tinkerpop.gremlin.jsr223.DefaultImportCustomizer
import org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer


class MongoPlugin : AbstractGremlinPlugin(NAME, imports) {

    override fun requireRestart(): Boolean {
        return true
    }

    companion object {

        private val NAME = "tinkerpop.mongo"

        private val imports: ImportCustomizer

        init {
            try {
                imports = DefaultImportCustomizer.build()
                        .addClassImports(MongoEdge::class.java,
                                MongoElement::class.java,
                                MongoGraph::class.java,
                                MongoProperty::class.java,
                                MongoVertex::class.java).create()
            } catch (ex: Exception) {
                throw RuntimeException(ex)
            }

        }

        private val instance = MongoPlugin()

        fun instance(): MongoPlugin {
            return instance
        }
    }
}
