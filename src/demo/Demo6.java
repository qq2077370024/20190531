package demo;

import java.util.concurrent.CountDownLatch;

public class Demo6 {
    static class Task implements Runnable{
        private final String name;
        private final int timeToStart;
        private final CountDownLatch latch;

        public Task(String name, int timeToStart, CountDownLatch latch){
            this.name = name;
            this.timeToStart = timeToStart;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeToStart);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"准备就绪");
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);

        for(int i=0;i<10;i++){
            new Thread(new Task("sport"+i,i*1000,latch)).start();
        }
        try {
            latch.await();//等待所有线程就绪
            System.out.println("所有就绪...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
