package br.ldamd.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {
    // URL de conexão RabbitMQ (substitua com sua URL do CloudAMQP)
    private static final String RABBITMQ_URL = "amqps://pimbevdk:JZWVvPtwPbtL0QHr9yYRRxPB8T-GzXig@cow.rmq2.cloudamqp.com/pimbevdk";

    // Método para obter a conexão
    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(RABBITMQ_URL);  // Define a URL de conexão
        return factory.newConnection();
    }
}
