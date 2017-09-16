import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class LogParser {

    public static void main(String[] args) throws IOException, ParseException {
        Path file = Paths.get("log-parser/src/main/resources/log.txt");
        List<String> listOfLines = getLinesLast24Hours(file);
        Map<Integer, Integer> AllUpStatusResponsesMap = getMapResponses(listOfLines);
        Map<Integer, Integer> upStatusResponses = getMapUpResponsesMoreThan200(AllUpStatusResponsesMap);
        System.out.println(getQuantityOfRequestsWithNon200Code(upStatusResponses) + " out of "
                + getQuantityOfAllRequests(getLinesLast24Hours(file)) + " returned non 200 code \n");
        for (Map.Entry<Integer, Integer> pair: upStatusResponses.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue() + " \n");
        }
        System.out.println("Average response with 200 code: " + (int)Math.round(getAverageOfXRespTime(getLinesLast24Hours(file)))
                + "ms");
    }


    private static String getUpStatus(String line) {
        return line.split("up_resp_time=")[0].split("up_status=")[1]
                .replace("\"", "");
    }

    private static String getXRespTime(String line) {
        return  line.split("x_worker")[0].split("x_resp_time=")[1]
                .replace("\"", "").replace("ms", "");
    }

    private static String getDate(String line) {
        return line.split(" +0300")[0].split("- - ")[1]
                .replace("[", "");
    }

    private static Map<Integer, Integer> getMapUpResponsesMoreThan200(Map<Integer, Integer> map) {
        Map<Integer, Integer> upStatus = new HashMap<>();

        for (Map.Entry<Integer, Integer> pair: map.entrySet()) {
            if (pair.getKey() > 200)
                upStatus.put(pair.getKey(), pair.getValue());
        }
        return upStatus;
    }


    private static List<String> getLinesLast24Hours(Path file) throws IOException, ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);

        List<String> lines = new ArrayList<>();

        for (String line : Files.readAllLines(file)){
            String twentyFourHoursAgo = simpleDateFormat.format(calendar.getTime());
            String dateFromLog = getDate(line);
            if (simpleDateFormat.parse(dateFromLog).after(simpleDateFormat.parse(twentyFourHoursAgo))) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static Map<Integer, Integer> getMapResponses(List<String> lines) {
        Map<Integer, Integer> map = new HashMap<>();

        for (String line : lines){

            String upStatusString = getUpStatus(line);
            int[] upStatusArray = Arrays.stream(upStatusString.split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            Integer[] IntegerUpStatusArray = Arrays.stream(upStatusArray).boxed().toArray(Integer[]::new);
            for (Integer upStatus: IntegerUpStatusArray) {
                map.merge(upStatus, 1, (a, b) -> a + b);
            }
        }
        return map;
    }

    private static int getQuantityOfAllRequests(List<String> lines) {
        return lines.size();
    }

    private static int getQuantityOfRequestsWithNon200Code(Map<Integer, Integer> map) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair: map.entrySet()) {
            if (pair.getKey() > 200){
                sum = sum + pair.getValue();
            }
        }
        return sum;
    }

    private static List<Double> listOfXRespTime(List<String> lines) {
        List<Double> list = new ArrayList<>();

        for (String line: lines) {
            if (getUpStatus(line).equals("200 ")) {
                list.add(Double.parseDouble(getXRespTime(line))); }
        }
        return list;
    }

    private static Double getAverageOfXRespTime(List<String> lines){
        List<Double> list = listOfXRespTime(lines);
        Double sum = 0.00;
        for (Double l : list) {
            sum = sum + l;
        }
        return sum / list.size();
    }

}
