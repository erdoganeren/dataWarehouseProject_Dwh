package pacApp;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

import pacApp.pacKafka.MqRequest;

@SpringBootApplication
public class DwhMachine {

	public static void main(String[] args) {
		SpringApplication.run(DwhMachine.class, args);

	}
	/*
	 * Boot will autowire this into the container factory.
	 */
	@Bean
	public SeekToCurrentErrorHandler errorHandler(KafkaTemplate<Object, Object> template) {
		return new SeekToCurrentErrorHandler(
				new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
	}
	@Bean
	public RecordMessageConverter converter() {
		return new StringJsonMessageConverter();
	}
	@Bean
	public NewTopic topic() {
		return new NewTopic("topic1", 1, (short) 1);
	}	
//	@KafkaListener(id = "fooGroup", topics = "topic1")
//	public void listen(String foo) {
//		System.out.println("Received: " + foo);
//		//this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
//	}
	@KafkaListener(id = "fooGroup", topics = "topic1")
	public void mqListen(MqRequest mqRequest) {
		System.out.println("Received: " + mqRequest.getMqMessage());
		//this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
	}
}
