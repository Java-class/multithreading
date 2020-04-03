public class MyThread extends Thread {
    public MyThread(String threadName){
        super(threadName);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("thread name: " + getName());
            System.out.println("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
