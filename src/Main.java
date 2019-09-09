import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello user kindly provide the full path to your csv file");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        System.out.println(" file path provided : " + filePath);

        String del = getDelimiter();
        List<String> erroredLines = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        String fileHeader = CsvProcessor.processFile(del,filePath,erroredLines,students);

        if (!students.isEmpty()){
            System.out.println(" STUDENTS FOUND =  " + students.size());
            System.out.println(" ");
            System.out.println(fileHeader);

            for (Student student : students){
                System.out.println(student.toString());
            }

        }

        if (!erroredLines.isEmpty()){
            System.out.println(" TOTAL ERRORS FOUND = " + erroredLines.size());

            for (String error : erroredLines){
                System.out.println(error);
            }

        }

    }

    private static String getDelimiter() {

        boolean validDel;
        int counter = 0;

        String del;

        do {

            del = CsvProcessor.getDelimiter();

            validDel = (CsvProcessor.validDelimiter(del));
            counter++;

            if (counter > 5) {
                System.out.println(" You have exceeded the maximum number of retries for providing a delimiter ");
                System.exit(1);
            }
        } while (!validDel);

        return del;
    }

}
