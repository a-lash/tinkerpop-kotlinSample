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

import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.Transaction
import java.util.function.Consumer

class MongoTransaction : Transaction {
    override fun open() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rollback() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun commit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClose(consumer: Consumer<Transaction>?): Transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <G : Graph?> createThreadedTx(): G {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isOpen(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun readWrite() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReadWrite(consumer: Consumer<Transaction>?): Transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTransactionListener(listener: Consumer<Transaction.Status>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearTransactionListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeTransactionListener(listener: Consumer<Transaction.Status>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
