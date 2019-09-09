import java.util.Scanner;

class CsvProcessor {

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
