package gov.nasa.xpc.ex;

import gov.nasa.xpc.XPlaneConnect;

import java.io.IOException;

/**
 * An example program demonstrating the use of X-PlaneConnect in a continuous loop.
 *
 * @author Jason Watkins
 * @version 1.0
 * @since 2015-06-19
 */
public class Main
{
    public static void main(String[] args)
    {
        try (XPlaneConnect xpc = new XPlaneConnect("192.168.0.102", 48001, 48000, 5000))
        {

            int aircraft = 0;
            while(true)
            {
                float[] posi = xpc.getPOSI(aircraft);
                float[] ctrl = xpc.getCTRL(aircraft);

//                System.out.format("Loc: (%, %, %, %) Aileron:%2f Elevator:%2f Rudder:%2f\n",
//                        posi[3], posi[4], posi[5], posi[6], ctrl[1], ctrl[0], ctrl[2]);

                System.out.printf("POSI: (%.0f, %.0f, %.0f, %.0f)\n", posi[3], posi[4], posi[5], posi[6]);
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException ex) {}

                if(System.in.available() > 0)
                {
                    break;
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println("Error:");
            System.out.println(ex.getMessage());
        }
    }
}
