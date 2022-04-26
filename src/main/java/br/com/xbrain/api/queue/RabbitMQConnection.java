package br.com.xbrain.api.queue;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
	
	private static final String EXCHANGE_NAME = "amq.direct";
	
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue queue(String queueName) {
		//queueName, durable, exclusive, autoDelete
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}
	
	private Binding binding (Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void createQueue() {
		
		Queue ordersQueue = this.queue("Pedidos");
		
		DirectExchange exchange = this.exchange();
		
		Binding ordersBinding = this.binding(ordersQueue, exchange);
		
		this.amqpAdmin.declareQueue(ordersQueue);
		this.amqpAdmin.declareExchange(exchange);
		this.amqpAdmin.declareBinding(ordersBinding);
		
		
	}
}
