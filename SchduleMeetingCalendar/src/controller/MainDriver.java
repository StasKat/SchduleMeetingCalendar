package controller;

import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.ViewSetup;


/**
 * Our main driver that runs and starts the program.
 * 
 * @author staskatrechko
 * @version 3
 */
public class MainDriver {
	
	private MainDriver() {
		throw new IllegalStateException("Private MainDriver was called.");
	}
	
	
	public static void main(final String[] theArgs) {

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (final UnsupportedLookAndFeelException e) {
       // handle exception
        } catch (final ClassNotFoundException e) {
       // handle exception
        } catch (final InstantiationException e) {
       // handle exception
        } catch (final IllegalAccessException e) {
       // handle exception
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewSetup().start();
            }
        }); 
	}
	
	

}
