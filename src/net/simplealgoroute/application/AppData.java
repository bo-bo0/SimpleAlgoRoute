package net.simplealgoroute.application;

public abstract class AppData
{
    private static final String version = "1.2.2";
    private static final String border = "════════════════════════════════";
    private static final int loadingTime = 600;

    public static String getVersion()
    {
        return version;
    }
    public static String getBorder()
    {
        return border;
    }
    public static int getLoadingTime()
    {
        return loadingTime;
    }
}
