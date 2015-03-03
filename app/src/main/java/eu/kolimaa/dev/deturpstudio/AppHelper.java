package eu.kolimaa.dev.deturpstudio;

public class AppHelper {

    public static void killApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
