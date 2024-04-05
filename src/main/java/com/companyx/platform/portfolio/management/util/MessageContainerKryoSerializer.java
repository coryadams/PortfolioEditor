package com.companyx.platform.portfolio.management.util;

import com.companyx.platform.portfolio.management.domain.MessageContainer;
import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import io.opentelemetry.context.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MessageContainerKryoSerializer
        implements StreamSerializer<MessageContainer> {

    private static final ThreadLocal<Kryo> kryoThreadLocal
            = new ThreadLocal() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(MessageContainer.class);
           // kryo.register(io.opentelemetry.javaagent.instrumentation.opentelemetryapi.context.AgentContextWrapper.class);
            return kryo;
        }
    };

    public MessageContainerKryoSerializer() {
    }

    public int getTypeId() {
        return 2;
    }

    public void write(ObjectDataOutput objectDataOutput,
                      MessageContainer messageContainer)
            throws IOException {
        Kryo kryo = kryoThreadLocal.get();
        Output output = new Output((OutputStream) objectDataOutput);
        kryo.writeObject(output, messageContainer);
        output.flush();
    }

    public MessageContainer read(ObjectDataInput objectDataInput)
            throws IOException {
        InputStream in = (InputStream) objectDataInput;
        Input input = new Input(in);
        Kryo kryo = kryoThreadLocal.get();
        return kryo.readObjectOrNull(input, MessageContainer.class);
    }

    public void destroy() {
    }
}