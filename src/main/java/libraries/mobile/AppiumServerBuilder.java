package libraries.mobile;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import com.github.genium_framework.server.exception.ServerDirectoryNotFoundException;
import libraries.utility.Common;
import org.apache.commons.exec.OS;

import java.io.File;

public class AppiumServerBuilder {

    private AppiumServer appiumServer;
    private File appiumDirectoryOnWin;

    public void startServer(String address, String port) {
        ServerArguments serverArguments = new ServerArguments();
        serverArguments.setArgument("--address", address);
        serverArguments.setArgument("--bootstrap-port", port);
        serverArguments.setArgument("--no-reset", true);
        serverArguments.setArgument("--local-timezone", true);

        if (OS.isFamilyMac()) {
            appiumServer = new AppiumServer(serverArguments);
        } else if (OS.isFamilyWindows()) {
            appiumServer = new AppiumServer(appiumDirectoryOnWin, serverArguments);
        }
        appiumServer.startServer();
        Common.wait(3);
        Common.log("Appium server has been started successfully.");
    }

    public void stopServer() {
        appiumServer.stopServer();
        Common.wait(3);
        Common.log("Appium server has been stopped successfully.");
    }

    public void setAppiumDirectoryOnWindowsOS(String path) {
        File directory = new File(path);
        if (directory.exists()) {
            appiumDirectoryOnWin = directory;
        } else {
            throw new ServerDirectoryNotFoundException();
        }
    }

}
