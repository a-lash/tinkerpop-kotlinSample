package com.mongodb.mongopop.gremlin;

import org.apache.tinkerpop.gremlin.jsr223.AbstractGremlinPlugin;
import org.apache.tinkerpop.gremlin.jsr223.DefaultImportCustomizer;
import org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer;
import com.mongodb.mongopop.gremlin.structure.*;

/**
 * @author
 */
@SuppressWarnings("ALL")
public final class MongoPlugin extends AbstractGremlinPlugin {

    private static final String NAME = "tinkerpop.mongo";

    private static final ImportCustomizer imports;

    static {
        try {
            imports = DefaultImportCustomizer.build()
                    .addClassImports(MongoEdge.class,
                            MongoElement.class,
                            MongoGraph.class,
                            MongoProperty.class,
                            MongoVertex.class).create();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static final MongoPlugin instance = new MongoPlugin();

    public MongoPlugin() {
        super(NAME, imports);
    }

    public static MongoPlugin instance() {
        return instance;
    }

    @Override
    public boolean requireRestart() {
        return true;
    }
}
