package pacApp.pacService;


import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pacApp.pacData.HausRepositoryM;
import pacApp.pacData.OrtRepositoryM;
import pacApp.pacData.PersonRepositoryM;
import pacApp.pacKafka.MqRequest;
import pacApp.pacKafka.SqlActionEnum;
import pacApp.pacModel.Haus;
import pacApp.pacModel.Ort;
import pacApp.pacModel.Person;

@Service
public class KafkaConsumer {
	
	private final OrtRepositoryM ortRepositoryM;
	private final PersonRepositoryM personRepositoryM;  
	private final HausRepositoryM hausRepositoryM; 
	
	public KafkaConsumer(OrtRepositoryM ortRepositoryM, PersonRepositoryM personRepositoryM,  HausRepositoryM hausRepositoryM) {
		this.ortRepositoryM = ortRepositoryM;
		this.personRepositoryM = personRepositoryM;
		this.hausRepositoryM = hausRepositoryM;
	}

	@KafkaListener(topics = "topic1", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consume(MqRequest mqRequest) {	
		handleMqRequest(mqRequest);
		System.out.println(mqRequest.getSqlAction());
	}
	
	private boolean handleMqRequest(MqRequest mqRequest) {
		Object obj = getMqMessageSqlObject(mqRequest);
		if (obj instanceof Person) {
			handlePersonRequest((Person)obj, mqRequest.getSqlAction());	
		}else if (obj instanceof Ort) {
			handleOrtRequest((Ort)obj, mqRequest.getSqlAction());		
		}else if (obj instanceof Haus) {
			handleHausRequest((Haus)obj, mqRequest.getSqlAction());	
		}
		return false;
	}
	private Object getMqMessageSqlObject(MqRequest mqRequest) {
		ObjectMapper mapper = new ObjectMapper();
		if (mqRequest.getClassName().equals(Person.class.getName()))
			return mapper.convertValue(mqRequest.getObj(), Person.class);
		else if (mqRequest.getClassName().equals(Ort.class.getName()))
			return mapper.convertValue(mqRequest.getObj(), Ort.class);
		else if (mqRequest.getClassName().equals(Haus.class.getName()))
			return mapper.convertValue(mqRequest.getObj(), Haus.class);
		return null;
	}
	
	private boolean handlePersonRequest(Person person, String sqlAction) {
		if (sqlAction.equals(SqlActionEnum.SQL_ACTION_ADD)) {
			personRepositoryM.save(person);
		} else if (sqlAction.equals(SqlActionEnum.SQL_ACTION_UPDATE)) {
			personRepositoryM.save(person);
		} else if (sqlAction.equals(SqlActionEnum.SQL_ACTION_DELETE)) {
			personRepositoryM.delete(person);
		}
		return false;
	}
	
	private boolean handleOrtRequest(Ort ort, String sqlAction) {
		if (sqlAction.equals(SqlActionEnum.SQL_ACTION_ADD)) {
			ortRepositoryM.save(ort);
		} else if (sqlAction.equals(SqlActionEnum.SQL_ACTION_UPDATE)) {
			ortRepositoryM.save(ort);
		} else if (sqlAction.equals(SqlActionEnum.SQL_ACTION_DELETE)) {	
			ortRepositoryM.deleteByOrtid(ort.getId());
		}
		return false;
	}	

	private boolean handleHausRequest(Haus haus, String sqlAction) {
		if (sqlAction.equals(SqlActionEnum.SQL_ACTION_ADD)) {
			hausRepositoryM.save(haus);
		} else if (sqlAction.equals(SqlActionEnum.SQL_ACTION_UPDATE)) {
			hausRepositoryM.save(haus);
		} else if (sqlAction.equals(SqlActionEnum.SQL_ACTION_DELETE)) {
			hausRepositoryM.delete(haus);
		}
		return false;
	}		
}
