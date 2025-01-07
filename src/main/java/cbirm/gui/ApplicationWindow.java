package cbirm.gui;

import static cbirm.helpers.ImageOperator.createImageIcon;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import cbirm.core.DateTimeSearcher;
import cbirm.core.ImageMatching;
import cbirm.core.LocationSearcher;
import cbirm.core.SemanticTagSearcher;
import cbirm.helpers.DataStructureOperator;
import cbirm.helpers.FileSystemOperator;
import cbirm.storage.ImageIndexHolder;

public class ApplicationWindow extends javax.swing.JFrame {
        public ApplicationWindow() {
                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }

                initComponents();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                btngrp_FilterFeatures = new javax.swing.ButtonGroup();
                panel_ButtonRow = new javax.swing.JPanel();
                btn_TabBrowse = new javax.swing.JButton();
                btn_TabAbout = new javax.swing.JButton();
                btn_TabExit = new javax.swing.JButton();
                btn_TabSearch = new javax.swing.JButton();
                btn_TabHelp = new javax.swing.JButton();
                panel_TabPanel = new javax.swing.JPanel();
                panel_TabBrowse = new javax.swing.JPanel();
                panel_BrowseFilter = new javax.swing.JPanel();
                panel_BrowseDatabaseFolder = new javax.swing.JPanel();
                tbx_BrowseDatabaseFolder = new javax.swing.JTextField();
                btn_BrowseBrowseDatabaseFolder = new javax.swing.JButton();
                panel_BrowseLocation = new javax.swing.JPanel();
                jspane_BrowseLocation = new javax.swing.JScrollPane();
                table_BrowseLocation = new javax.swing.JTable();
                panel_BrowseDateTime = new javax.swing.JPanel();
                jspane_BrowseDateTime = new javax.swing.JScrollPane();
                table_BrowseDateTime = new javax.swing.JTable();
                panel_BrowseSemanticTag = new javax.swing.JPanel();
                jspane_BrowseSemanticTag = new javax.swing.JScrollPane();
                table_BrowseSemanticTag = new javax.swing.JTable();
                panel_BrowseResult = new javax.swing.JPanel();
                jspane_BrowseResult = new javax.swing.JScrollPane();
                table_BrowseResult = new javax.swing.JTable();
                panel_TabSearch = new javax.swing.JPanel();
                panel_SearchFilters = new javax.swing.JPanel();
                panel_VisualFeatures = new javax.swing.JPanel();
                cbx_FeatureColorHistogram = new javax.swing.JCheckBox();
                cbx_FeatureColorCorrelogram = new javax.swing.JCheckBox();
                cbx_FeatureEdgeDirection = new javax.swing.JCheckBox();
                cbx_FeatureColorCoherenceVector = new javax.swing.JCheckBox();
                panel_TagFeature = new javax.swing.JPanel();
                tbx_FeatureSemanticTag = new javax.swing.JTextField();
                panel_SampleImage = new javax.swing.JPanel();
                btn_BrowseSampleImage = new javax.swing.JButton();
                label_QueryImage = new javax.swing.JLabel();
                btn_SearchReset = new javax.swing.JButton();
                btn_SearchStart = new javax.swing.JButton();
                panel_DatabaseFolder = new javax.swing.JPanel();
                tbx_DatabaseFolder = new javax.swing.JTextField();
                btn_BrowseDatabaseFolder = new javax.swing.JButton();
                btn_SearchSave = new javax.swing.JButton();
                panel_SearchResult = new javax.swing.JPanel();
                jspane_SearchResultTable = new javax.swing.JScrollPane();
                table_SearchResultTable = new javax.swing.JTable();
                panel_TabHelp = new javax.swing.JPanel();
                text_Help = new javax.swing.JTextArea();
                panel_TabAbout = new javax.swing.JPanel();
                text_About = new javax.swing.JTextArea();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("cbirm");

                btn_TabBrowse.setFont(new java.awt.Font("Cambria", 1, 18));
                btn_TabBrowse.setIcon(
                                new javax.swing.ImageIcon("img/btn_TabProfiles.png")); // NOI18N
                btn_TabBrowse.setText("Browse");
                btn_TabBrowse.setToolTipText("Browse and organize images");
                btn_TabBrowse.setIconTextGap(8);
                btn_TabBrowse.setMaximumSize(new java.awt.Dimension(80, 25));
                btn_TabBrowse.setMinimumSize(new java.awt.Dimension(80, 25));
                btn_TabBrowse.setPreferredSize(new java.awt.Dimension(80, 25));
                btn_TabBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_TabBrowseMouseClicked(evt);
                        }
                });

                btn_TabAbout.setFont(new java.awt.Font("Cambria", 1, 18));
                btn_TabAbout.setIcon(new javax.swing.ImageIcon("img/btn_TabAboutUs.png")); // NOI18N
                btn_TabAbout.setText("About");
                btn_TabAbout.setToolTipText("Information about the product and developemnt team");
                btn_TabAbout.setIconTextGap(8);
                btn_TabAbout.setMaximumSize(new java.awt.Dimension(80, 25));
                btn_TabAbout.setMinimumSize(new java.awt.Dimension(80, 25));
                btn_TabAbout.setPreferredSize(new java.awt.Dimension(80, 25));
                btn_TabAbout.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_TabAboutMouseClicked(evt);
                        }
                });

                btn_TabExit.setFont(new java.awt.Font("Cambria", 1, 18));
                btn_TabExit.setIcon(
                                new javax.swing.ImageIcon("img/btn_TabCloseProgram.png")); // NOI18N
                btn_TabExit.setText("Exit");
                btn_TabExit.setToolTipText("Close the program");
                btn_TabExit.setIconTextGap(8);
                btn_TabExit.setMaximumSize(new java.awt.Dimension(80, 25));
                btn_TabExit.setMinimumSize(new java.awt.Dimension(80, 25));
                btn_TabExit.setPreferredSize(new java.awt.Dimension(80, 25));
                btn_TabExit.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_TabExitMouseClicked(evt);
                        }
                });

                btn_TabSearch.setFont(new java.awt.Font("Cambria", 1, 18));
                btn_TabSearch.setIcon(
                                new javax.swing.ImageIcon("img/btn_TabSettings.png")); // NOI18N
                btn_TabSearch.setText("Search");
                btn_TabSearch.setToolTipText("Search for images using various filter patterns");
                btn_TabSearch.setIconTextGap(8);
                btn_TabSearch.setMaximumSize(new java.awt.Dimension(80, 25));
                btn_TabSearch.setMinimumSize(new java.awt.Dimension(80, 25));
                btn_TabSearch.setPreferredSize(new java.awt.Dimension(80, 25));
                btn_TabSearch.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_TabSearchMouseClicked(evt);
                        }
                });

                btn_TabHelp.setFont(new java.awt.Font("Cambria", 1, 18));
                btn_TabHelp.setIcon(new javax.swing.ImageIcon("img/btn_TabHelp.png")); // NOI18N
                btn_TabHelp.setText("Help");
                btn_TabHelp.setToolTipText("Read on using the program");
                btn_TabHelp.setIconTextGap(8);
                btn_TabHelp.setMaximumSize(new java.awt.Dimension(80, 25));
                btn_TabHelp.setMinimumSize(new java.awt.Dimension(80, 25));
                btn_TabHelp.setPreferredSize(new java.awt.Dimension(80, 25));
                btn_TabHelp.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_TabHelpMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panel_ButtonRowLayout = new javax.swing.GroupLayout(panel_ButtonRow);
                panel_ButtonRow.setLayout(panel_ButtonRowLayout);
                panel_ButtonRowLayout.setHorizontalGroup(
                                panel_ButtonRowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_ButtonRowLayout.createSequentialGroup()
                                                                .addComponent(btn_TabBrowse,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btn_TabSearch,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btn_TabHelp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btn_TabAbout,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btn_TabExit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                panel_ButtonRowLayout.setVerticalGroup(
                                panel_ButtonRowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_ButtonRowLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(btn_TabBrowse,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                60,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btn_TabSearch,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                60,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btn_TabHelp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                60,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btn_TabAbout,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                60,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btn_TabExit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                60,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                panel_TabPanel.setPreferredSize(new java.awt.Dimension(1200, 673));
                panel_TabPanel.setLayout(new java.awt.CardLayout());

                panel_BrowseFilter.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

                panel_BrowseDatabaseFolder.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createEtchedBorder(), "Database Folder",
                                javax.swing.border.TitledBorder.LEFT,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                tbx_BrowseDatabaseFolder.setEditable(false);
                tbx_BrowseDatabaseFolder.setToolTipText("Choose the image folder");

                btn_BrowseBrowseDatabaseFolder.setText("Browse");
                btn_BrowseBrowseDatabaseFolder.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_BrowseBrowseDatabaseFolderMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panel_BrowseDatabaseFolderLayout = new javax.swing.GroupLayout(
                                panel_BrowseDatabaseFolder);
                panel_BrowseDatabaseFolder.setLayout(panel_BrowseDatabaseFolderLayout);
                panel_BrowseDatabaseFolderLayout.setHorizontalGroup(
                                panel_BrowseDatabaseFolderLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn_BrowseBrowseDatabaseFolder,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 214,
                                                                Short.MAX_VALUE)
                                                .addGroup(panel_BrowseDatabaseFolderLayout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(tbx_BrowseDatabaseFolder,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                192,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                panel_BrowseDatabaseFolderLayout.setVerticalGroup(
                                panel_BrowseDatabaseFolderLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panel_BrowseDatabaseFolderLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(tbx_BrowseDatabaseFolder,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(btn_BrowseBrowseDatabaseFolder,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                panel_BrowseLocation
                                .setBorder(javax.swing.BorderFactory.createTitledBorder(
                                                javax.swing.BorderFactory.createEtchedBorder(),
                                                "Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                table_BrowseLocation.setAutoCreateRowSorter(true);
                table_BrowseLocation.setFont(new java.awt.Font("Cambria", 0, 16));
                table_BrowseLocation.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, null) {
                        Class[] types = new Class[] { java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                table_BrowseLocation.setFillsViewportHeight(true);
                table_BrowseLocation.setGridColor(new java.awt.Color(255, 255, 255));
                table_BrowseLocation.setShowHorizontalLines(false);
                table_BrowseLocation.setShowVerticalLines(false);
                jspane_BrowseLocation.setViewportView(table_BrowseLocation);
                table_BrowseLocation.getAccessibleContext().setAccessibleName("table_BrowseLocation");

                javax.swing.GroupLayout panel_BrowseLocationLayout = new javax.swing.GroupLayout(panel_BrowseLocation);
                panel_BrowseLocation.setLayout(panel_BrowseLocationLayout);
                panel_BrowseLocationLayout.setHorizontalGroup(
                                panel_BrowseLocationLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseLocationLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jspane_BrowseLocation,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                194,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                panel_BrowseLocationLayout.setVerticalGroup(
                                panel_BrowseLocationLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseLocationLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jspane_BrowseLocation,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                121,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                panel_BrowseDateTime
                                .setBorder(javax.swing.BorderFactory.createTitledBorder(
                                                javax.swing.BorderFactory.createEtchedBorder(),
                                                "Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                table_BrowseDateTime.setAutoCreateRowSorter(true);
                table_BrowseDateTime.setFont(new java.awt.Font("Cambria", 0, 16));
                table_BrowseDateTime.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, null) {
                        Class[] types = new Class[] { java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                table_BrowseDateTime.setFillsViewportHeight(true);
                table_BrowseDateTime.setGridColor(new java.awt.Color(255, 255, 255));
                table_BrowseDateTime.setShowHorizontalLines(false);
                table_BrowseDateTime.setShowVerticalLines(false);
                jspane_BrowseDateTime.setViewportView(table_BrowseDateTime);

                javax.swing.GroupLayout panel_BrowseDateTimeLayout = new javax.swing.GroupLayout(panel_BrowseDateTime);
                panel_BrowseDateTime.setLayout(panel_BrowseDateTimeLayout);
                panel_BrowseDateTimeLayout.setHorizontalGroup(
                                panel_BrowseDateTimeLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseDateTimeLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jspane_BrowseDateTime,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                194,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                panel_BrowseDateTimeLayout.setVerticalGroup(
                                panel_BrowseDateTimeLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseDateTimeLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jspane_BrowseDateTime,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                123,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                panel_BrowseSemanticTag
                                .setBorder(javax.swing.BorderFactory.createTitledBorder(
                                                javax.swing.BorderFactory.createEtchedBorder(),
                                                "Semantic Tags", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                table_BrowseSemanticTag.setAutoCreateRowSorter(true);
                table_BrowseSemanticTag.setFont(new java.awt.Font("Cambria", 0, 16));
                table_BrowseSemanticTag.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, null) {
                        Class[] types = new Class[] { java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                table_BrowseSemanticTag.setFillsViewportHeight(true);
                table_BrowseSemanticTag.setShowHorizontalLines(false);
                table_BrowseSemanticTag.setShowVerticalLines(false);
                jspane_BrowseSemanticTag.setViewportView(table_BrowseSemanticTag);

                javax.swing.GroupLayout panel_BrowseSemanticTagLayout = new javax.swing.GroupLayout(
                                panel_BrowseSemanticTag);
                panel_BrowseSemanticTag.setLayout(panel_BrowseSemanticTagLayout);
                panel_BrowseSemanticTagLayout.setHorizontalGroup(
                                panel_BrowseSemanticTagLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseSemanticTagLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jspane_BrowseSemanticTag,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                194,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                panel_BrowseSemanticTagLayout.setVerticalGroup(
                                panel_BrowseSemanticTagLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseSemanticTagLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jspane_BrowseSemanticTag,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                129,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                javax.swing.GroupLayout panel_BrowseFilterLayout = new javax.swing.GroupLayout(panel_BrowseFilter);
                panel_BrowseFilter.setLayout(panel_BrowseFilterLayout);
                panel_BrowseFilterLayout.setHorizontalGroup(
                                panel_BrowseFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panel_BrowseFilterLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(panel_BrowseFilterLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(panel_BrowseLocation,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                0,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(panel_BrowseSemanticTag,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(panel_BrowseDatabaseFolder,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(panel_BrowseDateTime,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap()));
                panel_BrowseFilterLayout.setVerticalGroup(
                                panel_BrowseFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BrowseFilterLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(panel_BrowseDatabaseFolder,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panel_BrowseLocation,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panel_BrowseDateTime,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panel_BrowseSemanticTag,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                panel_BrowseResult.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

                table_BrowseResult.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {},
                                new String[] { "", "", "", "", "" }) {
                        Class[] types = new Class[] {
                                        javax.swing.ImageIcon.class,
                                        javax.swing.ImageIcon.class,
                                        javax.swing.ImageIcon.class,
                                        javax.swing.ImageIcon.class,
                                        javax.swing.ImageIcon.class
                        };

                        boolean[] canEdit = new boolean[] {
                                        false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                table_BrowseResult.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                table_BrowseResult.setColumnSelectionAllowed(true);
                table_BrowseResult.setIntercellSpacing(new java.awt.Dimension(4, 4));
                table_BrowseResult.setRowHeight(110);
                table_BrowseResult.setShowHorizontalLines(false);
                table_BrowseResult.setShowVerticalLines(false);
                table_BrowseResult.getTableHeader().setReorderingAllowed(false);
                jspane_BrowseResult.setViewportView(table_BrowseResult);
                table_BrowseResult.getColumnModel().getSelectionModel()
                                .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

                javax.swing.GroupLayout panel_BrowseResultLayout = new javax.swing.GroupLayout(panel_BrowseResult);
                panel_BrowseResult.setLayout(panel_BrowseResultLayout);
                panel_BrowseResultLayout.setHorizontalGroup(
                                panel_BrowseResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jspane_BrowseResult, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                765, Short.MAX_VALUE));
                panel_BrowseResultLayout.setVerticalGroup(
                                panel_BrowseResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jspane_BrowseResult, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                685, Short.MAX_VALUE));

                javax.swing.GroupLayout panel_TabBrowseLayout = new javax.swing.GroupLayout(panel_TabBrowse);
                panel_TabBrowse.setLayout(panel_TabBrowseLayout);
                panel_TabBrowseLayout.setHorizontalGroup(
                                panel_TabBrowseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_TabBrowseLayout.createSequentialGroup()
                                                                .addComponent(panel_BrowseFilter,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panel_BrowseResult,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                panel_TabBrowseLayout.setVerticalGroup(
                                panel_TabBrowseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panel_BrowseFilter, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panel_BrowseResult, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                panel_TabPanel.add(panel_TabBrowse, "panel_TabBrowse");

                panel_TabSearch.setPreferredSize(new java.awt.Dimension(1200, 661));

                panel_SearchFilters.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

                panel_VisualFeatures.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createEtchedBorder(), "Visual Features",
                                javax.swing.border.TitledBorder.LEFT,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                cbx_FeatureColorHistogram.setText("Color Histogram");
                cbx_FeatureColorHistogram.setEnabled(false);

                cbx_FeatureColorCorrelogram.setText("Color Correlogram");
                cbx_FeatureColorCorrelogram.setEnabled(false);

                cbx_FeatureEdgeDirection.setText("Edge Direction");
                cbx_FeatureEdgeDirection.setEnabled(false);

                cbx_FeatureColorCoherenceVector.setText("Color Coherence Vector");
                cbx_FeatureColorCoherenceVector.setEnabled(false);

                javax.swing.GroupLayout panel_VisualFeaturesLayout = new javax.swing.GroupLayout(panel_VisualFeatures);
                panel_VisualFeatures.setLayout(panel_VisualFeaturesLayout);
                panel_VisualFeaturesLayout.setHorizontalGroup(
                                panel_VisualFeaturesLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_VisualFeaturesLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(panel_VisualFeaturesLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(cbx_FeatureEdgeDirection)
                                                                                .addComponent(cbx_FeatureColorCorrelogram)
                                                                                .addComponent(cbx_FeatureColorCoherenceVector)
                                                                                .addComponent(cbx_FeatureColorHistogram))
                                                                .addContainerGap(73, Short.MAX_VALUE)));
                panel_VisualFeaturesLayout.setVerticalGroup(
                                panel_VisualFeaturesLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_VisualFeaturesLayout.createSequentialGroup()
                                                                .addComponent(cbx_FeatureColorHistogram)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cbx_FeatureColorCoherenceVector)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cbx_FeatureColorCorrelogram)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cbx_FeatureEdgeDirection)));

                panel_TagFeature.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createEtchedBorder(), "Keyword",
                                javax.swing.border.TitledBorder.LEFT,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                tbx_FeatureSemanticTag.setToolTipText("Choose the image folder");

                javax.swing.GroupLayout panel_TagFeatureLayout = new javax.swing.GroupLayout(panel_TagFeature);
                panel_TagFeature.setLayout(panel_TagFeatureLayout);
                panel_TagFeatureLayout.setHorizontalGroup(
                                panel_TagFeatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 218, Short.MAX_VALUE)
                                                .addGroup(panel_TagFeatureLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(panel_TagFeatureLayout.createSequentialGroup()
                                                                                .addGap(12, 12, 12)
                                                                                .addComponent(tbx_FeatureSemanticTag,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                194,
                                                                                                Short.MAX_VALUE)
                                                                                .addGap(12, 12, 12))));
                panel_TagFeatureLayout.setVerticalGroup(
                                panel_TagFeatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 43, Short.MAX_VALUE)
                                                .addGroup(panel_TagFeatureLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(panel_TagFeatureLayout.createSequentialGroup()
                                                                                .addGap(8, 8, 8)
                                                                                .addComponent(tbx_FeatureSemanticTag,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(15,
                                                                                                Short.MAX_VALUE))));

                panel_SampleImage.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createEtchedBorder(), "Sample Image",
                                javax.swing.border.TitledBorder.LEFT,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
                panel_SampleImage.setPreferredSize(new java.awt.Dimension(218, 218));

                btn_BrowseSampleImage.setText("Browse");
                btn_BrowseSampleImage.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_BrowseSampleImageMouseClicked(evt);
                        }
                });

                label_QueryImage.setForeground(new java.awt.Color(255, 255, 255));
                label_QueryImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label_QueryImage.setIcon(new javax.swing.ImageIcon("img/blank.jpg")); // NOI18N
                label_QueryImage.setAlignmentY(0.0F);
                label_QueryImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                label_QueryImage.setIconTextGap(0);
                label_QueryImage.setMaximumSize(new java.awt.Dimension(173, 173));
                label_QueryImage.setMinimumSize(new java.awt.Dimension(173, 173));

                javax.swing.GroupLayout panel_SampleImageLayout = new javax.swing.GroupLayout(panel_SampleImage);
                panel_SampleImage.setLayout(panel_SampleImageLayout);
                panel_SampleImageLayout.setHorizontalGroup(
                                panel_SampleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn_BrowseSampleImage,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 218,
                                                                Short.MAX_VALUE)
                                                .addGroup(panel_SampleImageLayout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(label_QueryImage,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                185,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)));
                panel_SampleImageLayout.setVerticalGroup(
                                panel_SampleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panel_SampleImageLayout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(label_QueryImage,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                149,
                                                                                                Short.MAX_VALUE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(btn_BrowseSampleImage,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                label_QueryImage.getAccessibleContext().setAccessibleName("label_QueryImage");

                btn_SearchReset.setText("Reset");
                btn_SearchReset.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_SearchResetMouseClicked(evt);
                        }
                });

                btn_SearchStart.setText("Start");
                btn_SearchStart.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_SearchStartMouseClicked(evt);
                        }
                });

                panel_DatabaseFolder.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                javax.swing.BorderFactory.createEtchedBorder(), "Database Folder",
                                javax.swing.border.TitledBorder.LEFT,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

                tbx_DatabaseFolder.setText("D:\\Temporary\\images");
                tbx_DatabaseFolder.setToolTipText("Choose the image folder");

                btn_BrowseDatabaseFolder.setText("Browse");
                btn_BrowseDatabaseFolder.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_BrowseDatabaseFolderMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panel_DatabaseFolderLayout = new javax.swing.GroupLayout(panel_DatabaseFolder);
                panel_DatabaseFolder.setLayout(panel_DatabaseFolderLayout);
                panel_DatabaseFolderLayout.setHorizontalGroup(
                                panel_DatabaseFolderLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn_BrowseDatabaseFolder,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 218,
                                                                Short.MAX_VALUE)
                                                .addGroup(panel_DatabaseFolderLayout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(tbx_DatabaseFolder,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                196,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                panel_DatabaseFolderLayout.setVerticalGroup(
                                panel_DatabaseFolderLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panel_DatabaseFolderLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(tbx_DatabaseFolder,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(btn_BrowseDatabaseFolder,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                btn_SearchSave.setText("Save");
                btn_SearchSave.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btn_SearchSaveMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panel_SearchFiltersLayout = new javax.swing.GroupLayout(panel_SearchFilters);
                panel_SearchFilters.setLayout(panel_SearchFiltersLayout);
                panel_SearchFiltersLayout.setHorizontalGroup(
                                panel_SearchFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panel_SearchFiltersLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(panel_SearchFiltersLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(panel_VisualFeatures,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                panel_SearchFiltersLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(btn_SearchReset,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                68,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(btn_SearchSave,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                68,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(btn_SearchStart,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                68,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addComponent(panel_SampleImage,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                230,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(panel_TagFeature,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(panel_DatabaseFolder,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap()));
                panel_SearchFiltersLayout.setVerticalGroup(
                                panel_SearchFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panel_SearchFiltersLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(panel_DatabaseFolder,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                59,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(panel_TagFeature,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(panel_SampleImage,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                236,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(panel_VisualFeatures,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(panel_SearchFiltersLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(btn_SearchReset,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(btn_SearchSave,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(btn_SearchStart,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addContainerGap()));

                panel_SampleImage.getAccessibleContext().setAccessibleName("panel_SampleImage");

                panel_SearchResult.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

                table_SearchResultTable.setAutoCreateRowSorter(true);
                table_SearchResultTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {},
                                new String[] { "Preview", "Filename", "Tags", "Place Taken", "Date Taken" }) {
                        Class[] types = new Class[] {
                                        javax.swing.ImageIcon.class,
                                        java.lang.String.class,
                                        java.lang.String.class,
                                        java.lang.String.class,
                                        java.lang.String.class
                        };

                        boolean[] canEdit = new boolean[] {
                                        false, false, true, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                table_SearchResultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                table_SearchResultTable.setColumnSelectionAllowed(true);
                table_SearchResultTable.setIntercellSpacing(new java.awt.Dimension(4, 4));
                table_SearchResultTable.setRowHeight(120);
                table_SearchResultTable.setShowVerticalLines(false);
                table_SearchResultTable.getTableHeader().setReorderingAllowed(false);
                jspane_SearchResultTable.setViewportView(table_SearchResultTable);
                table_SearchResultTable.getColumnModel().getSelectionModel()
                                .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

                javax.swing.GroupLayout panel_SearchResultLayout = new javax.swing.GroupLayout(panel_SearchResult);
                panel_SearchResult.setLayout(panel_SearchResultLayout);
                panel_SearchResultLayout.setHorizontalGroup(
                                panel_SearchResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jspane_SearchResultTable,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 772,
                                                                Short.MAX_VALUE));
                panel_SearchResultLayout.setVerticalGroup(
                                panel_SearchResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jspane_SearchResultTable,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 685,
                                                                Short.MAX_VALUE));

                javax.swing.GroupLayout panel_TabSearchLayout = new javax.swing.GroupLayout(panel_TabSearch);
                panel_TabSearch.setLayout(panel_TabSearchLayout);
                panel_TabSearchLayout.setHorizontalGroup(
                                panel_TabSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_TabSearchLayout.createSequentialGroup()
                                                                .addComponent(panel_SearchFilters,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panel_SearchResult,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                panel_TabSearchLayout.setVerticalGroup(
                                panel_TabSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panel_SearchFilters, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panel_SearchResult,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                panel_TabPanel.add(panel_TabSearch, "panel_TabSearch");

                text_Help.setColumns(20);
                text_Help.setRows(5);
                text_Help.setText("Yet to implemented. Please help yourself!");

                javax.swing.GroupLayout panel_TabHelpLayout = new javax.swing.GroupLayout(panel_TabHelp);
                panel_TabHelp.setLayout(panel_TabHelpLayout);
                panel_TabHelpLayout.setHorizontalGroup(
                                panel_TabHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_TabHelpLayout.createSequentialGroup()
                                                                .addGap(0, 12, Short.MAX_VALUE)
                                                                .addComponent(text_Help,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                926,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 87, Short.MAX_VALUE)));
                panel_TabHelpLayout.setVerticalGroup(
                                panel_TabHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_TabHelpLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(text_Help,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                659,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 28, Short.MAX_VALUE)));

                panel_TabPanel.add(panel_TabHelp, "panel_TabHelp");

                text_About.setColumns(20);
                text_About.setRows(5);
                text_About.setText(
                                "Content-based Image Retrieval and Management (CBIRM)\n\nDeveloped by CS4341 Team 03:\n\nHuang Zhilong\nLe Xuan Bach\nTran Binh Nguyen");

                javax.swing.GroupLayout panel_TabAboutLayout = new javax.swing.GroupLayout(panel_TabAbout);
                panel_TabAbout.setLayout(panel_TabAboutLayout);
                panel_TabAboutLayout.setHorizontalGroup(
                                panel_TabAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(text_About, javax.swing.GroupLayout.DEFAULT_SIZE, 1025,
                                                                Short.MAX_VALUE));
                panel_TabAboutLayout.setVerticalGroup(
                                panel_TabAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(text_About, javax.swing.GroupLayout.DEFAULT_SIZE, 687,
                                                                Short.MAX_VALUE));

                panel_TabPanel.add(panel_TabAbout, "panel_TabAbout");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(panel_ButtonRow,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(panel_TabPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                1025,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(panel_ButtonRow,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panel_TabPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                687,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btn_SearchSaveMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_SearchSaveMouseClicked
                String filePath = table_SearchResultTable.getModel()
                                .getValueAt(table_SearchResultTable
                                                .convertRowIndexToModel(table_SearchResultTable.getSelectedRow()), 1)
                                .toString();
                String fileTags = table_SearchResultTable.getModel()
                                .getValueAt(table_SearchResultTable
                                                .convertRowIndexToModel(table_SearchResultTable.getSelectedRow()), 2)
                                .toString();

                cbirm.core.SemanticTag.saveImageIndex(filePath, fileTags.split(" "));
                ImageIndexHolder.getInstance().finalizeStorage();
        }// GEN-LAST:event_btn_SearchSaveMouseClicked

        //
        // Main button row
        //

        private void btn_TabBrowseMouseClicked(MouseEvent evt) {

                CardLayout cl = (CardLayout) panel_TabPanel.getLayout();
                cl.show(panel_TabPanel, "panel_TabBrowse");
        }

        private void btn_TabSearchMouseClicked(MouseEvent evt) {
                resetSearchInfo();
                CardLayout cl = (CardLayout) panel_TabPanel.getLayout();
                cl.show(panel_TabPanel, "panel_TabSearch");
        }

        private void btn_TabHelpMouseClicked(MouseEvent evt) {
                CardLayout cl = (CardLayout) panel_TabPanel.getLayout();
                cl.show(panel_TabPanel, "panel_TabHelp");
        }

        private void btn_TabAboutMouseClicked(MouseEvent evt) {
                CardLayout cl = (CardLayout) panel_TabPanel.getLayout();
                cl.show(panel_TabPanel, "panel_TabAbout");
        }

        private void btn_TabExitMouseClicked(MouseEvent evt) {
                dispose();
                System.exit(0);
        }

        //
        // tabSearch
        //

        private void btn_BrowseDatabaseFolderMouseClicked(MouseEvent evt) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        try {
                                tbx_DatabaseFolder.setText(chooser.getSelectedFile().getCanonicalPath());
                        } catch (IOException e) {
                        }
                }

        }

        private void btn_BrowseSampleImageMouseClicked(MouseEvent evt) {
                JFileChooser chooser = new JFileChooser(tbx_DatabaseFolder.getText().trim());
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.setFileFilter(FileSystemOperator.createChooserFileFilter("jpg"));

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        try {
                                String queryImage = chooser.getSelectedFile().getCanonicalPath();
                                label_QueryImage.setIcon(createImageIcon(queryImage));
                                label_QueryImage.setText(queryImage);

                                // Enable visual features
                                cbx_FeatureColorHistogram.setEnabled(true);
                                cbx_FeatureColorCorrelogram.setEnabled(true);
                                cbx_FeatureColorCoherenceVector.setEnabled(true);
                                cbx_FeatureEdgeDirection.setEnabled(true);
                        } catch (IOException ex) {
                        }
                }
        }

        private void btn_SearchStartMouseClicked(MouseEvent evt) {
                // Read query info
                String searchFolder = tbx_DatabaseFolder.getText().trim();
                // Normalize search folder by adding trailing \\
                if (!searchFolder.endsWith("\\"))
                        searchFolder = searchFolder + "\\";

                String keywords = tbx_FeatureSemanticTag.getText().trim();
                // Normalize keywords by removing extra spaces
                while (keywords.contains("  "))
                        keywords = keywords.replace("  ", " ");

                String queryImage = label_QueryImage.getText().trim();

                // Construct featureVector
                boolean[] featureVector = new boolean[5];
                featureVector[0] = cbx_FeatureColorHistogram.isSelected();
                featureVector[1] = cbx_FeatureColorCoherenceVector.isSelected();
                featureVector[2] = cbx_FeatureColorCorrelogram.isSelected();
                featureVector[3] = cbx_FeatureEdgeDirection.isSelected();
                featureVector[4] = (!keywords.equals(""));

                // Check for valid info
                if (searchFolder.equals("") || !(new File(searchFolder)).exists()) {
                        showMessageDialog(null, "Please choose a valid image folder!");
                        return;
                }

                if (queryImage.equals("")
                                && Arrays.equals(featureVector, new boolean[] { false, false, false, false, false })) {
                        showMessageDialog(null, "Please choose at least one feature to search!");
                        return;
                }

                if (!queryImage.equals("")
                                && ((Arrays.equals(featureVector, new boolean[] { false, false, false, false, true }))
                                                || (Arrays.equals(featureVector, new boolean[] { false, false, false,
                                                                false, false })))) {
                        showMessageDialog(null, "Please choose at lease one visual feature to search!");
                        return;
                }

                // Do search or feedback
                String[] fileDatabase = FileSystemOperator.getListOfFiles(searchFolder);
                List<String> resultTable;

                try {
                        if (searchState == SearchState.SEARCH) {
                                resultTable = ImageMatching.search(queryImage, keywords, fileDatabase, featureVector);

                                searchState = SearchState.FEEDBACK;
                                btn_SearchStart.setText("Feedback");
                        } else // (searchState == SearchState.FEEDBACK)
                        {
                                String feedbackImage = searchFolder
                                                + table_SearchResultTable.getModel()
                                                                .getValueAt(table_SearchResultTable
                                                                                .convertRowIndexToModel(
                                                                                                table_SearchResultTable
                                                                                                                .getSelectedRow()),
                                                                                1)
                                                                .toString();
                                resultTable = ImageMatching.relevantFeedbackHandler(queryImage, feedbackImage, keywords,
                                                fileDatabase,
                                                featureVector);
                        }

                        // Convert resultTable to Object[][] to display
                        System.out.println("DEBUG: Converting resultTable to object[][]...");
                        Object[][] searchResultData = DataStructureOperator
                                        .ListToSearchResultArray(resultTable);

                        // Output result
                        DefaultTableModel dtm = (DefaultTableModel) table_SearchResultTable.getModel();
                        dtm.setDataVector(searchResultData,
                                        new String[] { "P", "Filename", "Tags", "Place taken", "Date taken" });

                        // Finalize storage
                        System.out.println("DEBUG: Finalizing storage...");
                        ImageIndexHolder.getInstance().finalizeStorage();
                } catch (Exception e) {
                        System.out.println("Exception in btn_SearchStartMouseClicked()");
                }
        }

        private void btn_SearchResetMouseClicked(MouseEvent evt) {
                resetSearchInfo();
        }

        private void resetSearchInfo() {
                // Garbage collect old table data
                DefaultTableModel oldDTM = (DefaultTableModel) table_SearchResultTable.getModel();
                oldDTM.setDataVector(oldDTM.getDataVector(), null);
                oldDTM.setNumRows(0);

                // Reset search state
                searchState = SearchState.SEARCH;

                // Reset search info
                tbx_DatabaseFolder.setText("D:\\Temporary\\images");
                tbx_FeatureSemanticTag.setText("");
                label_QueryImage.setText("");
                label_QueryImage.setIcon(null);
                cbx_FeatureColorHistogram.setSelected(false);
                cbx_FeatureColorCoherenceVector.setSelected(false);
                cbx_FeatureColorCorrelogram.setSelected(false);
                cbx_FeatureEdgeDirection.setSelected(false);
                cbx_FeatureColorHistogram.setEnabled(false);
                cbx_FeatureColorCoherenceVector.setEnabled(false);
                cbx_FeatureColorCorrelogram.setEnabled(false);
                cbx_FeatureEdgeDirection.setEnabled(false);
                btn_SearchStart.setText("Start");
        }

        //
        // tabBrowse
        //

        private void btn_BrowseBrowseDatabaseFolderMouseClicked(MouseEvent evt) {
                JFileChooser chooser = new JFileChooser("D:\\Temporary\\images");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        try {
                                String browseFolder = chooser.getSelectedFile().getCanonicalPath();
                                tbx_BrowseDatabaseFolder.setText(browseFolder);

                                // Check for valid info
                                if (browseFolder.equals("") || !(new File(browseFolder)).exists()) {
                                        showMessageDialog(null, "Please choose a valid image folder!");
                                        return;
                                }

                                final String[] fileDatabase = FileSystemOperator.getListOfFiles(browseFolder);

                                // Initialize locations/tags/datetime
                                Object[][] locationData = DataStructureOperator
                                                .ListTo2DArray((new LocationSearcher(fileDatabase))
                                                                .getListOfLocations());
                                Object[][] datetimeData = DataStructureOperator
                                                .ListTo2DArray((new DateTimeSearcher(fileDatabase))
                                                                .getListOfDateTime());
                                Object[][] semantictagData = DataStructureOperator
                                                .ListTo2DArray((new SemanticTagSearcher(fileDatabase))
                                                                .getListOfTags());

                                displayBrowseFilter(table_BrowseLocation, locationData);
                                displayBrowseFilter(table_BrowseDateTime, datetimeData);
                                displayBrowseFilter(table_BrowseSemanticTag, semantictagData);

                                // Add mouse click event to tables
                                table_BrowseLocation.addMouseListener(new MouseAdapter() {
                                        public void mouseClicked(MouseEvent e) {
                                                JTable target = (JTable) e.getSource();
                                                int row = target.getSelectedRow();
                                                int column = target.getSelectedColumn();

                                                // Query and display result
                                                List<String> result = ImageMatching
                                                                .searchLocation(target.getModel()
                                                                                .getValueAt(row, column).toString(),
                                                                                fileDatabase);
                                                displayBrowseResult(result);

                                                // Finalize storage
                                                ImageIndexHolder.getInstance().finalizeStorage();
                                        }
                                });

                                table_BrowseDateTime.addMouseListener(new MouseAdapter() {
                                        public void mouseClicked(MouseEvent e) {
                                                JTable target = (JTable) e.getSource();
                                                int row = target.getSelectedRow();
                                                int column = target.getSelectedColumn();

                                                // Query and display result
                                                List<String> result = ImageMatching
                                                                .searchDateTime(target.getModel()
                                                                                .getValueAt(row, column).toString(),
                                                                                fileDatabase);
                                                displayBrowseResult(result);

                                                // Finalize storage
                                                ImageIndexHolder.getInstance().finalizeStorage();
                                        }
                                });

                                table_BrowseSemanticTag.addMouseListener(new MouseAdapter() {
                                        public void mouseClicked(MouseEvent e) {
                                                JTable target = (JTable) e.getSource();
                                                int row = target.getSelectedRow();
                                                int column = target.getSelectedColumn();

                                                // Query and display result
                                                List<String> result = ImageMatching.searchExactSemanticTag(
                                                                target.getModel().getValueAt(row, column).toString(),
                                                                fileDatabase);
                                                displayBrowseResult(result);

                                                // Finalize storage
                                                ImageIndexHolder.getInstance().finalizeStorage();
                                        }
                                });
                        } catch (Exception e) {
                        }
                }
        }

        private void displayBrowseFilter(JTable table, Object[][] data) {
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                dtm.setDataVector(data, new String[] { "" });
        }

        private void displayBrowseResult(List<String> result) {
                // Convert to Object[][]
                Object[][] data = (new DataStructureOperator()).ListTo2DImageArray(result);

                // Output result
                DefaultTableModel dtm = (DefaultTableModel) table_BrowseResult.getModel();
                dtm.setDataVector(data, new String[] { "", "", "", "", "" });
        }

        //
        // Private filed declaration
        //

        private enum SearchState {
                SEARCH,
                FEEDBACK
        }

        private SearchState searchState;

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn_BrowseBrowseDatabaseFolder;
        private javax.swing.JButton btn_BrowseDatabaseFolder;
        private javax.swing.JButton btn_BrowseSampleImage;
        private javax.swing.JButton btn_SearchReset;
        private javax.swing.JButton btn_SearchSave;
        private javax.swing.JButton btn_SearchStart;
        private javax.swing.JButton btn_TabAbout;
        private javax.swing.JButton btn_TabBrowse;
        private javax.swing.JButton btn_TabExit;
        private javax.swing.JButton btn_TabHelp;
        private javax.swing.JButton btn_TabSearch;
        private javax.swing.ButtonGroup btngrp_FilterFeatures;
        private javax.swing.JCheckBox cbx_FeatureColorCoherenceVector;
        private javax.swing.JCheckBox cbx_FeatureColorCorrelogram;
        private javax.swing.JCheckBox cbx_FeatureColorHistogram;
        private javax.swing.JCheckBox cbx_FeatureEdgeDirection;
        private javax.swing.JScrollPane jspane_BrowseDateTime;
        private javax.swing.JScrollPane jspane_BrowseLocation;
        private javax.swing.JScrollPane jspane_BrowseResult;
        private javax.swing.JScrollPane jspane_BrowseSemanticTag;
        private javax.swing.JScrollPane jspane_SearchResultTable;
        private javax.swing.JLabel label_QueryImage;
        private javax.swing.JPanel panel_BrowseDatabaseFolder;
        private javax.swing.JPanel panel_BrowseDateTime;
        private javax.swing.JPanel panel_BrowseFilter;
        private javax.swing.JPanel panel_BrowseLocation;
        private javax.swing.JPanel panel_BrowseResult;
        private javax.swing.JPanel panel_BrowseSemanticTag;
        private javax.swing.JPanel panel_ButtonRow;
        private javax.swing.JPanel panel_DatabaseFolder;
        private javax.swing.JPanel panel_SampleImage;
        private javax.swing.JPanel panel_SearchFilters;
        private javax.swing.JPanel panel_SearchResult;
        private javax.swing.JPanel panel_TabAbout;
        private javax.swing.JPanel panel_TabBrowse;
        private javax.swing.JPanel panel_TabHelp;
        private javax.swing.JPanel panel_TabPanel;
        private javax.swing.JPanel panel_TabSearch;
        private javax.swing.JPanel panel_TagFeature;
        private javax.swing.JPanel panel_VisualFeatures;
        private javax.swing.JTable table_BrowseDateTime;
        private javax.swing.JTable table_BrowseLocation;
        private javax.swing.JTable table_BrowseResult;
        private javax.swing.JTable table_BrowseSemanticTag;
        private javax.swing.JTable table_SearchResultTable;
        private javax.swing.JTextField tbx_BrowseDatabaseFolder;
        private javax.swing.JTextField tbx_DatabaseFolder;
        private javax.swing.JTextField tbx_FeatureSemanticTag;
        private javax.swing.JTextArea text_About;
        private javax.swing.JTextArea text_Help;
        // End of variables declaration//GEN-END:variables

}
