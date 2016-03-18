package mobile.support;

public class Device {

    public static boolean isPhone;
    public static boolean isTablet;
    public static boolean isIOS;
    public static boolean isAndroid;

    public static int screenWidth;
    public static int screenHeight;
    public static int hotspotX;
    public static int hotspotY;

    public static void setDeviceInfo(String deviceName) {
        switch (deviceName) {
            case "ZX1G22HQTF":      // Nexus 6
                isPhone = true;
                isTablet = false;
                isIOS = false;
                isAndroid = true;
                screenWidth = 1440;
                screenHeight = 2392;
                hotspotX = 1360;
                hotspotY = 1700;
                break;

            case "HT5ADJT00550":    // Nexus 9
                isPhone = false;
                isTablet = true;
                isIOS = false;
                isAndroid = true;
                screenWidth = 1536;
                screenHeight = 1952;
                hotspotX = 1480;
                hotspotY = 1370;
                break;

            case "13abea4772f9fea654abf948a67cfe20917c8c9d":    // eXplorer iPhone 6
                isPhone = true;
                isTablet = false;
                isIOS = true;
                isAndroid = false;
                screenWidth = 375;
                screenHeight = 667;
                break;

            case "659f63fdef0d51b5352c1c31e4a20f2c23d82f44":    // eXplorer iPad Air
                isPhone = false;
                isTablet = true;
                isIOS = true;
                isAndroid = false;
                screenWidth = 375;
                screenHeight = 667;
                break;

            case "a8f01479cabaaeb16d13f5d1373550913a67bf74":    // iPod Touch
                isPhone = true;
                isTablet = false;
                isIOS = true;
                isAndroid = false;
                screenWidth = 320;
                screenHeight = 568;
                break;
        }
    }

}
