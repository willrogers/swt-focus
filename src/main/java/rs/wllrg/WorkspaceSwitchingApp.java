package rs.wllrg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.gtk.GTK;

/**
 * A small app that launches two windows.
 *
 * Each has a button that transfers focus to the other.
 * The behaviour on Gnome Shell differs between SWT versions 3.112.0
 * and 3.113.0 as an unintended consequence of this change:
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=371545
 */
public class WorkspaceSwitchingApp {

    static Shell createChild(Display display, Shell parentShell, int x, int y, int cx, int cy, int level) {

       FillLayout layout = new FillLayout();
       layout.marginHeight = 10;
       layout.marginWidth = 10;

       int shellStyle = (level == 0) ? SWT.SHELL_TRIM : SWT.DIALOG_TRIM;

       Shell shell = new Shell(display, shellStyle);
       shell.setLayout(layout);
       shell.setText("Child");

       Button button = new Button(shell, SWT.NONE);
       button.setText("focus parent");
       button.addListener(SWT.Selection, e -> {
           System.out.println("Setting focus on parent.");
           parentShell.forceFocus();
       });

       shell.setBounds(x, y, cx, cy);
       shell.open();

       shell.addListener(SWT.Activate, e -> {
           System.out.println("Child was activated.");
       });
       return shell;

   }

   public static void main (String [] args) {
       System.out.println("GTK Version: " + GTK.gtk_get_major_version() + "." + GTK.gtk_get_minor_version() + "." + GTK.gtk_get_micro_version());
       Display display = new Display ();

       FillLayout layout = new FillLayout();
       layout.marginHeight = 10;
       layout.marginWidth = 10;

       Shell shell = new Shell(display);
       shell.setLayout(layout);
       shell.setText("Parent");

       Button button = new Button(shell, SWT.NONE);
       button.setText("focus child");

       final int x = 100;
       final int y = 100;
       final int cx = 200;
       final int cy = 200;
       shell.setBounds(x, y, cx, cy);
       shell.open();

       Shell childShell = createChild(display, shell, x + cx, y, cx, cy, 0);
       shell.addListener(SWT.Activate, e -> {
           System.out.format("Root shell was activated\n");

       });
       button.addListener(SWT.Selection, e -> {
           System.out.println("Setting focus on child.");
           childShell.forceFocus();
       });

       while (!shell.isDisposed()) {
           if (!display.readAndDispatch ()) display.sleep ();
       }

       display.dispose ();
   }
}
