package com.example.jeremiah8100.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by jeremiah8100 on 14-5-2018.
 */

public class Async {
    public static Async main = new Async();
    public static abstract class Item {
        int time;
        int currenttime = 0;
        public Item(int time){
            this.time = time;
        }
        public abstract void Method();

    }
    private List<Item> Items = new ArrayList<Item>();

    public void Add(Item i){
        Items.add(i);
    }

    public static void RunTask(final Item i){
        final Semaphore locker = new Semaphore(0);
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                i.Method();
                locker.release();
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            locker.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Async() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               // Tick();
            }
        }).start();
    }

    private void Tick(){
        for(Item i : Items){
            if(i.currenttime == i.time) {
                i.Method();
                Items.remove(i);
            } else {
                i.currenttime++;
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tick();
    }


}
