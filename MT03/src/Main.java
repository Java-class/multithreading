import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("main is started...");


        List<Worker> alltasks = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++){
            Worker worker = new Worker(i);
            alltasks.add(worker);

        }

        List<Future<Integer>> futures = es.invokeAll(alltasks);


        es.shutdown();
        es.awaitTermination(300000,TimeUnit.MILLISECONDS);
        for(Future future :futures){
            System.out.println(future.get());
        }

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
