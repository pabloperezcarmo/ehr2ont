/*
 *CISEP - An intelligent clinical record to improve patient security
 *
 *Copyright (c) 2007-2008, Information Eng. Research Unit - Univ. of Alcalá
 *http://www.cc.uah.es/ie
 *
 *This library is free software; you can redistribute it and/or modify it under
 *the terms of the GNU Lesser General Public License as published by the Free
 *Software Foundation; either version 2.1 of the License, or (at your option)
 *any later version.
 *This library is distributed in the hope that it will be useful, but WITHOUT
 *ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 *details.
 *You should have received a copy of the GNU Lesser General Public License along
 *with this library; if not, write to the Free Software Foundation, Inc.,
 *59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
/*
 * ADLOntologizerGUI.java
 *
 * Created on 5 de febrero de 2008, 19:12
 */

package es.uah.cc.ie.ehr2ont.GUI;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import es.uah.cc.ie.ehr2ont.parser.ArchetypeUtils;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import es.uah.cc.ie.ehr2ont.translator.OpenEHR2OwlTranslator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import se.acode.openehr.parser.ADLParser;
import se.acode.openehr.parser.ParseException;

/**
 * Application GUI allows users to select input adl file and start translation 
 * process
 * @author  Leonardo Lezcano
 */
public class ADLOntologizerGUI extends javax.swing.JFrame {
    
    /** Creates new form ADLOntologizerGUI */
    Archetype selectedArc = null;
    public ADLOntologizerGUI() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAdlFileChooser = new javax.swing.JFileChooser();
        jAdlFileChooser.setFileFilter(new ADLFilter());
        jInfoLabel = new javax.swing.JLabel();
        transjButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton_spa = new javax.swing.JRadioButton();
        jRadioButton_eng = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Archetype Ontologizer");

        jAdlFileChooser.setControlButtonsAreShown(false);
        jAdlFileChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jAdlFileChooserPropertyChange(evt);
            }
        });

        jInfoLabel.setFont(new java.awt.Font("Tahoma", 3, 12));
        jInfoLabel.setForeground(new java.awt.Color(0, 204, 0));

        transjButton.setText("Convert");
        transjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transjButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Selected Archetype Concept:");

        jRadioButton_spa.setText("Spanish terms");
        jRadioButton_spa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_spaActionPerformed(evt);
            }
        });

        jRadioButton_eng.setSelected(true);
        jRadioButton_eng.setText("English terms");
        jRadioButton_eng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_engActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAdlFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(transjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_spa)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton_eng)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jInfoLabel)))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jAdlFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transjButton)
                    .addComponent(jRadioButton_eng)
                    .addComponent(jLabel1)
                    .addComponent(jInfoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton_spa))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAdlFileChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jAdlFileChooserPropertyChange
        String changeName = evt.getPropertyName();
        if (changeName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
        { 
                File selectedFile = (File) evt.getNewValue();
                if (selectedFile != null && !selectedFile.isDirectory()) 
                    this.SetADLInfo(selectedFile); 
        }
    }//GEN-LAST:event_jAdlFileChooserPropertyChange

    private void transjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transjButtonActionPerformed
        
        //Missing Implementation of RM ontology path definition interface
        String directory = "./Repository/OpenEHROntos";        
        String ontology = "./Repository/CISEPOnto/CISEPonto.owl";
        //String ontology = "./Repository/OpenEHROntos/EHR_RM.owl";
        //////
        JenaModelWrapper om = new JenaModelWrapper(directory,  ontology);
        
        //if(selectedArc = null)
            //throw new Exception or messageBox
        OpenEHR2OwlTranslator tlr = new OpenEHR2OwlTranslator(selectedArc, om);
        tlr.Translate();
    }//GEN-LAST:event_transjButtonActionPerformed

    private void jRadioButton_engActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_engActionPerformed
        jRadioButton_eng.setSelected(true);
        jRadioButton_spa.setSelected(false);
        ArchetypeUtils.idiom = "en";
               
        System.out.println(ArchetypeUtils.idiom);
    }//GEN-LAST:event_jRadioButton_engActionPerformed

    private void jRadioButton_spaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_spaActionPerformed
        jRadioButton_eng.setSelected(false);
        jRadioButton_spa.setSelected(true);
        ArchetypeUtils.idiom = "es";
               
        System.out.println(ArchetypeUtils.idiom);
    }//GEN-LAST:event_jRadioButton_spaActionPerformed
    
  
    private void SetADLInfo(File adlFile)
    {
       // this.jInfoLabel.setText(adlFile.getName());
        
        
        try {
            
            FileInputStream fileIn = new FileInputStream(adlFile);
            InputStreamReader strReader = new InputStreamReader(fileIn);
            
            ADLParser adlp =new ADLParser(strReader);
            //ADLParser adlp =new ADLParser(adlFile);        
            selectedArc = adlp.parse();  
               
           ArchetypeOntology ao = selectedArc.getOntology();
           List<OntologyDefinitions> odefs = ao.getTermDefinitionsList();
           Iterator it = odefs.iterator();
           while (it.hasNext())
           {
               OntologyDefinitions od = (OntologyDefinitions)it.next();               
               if (od.getLanguage().equals(ArchetypeUtils.idiom))
               {
                   List<ArchetypeTerm> aterms = od.getDefinitions();
                   Iterator it2 = aterms.iterator();
                   while (it2.hasNext())
                   {
                       ArchetypeTerm at =(ArchetypeTerm)it2.next();
                       if (at.getCode().equals(selectedArc.getConcept()))
                       {
                           this.jInfoLabel.setText(at.getItem("text"));
                           //System.out.println(at.getItem("text"));
                           break;
                       }                           
                   }
               }
               break;              
           }
           
           
    
                
            } catch (IOException ex) {
                Logger.getLogger(ADLOntologizerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }  catch (ParseException ex) {
                Logger.getLogger(ADLOntologizerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADLOntologizerGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jAdlFileChooser;
    private javax.swing.JLabel jInfoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton_eng;
    private javax.swing.JRadioButton jRadioButton_spa;
    private javax.swing.JButton transjButton;
    // End of variables declaration//GEN-END:variables
    
}
