package com.companyx.platform.portfolio.management;


import com.companyx.platform.portfolio.management.domain.MessageContainer;
import com.companyx.platform.portfolio.management.util.MessageContainerKryoSerializer;
import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHazelcast {

    private static Logger log = LoggerFactory.getLogger(ServerHazelcast.class);

    public static void main(String[] args) {
        Config config = new Config();
        config.setClusterName("dev");
        config.getSerializationConfig().getSerializerConfigs().add(
                new SerializerConfig().
                        setTypeClass(MessageContainer.class).
                        setImplementation(new MessageContainerKryoSerializer()));

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
        IMap<String, MessageContainer> map = hz.getMap("map");

        log.info("Entry Listener registered");
    }

    static class MyEntryListener
            implements EntryAddedListener<String, MessageContainer>,
            EntryUpdatedListener<String, MessageContainer>,
            EntryRemovedListener<String, MessageContainer> {
        @Override
        public void entryAdded(EntryEvent<String, MessageContainer> event) {
            System.out.println("Entry Added:" + event);
            MessageContainer messageContainer = event.getValue();
            if (messageContainer.getContext() !=null) {
                messageContainer.getContext().makeCurrent();
                Span.fromContext(Context.current());
                String spanContent = Span.current().toString();
                log.info("Remote Span toString ", spanContent);
            }

        }

        @Override
        public void entryRemoved(EntryEvent<String, MessageContainer> event) {
            System.out.println("Entry Removed:" + event);
        }

        @Override
        public void entryUpdated(EntryEvent<String, MessageContainer> event) {
            System.out.println("Entry Updated:" + event);
        }
    }
}
