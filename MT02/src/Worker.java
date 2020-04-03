import java.util.concurrent.Callable;

public class Worker implements Runnable {
    private  int id;

    public Worker(int id) {
        this.id = id;
    }

   @Override
    public void run() {
        try {
            System.out.println("worker " + id + " is running...");
            Thread.sleep(3000);
            System.out.println("worker " + id + "  is finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
