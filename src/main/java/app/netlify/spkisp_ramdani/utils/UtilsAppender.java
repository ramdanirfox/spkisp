/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 *
 * @author iramd
 */
public class UtilsAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent e) {
        System.out.println(e.getLevel() + " --- " + e.getMessage());
        UtilsStatic.appendLogData(e.getMessage());
    }
    
}
