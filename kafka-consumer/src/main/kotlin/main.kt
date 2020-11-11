import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(){
    println("Instantiating producer")
    val props = Properties()

    props["bootstrap.servers"] = "localhost:9092"
    props["acks"] = "all"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["group.id"] = "my-group"

    val consumer: KafkaConsumer<String, String> = KafkaConsumer(props)

    val topics: HashSet<String> = HashSet()
    topics.add("my-topic")

    consumer.subscribe(topics)

    try {
        while(true) {
            val records: ConsumerRecords<String, String> = consumer.poll(10)
            for (record in records) {
                println(record.key() + ": " + record.value())
            }
        }
    } catch (e: Exception){
        println(e.message)
    } finally {
        consumer.close()
    }

}