package com.lakala.sh.sa.mq;

import com.lakala.sh.sa.mq.kafka.KafkaSender;
import com.lakala.sh.sa.mq.rabbit.RabbitSender;
import com.lakala.sh.sa.mq.rocket.RocketMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqApplicationTests {

	//  RabbitMQ用来调用消息生产
	@Autowired
	private RabbitSender rabbitSender;

	@Autowired
	private KafkaSender kafkaSender;

	@Autowired
	private RocketMQProducer rocketMQProducer;

	/**
	 * RabbitMQ用来调用消息生产
	 * @throws Exception
	 */
	@Test
	public void testRabbitMQ() throws Exception {
		for (int i =0 ;i < 100; i++) {
            rabbitSender.send();
		}
	}
	@Test
	public void testKafka() throws Exception {
		kafkaSender.sendTest();
	}

	@Test
	public void testRocketMQ() throws Exception {
		rocketMQProducer.sendMsg();
	}
}
