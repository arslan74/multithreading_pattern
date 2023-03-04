package com.arslanhiader.www.design_pattern;


import com.arslanhiader.www.data_models.Topic;
import com.arslanhiader.www.exceptions.ProducerExceptions;
import com.arslanhiader.www.utils.Constants;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    // Buffer in the memory to hold topics
    private Queue<Topic> topics;
    // number of Topics that producer will create
    private int numObjects;
    private long bufferSize;
    private boolean isWorkDone;

    public Producer(Queue<Topic> topics, int numObjects, long bufferSize) {
        this.topics = topics;
        this.numObjects = numObjects;
        this.isWorkDone = false;
        this.bufferSize = bufferSize;
    }

    @Override
    public void run() {

        for (int i = 0; i< numObjects; i++){
            String randomKeyValue = Constants.uniqueNumberGenerator();
            Topic topic = new Topic(i,
                    randomKeyValue,
                    System.currentTimeMillis());

            synchronized (topics){

                while (topics.size() == bufferSize){
                    try {
                        topics.wait();
                    }catch (InterruptedException interruptedException){
                        interruptedException.printStackTrace();
                        throw new ProducerExceptions( "Producer Interrupted during wait",interruptedException);
                    }catch (NullPointerException nullPointerException) {
                        System.out.println("Caught NullPointerException: " + nullPointerException.getMessage());
                    }
                }

                try {
                    topics.add(topic);
                    System.out.println("Produced: " + topic.getId() + " | " +topic.getKeyValue());
                    topics.notifyAll();
                }catch (IndexOutOfBoundsException indexOutOfBoundsException){

                }

            }
        }
    }

    public boolean isWorkDone(){
        return isWorkDone;
    }
}
