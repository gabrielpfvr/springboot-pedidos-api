package br.com.xbrain.api.queue;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue fila(String nomeFila) {
		//nomeFila, durable, exclusive, autoDelete
		return new Queue(nomeFila, true, false, false);
	}
	
	private DirectExchange exchange() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding relacionamento (Queue fila, DirectExchange exchange) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, exchange.getName(), fila.getName(), null);
	}
	
	@PostConstruct
	private void adiciona() {
		Queue filaPedidos = this.fila("Pedidos");
		
		DirectExchange troca = this.exchange();
		
		Binding ligacaoPedidos = this.relacionamento(filaPedidos, troca);
		
		this.amqpAdmin.declareQueue(filaPedidos);
		this.amqpAdmin.declareExchange(troca);
		this.amqpAdmin.declareBinding(ligacaoPedidos);
		
		
	}
}
