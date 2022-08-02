package br.com.gabrielmotta.modules.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmotta.modules.model.Pedido;

@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String queueName, Object message) {
		this.rabbitTemplate.convertAndSend(queueName, message);
	}
	
	@RabbitListener(queues = "Pedidos")
	public void receiveMessage(Pedido pedido) {
		System.out.println(pedido);
	}

}


