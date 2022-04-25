package br.com.xbrain.api.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xbrain.api.model.Pedido;

@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void enviarMensagem(String nomeFila, Object mensagem) {
		this.rabbitTemplate.convertAndSend(nomeFila, mensagem);
	}
	
	@RabbitListener(queues = "Pedidos")
	public void receiveMessage(Pedido pedido) {
		System.out.println(pedido);
	}

}


