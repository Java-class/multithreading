import java.util.Random;
import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {
    private  int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public Integer call() {
        Integer result = 0;
        try {
            System.out.println("worker " + id + " is running...");
            Random r = new Random();
            result = r.nextInt(1000);
            Thread.sleep(3000);
            System.out.println("worker " + id + "  is finished...");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  result;

    }

    /*@Override
    public Object call() throws Exception {
        return null;
    }*/

  /* @Override
    public void run() {
        try {
            System.out.println("worker " + id + " is running...");
            Thread.sleep(3000);
            System.out.println("worker " + id + "  is finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


}
