package com.highwire.cochrane.utils;



import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;

/**
 * 
 * @author Sharadha Sharma
 *
 */
public class BrowserDriver {

    private static final Logger LOGGER = Logger.getLogger(BrowserDriver.class.getName());
  
    private static WebDriver mDriver;

    

    public synchronized static WebDriver getCurrentDriver() {
        if (mDriver == null) {

            try {
                mDriver = BrowserFactory.getBrowser();
            } catch (UnreachableBrowserException e) {
            	e.printStackTrace();
               
            } catch (WebDriverException e) {
            	e.printStackTrace();
               
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
        }
        return mDriver;
    }

    public static void close() {
        try {
            getCurrentDriver().quit();
            mDriver = null;
            LOGGER.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOGGER.info("cannot close browser: unreachable browser");
        }
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }


    
    }

