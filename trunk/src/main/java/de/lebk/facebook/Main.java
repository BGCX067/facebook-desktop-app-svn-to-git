/**
 * 
 */
package de.lebk.facebook;

import de.lebk.facebook.gui.MainGUI;

/**
 * @author viktor.reiberger
 * Email: <a href="mailto:VReiberger@wolterskluwer.de">VReiberger@wolterskluwer.de</a>
 *
 * @since 06.11.2013
 */
public class Main
{

    /**
     * Einstiegspunkt fuer die Applikation.
     * s
     * @param args
     */
    public static void main(String[] args)
    {  	
    	MainGUI gui = new MainGUI();
    	gui.MainGUIInit();
    	gui.setVisible(true);
    	
    }

}
