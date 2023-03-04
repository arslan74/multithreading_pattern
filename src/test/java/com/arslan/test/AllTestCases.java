package com.arslan.test;

import com.arslanhiader.www.data_models.Topic;
import com.arslanhiader.www.design_pattern.Consumer;
import com.arslanhiader.www.design_pattern.Producer;
import com.arslanhiader.www.utils.Constants;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class AllTestCases {

    @Test
    public void testTopicQueue(){

        int numTopics = 5;
       Queue<Topic> topics = new LinkedList<>();

        // testing empty queue
        assertTrue(topics.isEmpty());
        assertEquals(0, topics.size());

        // adding topic in queue
        Topic topic1 = new Topic(111, Constants.uniqueNumberGenerator(), System.currentTimeMillis());
        topics.add(topic1);
        assertFalse(topics.isEmpty());
        assertEquals(1, topics.size());
        assertEquals(topic1, topics.peek());

        // remove an element from the buffer
        Topic topic2 = topics.remove();
        assertEquals(topic1, topic2);
        assertEquals(0, topics.size());
    }

    @Test
    public void testProducer() {

        int numTopics = 10;
        // create a queue for 10 topics
        Queue<Topic> topics1 = new LinkedList<>();

        Producer producer1 = new Producer(topics1, numTopics, 15);
        producer1.run();
        assertEquals(topics1.size(), 10);


        Queue<Topic> topics2 = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(topics2, numTopics, 10);
        producer.run();
        assertEquals(topics2.size(), numTopics);
    }

    @Test
    public void testConsumer() throws InterruptedException {

        // topics that consumer will process
        Queue<Topic> topics = new LinkedList<>();
        topics.add(new Topic(111, Constants.uniqueNumberGenerator(), System.currentTimeMillis()));
        topics.add(new Topic(112, Constants.uniqueNumberGenerator(), System.currentTimeMillis()));
        topics.add(new Topic(113, Constants.uniqueNumberGenerator(), System.currentTimeMillis()));

        Consumer consumer = new Consumer(topics, Constants.RETENTION_PERIOD);

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        //consumerThread.join();

        consumerThread.sleep(5000);

        // Interrupt the consumer thread
        consumerThread.interrupt();

        assertEquals(0, topics.size());

    }

    @Test
    public void testRetentionTime() throws InterruptedException {
        int numTopics = 5;
        int retentionPeriod = 1000;
        Queue<Topic> topics = new ArrayBlockingQueue<>(5);

        Producer producer = new Producer(topics, numTopics, Constants.BUFFER_SIZE);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        Consumer consumer = new Consumer(topics, retentionPeriod);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Thread.sleep(retentionPeriod + 500);

        producerThread.interrupt();
        consumerThread.interrupt();

        producerThread.join();
        consumerThread.join();

        assertEquals(numTopics, consumer.getConsumedCount());

    }
}
