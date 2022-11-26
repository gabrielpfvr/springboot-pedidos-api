package br.com.gabrielmotta.modules.pedido.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String queueName, Object message) {
		this.rabbitTemplate.convertAndSend(queueName, message);
	}
	
//	@RabbitListener(queues = "Pedidos")
//	public void receiveMessage(Pedido pedido) {
//		System.out.println(pedido);
//	}

}


