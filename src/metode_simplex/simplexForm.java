package metode_simplex;

import java.awt.Component;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class simplexForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form formDP
     */
    int kendala = Variable.DP_KENDALA;
    int variabel = Variable.DP_VARIABEL;
    // MATRIKS
    public double Z[] = new double[variabel + kendala + 2];
    public double K[][] = new double[kendala + 1][variabel + kendala + 1];
    public int KDS[] = new int[kendala + 1]; //INDEKS VARIABEL
    public String E[] = new String[kendala + 1];
    public double S[] = new double[kendala + 1];

    protected JTextField textField;
    protected JLabel label;
    protected JComboBox kombo;

    public simplexForm() {
        initComponents();
        setTitle("METODE YANG DIGUNAKAN : " + Variable.DP_PROBLEM);
        kendala = Variable.DP_KENDALA;
        variabel = Variable.DP_VARIABEL;
        jLabel1.setText(Variable.DP_PROBLEM); // PEMBERIAN JUDUL KASUS
        double tZ[] = new double[variabel + 1];
        double tK[][] = new double[kendala + 1][variabel + kendala + 1];
        String tE[] = new String[kendala + 1];
        double tS[] = new double[kendala + 1];
        String TargetD = "";
        if (Variable.DP_FILE) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(Variable.DP_PROBLEM));
                String line;
                int ik = 1;
                while ((line = in.readLine()) != null) {
                    String parts[] = line.split(";");
                    if (ik == 1) {

                    } else if (ik == 2) {
                        for (int i = 1; i <= variabel; i++) {
                            //System.out.println(parts[i]);
                            tZ[i] = Double.parseDouble(parts[i]);
                            TargetD = parts[0];
                        }
                    } else {
                        for (int i = 1; i <= variabel; i++) {
                            tK[ik - 2][i] = Double.parseDouble(parts[i]);
                        }
                        tE[ik - 2] = parts[variabel + 1];
                        tS[ik - 2] = Double.parseDouble(parts[variabel + 2]);
                    }
                    ik++;
                }
                in.close();
            } catch (IOException e) {

            }
        }
        int ent = 90;
        for (int i = 1; i <= variabel; i++) {
            textField = new JTextField();
            textField.setText(" ");
            textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            textField.setName("Z-" + i);
            jPanel1.add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent, 40, 60, -1));
            label = new JLabel();
            label.setText("X" + i);
            if (tZ[i] != 0.0) {
                textField.setText(tZ[i] + "");
            }
            jPanel1.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent + 25, 23, 60, -1));
            ent += 60;
        }
        ent = 90;
        int ust = 68;
        for (int i = 1; i <= kendala; i++) {
            for (int k = 1; k <= variabel; k++) {
                // NILAI VARIABEL MASING - MASING
                textField = new JTextField();
                textField.setText(" ");
                textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                textField.setName("KD-" + i + "-" + k);
                if (tK[i][k] != 0.0) {
                    textField.setText(tK[i][k] + "");
                }
                jPanel1.add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent, ust, 60, -1));
                ent += 60;
            }
            // KENDALA SAMA DENGAN
            kombo = new JComboBox();
            if ("<=".equals(tE[i])) {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"<=", ">=", "="}));
            } else if (">=".equals(tE[i])) {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{">=", "<=", "="}));
            } else if ("=".equals(tE[i])) {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"=", "<=", ">="}));
            } else {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"<=", ">=", "="}));
            }
            if ("Min".equals(TargetD)) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Min", "Maks"}));
            } else {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Maks", "Min"}));
            }
            kombo.setName("KE-" + i + "");
            
            jPanel1.add(kombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent, ust, 60, -1));
            // PENEMPATAN NILAI RHS
            textField = new JTextField();
            textField.setText(" ");
            textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            textField.setName("KS-" + i);
            if (tS[i] != 0.0) {
                textField.setText(tS[i] + "");
            }
            jPanel1.add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent + 63, ust, 60, -1));
            label = new JLabel();
            label.setText("kendala" + i);
            jPanel1.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, ust + 6, 60, -1));
            ent = 90;
            ust += 27;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Kelompok TRO");
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TEKNIK RISET OPERASIONAL");
        jLabel1.setName("soruetiket"); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PROSES SIMPLEKS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("SIMPAN MODEL KE EXCEL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(102, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("JENIS KASUS");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 51));
        jTextArea1.setColumns(1);
        jTextArea1.setForeground(java.awt.Color.blue);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jComboBox1.setBackground(new java.awt.Color(255, 102, 0));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Maks", "Min" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("LIHAT ALGORITMA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane1.setViewportView(jPanel1);

        jScrollPane4.setViewportView(jScrollPane1);

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("KETERBATASAN APLIKASI");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("HASIL AKHIR");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 204));
        jLabel4.setText("KELOMPOK TRO");

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("KONTAK DEVELOPER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setBackground(new java.awt.Color(255, 255, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 612, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(130, 130, 130))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton5)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public double putaran(double x) {
        return (double) Math.round((x * 100)) / 100;
    }

    public void error(String mesaj) {
        JOptionPane.showMessageDialog(this, mesaj, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void muat_matriks() {
        Component[] cList = jPanel1.getComponents();
        for (int x = 0; x < cList.length; x++) {
            if (cList[x] instanceof javax.swing.JTextField) {
                String value = ((javax.swing.JTextField) cList[x]).getName();
                String[] parts = value.split("-");
                if (parts[0].contains("Z")) {
                    int t = Integer.parseInt(parts[1]);
                    Z[t] = -1 * Double.parseDouble(((javax.swing.JTextField) cList[x]).getText());
                }
                if (parts[0].contains("KD")) {
                    int k = Integer.parseInt(parts[1]);
                    int d = Integer.parseInt(parts[2]);
                    K[k][d] = Double.parseDouble(((javax.swing.JTextField) cList[x]).getText());
                }
                if (parts[0].contains("KS")) {
                    int k = Integer.parseInt(parts[1]);
                    S[k] = Double.parseDouble(((javax.swing.JTextField) cList[x]).getText());
                    KDS[k] = variabel + k;
                }
            }
            if (cList[x] instanceof javax.swing.JComboBox) {
                String value = ((javax.swing.JComboBox) cList[x]).getName();
                if (value != null) {
                    String[] parts = value.split("-");
                    if (parts[0].contains("KE")) {
                        int k = Integer.parseInt(parts[1]);
                        E[k] = ((javax.swing.JComboBox) cList[x]).getSelectedItem().toString();
                    }
                }
            }
        }
    }

    public void tambah_teks(String x) {
        jTextArea1.setText(jTextArea1.getText() + x + "\n");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // MATRIKS PENCIPTAAN DARI VARIABEL 
        muat_matriks();
        // FORMASI MATRIKS VARIABEL
        String Target = jComboBox1.getSelectedItem().toString();
        jTable1.setModel(new DefaultTableModel());
        jTextArea1.setText("");
        boolean Maks = false;
        for (int k = 1; k <= kendala; k++) {
            if (E[k] != "<=") {
                error("SEMUA KENDALA HARUS <=");
                return;
            }
            if (S[k] < 0) {
                error("NILAI RHS TIDAK BOLEH KURANG DARI 0");
                return;
            }
        }
        if (Target == "Maks") {
            Maks = true;
        } else if (Target == "Min") {
            Maks = false;
        }
        // MENAMBAH SLAK VARIABEL
        for (int i = 1; i <= kendala; i++) {
            for (int k = 1; k <= kendala; k++) {
                if (i == k) {
                    K[i][variabel + k] = 1.00;

                } else {
                    K[i][variabel + k] = 0.00;
                }
            }
            Z[variabel + i] = 0.00;
        }
        // NILAI RHS Z
        Z[kendala + variabel + 1] = 0.0;
        // MEMULAI ITERASI
        boolean optimal = false;
        int itr = 1;

        // TABEL ITERASI
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setDefaultRenderer(String.class, centerRenderer);

        DefaultTableModel dtm;
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 0;
            }
        };
        jTable1.setModel(dtm);
        dtm.addColumn("BASIS");
        for (int k = 1; k <= (variabel + kendala); k++) {
            dtm.addColumn("X" + k);
        }

        dtm.addColumn("SOLUSI");
        for (int i = 0; i <= (variabel + kendala + 1); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        do {
            Vector rowx = new Vector();
            rowx.add(itr + " ITERASI");
            dtm.addRow(rowx);
            Vector row = new Vector();
            row.add("Z");
            for (int k = 1; k <= (variabel + kendala + 1); k++) {
                row.add(putaran(Z[k]));
                dtm.isCellEditable(1, k);
            }
            dtm.addRow(row);
            for (int i = 1; i <= (kendala); i++) {
                Vector row2 = new Vector();
                row2.add("X" + KDS[i]);
                for (int k = 1; k <= (variabel + kendala); k++) {
                    row2.add(putaran(K[i][k]));
                    dtm.isCellEditable(i, k);
                }
                row2.add(putaran(S[i]));
                dtm.addRow(row2);
            }

            if (Maks) {
                // JIKA YANG DIPILIH MODEL MAKSIMASI
                int PC = 0; // KOLOM UTAMA
                double PCV = 0; // NILAI BARIS UTAMA Z 
                for (int c = 1; c <= (variabel + kendala); c++) {
                    boolean dasarkoneksi = false;
                    for (int i = 1; i <= kendala; i++) {
                        if (dasarkoneksi) {

                        } else {
                            if (KDS[i] == c) {
                                dasarkoneksi = true;
                            }
                        }
                    }
                    double absolut = 0 - Z[c];
                    if ((absolut > PCV) && (!dasarkoneksi)) {
                        PCV = absolut;
                        PC = c;
                    }
                }
                int PR = 0;
                double PRV = 0;
                for (int k = 1; k <= kendala; k++) {
                    double absolut = S[k] / K[k][PC];
                    if (k == 1) {
                        PRV = absolut;
                    }
                    if ((absolut > 0) && (absolut <= PRV)) {
                        PRV = absolut;
                        PR = k;
                        
                    }
                }
                
                System.out.println(PR + "-" + PC);
                if (PC == 0) {
                    optimal = true;
                    tambah_teks("Jumlah Iterasi : " + itr);
                    tambah_teks("Nilai Optimum Maks Z = " + putaran(Z[variabel + kendala + 1]));
                    for (int k = 1; k <= kendala; k++) {
                        tambah_teks("Nilai X" + KDS[k] + " = " + putaran(S[k]));
                    }
                }

                if (!optimal) { // JIKA TABEL OPTIMAL
                    KDS[PR] = PC;
                    // PENENTUAN PIVOT BARU
                    double pivot = 0.00;
                    for (int k = 1; k <= (variabel + kendala); k++) {
                        if (k == 1) {
                            pivot = K[PR][PC];
                        }
                        double sel = (K[PR][k] / pivot);
                        K[PR][k] = sel;
                    }
                    S[PR] = S[PR] / pivot;
                    // Garis Baru Lainnya
                    for (int i = 1; i <= (kendala); i++) {
                        if (i != PR) {
                            pivot = K[i][PC];
                            for (int k = 1; k <= variabel + kendala; k++) {
                                K[i][k] = K[i][k] - (pivot * K[PR][k]);
                            }
                            S[i] = S[i] - (pivot * S[PR]);
                        }
                    }
                    // NILAI Z BARU
                    pivot = Z[PC];
                    for (int k = 1; k <= (variabel + kendala); k++) {
                        Z[k] = Z[k] - (pivot * K[PR][k]);
                    }
                    // NILAI SOLUSI Z
                    int cz = variabel + kendala + 1;
                    Z[cz] = Z[cz] - pivot * S[PR];
                }
            }
            if (!Maks) {
                // SELAIN MODEL MAKSIMASI
                int PC = 0; // KOLOM UTAMA
                double PCV = 0; // PENENTUAN NILAI Z UTAMA
                for (int c = 1; c <= (variabel + kendala); c++) {
                    boolean dasarkoneksi = false;
                    for (int i = 1; i <= kendala; i++) {
                        if (dasarkoneksi) {

                        } else {
                            if (KDS[i] == c) {
                                dasarkoneksi = true;
                            }
                        }
                    }
                    double absolut = Z[c];
                    if ((absolut > PCV) && (!dasarkoneksi)) {
                        PCV = absolut;
                        PC = c;
                    }
                }
                int PR = 0;
                double PRV = 0;
                for (int k = 1; k <= kendala; k++) {
                    double absolut = S[k] / K[k][PC];
                    if (k == 1) {
                        PRV = absolut;
                    }
                    if ((absolut > 0) && (absolut <= PRV)) {
                        PRV = absolut;
                        PR = k;
                    }
                    
                }

                if (PC == 0) {
                    optimal = true;
                }
                if (PC == 0) {
                    optimal = true;
                    tambah_teks("Jumlah Iterasi : " + itr);
                    tambah_teks("Nilai Optimum Min Z = " + putaran(Z[variabel + kendala + 1]));
                    for (int k = 1; k <= kendala; k++) {
                        tambah_teks("Nilai X" + KDS[k] + " = " + putaran(S[k]));
                    }
                }
                if (!optimal) { // JIKA TABEL OPTIMAL
                    KDS[PR] = PC;
                    // BARIS PIVOT BARU
                    double pivot = 0.00;
                    for (int k = 1; k <= (variabel + kendala); k++) {
                        if (k == 1) {
                            pivot = K[PR][PC];
                        }
                        double sel = (K[PR][k] / pivot);
                        K[PR][k] = sel;
                    }
                    S[PR] = S[PR] / pivot;
                    // GARIS BARU LAINNYA
                    for (int i = 1; i <= (kendala); i++) {
                        if (i != PR) {
                            pivot = K[i][PC];
                            for (int k = 1; k <= variabel + kendala; k++) {
                                K[i][k] = K[i][k] - (pivot * K[PR][k]);
                            }
                            S[i] = S[i] - (pivot * S[PR]);
                        }
                    }
                    // NILAI Z BARU
                    pivot = Z[PC];
                    for (int k = 1; k <= (variabel + kendala); k++) {
                        Z[k] = Z[k] - (pivot * K[PR][k]);
                    }
                    // NILAI SOLUSI Z
                    int cz = variabel + kendala + 1;
                    Z[cz] = Z[cz] - pivot * S[PR];
                }
            }
            itr++;
        } while (!optimal);
        // TABEL MODEL

    }//GEN-LAST:event_jButton1ActionPerformed
    public String spasi(int x) {
        String rtr = "";
        for (int i = 1; i <= x; i++) {
            rtr = rtr + " ";
        }
        return rtr;
    }

    public void DPress(String text, String berkas) {
        try {
            String content = text;
            File file = new File(berkas);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFileChooser FileChooser = new JFileChooser();
        FileChooser.setDialogTitle("Pilih tempat menyimpan model");
        int userSelection = FileChooser.showSaveDialog(this);
        String File = "";
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File FileToSave = FileChooser.getSelectedFile();
            File = FileToSave.getAbsolutePath() + ".csv";
        }
        muat_matriks();
        String Target = jComboBox1.getSelectedItem().toString();
        String text = "D=" + variabel + "=K=" + kendala + ";";
        for (int k = 1; k <= (variabel); k++) {
            text = text + "X" + k + ";";
        }
        text = text + "SAMA DENGAN;SOLUSI;\n";
        text = text + Target + ";";
        for (int k = 1; k <= (variabel); k++) {
            text = text + (-1 * Z[k]) + ";";
        }
        text = text + ";;\n";
        for (int i = 1; i <= (kendala); i++) {
            text = text + "KENDALA;";
            for (int k = 1; k <= (variabel); k++) {
                text = text + putaran(K[i][k]) + ";";
            }
            text = text + E[i] + ";";
            text = text + S[i] + ";";
            text = text + "\n";
        }
        DPress(text, File);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    algoritmaText hF = new algoritmaText();
    hF.setVisible(true);
    setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    keterbatasanAplikasi hF = new keterbatasanAplikasi();
    hF.setVisible(true);
    setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    kontakDeveloper hF = new kontakDeveloper();
    hF.setVisible(true);
    setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
