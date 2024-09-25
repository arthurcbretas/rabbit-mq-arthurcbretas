package br.ldamd.consumer;

import com.rabbitmq.client.*;

import br.ldamd.config.RabbitMQConfig;

public class GeneralConsumer extends BaseConsumer {

    public static void main(String[] args) throws Exception {
        GeneralConsumer consumer = new GeneralConsumer();
        consumer.startGeneralConsumer();
    }

    // Método para o consumidor geral (para Salada e Tacos)
    public void startGeneralConsumer() throws Exception {
        Connection connection = RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME = "restaurant_orders";
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        // Faz o bind para os tipos de refeições restantes (salada, tacos)
        channel.queueBind(queueName, EXCHANGE_NAME, "refeicao.salada");
        channel.queueBind(queueName, EXCHANGE_NAME, "refeicao.tacos");

        System.out.println(" [*] Aguardando pedidos para Salada e Tacos");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            String type = message.contains("salada") ? "Salada" : "Tacos";

            System.out.println(" [x] Cozinha de " + type + " recebeu '" + message + "'");

            // Simula o tempo de preparo de 2 a 5 segundos
            int processingTime = 2000 + (int) (Math.random() * 3000);

            System.out.println(" [OK] Cozinha de " + type + " completou o pedido '" + message + "' em " + (processingTime / 1000) + " segundos.");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
