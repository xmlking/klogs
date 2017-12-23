package org.sumo.klogs

import java.time.Instant
import java.time.ZoneId

import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.TimeWindows

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.binder.kstream.annotations.KStreamProcessor
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerde
import org.springframework.messaging.handler.annotation.SendTo
import java.awt.SystemColor.window
import jdk.nashorn.tools.ShellFunctions.input
import org.apache.kafka.streams.kstream.Materialized
import org.springframework.beans.factory.annotation.Autowired






@SpringBootApplication
@EnableBinding(KStreamProcessor::class)
class KlogsApplication

fun main(args: Array<String>) {
    runApplication<KlogsApplication>(*args)
}
//https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kstream/kstream-product-tracker
@Configuration
class KafkaProcessorConfiguration {

    private val windowLength = 5000L
    private val advanceBy = 0L
    private val storeName = "WordCounts"

    @StreamListener("input")
    @SendTo("output")
    fun process(input: KStream<Any, Product>): KStream<Int, ProductStatus> {

        return input
                .filter { key, product -> productIds().contains(product.id) }
                .map { key, value -> KeyValue(value, value) }
                .groupByKey(JsonSerde(Product::class.java), JsonSerde(Product::class.java))
                .windowedBy(configuredTimeWindow())
                .count(Materialized.`as`("product-counts"))

                .toStream()
                .map { key, value ->
                    KeyValue<Int, ProductStatus>(key.key().id, ProductStatus(key.key().id,
                            value, Instant.ofEpochMilli(key.window().start()).atZone(ZoneId.systemDefault()).toLocalTime(),
                            Instant.ofEpochMilli(key.window().end()).atZone(ZoneId.systemDefault()).toLocalTime()))
                }
    }

    private fun configuredTimeWindow(): TimeWindows {
        return if (advanceBy > 0)
            TimeWindows.of(windowLength).advanceBy(advanceBy)
        else
            TimeWindows.of(windowLength)
    }

    private fun productIds(): Set<Int> {
        return setOf(123,124,125)
    }
}






