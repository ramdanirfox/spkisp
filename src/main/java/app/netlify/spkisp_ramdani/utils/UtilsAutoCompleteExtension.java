/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;
import app.netlify.spkisp_ramdani.forms.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UtilsAutoCompleteExtension {
    JTextField city = new JTextField(10);
    String enteredName = null;
    String[] cities = {"Telkom sel","Indihome",
            "Infomedia","essex","london","delhi","new york"};
    JList list = new JList();
    JScrollPane pane = new JScrollPane();
    ResultWindow r = new ResultWindow();
//------------------------------------------------------------------------------
//    public static void main(JTextField textField) {
//        new UtilsAutoCompleteExtension(textField);
//    }
//------------------------------------------------------------------------------
    public UtilsAutoCompleteExtension(JTextField textField, String[] opsi){
        city = textField;
        city.addKeyListener(new TextHandler());
        city.addMouseListener(new FieldHandler());
        list.addListSelectionListener(new ListHandler());
        cities = opsi;
    }
//------------------------------------------------------------------------------
    public void initiateSearch(String lookFor){
        Vector<String> matches = new Vector<>();
        lookFor = lookFor.toLowerCase();
        for(String each : cities){
            String eachLower = each.toLowerCase();
            if(eachLower.contains(lookFor)){
                matches.add(each);
                System.out.println("Match: " + each);
            }
        }
//        this.repaint();

        if(matches.size()!=0){
            list.setListData(matches);
            r.searchResult = list;
            r.pane = pane;
            r.initiateDisplay();
        }else{
            matches.add("No Match Found");
            list.setListData(matches);
            r.searchResult = list;
            r.pane = pane;
            r.initiateDisplay();
        }

    }
//------------------------------------------------------------------------------
    public class ResultWindow extends JWindow{
        public JScrollPane pane;
        public JList searchResult;
//------------------------------------------------------------------------------
        public ResultWindow(){
            
        }
//------------------------------------------------------------------------------
        public void initiateDisplay(){
            pane.setViewportView(searchResult);
            add(pane);
            pack();
            
//            this.setLocation(AutoCompleteTest.this.getX() + 2, 
//                    AutoCompleteTest.this.getY()+
//                    AutoCompleteTest.this.getHeight());
            
            this.setLocation(city.getLocationOnScreen().x + 2, 
                    city.getLocationOnScreen().y +
                    city.getHeight());

//          this.setPreferredSize(city.getPreferredSize());
            this.setVisible(true);
        }
    }
//------------------------------------------------------------------------------

    class TextHandler implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e){

        }

        @Override
        public void keyPressed(KeyEvent e){
            
        }

        @Override
        public void keyReleased(KeyEvent e){
            if(r.isVisible()){
                r.setVisible(false);
            }
//            if(e.getKeyChar() == '\n'){
                initiateSearch(city.getText());
//            }
        }
    }
    
    class ListHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (list.getSelectedValue() != null) {
                city.setText(list.getSelectedValue().toString());
                r.setVisible(false);
            }
        }
        
    }
    
    class FieldHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Handle mouse click event here
            System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
            if(r.isVisible()){
                r.setVisible(false);
            }
            else {
                initiateSearch(city.getText());
                r.initiateDisplay();
                r.setVisible(true);
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
        
        }
    }
//------------------------------------------------------------------------------
}
/**
 *
 * @author iramd
 */
