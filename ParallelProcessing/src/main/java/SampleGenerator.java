import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SampleGenerator {

    public static void main(String[] args) throws IOException {
        String sampleDirectoryPath = "E:\\0000-data\\";
        File sampleDirectory = new File(sampleDirectoryPath);
        if(!sampleDirectory.exists())
            FileUtils.forceMkdir(sampleDirectory);
        if(sampleDirectory.exists())
            System.out.println("*** sample directory loaded Successfully... ***");

        File _100k = new File(sampleDirectory + "/" + "100K.txt");
        File _1M = new File(sampleDirectory + "/" + "1M.txt");
        File _100M = new File(sampleDirectory + "/" + "100M.txt");
        File _500M = new File(sampleDirectory + "/" + "500M.txt");

        generateSampleRandomData(_100k,100_000);
        generateSampleRandomData(_1M,1_000_000);
        generateSampleRandomData(_100M,100_000_000);
        generateSampleRandomData(_500M,500_000_000);


    }

    public static void generateSampleRandomData(File sample,int dataCount){
        try {
            String newLine = System.getProperty("line.separator");
            FileWriter fout = new FileWriter(sample.getAbsolutePath());
            for (int i = 0; i < dataCount; i++) {
                String random = RandomStringUtils.randomNumeric(5);
                fout.write(random + newLine);
                if (i % 10000 == 0) {
                    System.out.println(i);
                    fout.flush();
                }
            }
            fout.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
