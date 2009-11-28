package org.kirhgoff.fractals;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class FractalApplet extends JApplet {

	private static final long serialVersionUID = 2123707524426851709L;
	private FractalPanel panel;
	
    public void init() {
        //Execute a job on the event-dispatching thread:
        //creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    panel = new FractalPanel ();
                    add (panel);
                    addKeyListener(panel.asKeyListener());
                    requestFocus();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
    }
    
    @Override
    public void destroy() {
        //Execute a job on the event-dispatching thread:
        //taking the text field out of this applet.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    remove(panel);
                }
            });
        } catch (Exception e) {
            System.err.println("cleanUp didn't successfully complete");
        }
        panel = null;
    }

    @Override
    public boolean isFocusTraversable() {
    	return true;
    }
}
