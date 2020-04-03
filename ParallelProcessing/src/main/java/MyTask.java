import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.concurrent.Callable;

public class MyTask implements Callable {
    private int id;
    private File file;
    private String keyword;
    private long keyword_count;
    private long startLine;
    private long endLine ;

    MyTask(int id,File file,long startLine, long endLine ,String keyword){
        this.id = id;
        this.file = file;
        this.startLine = startLine;
        this.endLine = endLine;
        this.keyword = keyword;
        this.keyword_count = 0;
    }

    @Override
    public Object call() throws Exception {


        //Construct the LineNumberReader object
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));

        //Print initial line number
        //System.out.println("Line " + lineNumberReader.getLineNumber() + " index" + id);

        //Setting initial line number
        //lineNumberReader.setLineNumber(startLine);
        //System.out.println("$$$$$$$$$$: " + id + " " + startLine*7);
        lineNumberReader.skip(startLine*7);

        //Get current line number
        //System.out.println("Line " + lineNumberReader.getLineNumber() + " index: " + id);

        //Read all lines now; Every read increase the line number by 1
        String line;
        while (lineNumberReader.getLineNumber()!=endLine) {
            line = lineNumberReader.readLine();
            //System.out.println(line);
            if(line==null)
                break;
            if(line.equals(keyword)) {
                keyword_count++;
               //System.out.println("item found in line: " + lineNumberReader.getLineNumber());
            }
        }

        //System.out.println("Line " + lineNumberReader.getLineNumber() + "index: " + id)
        ///System.out.println("My task: " + id +" count " + keyword_count +  " startLine: " + startLine + " endline: " + lineNumberReader.getLineNumber());
        lineNumberReader.close();

        return keyword_count;
    }
}
