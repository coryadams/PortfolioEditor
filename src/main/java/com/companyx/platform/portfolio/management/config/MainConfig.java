package com.companyx.platform.portfolio.management.config;

import com.companyx.platform.portfolio.management.domain.MessageContainer;
import com.companyx.platform.portfolio.management.util.MessageContainerKryoSerializer;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MainConfig {


    @Bean
    HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.getSerializationConfig().getSerializerConfigs().add(
                new SerializerConfig().
                        setTypeClass(MessageContainer.class).
                        setImplementation(new MessageContainerKryoSerializer()));

        clientConfig.setClusterName("dev");
        clientConfig.getNetworkConfig().addAddress("127.0.0.1");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        return client;
    }

//    @Bean
//    public OpenTelemetry openTelemetry() {
//        Resource resource = Resource.getDefault().toBuilder().put(ResourceAttributes.SERVICE_NAME, "dice-server").put(ResourceAttributes.SERVICE_VERSION, "0.1.0").build();
//
//        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
//                .addSpanProcessor(SimpleSpanProcessor.create(LoggingSpanExporter.create()))
//                .setResource(resource)
//                .build();
//
//        SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
//                .registerMetricReader(PeriodicMetricReader.builder(LoggingMetricExporter.create()).build())
//                .setResource(resource)
//                .build();
//
//        SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder()
//                .addLogRecordProcessor(BatchLogRecordProcessor.builder(SystemOutLogRecordExporter.create()).build())
//                .setResource(resource)
//                .build();
//
//        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
//                .setTracerProvider(sdkTracerProvider)
//                .setMeterProvider(sdkMeterProvider)
//                .setLoggerProvider(sdkLoggerProvider)
//               .setPropagators(ContextPropagators.create(TextMapPropagator.composite(W3CTraceContextPropagator.getInstance(), W3CBaggagePropagator.getInstance())))
//                .buildAndRegisterGlobal();
//
//        return openTelemetry;
//    }

}
