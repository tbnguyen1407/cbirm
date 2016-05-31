import gui.ApplicationWindow;
import storage.ImageIndexHolder;

import java.awt.*;

public class cbirm
{
    public static void main(String[] args)
    {

        // Initialize storage
        (new Thread()
        {
            public void run()
            {
                ImageIndexHolder.getInstance().initializeStorage();
            }
        }).run();


        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ApplicationWindow app = new ApplicationWindow();
                app.setTitle("Content-based Image Retrieval and Management");
                app.setVisible(true);
            }
        });

        //Tests ImageMatching / Used to initialize storage files for first run
        //ImageMatching.testImageMatching();
    }
}
