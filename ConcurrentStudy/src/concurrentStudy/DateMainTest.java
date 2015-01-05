package concurrentStudy;

public class DateMainTest {

    public static void main(String[] args) {

        DateUtilInterface dateUtil = new DateUtil01();
        Runnable runabble = new DateThread(dateUtil);
        for (int i = 0; i < 10; i++) {
            new Thread(runabble).start();
        }
    }
}
