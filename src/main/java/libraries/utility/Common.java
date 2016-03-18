package libraries.utility;

public class Common {

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void log(String text) {
        System.out.println("--- " + text);
    }

}
