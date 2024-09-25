package br.ldamd.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    private static final String EXCHANGE_NAME = "restaurant_orders";

    public static void main(String[] args) throws Exception {
        // Cria conexão e canal com o RabbitMQ
        Connection connection = br.ldamd.config.RabbitMQConfig.getConnection();
        Channel channel = connection.createChannel();

        // Declara uma exchange do tipo 'topic'
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // Tipos de refeições e suas chaves de roteamento
        String[] refeicoes = {"Pizza", "Hamburguer", "Sushi", "Salada", "Tacos"};
        String[] routingKeys = {"refeicao.pizza", "refeicao.hamburguer", "refeicao.sushi", "refeicao.salada", "refeicao.tacos"};

        // Enviar 10 pedidos aleatórios
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) (Math.random() * refeicoes.length);
            String refeicao = refeicoes[randomIndex];
            String routingKey = routingKeys[randomIndex];

            String message = ": " + refeicao;
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] Enviado '" + routingKey + message + "'");
        }

        // Fecha o canal e a conexão
        channel.close();
        connection.close();
    }
}