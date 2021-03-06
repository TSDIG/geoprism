/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package net.geoprism.gis.geoserver;

import it.geosolutions.geoserver.rest.GeoServerRESTReader;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class GeoserverInitializer implements UncaughtExceptionHandler
{
  private static boolean               initialized = false;

  private static final ReentrantLock   lock        = new ReentrantLock();

  private static final Log             initLog     = LogFactory.getLog(GeoserverInitializer.class);

  private static final CleanupRunnable cleanup     = new CleanupRunnable();

  // private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public static class CheckThread implements Runnable
  {

    private static final Log log = LogFactory.getLog(CheckThread.class);

    public CheckThread()
    {
      super();
    }

    @Override
    public void run()
    {
      GeoServerRESTReader reader = GeoserverProperties.getReader();

      while (true)
      {
        try
        {
          lock.lock();

          log.debug("Attempting to check existence of geoserver");

          if (reader.existGeoserver())
          {
            log.debug("Geoserver available.");

            // To prevent a problem if the database connection information of the
            // datastore ever changes we must delete and recreate the store and workspace.
            if (GeoserverFacade.workspaceExists())
            {
              GeoserverFacade.removeWorkspace();
              GeoserverFacade.removeStore();
            }

            GeoserverFacade.publishWorkspace();
            GeoserverFacade.publishStore();

            initialized = true;
            log.debug("Geoserver initialized.");
            return; // we are done here
          }
          else
          {
            try
            {
              log.debug("Waiting for Geoserver to start.");
              Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
              // allow another try
              log.warn(e);
            }
          }
        }
        catch (Throwable t)
        {
          // we couldn't hit the application correctly, so log the error
          // and quit the loop to avoid excessive logging
          log.error("Unable to start the application.", t);
          return;
        }
        finally
        {
          lock.unlock();
        }
      }
    }

  }

  public static boolean isInitialized()
  {
    try
    {
      lock.lock();

      return initialized;
    }
    finally
    {
      lock.unlock();
    }
  }

  public static void setup()
  {
    GeoserverInitializer init = new GeoserverInitializer();

    try
    {
      initLog.debug("Attempting to initialize context.");

      // create another thread to avoid blocking the one starting the webapps.
      Thread t = new Thread(new CheckThread());
      t.setUncaughtExceptionHandler(init);
      t.setDaemon(true);
      t.start();

      initLog.debug("Context initialized...[" + GeoserverInitializer.class + "] started.");
    }
    catch (Throwable t)
    {
      initLog.error("Could not initialize context.", t);
    }

    // Start the mapping database view cleanup thread
    Thread t = new Thread(cleanup);
    t.setUncaughtExceptionHandler(init);
    t.setDaemon(true);
    t.start();

    // scheduler.scheduleWithFixedDelay(new CleanupRunnable(), 1, 5, TimeUnit.MINUTES);
  }

  /**
   * Log the error.
   */
  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    initLog.error(t, e);
  }

  public static void shutdown()
  {
    // Shutdown the mapping database view cleanup thread
    cleanup.shutdown();
  }

}