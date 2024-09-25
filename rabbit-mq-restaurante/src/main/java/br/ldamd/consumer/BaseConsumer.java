package br.ldamd.consumer;

import com.rabbitmq.client.*;
import br.ldamd.config.RabbitMQConfig;

public abstract class BaseConsumer {
    private static final String EXCHANGE_NAME = "restaurant_orders";

    // Método comum para iniciar o consumidor
    protected void startConsumer(String kitchenRoutingKey, String kitchenName) throws Exception {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();

        // Declara a exchange e cria a fila
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, kitchenRoutingKey);

        System.out.println(" [*] Aguardando pedidos para " + kitchenName);

        // Callback para processamento de pedidos
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Cozinha de " + kitchenName + " recebeu '" + message + "'");

            // Simula o tempo de preparo de 2 a 5 segundos
            int processingTime = 2000 + (int) (Math.random() * 3000);

            System.out.println(" [OK] Cozinha de " + kitchenName + " completou o pedido '" + message + "' em " + (processingTime / 1000) + " segundos.");
        };

        // Inicia o consumo de mensagens
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
