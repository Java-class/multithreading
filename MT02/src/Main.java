import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main is started...");


        ExecutorService es = Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++){
            Runnable r = new Worker(i);
            es.execute(r);
        }


        es.shutdown();
        es.awaitTermination(300000,TimeUnit.MILLISECONDS);

        //Worker worker = new Worker();
        /*Runnable worker1 = new Worker();
        Thread t1 = new Thread(worker1);
        t1.start();

        Runnable worker2 = new Worker();
        Thread t2 = new Thread(worker2);
        t2.start();



        Runnable worker3 = new Worker();
        Thread t3 = new Thread(worker3);
        t3.start();


        Runnable worker4 = new Worker();
        Thread t4 = new Thread(worker4);
        t4.start();




        while (t1.isAlive()){
            System.out.println("wait for t1...");
        }

        while (t2.isAlive()){
            System.out.println("wait for t2...");
        }
        while (t3.isAlive()){
            System.out.println("wait for t3...");
        }
        while (t4.isAlive()){
            System.out.println("wait for t4...");
        }*/


        System.out.println("main is finished.");

    }
}
