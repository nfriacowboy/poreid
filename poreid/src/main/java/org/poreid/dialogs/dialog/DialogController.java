/*
 * The MIT License
 *
 * Copyright 2014 Rui Martinho (rmartinho@gmail.com), António Braz (antoniocbraz@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.poreid.dialogs.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.poreid.config.POReIDConfig;

/**
 *
 * @author POReID
 */
public class DialogController {
    private String title;
    private String message;
    private Locale locale;
    private boolean error;
    
    private DialogController(String title, String message, Locale locale, boolean error) {
        try {
            if (null != UIManager.getLookAndFeel()) {
                UIManager.setLookAndFeel(POReIDConfig.LAF);
            }
            this.title = title;
            this.message = message;
            this.locale = locale;
            this.error = error;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            throw new RuntimeException("Não foi possivel criar a janela de dialogo.", ex);
        }
    }
    
  
    public static DialogController getInstance(String title, String message, Locale locale, boolean error){
        return new DialogController(title, message, locale, error);
    }
    
    
    public void displayDialog(){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    Dialog dialog = new Dialog(title, message, locale, error);
                    dialog.setVisible(true);
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            throw new RuntimeException("Não foi possivel criar a janela de dialogo.", ex);
        }
    }
}
