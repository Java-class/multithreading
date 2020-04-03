public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1");
        Thread t1 = new MyThread("t1");
        t1.start();
        System.out.println("state of thread:" + t1.getState().name());
        //Thread.sleep(5000);

        Thread t2 = new MyThread("t2");
        t2.start();
        ///System.out.println("2");
        System.out.println("3");
        Thread.sleep(7000);
        System.out.println("state of thread:" + t1.getState().name());


    }
}
