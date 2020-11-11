import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.lang.Exception
import java.util.*

fun main(){
    println("Instantiating producer")
    val props = Properties()

    props["bootstrap.servers"] = "localhost:9092"
    props["acks"] = "all"
    props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

    val myProducer: KafkaProducer<String, String> = KafkaProducer(props)
    println("Kafka producer instantiated")

    println("Sending record")
    val myRecord: ProducerRecord<String, String> = ProducerRecord("my-topic", "myKey", "myValue")

    try {
        val result = myProducer.send(myRecord)
        println("Record sent \n" + result.get())
    } catch (e: Exception){
        println(e.message)
    } finally {
        myProducer.close()
    }

}