/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printerComponent;

import java.awt.print.*;
import java.awt.*;
import javax.swing.*;
import javax.print.attribute.*;

/**
 *
 * @author MinamRosh
 */
public class SamplePrinter implements Printable {
    public int print(Graphics g, PageFormat pF, int page)throws PrinterException{
        
        if(page > 0)
            return NO_SUCH_PAGE;
        
        //manageing coordinate;
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pF.getImageableX(), pF.getImageableY());
        g.drawString("Hello World", 100, 100);
        return PAGE_EXISTS;
    }
    public void printJob(){
        PrinterJob job = PrinterJob.getPrinterJob();
        ///PrintRequestAttributeSet attr = new HashPrintRequestAtributeSet();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if(ok){
            try{
            job.print();
            }
            catch(PrinterException ex){
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            }
        }
    }
    
    public static void main(String args[]){
        SamplePrinter pr = new SamplePrinter();
        pr.printJob();
    }
}
