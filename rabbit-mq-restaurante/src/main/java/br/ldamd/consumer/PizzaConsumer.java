package br.ldamd.consumer;

public class PizzaConsumer extends BaseConsumer {

    public static void main(String[] args) throws Exception {
        PizzaConsumer consumer = new PizzaConsumer();
        consumer.startConsumer("refeicao.pizza", "Pizza");
    }
}
