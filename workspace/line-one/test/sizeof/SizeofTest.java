package sizeof;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.sourceforge.sizeof.SizeOf;

public class SizeofTest {

    public static void main (String [] args) {

        ArrayBlockingQueue <String> asa = new ArrayBlockingQueue <String> (100);
        LinkedBlockingQueue <String> aabb = new LinkedBlockingQueue <String> ();

        SizeOf.skipStaticField (true);
        SizeOf.setMinSizeToLog (10);

        try {
            System.out.println (SizeOf.iterativeSizeOf (asa));
            System.out.println (SizeOf.iterativeSizeOf (aabb));
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
    }
}
