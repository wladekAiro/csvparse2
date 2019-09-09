import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class CsvProcessor {

    static String processFile(String del, String filePath, List<String> erroredLines, List<Student> students) {

        String line;
        String fileHeader = null;

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

    static String getDelimiter(){
        System.out.println(" Please provide your csv file delimiter i.e either ',' or ';' ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    static boolean validDelimiter(String delimiter){
        char d = delimiter.charAt(0);
        return delimiter.length() < 2 && ( d == ';' || d == ',');
    }

    private static boolean matchDelimiter(String del , String line){
        return line.contains(del);
    }

}
