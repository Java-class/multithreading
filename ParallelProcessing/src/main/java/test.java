
import org.jfree.chart.ui.UIUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class test {
    public static void main(String[] args) throws InterruptedException, IOException {

        String keyword = "12489";
        System.out.println("number of cpu cores: " + Runtime.getRuntime().availableProcessors());

        String sampleDirectoryPath = "E:\\0000-data\\";
        File sampleDirectory = new File(sampleDirectoryPath);
        if (sampleDirectory.exists())
            System.out.println("*** sample directory loaded Successfully... ***");

        File _100k = new File(sampleDirectory + "/" + "100K.txt");
        File _1M = new File(sampleDirectory + "/" + "1M.txt");
        File _100M = new File(sampleDirectory + "/" + "100M.txt");
        File _500M = new File(sampleDirectory + "/" + "500M.txt");

        System.out.println("100K started....");
        TimingResult timingResult100K = doCounting(_100k, 100_000, keyword);
        System.out.println("100k finished...");


        System.out.println("1m started....");
        TimingResult timingResult1M = doCounting(_1M, 1_000_000, keyword);
        System.out.println("1m finished...");


        System.out.println("100m started...");
        TimingResult timingResult100M = doCounting(_100M, 100_000_000, keyword);
        System.out.println("100m finished...");


        System.out.println("500m started...");
        TimingResult timingResult500M = doCounting(_500M, 500_000_000, keyword);
        System.out.println("500m finished...");

        showResultChart(timingResult100K, timingResult1M, timingResult100M, timingResult500M);


    }

    public  static  void splitTextFiles(Path bigFile, int maxRows) throws IOException{
        int i = 1;
        try(BufferedReader reader = Files.newBufferedReader(bigFile)){
            String line = null;
            int lineNum = 1;
            Path splitFile = Paths.get("E:\\0000-data\\chunks\\" + i + ".txt");
            BufferedWriter writer = Files.newBufferedWriter(splitFile, StandardOpenOption.CREATE);
            while ((line = reader.readLine()) != null) {
                if(lineNum > maxRows){
                    writer.close();
                    lineNum = 1;
                    i++;
                    splitFile = Paths.get( "E:\\0000-data\\chunks\\"+ i + ".txt");
                    writer = Files.newBufferedWriter(splitFile, StandardOpenOption.CREATE);
                }
                writer.append(line);
                writer.newLine();
                lineNum++;
            }
            writer.close();
        }
    }

    public static TimingResult doCounting(File input,int fileRow,String keyword) throws InterruptedException, IOException {
      long linear = linearCount(input,keyword);
      long _2Thread = parallelCount(input,fileRow,keyword,2);
      long _4Thread = parallelCount(input,fileRow,keyword,4);
      long _8Thread = parallelCount(input,fileRow,keyword,8);
      return new TimingResult(linear,_2Thread,_4Thread,_8Thread);
    }

    public static long parallelCount(File input, int fileRow,String keyword, int thread_count) throws FileNotFoundException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newWorkStealingPool(thread_count);
        List<Callable<Long>> taskList = new ArrayList<>();// = Arrays.asList(task1, task2, task3, task4);
        MyTask task;
        long pageSize = 100_000;
        if(fileRow > 100_000){
            pageSize = fileRow / thread_count;
        }
        for(int i=0;i<fileRow/pageSize;i++){
            long startLine = i*pageSize;
            long endLine = (i+1)*pageSize;
            task = new MyTask(i,input,startLine,endLine,keyword);
            //executor.submit(task);
            taskList.add(task);
        }
        //executor.awaitTermination(Long., TimeUnit.MILLISECONDS);

        long totalCount = 0;
        List<Long> counts = executor.invokeAll(taskList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                }).collect(Collectors.toList());
        executor.shutdown();
        for (long l : counts) {
            totalCount += l;
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("totalCount: " + totalCount + " duration: " + duration  + " with: " + thread_count + " number of Thread.");
        return duration;
    }

    public static long linearCount(File input, String keyword)throws FileNotFoundException{
        long startTime = System.currentTimeMillis();
        long keyword_count = 0;
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            if (scanner.next().equals(keyword))
                keyword_count++;
        }
        scanner.close();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("#### Linear " + "totalCount: " + keyword_count + " duration: " + duration);
        return  duration;
    }

    public static  void showResultChart(TimingResult timingResult100K,TimingResult timingResult1M,  TimingResult timingResult100M, TimingResult timingResult500M ){
        BarChart3D demo = new BarChart3D( "Parallel Search" ,timingResult100K,timingResult1M,timingResult100M,timingResult500M);
        demo.setSize( 1000 , 800 );
        demo.setResizable(false);
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible( true );
    }


}
