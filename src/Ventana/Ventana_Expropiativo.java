/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import DAO.daoPCB_Expro;
import DAO.daoProceso;
import DTO.PairFCFS;
import DTO.PairRR;
import DTO.PairSJF;
import DTO.PairTiempo;
import DTO.dtoProceso;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import javax.swing.JFrame;
import javax.swing.JTable;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 *
 * @author User
 */
public class Ventana_Expropiativo extends JFrame {

    /**
     * Creates new form Ventana_No_Expropiativo
     */
    public static boolean CambioCola = false;
    public static int Contador = 1; //numero total de procesos ingresados
    public static int Tamano;
    public static int BurstTime;
    public static int Quantum = -1;//Carga el quantum en ejecución
    //interrupciones
    public static int ContadorInterrupciones_FCFS = 0;
    public static int ContadorInterrupciones_SJF = 0;
    public static int ContadorInterrupciones_RR = 0;
    public static boolean primera = true;//para interrupciones y procesos
    private static double contadorTerminados_FCFS = 0;
    private static double contadorListos_FCFS = 0;
    private static double contadorEjecutando_FCFS = 0;
    private static double contadorTerminados_SJF;
    private static int contadorEjecutando_SJF;
    private static int contadorListos_SJF;
    private static int contadorEjecutando_RR;
    private static int contadorListos_RR;
    private static double contadorTerminados_RR;
    private static double contadorBloqueados_SJF;
    private static double contadorBloqueados_RR;

    public Thread principal = new Thread(new Hilo());
    private static double contadorTotal_FCFS = 0;

    public Ventana_Expropiativo() {
        initComponents();
        System.out.println("run");
        double[][] data = {{0}, {0}, {0}, {0}};
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Estado", "", data);

        barChart_FCFS = ChartFactory.createBarChart("", "Proceso", "Progreso", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        barChart_SJF = ChartFactory.createBarChart("", "Proceso", "Progreso", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        barChart_RR = ChartFactory.createBarChart("", "Proceso", "Progreso", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        refrescarTablaContadores(1);
        refrescarTablaContadores(2);
        refrescarTablaContadores(3);
    }

    public static JFreeChart barChart_FCFS;
    public static JFreeChart barChart_SJF;
    public static JFreeChart barChart_RR;
    public Queue< PairFCFS> FCFS = new LinkedList();
    public PriorityQueue< PairSJF> SJF = new PriorityQueue<>();
    public ArrayList<PairRR> RR = new ArrayList<PairRR>();
    public ArrayList<dtoProceso> ProcesosFCFS = new ArrayList<dtoProceso>();
    public ArrayList<dtoProceso> ProcesosSJF = new ArrayList<dtoProceso>();
    public ArrayList<dtoProceso> ProcesosRR = new ArrayList<dtoProceso>();
    public ArrayList<PairTiempo> DuracionFCFS = new ArrayList<PairTiempo>();
    public ArrayList<PairTiempo> DuracionSJF = new ArrayList<PairTiempo>();
    public ArrayList<PairTiempo> DuracionRR = new ArrayList<PairTiempo>();
    public int cntFCFS = 1;
    public int cntSJF = 1;
    public int cntRR = 1;
    public int IdProcesoFCFS = 1;
    public int IdProcesoSJF = 1;
    public int IdProcesoRR = 1;
    public int TimeTot = 0;
    public PairRR UltimoRR;
    public Estadisticas VentanaEstadisticas = new Estadisticas();
    public int T_INI_FCFS = 0;
    public int cnt_INI_FCFS = 0;
    public int T_FIN_FCFS = 0;
    public int cnt_FIN_FCFS = 0;
    public int T_INI_SJF = 0;
    public int cnt_INI_SJF = 0;
    public int T_FIN_SJF = 0;
    public int cnt_FIN_SJF = 0;
    public int T_INI_RR = 0;
    public int cnt_INI_RR = 0;
    public int T_FIN_RR = 0;
    public int cnt_FIN_RR = 0;
    public int BurstFCFS = 0;
    public int BurstSJF = 0;
    public int BurstRR = 0;
    public double RetornoFCFS = 0.0;
    public double RetornoSJF = 0.0;
    public double RetornoRR = 0.0;
    DefaultCategoryDataset barChartData = new DefaultCategoryDataset();

    public static double contadorBloqueados_FCFS = 0;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jLabel12 = new javax.swing.JLabel();
        txtQuantum = new javax.swing.JTextField();
        btnDefinirQuantum = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBurstTime = new javax.swing.JTextField();
        btnAnadirProcesoUsuario = new javax.swing.JButton();
        txtTamano = new javax.swing.JTextField();
        btnGenerarAleatorio = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCntFCFS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFCFS = new javax.swing.JTable();
        jLabelNProceso = new javax.swing.JLabel();
        txtIdProcesoFCFS = new javax.swing.JTextField();
        jLabelPorcentaje = new javax.swing.JLabel();
        txtPorcentajeFCFS = new javax.swing.JTextField();
        pgrFCFS = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblInterrupciones_FCFS = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPCB_FCFS = new javax.swing.JTable();
        JPanel_FCFS = new javax.swing.JScrollPane();
        btnDefinirQuantum1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCntSJF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSJF = new javax.swing.JTable();
        jLabelNProceso1 = new javax.swing.JLabel();
        txtIdProcesoSJF = new javax.swing.JTextField();
        jLabelPorcentaje2 = new javax.swing.JLabel();
        txtPorcentajeSJF = new javax.swing.JTextField();
        pgrSJF = new javax.swing.JProgressBar();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblInterrupciones_SJF = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblPCB_SJF = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        JPanel_SJF = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCntRR = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRR = new javax.swing.JTable();
        jLabelNProceso2 = new javax.swing.JLabel();
        txtIdProcesoRR = new javax.swing.JTextField();
        jLabelPorcentaje1 = new javax.swing.JLabel();
        txtPorcentajeRR = new javax.swing.JTextField();
        pgrRR = new javax.swing.JProgressBar();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblInterrupciones_RR = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblPCB_RR = new javax.swing.JTable();
        JPanel_RR = new javax.swing.JPanel();
        btnDefinirQuantum4 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Definir Quantum");

        txtQuantum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantumActionPerformed(evt);
            }
        });

        btnDefinirQuantum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDefinirQuantum.setText("Definir Quantum");
        btnDefinirQuantum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefinirQuantumActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("PROCESOS EXPROPIATIVOS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nuevo Proceso");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tamaño (Mb)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Burst-Time (seg)");

        btnAnadirProcesoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAnadirProcesoUsuario.setText("Añadir Proceso Usuario");
        btnAnadirProcesoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirProcesoUsuarioActionPerformed(evt);
            }
        });

        txtTamano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTamanoActionPerformed(evt);
            }
        });

        btnGenerarAleatorio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGenerarAleatorio.setText("Generar Aleatorio");
        btnGenerarAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarAleatorioActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Generar Estadísticas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("First Come First Served (FCFS)");

        txtCntFCFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCntFCFSActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Procesos Principales Creados");

        tblFCFS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Proceso", "Burst-Time", "Residuo", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tblFCFS);

        jLabelNProceso.setText("Proceso Actual");

        txtIdProcesoFCFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProcesoFCFSActionPerformed(evt);
            }
        });

        jLabelPorcentaje.setText("Porcentaje Procesado");

        txtPorcentajeFCFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeFCFSActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("INTERRUPCIONES");

        tblInterrupciones_FCFS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Interrupcion", "Proceso", "Tipo", "Duración", "Restante", "Estado"
            }
        ));
        jScrollPane4.setViewportView(tblInterrupciones_FCFS);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setText("PCB's");

        tblPCB_FCFS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Proceso", "EstadoCPU", "Procesador", "Memoria", "Estado", "Rescursos", "Planificador", "Prioridad", "Contabilizacion", "Ancestro", "Descendientes"
            }
        ));
        jScrollPane7.setViewportView(tblPCB_FCFS);

        btnDefinirQuantum1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDefinirQuantum1.setText("Volver");
        btnDefinirQuantum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefinirQuantum1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPorcentajeFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(167, 167, 167)
                                        .addComponent(txtCntFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane4)
                                .addComponent(pgrFCFS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelNProceso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdProcesoFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabelPorcentaje))
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JPanel_FCFS, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDefinirQuantum1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCntFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPorcentajeFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelPorcentaje))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdProcesoFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelNProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pgrFCFS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JPanel_FCFS)))
                .addGap(38, 38, 38)
                .addComponent(btnDefinirQuantum1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("FCFS", jPanel1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Shortest Job First (SJF)");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Procesos Principales Creados");

        txtCntSJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCntSJFActionPerformed(evt);
            }
        });

        tblSJF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Proceso", "Burst-Time", "Residuo", "Estado"
            }
        ));
        jScrollPane2.setViewportView(tblSJF);

        jLabelNProceso1.setText("Proceso Actual");

        txtIdProcesoSJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProcesoSJFActionPerformed(evt);
            }
        });

        jLabelPorcentaje2.setText("Porcentaje Procesado");

        txtPorcentajeSJF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeSJFActionPerformed(evt);
            }
        });

        tblInterrupciones_SJF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Interrupcion", "Proceso", "Tipo", "Duración", "Restante", "Estado"
            }
        ));
        jScrollPane5.setViewportView(tblInterrupciones_SJF);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("INTERRUPCIONES");

        tblPCB_SJF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Proceso", "EstadoCPU", "Procesador", "Memoria", "Estado", "Rescursos", "Planificador", "Prioridad", "Contabilizacion", "Ancestro", "Descendientes"
            }
        ));
        jScrollPane8.setViewportView(tblPCB_SJF);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 204));
        jLabel16.setText("PCB's");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPorcentajeSJF, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(152, 152, 152)
                                    .addComponent(txtCntSJF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabelNProceso1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdProcesoSJF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelPorcentaje2))
                            .addComponent(jLabel13)
                            .addComponent(pgrSJF, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPanel_SJF)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCntSJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelNProceso1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdProcesoSJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPorcentajeSJF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelPorcentaje2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pgrSJF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JPanel_SJF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SJF", jPanel2);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Round Robin (RR)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Procesos Principales Creados");

        tblRR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Proceso", "Burst-Time", "Residuo", "Estado"
            }
        ));
        jScrollPane3.setViewportView(tblRR);

        jLabelNProceso2.setText("Proceso Actual");

        txtIdProcesoRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProcesoRRActionPerformed(evt);
            }
        });

        jLabelPorcentaje1.setText("Porcentaje Procesado");

        txtPorcentajeRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeRRActionPerformed(evt);
            }
        });

        tblInterrupciones_RR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Interrupcion", "Proceso", "Tipo", "Duración", "Restante", "Estado"
            }
        ));
        jScrollPane6.setViewportView(tblInterrupciones_RR);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("INTERRUPCIONES");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 204));
        jLabel17.setText("PCB's");

        tblPCB_RR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Proceso", "EstadoCPU", "Procesador", "Memoria", "Estado", "Rescursos", "Planificador", "Prioridad", "Contabilizacion", "Ancestro", "Descendientes"
            }
        ));
        jScrollPane9.setViewportView(tblPCB_RR);

        javax.swing.GroupLayout JPanel_RRLayout = new javax.swing.GroupLayout(JPanel_RR);
        JPanel_RR.setLayout(JPanel_RRLayout);
        JPanel_RRLayout.setHorizontalGroup(
            JPanel_RRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 886, Short.MAX_VALUE)
        );
        JPanel_RRLayout.setVerticalGroup(
            JPanel_RRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtCntRR, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel7))
                                    .addGap(276, 276, 276)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pgrRR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabelNProceso2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIdProcesoRR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelPorcentaje1)
                            .addGap(23, 23, 23)
                            .addComponent(txtPorcentajeRR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(JPanel_RR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCntRR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPorcentaje1)
                            .addComponent(txtIdProcesoRR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPorcentajeRR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNProceso2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pgrRR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JPanel_RR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RR", jPanel3);

        btnDefinirQuantum4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDefinirQuantum4.setText("Volver");
        btnDefinirQuantum4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefinirQuantum4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTamano, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBurstTime, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(335, 335, 335)
                                .addComponent(btnDefinirQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnadirProcesoUsuario)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGenerarAleatorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(308, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(btnDefinirQuantum4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDefinirQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnadirProcesoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBurstTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTamano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnGenerarAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDefinirQuantum4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCntSJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCntSJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCntSJFActionPerformed

    private void txtIdProcesoFCFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProcesoFCFSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProcesoFCFSActionPerformed

    private void txtPorcentajeFCFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeFCFSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeFCFSActionPerformed

    private void btnDefinirQuantumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefinirQuantumActionPerformed
        // TODO add your handling code here:
        Quantum = Integer.parseInt(txtQuantum.getText()); // recoge quamtum
    }//GEN-LAST:event_btnDefinirQuantumActionPerformed

    private void txtIdProcesoSJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProcesoSJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProcesoSJFActionPerformed

    private void txtPorcentajeSJFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeSJFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeSJFActionPerformed

    private void txtIdProcesoRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProcesoRRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProcesoRRActionPerformed

    private void btnAnadirProcesoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirProcesoUsuarioActionPerformed
        // TODO add your handling code here:
        Tamano = Integer.parseInt(txtTamano.getText()); // recoge tamaño
        BurstTime = Integer.parseInt(txtBurstTime.getText()); // recoge burst time
        DefaultTableModel NuevaTabla1 = (DefaultTableModel) tblFCFS.getModel();
        DefaultTableModel NuevaTabla2 = (DefaultTableModel) tblSJF.getModel();
        DefaultTableModel NuevaTabla3 = (DefaultTableModel) tblRR.getModel();

        CambioCola = true;

        if (Quantum > 0) {
            // Crea el objeto Proceso
            dtoProceso proceso = new dtoProceso();
            daoPCB_Expro daopcb = new daoPCB_Expro();
            if (Contador % 3 == 1) {
                proceso.setIdentificador(IdProcesoFCFS);
                txtCntFCFS.setText(String.valueOf(cntFCFS));
                IngresaEstadistica(IdProcesoFCFS, BurstTime, 1);
                IdProcesoFCFS++;
                cntFCFS++;
            } else if (Contador % 3 == 2) {
                proceso.setIdentificador(IdProcesoSJF);
                txtCntSJF.setText(String.valueOf(cntSJF));
                IngresaEstadistica(IdProcesoSJF, BurstTime, 2);
                IdProcesoSJF++;
                cntSJF++;
            } else {
                proceso.setIdentificador(IdProcesoRR);
                txtCntRR.setText(String.valueOf(cntRR));
                IngresaEstadistica(IdProcesoRR, BurstTime, 3);
                IdProcesoRR++;
                cntRR++;
            }
            proceso.setEstadoCPU(1);
            proceso.setProcesador(1);
            proceso.setMemoria(new Pair<>(0, Tamano));
            proceso.setEstadoProceso("Nuevo");
            proceso.setRecursos(new ArrayList<String>());
            proceso.setPlanificador("Largo");
            proceso.setPrioridad(0);
            proceso.setContabilizacion(0);
            proceso.setAncestro(0);
            proceso.setDescendientes(new ArrayList<Integer>());

            String idNueva = proceso.getAncestro().toString() + '-' + proceso.getIdentificador().toString();
            if (Contador % 3 == 1) {
                BurstFCFS += BurstTime;
                ProcesosFCFS.add(proceso);
                daopcb.CargarPCB(this, 1, proceso, -1, -1);
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 1);

                contarDatos(tblPCB_FCFS, 1);
                refrescarTablaContadores(1);
                FCFS.add(new PairFCFS(BurstTime, proceso.getIdentificador(), BurstTime));
                DuracionFCFS.add(new PairTiempo(null, null));
                daoProceso.AgregarProceso(NuevaTabla1, idNueva, BurstTime);
                tblFCFS.setModel(NuevaTabla1);
            } else if (Contador % 3 == 2) {
                BurstSJF += BurstTime;
                ProcesosSJF.add(proceso);
                daopcb.CargarPCB(this, 2, proceso, -1, -1);
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 2);

                contarDatos(tblPCB_SJF, 2);
                refrescarTablaContadores(2);
                SJF.add(new PairSJF(BurstTime, BurstTime, proceso.getIdentificador()));
                DuracionSJF.add(new PairTiempo(null, null));
                daoProceso.AgregarProceso(NuevaTabla2, idNueva, BurstTime);
                tblSJF.setModel(NuevaTabla2);
            } else {
                BurstRR += BurstTime;
                if (RR.size() == 0) {
                    UltimoRR = new PairRR(BurstTime, proceso.getIdentificador(), BurstTime);
                }
                ProcesosRR.add(proceso);
                daopcb.CargarPCB(this, 3, proceso, -1, -1);
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 3);

                contarDatos(tblPCB_SJF, 3);
                refrescarTablaContadores(3);
                RR.add(new PairRR(BurstTime, proceso.getIdentificador(), BurstTime));
                DuracionRR.add(new PairTiempo(null, null));
                daoProceso.AgregarProceso(NuevaTabla3, idNueva, BurstTime);
                tblRR.setModel(NuevaTabla3);
            }
            if (primera) {
                principal.start();
                primera = !primera;
            }
            txtTamano.setText(null);
            txtBurstTime.setText(null);
            Contador++;
        } else {
            JOptionPane.showMessageDialog(null, "Escriba el valor del Quantum");
        }
    }//GEN-LAST:event_btnAnadirProcesoUsuarioActionPerformed

    private void txtTamanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTamanoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamanoActionPerformed

    private void txtPorcentajeRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeRRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeRRActionPerformed

    private void txtQuantumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantumActionPerformed

    private void btnGenerarAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarAleatorioActionPerformed
        // TODO add your handling code here:
        DefaultTableModel NuevaTabla1 = (DefaultTableModel) tblFCFS.getModel();
        DefaultTableModel NuevaTabla2 = (DefaultTableModel) tblSJF.getModel();
        DefaultTableModel NuevaTabla3 = (DefaultTableModel) tblRR.getModel();

        if (Quantum > 0) {

            for (int i = 0; i < 10; i++) {
                dtoProceso proceso = new dtoProceso();
                daoPCB_Expro daopcb = new daoPCB_Expro();
                Random r = new Random();

                BurstTime = 200 + r.nextInt(50);
                Tamano = 500 + r.nextInt(50);

                proceso.setIdentificador(IdProcesoFCFS);
                txtCntFCFS.setText(String.valueOf(cntFCFS));
                IngresaEstadistica(IdProcesoFCFS, BurstTime, 1);
                IdProcesoFCFS++;
                cntFCFS++;

                proceso.setIdentificador(IdProcesoSJF);
                txtCntSJF.setText(String.valueOf(cntSJF));
                IngresaEstadistica(IdProcesoSJF, BurstTime, 2);
                IdProcesoSJF++;
                cntSJF++;

                proceso.setIdentificador(IdProcesoRR);
                txtCntRR.setText(String.valueOf(cntRR));
                IngresaEstadistica(IdProcesoRR, BurstTime, 3);
                IdProcesoRR++;
                cntRR++;

                proceso.setEstadoCPU(1);
                proceso.setProcesador(1);
                proceso.setMemoria(new Pair<>(0, Tamano));
                proceso.setEstadoProceso("Nuevo");
                proceso.setRecursos(new ArrayList<String>());
                proceso.setPlanificador("Largo");
                proceso.setPrioridad(0);
                proceso.setContabilizacion(0);
                proceso.setAncestro(0);
                proceso.setDescendientes(new ArrayList<Integer>());

                String idNueva = proceso.getAncestro().toString() + '-' + proceso.getIdentificador().toString();

                BurstFCFS += BurstTime;
                ProcesosFCFS.add(proceso);
                daopcb.CargarPCB(this, 1, proceso, -1, -1);
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 1);

                contarDatos(tblPCB_FCFS, 1);
                refrescarTablaContadores(1);
                FCFS.add(new PairFCFS(BurstTime, proceso.getIdentificador(), BurstTime));
                DuracionFCFS.add(new PairTiempo(null, null));
                daoProceso.AgregarProceso(NuevaTabla1, idNueva, BurstTime);
                tblFCFS.setModel(NuevaTabla1);

                BurstSJF += BurstTime;
                ProcesosSJF.add(proceso);
                daopcb.CargarPCB(this, 2, proceso, -1, -1);
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 2);

                contarDatos(tblPCB_FCFS, 2);
                refrescarTablaContadores(2);
                SJF.add(new PairSJF(BurstTime, BurstTime, proceso.getIdentificador()));
                DuracionSJF.add(new PairTiempo(null, null));
                daoProceso.AgregarProceso(NuevaTabla2, idNueva, BurstTime);
                tblSJF.setModel(NuevaTabla2);

                BurstRR += BurstTime;
                if (RR.size() == 0) {
                    UltimoRR = new PairRR(BurstTime, proceso.getIdentificador(), BurstTime);
                }
                ProcesosRR.add(proceso);
                daopcb.CargarPCB(this, 3, proceso, -1, -1);
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 3);

                contarDatos(tblPCB_RR, 3);
                refrescarTablaContadores(3);
                RR.add(new PairRR(BurstTime, proceso.getIdentificador(), BurstTime));
                DuracionRR.add(new PairTiempo(null, null));
                daoProceso.AgregarProceso(NuevaTabla3, idNueva, BurstTime);
                tblRR.setModel(NuevaTabla3);

                txtTamano.setText(null);
                txtBurstTime.setText(null);
                Contador += 3;
                if (primera) {
                    principal.start();
                    primera = !primera;
                }
            }

        } else {
            // Si no ha escrito el valor de quantum
            JOptionPane.showMessageDialog(null, "Escriba el valor del Quantum");
        }
    }//GEN-LAST:event_btnGenerarAleatorioActionPerformed

    private void btnDefinirQuantum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefinirQuantum1ActionPerformed
        // TODO add your handling code here:
        principal.stop();
        Ventana_inicio interfaz = new Ventana_inicio();
        interfaz.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDefinirQuantum1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VentanaEstadisticas.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCntFCFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCntFCFSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCntFCFSActionPerformed

    private void btnDefinirQuantum4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefinirQuantum4ActionPerformed
        // TODO add your handling code here:
        principal.stop();
        Ventana_inicio interfaz = new Ventana_inicio();
        interfaz.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDefinirQuantum4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ventana_Expropiativo ventana_Expro = new Ventana_Expropiativo();

                ventana_Expro.setVisible(true);
            }
        });
    }

    private class Hilo implements Runnable { //Objeto de tipo Hilo con extension ejecutable

        @Override
        public void run() {
            while (true) {
                MuestraUsoCPU();
                System.out.println("-> " + FCFS.size() + " " + SJF.size() + " " + RR.size());
                if (!FCFS.isEmpty()) {
                    PairFCFS ac = FCFS.peek();
                    dtoProceso procesoActual = new dtoProceso();
                    procesoActual = ProcesosFCFS.get(ac.getIdentificador().intValue() - 1);
                    String idNuevo = procesoActual.getAncestro().toString() + '-' + procesoActual.getIdentificador().toString();
                    int PosicionTabla = BuscaPosicionTabla(idNuevo, 1);
                    int tiempo = ac.getBurstTime().intValue();
                    if (ac.getNivel().equals(ac.getBurstTime())) {
                        AsignaInicio(ac.getIdentificador().intValue(), 1);
                    }
                    int i;
                    for (i = 1; i <= tiempo; i++) {
                        // contador ejecutando

                        TimeTot++;
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Ejecutando", 1);
                        contarDatos(tblPCB_FCFS, 1);
                        refrescarTablaContadores(1);

                        MostrarProgreso(ac.getNivel().intValue() - ac.getBurstTime().intValue() + i, ac.getNivel().intValue(), ac.getIdentificador().intValue(), 1);
                        tblFCFS.setValueAt(procesoActual.getEstadoProceso(), PosicionTabla, 3);
                        tblFCFS.setValueAt(String.valueOf(tiempo - i), PosicionTabla, 2);

                        Dormir();
                        GeneraInterrupcion(PosicionTabla, ac.getIdentificador().intValue(), 1);
                        if (CambioCola) {
                            CambioCola = false;
                            break;
                        }
                        GeneraHijo(PosicionTabla, ac.getIdentificador().intValue(), 1);
                        if (CambioCola) {
                            CambioCola = false;
                            break;
                        }
                    }
                    if (i >= tiempo) {
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Terminado", 1);
                        // contador terminado FCFS
                        contarDatos(tblPCB_FCFS, 1);
                        refrescarTablaContadores(1);
                        Borrar(PosicionTabla, 1);
                        AsignaFin(ac.getIdentificador().intValue(), 1);
                        FCFS.remove();

                    } else {
                        // contador listo

                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Listo", 1);
                        contarDatos(tblPCB_FCFS, 1);
                        refrescarTablaContadores(1);
                        tblFCFS.setValueAt("Listo", PosicionTabla, 3);
                        FCFS.peek().setBurstTime(tiempo - i);

                    }
                    /*PairTiempo aux = new PairTiempo();
                    aux=DuracionFCFS.get(ac.getIdentificador().intValue()-1);
                    System.out.println("Tipo 1 para "+ac.getIdentificador()+" -> "+aux.getInicio()+" "+aux.getFin());*/

                }

                if (!SJF.isEmpty()) {
                    PairSJF ac = SJF.peek();
                    dtoProceso procesoActual = new dtoProceso();
                    procesoActual = ProcesosSJF.get(ac.getIdentificador().intValue() - 1);
                    String idNuevo = procesoActual.getAncestro().toString() + '-' + procesoActual.getIdentificador().toString();
                    int PosicionTabla = BuscaPosicionTabla(idNuevo, 2);
                    int tiempo = ac.getBurstTime().intValue();
                    if (ac.getNivel().equals(ac.getBurstTime())) {
                        AsignaInicio(ac.getIdentificador().intValue(), 2);
                    }
                    int i;
                    for (i = 1; i <= tiempo; i++) {
                        TimeTot++;
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Ejecutando", 2);
                        contarDatos(tblPCB_SJF, 2);
                        refrescarTablaContadores(2);
                        MostrarProgreso(ac.getNivel().intValue() - ac.getBurstTime().intValue() + i, ac.getNivel().intValue(), ac.getIdentificador().intValue(), 2);
                        tblSJF.setValueAt(procesoActual.getEstadoProceso(), PosicionTabla, 3);
                        tblSJF.setValueAt(String.valueOf(tiempo - i), PosicionTabla, 2);
                        Dormir();
                        GeneraInterrupcion(PosicionTabla, ac.getIdentificador().intValue(), 2);
                        if (CambioCola) {
                            CambioCola = false;
                            break;
                        }
                        GeneraHijo(PosicionTabla, ac.getIdentificador().intValue(), 2);
                        if (CambioCola) {
                            CambioCola = false;
                            break;
                        }
                    }
                    if (i >= tiempo) {
                        // Contador terminado SJF
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Terminado", 2);
                        contarDatos(tblPCB_SJF, 2);
                        refrescarTablaContadores(2);
                        Borrar(PosicionTabla, 2);
                        AsignaFin(ac.getIdentificador().intValue(), 2);
                        SJF.remove();
                    } else {
                        // contador listo SJF
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Listo", 2);
                        contarDatos(tblPCB_SJF, 2);
                        refrescarTablaContadores(2);
                        tblSJF.setValueAt("Listo", PosicionTabla, 3);
                        SJF.peek().setBurstTime(tiempo - i);
                    }


                    /*PairTiempo aux = new PairTiempo();
                    aux=DuracionSJF.get(ac.getIdentificador().intValue()-1);
                    System.o
                    ut.println("Tipo 2 para "+ac.getIdentificador()+" -> "+aux.getInicio()+" "+aux.getFin());*/
                }
                int sz = RR.size();
                if (sz != 0) {
                    dtoProceso procesoActual = new dtoProceso();
                    procesoActual = ProcesosRR.get(UltimoRR.getIdentificador().intValue() - 1);
                    String idNuevo = procesoActual.getAncestro().toString() + '-' + procesoActual.getIdentificador().toString();
                    int PosicionTabla = BuscaPosicionTabla(idNuevo, 3);
                    int tiempo = UltimoRR.getResiduo().intValue();
                    int tiempoReducir;
                    boolean termina = false;
                    if (UltimoRR.getBurstTime().equals(UltimoRR.getResiduo())) {
                        AsignaInicio(UltimoRR.getIdentificador().intValue(), 3);
                    }
                    if (termina && tiempo > Quantum) {
                        tiempoReducir = Quantum;
                    } else {
                        termina = true;
                        tiempoReducir = tiempo;
                    }

                    int i;

                    for (i = 1; i <= tiempoReducir; i++) {
                        TimeTot++;
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Ejecutando", 3);
                        contarDatos(tblPCB_RR, 3);
                        refrescarTablaContadores(3);
                        MostrarProgreso(UltimoRR.getBurstTime().intValue() - (UltimoRR.getResiduo().intValue() - i), UltimoRR.getBurstTime().intValue(), UltimoRR.getIdentificador().intValue(), 3);
                        tblRR.setValueAt(procesoActual.getEstadoProceso(), PosicionTabla, 3);
                        tblRR.setValueAt(String.valueOf(tiempo - i), PosicionTabla, 2);
                        Dormir();
                        GeneraInterrupcion(PosicionTabla, UltimoRR.getIdentificador().intValue(), 3);
                        if (CambioCola) {
                            CambioCola = false;
                            break;
                        }
                        GeneraHijo(PosicionTabla, UltimoRR.getIdentificador().intValue(), 3);
                        if (CambioCola) {
                            CambioCola = false;
                            break;
                        }
                    }

                    if (i >= tiempoReducir) {
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Terminado", 3);
                        contarDatos(tblPCB_RR, 3);
                        refrescarTablaContadores(3);
                        AsignaFin(UltimoRR.getIdentificador().intValue(), 3);

                        /*PairTiempo aux = new PairTiempo();
                        aux=DuracionRR.get(UltimoRR.getIdentificador().intValue()-1);
                        System.out.println("Tipo 3 para "+UltimoRR.getIdentificador()+" -> "+aux.getInicio()+" "+aux.getFin());*/
                        RR.remove(PosicionTabla);
                        sz = RR.size();
                        if (sz != 0) {
                            UltimoRR = RR.get(PosicionTabla % sz);
                        }
                        Borrar(PosicionTabla, 3);
                    } else {
                        CambiaEstado(procesoActual.getIdentificador().intValue(), "Listo", 3);
                        contarDatos(tblPCB_RR, 3);
                        refrescarTablaContadores(3);

                        RR.set(PosicionTabla, new PairRR(UltimoRR.getBurstTime(), UltimoRR.getIdentificador(), tiempo - i));
                        tblRR.setValueAt("Listo", PosicionTabla, 3);
                        UltimoRR = RR.get((PosicionTabla + 1) % sz);
                    }
                    contarDatos(tblPCB_RR, 3);
                    refrescarTablaContadores(3);
                }
                /*if(FCFS.size()==0 && SJF.size()==0 && RR.size()==0){
                    System.out.println("Termina");
                    primera=true;
                }*/
            }
        }
    }

    public void Borrar(int pos, int tipo) {
        if (tipo == 1) {
            DefaultTableModel dtm = (DefaultTableModel) tblFCFS.getModel();
            dtm.removeRow(pos);
            tblFCFS.setModel(dtm);
        } else if (tipo == 2) {
            DefaultTableModel dtm = (DefaultTableModel) tblSJF.getModel();
            dtm.removeRow(pos);
            tblSJF.setModel(dtm);
        } else {
            DefaultTableModel dtm = (DefaultTableModel) tblRR.getModel();
            dtm.removeRow(pos);
            tblRR.setModel(dtm);
        }
    }

    public static void refrescarTablaContadores(int tipo) {
        if (tipo == 1) {
            double[][] data = {{contadorEjecutando_FCFS}, {contadorListos_FCFS}, {contadorTerminados_FCFS}, {contadorBloqueados_FCFS}};
              Comparable comparable1 = (Comparable)("Ejecutando");
            Comparable comparable2 = (Comparable)("Listo");
            Comparable comparable3 = (Comparable)("Terminado");
            Comparable  comparable4 = (Comparable)("Bloqueado");
            Comparable[] estados= {comparable1,comparable2,comparable3,comparable4};
            Comparable[] prog = {(Comparable) ("Progreso")};
            
            
            CategoryDataset dataset = DatasetUtilities.createCategoryDataset(estados, prog, data);
            

            CategoryPlot barChartPlot = barChart_FCFS.getCategoryPlot();
            barChartPlot.setDataset(dataset);

            BarRenderer br = (BarRenderer) barChartPlot.getRenderer();
            br.setMaximumBarWidth(.15);
            barChartPlot.getRenderer().setSeriesPaint(0, new Color(0, 255, 0));
            barChartPlot.getRenderer().setSeriesPaint(1, new Color(0, 0, 255));
            barChartPlot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));

            ChartPanel bar_FCFS = new ChartPanel(barChart_FCFS);
            bar_FCFS.setSize(700, 200);

            JPanel_FCFS.add(bar_FCFS);
            JPanel_FCFS.setSize(700, 200);
        } else if (tipo == 2) {
            double[][] data = {{contadorEjecutando_SJF}, {contadorListos_SJF}, {contadorTerminados_SJF}, {contadorBloqueados_SJF}};
            CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Estado", "", data);

            CategoryPlot barChartPlot = barChart_SJF.getCategoryPlot();
            barChartPlot.setDataset(dataset);

            BarRenderer br = (BarRenderer) barChartPlot.getRenderer();
            br.setMaximumBarWidth(.15);
            barChartPlot.getRenderer().setSeriesPaint(0, new Color(0, 255, 0));
            barChartPlot.getRenderer().setSeriesPaint(1, new Color(0, 0, 255));
            barChartPlot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));

            ChartPanel bar_SJF = new ChartPanel(barChart_SJF);
            bar_SJF.setSize(700, 200);

            JPanel_SJF.add(bar_SJF);
            JPanel_SJF.setSize(700, 200);

        } else {
            double[][] data = {{contadorEjecutando_RR}, {contadorListos_RR}, {contadorTerminados_RR}, {contadorBloqueados_RR}};
            CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Estado", "", data);

            CategoryPlot barChartPlot = barChart_RR.getCategoryPlot();
            barChartPlot.setDataset(dataset);

            BarRenderer br = (BarRenderer) barChartPlot.getRenderer();
            br.setMaximumBarWidth(.15);
            barChartPlot.getRenderer().setSeriesPaint(0, new Color(0, 255, 0));
            barChartPlot.getRenderer().setSeriesPaint(1, new Color(0, 0, 255));
            barChartPlot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));

            ChartPanel bar_RR = new ChartPanel(barChart_RR);
            bar_RR.setSize(700, 200);

            JPanel_RR.add(bar_RR);
            JPanel_RR.setSize(700, 200);

        }

    }

    public void GeneraHijo(int PosicionTabla, int IdProceso, int tipo) {
        Random r = new Random();
        daoPCB_Expro daopcb = new daoPCB_Expro();
        if (r.nextInt(100) < 5) {
            ActualizaDescendientes(IdProceso, tipo);
            CambiaEstado(IdProceso, "Bloqueado", tipo);
            contarDatos(tblPCB_FCFS, tipo);
            refrescarTablaContadores(tipo);
            dtoProceso proceso = new dtoProceso();
            if (tipo == 1) {
                // contador bloqueado por tipo

                tblFCFS.setValueAt("Bloqueado por Hijo", PosicionTabla, 3);
                proceso.setIdentificador(IdProcesoFCFS);
                IdProcesoFCFS++;
                // contador total_FCFS ++
            } else if (tipo == 2) {
                // contador total SJF
                tblSJF.setValueAt("Bloqueado por Hijo", PosicionTabla, 3);
                proceso.setIdentificador(IdProcesoSJF);
                IdProcesoSJF++;

            } else {
                tblRR.setValueAt("Bloqueado por Hijo", PosicionTabla, 3);
                proceso.setIdentificador(IdProcesoRR);
                IdProcesoRR++;
            }
            Tamano = r.nextInt(100) + 1;
            proceso.setEstadoCPU(1);
            proceso.setProcesador(1);
            proceso.setMemoria(new Pair<>(0, Tamano));
            proceso.setEstadoProceso("Nuevo");
            proceso.setRecursos(new ArrayList<String>());
            proceso.setPlanificador("Largo");
            proceso.setPrioridad(0);
            proceso.setContabilizacion(0);
            proceso.setAncestro(IdProceso);
            proceso.setDescendientes(new ArrayList<Integer>());
            if (Quantum > 20) {
                BurstTime = r.nextInt(20) + 1;
            } else {
                BurstTime = r.nextInt(Quantum) + 1;
            }
            if (tipo == 1) {
                IngresaEstadistica(IdProcesoFCFS - 1, BurstTime, 1);
            } else if (tipo == 2) {
                IngresaEstadistica(IdProcesoSJF - 1, BurstTime, 2);
            } else {
                IngresaEstadistica(IdProcesoRR - 1, BurstTime, 3);
            }
            String idNueva = proceso.getAncestro().toString() + '-' + proceso.getIdentificador().toString();
            if (tipo == 1) {
                BurstFCFS += BurstTime;
                DefaultTableModel NuevaTabla1 = (DefaultTableModel) tblFCFS.getModel();
                ProcesosFCFS.add(proceso);
                daopcb.CargarPCB(this, tipo, proceso, -1, -1);
                DuracionFCFS.add(new PairTiempo(null, null));
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 1);

                contarDatos(tblPCB_FCFS, 1);
                refrescarTablaContadores(1);
                daoProceso.AgregarProceso(NuevaTabla1, idNueva, BurstTime);
                tblFCFS.setModel(NuevaTabla1);

            } else if (tipo == 2) {
                BurstSJF += BurstTime;
                DefaultTableModel NuevaTabla2 = (DefaultTableModel) tblSJF.getModel();
                ProcesosSJF.add(proceso);
                daopcb.CargarPCB(this, tipo, proceso, -1, -1);
                DuracionSJF.add(new PairTiempo(null, null));
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 2);
                contarDatos(tblPCB_SJF, 2);
                refrescarTablaContadores(2);
                daoProceso.AgregarProceso(NuevaTabla2, idNueva, BurstTime);
                tblSJF.setModel(NuevaTabla2);

            } else {
                BurstRR += BurstTime;
                DefaultTableModel NuevaTabla3 = (DefaultTableModel) tblRR.getModel();
                ProcesosRR.add(proceso);
                daopcb.CargarPCB(this, tipo, proceso, -1, -1);
                DuracionRR.add(new PairTiempo(null, null));
                CambiaEstado(proceso.getIdentificador().intValue(), "Listo", 3);
                contarDatos(tblPCB_RR, 3);
                refrescarTablaContadores(3);
                daoProceso.AgregarProceso(NuevaTabla3, idNueva, BurstTime);
                tblRR.setModel(NuevaTabla3);

            }

            AtiendeHijo(proceso, tipo, BurstTime);
        }
    }

    public void AtiendeHijo(dtoProceso procesoHijo, int tipo, int tiempo) {
        int PosicionTabla;
        AsignaInicio(procesoHijo.getIdentificador().intValue(), tipo);
        if (tipo == 1) {
            String idNuevo = procesoHijo.getAncestro().toString() + '-' + procesoHijo.getIdentificador().toString();
            PosicionTabla = BuscaPosicionTabla(idNuevo, 1);
            for (int i = 1; i <= tiempo; i++) {
                TimeTot++;
                CambiaEstado(procesoHijo.getIdentificador().intValue(), "Ejecutando", tipo);
                contarDatos(tblPCB_FCFS, 1);
                refrescarTablaContadores(1);
                MostrarProgreso(i, tiempo, procesoHijo.getIdentificador().intValue(), 1);
                tblFCFS.setValueAt(procesoHijo.getEstadoProceso(), PosicionTabla, 3);
                tblFCFS.setValueAt(String.valueOf(tiempo - i), PosicionTabla, 2);
                Dormir();
            }

        } else if (tipo == 2) {
            String idNuevo = procesoHijo.getAncestro().toString() + '-' + procesoHijo.getIdentificador().toString();
            PosicionTabla = BuscaPosicionTabla(idNuevo, 2);
            for (int i = 1; i <= tiempo; i++) {
                TimeTot++;
                CambiaEstado(procesoHijo.getIdentificador().intValue(), "Ejecutando", tipo);
                contarDatos(tblPCB_SJF, 2);
                refrescarTablaContadores(2);

                MostrarProgreso(i, tiempo, procesoHijo.getIdentificador().intValue(), 2);
                tblSJF.setValueAt(procesoHijo.getEstadoProceso(), PosicionTabla, 3);
                tblSJF.setValueAt(String.valueOf(tiempo - i), PosicionTabla, 2);
                Dormir();
            }
        } else {
            String idNuevo = procesoHijo.getAncestro().toString() + '-' + procesoHijo.getIdentificador().toString();
            PosicionTabla = BuscaPosicionTabla(idNuevo, 3);
            for (int i = 1; i <= tiempo; i++) {
                TimeTot++;
                CambiaEstado(procesoHijo.getIdentificador().intValue(), "Ejecutando", tipo);
                contarDatos(tblPCB_RR, 3);
                refrescarTablaContadores(3);

                MostrarProgreso(i, tiempo, procesoHijo.getIdentificador().intValue(), 3);
                tblRR.setValueAt(procesoHijo.getEstadoProceso(), PosicionTabla, 3);
                tblRR.setValueAt(String.valueOf(tiempo - i), PosicionTabla, 2);
                Dormir();
            }
        }
        CambiaEstado(procesoHijo.getIdentificador().intValue(), "Terminado", tipo);
        contarDatos(tblPCB_FCFS, 1);
        refrescarTablaContadores(1);

        Borrar(PosicionTabla, tipo);
        AsignaFin(procesoHijo.getIdentificador().intValue(), tipo);
        CambioCola = true;
    }

    public static int BuscaPosicionTabla(String idNueva, int tipo) {
        if (tipo == 1) {
            for (int i = 0; i < tblFCFS.getRowCount(); i++) {
                if (String.valueOf(tblFCFS.getValueAt(i, 0)).equals(idNueva)) {
                    return i;
                }
            }
            return -1;
        } else if (tipo == 2) {
            for (int i = 0; i < tblSJF.getRowCount(); i++) {
                if (String.valueOf(tblSJF.getValueAt(i, 0)).equals(idNueva)) {
                    return i;
                }
            }
            return -1;
        } else {
            for (int i = 0; i < tblRR.getRowCount(); i++) {
                if (String.valueOf(tblRR.getValueAt(i, 0)).equals(idNueva)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public void Dormir() {
        try {
            Thread.sleep(200); //Dormir sistema
        } catch (InterruptedException ex) {
            Logger.getLogger(Ventana_No_Expropiativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GeneraInterrupcion(int filaTabla, int IdProceso, int tipo) {
        Random r = new Random();
        if (r.nextInt(100) < 5) {
            //existe interrupcion
            CambiaEstado(IdProceso, "Bloqueado", tipo);
            if (tipo == 1) {
                tblFCFS.setValueAt("Bloqueado por Interrupción", filaTabla, 3);
            } else if (tipo == 2) {
                tblSJF.setValueAt("Bloqueado por Interrupción", filaTabla, 3);
            } else {
                tblRR.setValueAt("Bloqueado por Interrupción", filaTabla, 3);
            }
            int TiempoInterrupcion = r.nextInt(10) + 1;
            IngresarInterrupcion(TiempoInterrupcion, IdProceso, tipo);
            if (tipo == 1) {
                for (int j = 1; j <= TiempoInterrupcion; j++) {
                    tblInterrupciones_FCFS.setValueAt(String.valueOf(TiempoInterrupcion - j), ContadorInterrupciones_FCFS - 1, 4);
                    Dormir();

                }
                tblInterrupciones_FCFS.setValueAt("Terminado", ContadorInterrupciones_FCFS - 1, 5);
            } else if (tipo == 2) {
                for (int j = 1; j <= TiempoInterrupcion; j++) {
                    tblInterrupciones_SJF.setValueAt(String.valueOf(TiempoInterrupcion - j), ContadorInterrupciones_SJF - 1, 4);
                    Dormir();
                }
                tblInterrupciones_SJF.setValueAt("Terminado", ContadorInterrupciones_SJF - 1, 5);
            } else {
                for (int j = 1; j <= TiempoInterrupcion; j++) {
                    tblInterrupciones_RR.setValueAt(String.valueOf(TiempoInterrupcion - j), ContadorInterrupciones_RR - 1, 4);
                    Dormir();
                }
                tblInterrupciones_RR.setValueAt("Terminado", ContadorInterrupciones_RR - 1, 5);
            }

            CambioCola = true;
        }
    }

    public void IngresarInterrupcion(int tiempoInterrupcion, int IdProceso, int tipo) {
        DefaultTableModel modelo;
        Object[] miTabla = new Object[6];
        miTabla[1] = String.valueOf(IdProceso);
        if (tipo == 1) {
            ContadorInterrupciones_FCFS++;
            miTabla[0] = ContadorInterrupciones_FCFS;
            modelo = (DefaultTableModel) tblInterrupciones_FCFS.getModel();
            miTabla[2] = String.valueOf("FCFS");
        } else if (tipo == 2) {
            ContadorInterrupciones_SJF++;
            miTabla[0] = ContadorInterrupciones_SJF;
            modelo = (DefaultTableModel) tblInterrupciones_SJF.getModel();
            miTabla[2] = String.valueOf("SJF");
        } else {
            ContadorInterrupciones_RR++;
            miTabla[0] = ContadorInterrupciones_RR;
            modelo = (DefaultTableModel) tblInterrupciones_RR.getModel();
            miTabla[2] = String.valueOf("RR");
        }
        miTabla[3] = String.valueOf(tiempoInterrupcion);
        miTabla[4] = String.valueOf(tiempoInterrupcion);
        miTabla[5] = "Atendiendo";
        modelo.addRow(miTabla);
        if (tipo == 1) {
            tblInterrupciones_FCFS.setModel(modelo);

        } else if (tipo == 2) {
            tblInterrupciones_SJF.setModel(modelo);

        } else {
            tblInterrupciones_RR.setModel(modelo);

        }

    }

    public static void contarDatos(JTable table, int tipo) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        double terminados = 0;
        double listos = 0;
        double bloqueados = 0;
        double ejecutados = 0;

        for (int i = 0; i < dtm.getRowCount(); i++) {
            try {
                if (dtm.getValueAt(i, 4) == "Listo") {
                    listos++;
                } else if (dtm.getValueAt(i, 4) == "Terminado") {
                    terminados++;
                } else if (dtm.getValueAt(i, 4) == "Ejecutando") {
                    ejecutados++;
                } else {
                    bloqueados++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (tipo == 1) {
            contadorTerminados_FCFS = terminados;
            contadorListos_FCFS = listos;
            contadorEjecutando_FCFS = ejecutados;
            contadorBloqueados_FCFS = bloqueados;
        } else if (tipo == 2) {
            contadorTerminados_SJF = terminados;
            contadorListos_SJF = (int) listos;
            contadorEjecutando_SJF = (int) ejecutados;
            contadorBloqueados_SJF = bloqueados;
        } else {
            contadorTerminados_RR = terminados;
            contadorListos_RR = (int) listos;
            contadorEjecutando_RR = (int) ejecutados;
            contadorBloqueados_RR = bloqueados;
        }

    }

    public void ActualizaDescendientes(int IdProceso, int tipo) {
        dtoProceso actualiza = new dtoProceso();
        daoPCB_Expro daopcb = new daoPCB_Expro();
        ArrayList<Integer> nuevaLista = new ArrayList<Integer>();
        if (tipo == 1) {
            actualiza = ProcesosFCFS.get(IdProceso - 1);
            nuevaLista = actualiza.getDescendientes();
            nuevaLista.add(IdProcesoFCFS);
            actualiza.setDescendientes(nuevaLista);
            ProcesosFCFS.set(IdProceso - 1, actualiza);
            daopcb.ActualizaDescendientesPCB(this, IdProceso, tipo, IdProcesoFCFS);
        } else if (tipo == 2) {
            actualiza = ProcesosSJF.get(IdProceso - 1);
            nuevaLista = actualiza.getDescendientes();
            nuevaLista.add(IdProcesoSJF);
            actualiza.setDescendientes(nuevaLista);
            ProcesosSJF.set(IdProceso - 1, actualiza);
            daopcb.ActualizaDescendientesPCB(this, IdProceso, tipo, IdProcesoSJF);
        } else {
            actualiza = ProcesosRR.get(IdProceso - 1);
            nuevaLista = actualiza.getDescendientes();
            nuevaLista.add(IdProcesoRR);
            actualiza.setDescendientes(nuevaLista);
            ProcesosRR.set(IdProceso - 1, actualiza);
            daopcb.ActualizaDescendientesPCB(this, IdProceso, tipo, IdProcesoRR);
        }
    }

    public void CambiaEstado(int IdProceso, String estado, int tipo) {
        dtoProceso actualiza = new dtoProceso();
        daoPCB_Expro daopcb = new daoPCB_Expro();
        daopcb.ActualizaEstadoPCB(this, IdProceso, tipo, estado);
        if (tipo == 1) {
            actualiza = ProcesosFCFS.get(IdProceso - 1);
            actualiza.setEstadoProceso(estado);
            ProcesosFCFS.set(IdProceso - 1, actualiza);
        } else if (tipo == 2) {
            actualiza = ProcesosSJF.get(IdProceso - 1);
            actualiza.setEstadoProceso(estado);
            ProcesosSJF.set(IdProceso - 1, actualiza);
        } else {
            actualiza = ProcesosRR.get(IdProceso - 1);
            actualiza.setEstadoProceso(estado);
            ProcesosRR.set(IdProceso - 1, actualiza);
        }
    }

    public void MostrarProgreso(int avance, int total, Integer Id, int tipo) {
        int porcentaje = (avance * 100) / total;
        if (tipo == 1) {
            txtIdProcesoFCFS.setText(Id.toString());
            txtPorcentajeFCFS.setText(String.valueOf(porcentaje + "%"));
            pgrFCFS.setValue(porcentaje);
            pgrFCFS.repaint();
        } else if (tipo == 2) {
            txtIdProcesoSJF.setText(Id.toString());
            txtPorcentajeSJF.setText(String.valueOf(porcentaje + "%"));
            pgrSJF.setValue(porcentaje);
            pgrSJF.repaint();
        } else {
            txtIdProcesoRR.setText(Id.toString());
            txtPorcentajeRR.setText(String.valueOf(porcentaje + "%"));
            pgrRR.setValue(porcentaje);
            pgrRR.repaint();
        }
    }

    public void AsignaInicio(int Id, int tipo) {
        PairTiempo par = new PairTiempo();
        daoPCB_Expro daopcb = new daoPCB_Expro();
        daopcb.ActualizaEstadisticaINI(VentanaEstadisticas, Id, tipo, TimeTot);
        if (tipo == 1) {
            par = DuracionFCFS.get(Id - 1);
            par.setInicio(TimeTot);
            DuracionFCFS.set(Id - 1, par);
            T_INI_FCFS += TimeTot;
            cnt_INI_FCFS++;
            VentanaEstadisticas.txtEsperaFCFS.setText(String.valueOf((T_INI_FCFS * 1.0) / (cnt_INI_FCFS * 1.0)));
        } else if (tipo == 2) {
            par = DuracionSJF.get(Id - 1);
            par.setInicio(TimeTot);
            DuracionSJF.set(Id - 1, par);
            T_INI_SJF += TimeTot;
            cnt_INI_SJF++;
            VentanaEstadisticas.txtEsperaSJF.setText(String.valueOf((T_INI_SJF * 1.0) / (cnt_INI_SJF * 1.0)));
        } else {
            par = DuracionRR.get(Id - 1);
            par.setInicio(TimeTot);
            DuracionRR.set(Id - 1, par);
            T_INI_RR += TimeTot;
            cnt_INI_RR++;
            VentanaEstadisticas.txtEsperaRR.setText(String.valueOf((T_INI_RR * 1.0) / (cnt_INI_RR * 1.0)));
        }

    }

    public void AsignaFin(int Id, int tipo) {
        PairTiempo par = new PairTiempo();
        daoPCB_Expro daopcb = new daoPCB_Expro();
        daopcb.ActualizaEstadisticaFIN(VentanaEstadisticas, Id, tipo, TimeTot);
        if (tipo == 1) {
            int inicio = DuracionFCFS.get(Id - 1).getInicio().intValue();
            par = DuracionFCFS.get(Id - 1);
            par.setFin(TimeTot);
            DuracionFCFS.set(Id - 1, par);
            T_FIN_FCFS += TimeTot;
            cnt_FIN_FCFS++;
            VentanaEstadisticas.txtRetornoFCFS.setText(String.valueOf((T_FIN_FCFS * 1.0) / (cnt_FIN_FCFS * 1.0)));
        } else if (tipo == 2) {
            int inicio = DuracionSJF.get(Id - 1).getInicio().intValue();
            par = DuracionSJF.get(Id - 1);
            par.setFin(TimeTot);
            DuracionSJF.set(Id - 1, par);
            T_FIN_SJF += TimeTot;
            cnt_FIN_SJF++;
            VentanaEstadisticas.txtRetornoSJF.setText(String.valueOf((T_FIN_SJF * 1.0) / (cnt_FIN_SJF * 1.0)));
        } else {
            int inicio = DuracionRR.get(Id - 1).getInicio().intValue();
            par = DuracionRR.get(Id - 1);
            par.setFin(TimeTot);
            DuracionRR.set(Id - 1, par);
            T_FIN_RR += TimeTot;
            cnt_FIN_RR++;
            VentanaEstadisticas.txtRetornoSJF.setText(String.valueOf((T_FIN_RR * 1.0) / (cnt_FIN_RR * 1.0)));
        }
    }

    public void IngresaEstadistica(int id, int burst, int tipo) {
        DefaultTableModel modelo1 = (DefaultTableModel) VentanaEstadisticas.tblEstadisticaFCFS.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) VentanaEstadisticas.tblEstadisticaSJF.getModel();
        DefaultTableModel modelo3 = (DefaultTableModel) VentanaEstadisticas.tblEstadisticaRR.getModel();
        Object[] miTabla = new Object[4];
        miTabla[0] = String.valueOf(id);
        miTabla[1] = String.valueOf(burst);
        miTabla[2] = String.valueOf("-1");
        miTabla[3] = String.valueOf("-1");
        if (tipo == 1) {
            modelo1.addRow(miTabla);
            VentanaEstadisticas.tblEstadisticaFCFS.setModel(modelo1);
        } else if (tipo == 2) {
            modelo2.addRow(miTabla);
            VentanaEstadisticas.tblEstadisticaSJF.setModel(modelo2);
        } else {
            modelo3.addRow(miTabla);
            VentanaEstadisticas.tblEstadisticaRR.setModel(modelo3);
        }
    }

    public void MuestraUsoCPU() {
        VentanaEstadisticas.txtCPUFCFS.setText(String.valueOf((BurstFCFS * 100.0) / ((BurstFCFS + BurstSJF + BurstRR) * 1.0)) + '%');
        VentanaEstadisticas.txtCPUSJF.setText(String.valueOf((BurstSJF * 100.0) / ((BurstFCFS + BurstSJF + BurstRR) * 1.0)) + '%');
        VentanaEstadisticas.txtCPURR.setText(String.valueOf((BurstRR * 100.0) / ((BurstFCFS + BurstSJF + BurstRR) * 1.0)) + '%');
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JScrollPane JPanel_FCFS;
    private static javax.swing.JPanel JPanel_RR;
    private static javax.swing.JScrollPane JPanel_SJF;
    private javax.swing.JButton btnAnadirProcesoUsuario;
    private javax.swing.JButton btnDefinirQuantum;
    private javax.swing.JButton btnDefinirQuantum1;
    private javax.swing.JButton btnDefinirQuantum4;
    private javax.swing.JButton btnGenerarAleatorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNProceso;
    private javax.swing.JLabel jLabelNProceso1;
    private javax.swing.JLabel jLabelNProceso2;
    private javax.swing.JLabel jLabelPorcentaje;
    private javax.swing.JLabel jLabelPorcentaje1;
    private javax.swing.JLabel jLabelPorcentaje2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JProgressBar pgrFCFS;
    private javax.swing.JProgressBar pgrRR;
    private javax.swing.JProgressBar pgrSJF;
    public static javax.swing.JTable tblFCFS;
    public static javax.swing.JTable tblInterrupciones_FCFS;
    public static javax.swing.JTable tblInterrupciones_RR;
    public static javax.swing.JTable tblInterrupciones_SJF;
    public static javax.swing.JTable tblPCB_FCFS;
    public static javax.swing.JTable tblPCB_RR;
    public static javax.swing.JTable tblPCB_SJF;
    public static javax.swing.JTable tblRR;
    public static javax.swing.JTable tblSJF;
    private javax.swing.JTextField txtBurstTime;
    private javax.swing.JTextField txtCntFCFS;
    private javax.swing.JTextField txtCntRR;
    private javax.swing.JTextField txtCntSJF;
    private javax.swing.JTextField txtIdProcesoFCFS;
    private javax.swing.JTextField txtIdProcesoRR;
    private javax.swing.JTextField txtIdProcesoSJF;
    private javax.swing.JTextField txtPorcentajeFCFS;
    private javax.swing.JTextField txtPorcentajeRR;
    private javax.swing.JTextField txtPorcentajeSJF;
    private javax.swing.JTextField txtQuantum;
    private javax.swing.JTextField txtTamano;
    // End of variables declaration//GEN-END:variables
}
