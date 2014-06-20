/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reusableClass;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author SUSHIL
 */
public class Validator {
 public static  void DecimalMaker(JFormattedTextField jf){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                
                //formatter.setMinimum(0.0);
                //formatter.setMaximum(1000.00);
                return formatter;
            }
        });
      jf.setValue(new Double(0.00));
 } 
 public static  void DecimalMaker(JFormattedTextField jf,final Double upperlimit){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                //formatter.setMinimum(0.0);
                formatter.setMaximum(upperlimit);
//                JOptionPane.showMessageDialog(tf, upperlimit);
                return formatter;
            }
        });
      jf.setValue(new Double(0.00));
 }
 public static  void DecimalMakerWithDefaultValue(JFormattedTextField jf,Double value){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                //formatter.setMinimum(0.0);
                //formatter.setMaximum(1000.00);
                return formatter;
            }
        });
      jf.setValue(value);
 } 
 public static void PercentageMaker(JFormattedTextField jf){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
//                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                formatter.setMinimum(0.0);
                formatter.setMaximum(100.00);
                return formatter;
            }
        });
       jf.setValue(new Double(0.00));
 }
 public static void PercentageMakerWithDefaultValue(JFormattedTextField jf,Double perc){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
//                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                formatter.setMinimum(0.0);
                formatter.setMaximum(100.00);
                return formatter;
            }
        });
       jf.setValue(perc);
 }
 public static  void NumberMaker(JFormattedTextField jf){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = NumberFormat.getInstance();
               
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                
                //formatter.setMinimum(0.0);
                //formatter.setMaximum(1000.00);
                return formatter;
            }
        });
      jf.setValue(new Integer(0));
 } 
 public static  void PhoneNumberMaker(JFormattedTextField jf){
      jf.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = NumberFormat.getInstance();
//                MaskFormatter format = new MaskFormatter("(###) ###-####");
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);
                
                //formatter.setMinimum(0.0);
                //formatter.setMaximum(1000.00);
                return formatter;
            }
        });
      jf.setValue(new Integer(0));
 } 
 
}
