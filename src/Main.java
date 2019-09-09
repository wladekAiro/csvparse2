import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        String fileHeader = null;

        fileHeader = processFile(del,filePath,erroredLines,students,fileHeader);

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

    private static String processFile(String del, String filePath, List<String> erroredLines, List<Student> students,
                                      String fileHeader) {

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            int lineNumber = 1;

            while ((line = br.readLine()) != null) {

                if (!line.contains(del)) {
                    del = getDelimiter();
                }

                if (lineNumber > 1) {
                    // use del as separator
                    String[] fileLine = line.split(del);
                    validateFileLine(lineNumber, fileLine, erroredLines, students);
                }else {
                    fileHeader = line;
                }

                lineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileHeader;
    }

    private static void validateFileLine(int lineNumber, String[] fileLine, List<String> erroredLines, List<Student> students) {

        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String idStrng = fileLine[0];
            String firstName = fileLine[1];
            String lastName = fileLine[2];
            String grade = fileLine[3];
            String regDate = fileLine[4];

            Date regiDate = dateFormater.parse(regDate);

            Student student = new Student(Integer.parseInt(idStrng), firstName, lastName, grade, regiDate);
            students.add(student);

        } catch (Exception e) {
            String error = " Error file line " + lineNumber + " : " + e.getMessage();
            erroredLines.add(error);
        }
    }

}
