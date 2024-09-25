package br.ldamd.consumer;

public class HamburguerConsumer extends BaseConsumer {

    public static void main(String[] args) throws Exception {
        HamburguerConsumer consumer = new HamburguerConsumer();
        consumer.startConsumer("refeicao.hamburguer", "Hambúrguer");
    }
}
