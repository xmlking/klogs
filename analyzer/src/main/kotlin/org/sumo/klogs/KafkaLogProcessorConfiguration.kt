package org.sumo.klogs

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.kstream.Serialized
import org.apache.kafka.streams.kstream.TimeWindows
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.annotation.SendTo
import java.util.Arrays
import java.util.Date
import org.springframework.beans.factory.annotation.Value


@Configuration
class KafkaLogProcessorConfiguration(
    val timeWindows: TimeWindows,
    @Value(value = "\${app.kafka.store-name:for-WordCounts}") private val storeName: String) {

    //@StreamListener("input", condition = "headers['type']=='foo'")
    @StreamListener("input")
    @SendTo("output")
    fun splitStrings(input: KStream<*, String>): KStream<*, WordCount> {

        return input
                .flatMapValues { value -> Arrays.asList(*value.toLowerCase().split("\\W+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) }
                .map { key, value -> KeyValue(value, value) }
                .groupByKey(Serialized.with(Serdes.String(), Serdes.String()))
                .windowedBy(timeWindows)
                .count(Materialized.`as`(storeName))
                //.count(timeWindows, storeName)
                .toStream()
                .map { key, value -> KeyValue<Any, WordCount>(null, WordCount(key.key(), value, Date(key.window().start()), Date(key.window().end()))) }
    }

}

