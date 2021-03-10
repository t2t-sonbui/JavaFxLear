package vn.mht.app.domain;

public class OsUtils {
    private static final String os = System.getProperty("os.name");
    private static final String version = System.getProperty("os.version");


    private static final boolean WINDOWS = os.startsWith("Windows");
    private static final boolean WINDOWS_VISTA_OR_LATER = WINDOWS && versionNumberGreaterThanOrEqualTo(6.0f);
    private static final boolean WINDOWS_7_OR_LATER = WINDOWS && versionNumberGreaterThanOrEqualTo(6.1f);
    private static final boolean MAC = os.startsWith("Mac");
    private static final boolean LINUX = os.startsWith("Linux");
    private static final boolean SOLARIS = os.startsWith("SunOS");
    private static final boolean IOS = os.startsWith("iOS");

    private static boolean versionNumberGreaterThanOrEqualTo(float value) {
        try {
            return Float.parseFloat(version) >= value;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if the operating system is a form of Windows.
     */
    public static boolean isWindows(){
        return WINDOWS;
    }

    /**
     * Returns true if the operating system is at least Windows Vista(v6.0).
     */
    public static boolean isWinVistaOrLater(){
        return WINDOWS_VISTA_OR_LATER;
    }

    /**
     * Returns true if the operating system is at least Windows 7(v6.1).
     */
    public static boolean isWin7OrLater(){
        return WINDOWS_7_OR_LATER;
    }

    /**
     * Returns true if the operating system is a form of Mac OS.
     */
    public static boolean isMac(){
        return MAC;
    }

    /**
     * Returns true if the operating system is a form of Linux.
     */
    public static boolean isLinux(){
        return LINUX;
    }

    /**
     * Returns true if the operating system is a form of Unix, including Linux.
     */
    public static boolean isSolaris(){
        return SOLARIS;
    }

    /**
     * Returns true if the operating system is a form of Linux or Solaris
     */
    public static boolean isUnix(){
        return LINUX || SOLARIS;
    }

    /**
     * Returns true if the operating system is iOS
     */
    public static boolean isIOS(){
        return IOS;
    }
}
