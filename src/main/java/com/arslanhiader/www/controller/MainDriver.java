package com.arslanhiader.www.controller;

import com.arslanhiader.www.data_models.Topic;
import com.arslanhiader.www.design_pattern.Consumer;
import com.arslanhiader.www.design_pattern.Producer;
import com.arslanhiader.www.utils.Constants;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MainDriver {

    public static void main(String[] args) {

        // args takes at least one element
        if(args.length == 0){
            System.out.println("Argument : Number of topics are required as arguments");
            System.exit(1);
        }

        int numTopics = Integer.parseInt(args[0]);

        // check if number of topics is negative or zero
        if(numTopics <= 0){
            System.out.println("Wrong Value: Number of topics can't not be zero or less then zero");
            System.exit(1);
        }

        // data structure to hold all topic objects
        Queue<Topic> topics = new LinkedList<>();

        // creating producer and consumer objects having there on threads
        Producer producer = new Producer(topics, numTopics, Constants.BUFFER_SIZE);
        Consumer consumer = new Consumer(topics, Constants.RETENTION_PERIOD);

        // initializing and starting threads
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();


        // while (!producer.isWorkDone() || !consumer.isWorkDone()) {
        //     try {
        //         Thread.sleep(1000);
        //     } catch (InterruptedException e) {
        //        throw new RuntimeException(e);
        //    }
        // }

        //try {
        //    producerThread.join();
        //    consumerThread.join();
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        // }




    }
}
