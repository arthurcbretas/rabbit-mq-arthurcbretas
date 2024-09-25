package br.ldamd.consumer;

public class SushiConsumer extends BaseConsumer {

    public static void main(String[] args) throws Exception {
        SushiConsumer consumer = new SushiConsumer();
        consumer.startConsumer("refeicao.sushi", "Sushi");
    }
}
