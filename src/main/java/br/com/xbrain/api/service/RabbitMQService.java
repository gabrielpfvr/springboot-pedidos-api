package br.com.xbrain.api.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void enviarMensagem(String nomeFila, Object mensagem) {
		this.rabbitTemplate.convertAndSend(nomeFila, mensagem);
	}

}
