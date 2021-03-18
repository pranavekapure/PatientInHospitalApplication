package com.patient.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.web.client.RestTemplate;

@EnableKafka
@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}
	
	@Bean
	public ConsumerFactory<String, String> getConsumerFactory(){
		
		Map<String,Object> config = new HashMap<String,Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaConsumerFactory<String,String>(config);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> getFactory(){
		ConcurrentKafkaListenerContainerFactory factory=new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(getConsumerFactory());
		return factory;
	}
}
