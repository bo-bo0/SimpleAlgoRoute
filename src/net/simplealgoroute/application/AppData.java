package net.simplealgoroute.application;

public abstract class AppData
{
    private static final String version = "1.2.0";
    private static final String border = "══════════════════════════";

    public static String getVersion()
    {
        return version;
    }
    public static String getBorder()
    {
        return border;
    }
}
