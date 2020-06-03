package pacApp.pacService;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import pacApp.pacData.OrtRepositoryM;
import pacApp.pacKafka.MqRequest;
import pacApp.pacModel.Ort;

@Service
public class KafkaConsumer {
	
	private final OrtRepositoryM repository;
	
	public KafkaConsumer(OrtRepositoryM repository) {
		this.repository = repository;
	}

	@KafkaListener(topics = "topic1", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consume(MqRequest mqRequest) {
		this.repository.save(new Ort());
		System.out.println(mqRequest.getMqMessage());
	}
}
