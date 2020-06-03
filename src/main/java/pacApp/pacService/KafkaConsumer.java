package pacApp.pacService;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import pacApp.pacKafka.MqRequest;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "topic1", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consume(MqRequest mqRequest) {
		System.out.println(mqRequest.getMqMessage());
	}
}
