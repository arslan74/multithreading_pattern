package com.arslanhiader.www.design_pattern;


import com.arslanhiader.www.data_models.Topic;
import com.arslanhiader.www.exceptions.ConsumerException;
import com.arslanhiader.www.utils.Constants;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;

public class Consumer implements Runnable {

    private Queue<Topic> topics;
    private long retentionPeriod;

    private int consumedCount;
    private boolean isWorkDone;
    public Consumer(Queue<Topic> topics, long retentionPeriod) {
        this.topics = topics;
        this.retentionPeriod = retentionPeriod;
        this.consumedCount = 0;
    }

    @Override
    public void run() {
            while (true){
                synchronized (topics){
                    while (topics.isEmpty()){
                        try {
                            topics.wait();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                            throw new ConsumerException("Consumer is Interrupted",interruptedException);
                        }catch (NullPointerException nullPointerException) {
                            System.out.println("Caught NullPointerException: " + nullPointerException.getMessage());
                        }
                    }

                    Topic topic = topics.peek();

                    if(topic.getTimestamp() + retentionPeriod < System.currentTimeMillis()){
                        topics.remove();
                        consumedCount++;
                        System.out.println("Consumed: " + topic.getId() + "| " +  topic.getKeyValue());
                        topics.notifyAll();
                    }
                }
            }


    }

    public boolean isWorkDone(){
        return isWorkDone;
    }

    public int getConsumedCount(){
        return consumedCount;
    }

}
