/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectneeraotongrod;

import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author student
 */
// This class represents a directed graph using adjacency 
// list representation 
class Vertex1 {

    private boolean isVisit;
    private String label;

    public Vertex1(String lb) {
        isVisit = false;
        label = lb;
    }

    public void setVisit(boolean visit) {
        isVisit = visit;
    }

    public boolean getVisit() {
        return isVisit;
    }

    public String getLabel() {
        return label;
    }
}

class Graph1 {

    int V; // No. of vertices 
    Vector<Integer>[] adj; // No. of vertices 
    private ArrayList<Vertex1> vertexList = new ArrayList<Vertex1>();
    private int[][] adjMatrix;
    private int matrixSize;
    int level; //int meter ;
    public String str2 = "";
    public static String[] header = {"Faculty of ", "Science",
        "Education",
        "Pharmacy",
        "Engineering",
        "Arts"};
    public static String[] header2 = {"Science",
        "Education",
        "Pharmacy",
        "Engineering",
        "Arts"};
    DefaultTableModel model1;
    // Constructor 

    @SuppressWarnings("unchecked")
    public Graph1(int V) {
        this.V = V;
        this.adj = new Vector[V * 2];
        matrixSize = 2;
        //vertexList = new ArrayList<>(V);
        initMatrix();
        //meter = new Random().nextInt(801)+500;

    }

    private void initMatrix() {
        adjMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < V * 2; i++) {
            adj[i] = new Vector<>();
        }
    }
//        Graph1(int V) 
//        { 
//            this.V = V; 
//            this.adj = new Vector[2 * V]; 
//  
//            for (int i = 0; i < 2 * V; i++) 
//                this.adj[i] = new Vector<>(); 
//        } 

    public Vertex1 getVertex(String str) {
        Vertex1 ver = new Vertex1("");
        String v = new String();
        for (int i = 0; i < vertexList.size(); i++) {
            if (((Vertex1) vertexList.get(i)).getLabel().equals(str)) {
                ver = (Vertex1) vertexList.get(i);
                break;
            }
        }//System.out.println("ver is "+ ver.getLabel());
        return ver;
    }
    //addVertex

    public void addVertex(Vertex1 v) {
        vertexList.add(v);
        if (matrixSize == vertexList.size()) {
            matrixSize = matrixSize * 2;
            int[][] tmpMatrix = adjMatrix;
            initMatrix();
            for (int i = 0; i < vertexList.size(); i++) {
                for (int j = 0; j < vertexList.size(); j++) {
                    adjMatrix[i][j] = tmpMatrix[i][j];
                }
            }
            tmpMatrix = null;
        }
    }
    // adds an edge 

    public void addEdge(String start, String end, int weight) {
        /*for(int i= 0;i< vertexList.size();i++){
            System.out.print("Edge: ");
            System.out.println(vertexList.get(i));
        }*/
        //System.out.println(start);
        String s[] = start.split(" ");
        String e[] = end.split(" ");
        //int v = vertexList.indexOf(s[2]);
        //int u = vertexList.indexOf(e[2]);
        int v = -1;
        int u = -1;
        //System.out.println(adjMatrix.length);
        
        for(int i = 0;i<vertexList.size();i++){
            //System.out.println(vertexList.get(i).getLabel());
            if (vertexList.get(i).getLabel().equalsIgnoreCase(s[2])){
                v = i;
            }
            if (vertexList.get(i).getLabel().equalsIgnoreCase(e[2])){
                u = i;
            }
        }
        //System.out.println(v+" "+u);
        adjMatrix[v][u] = weight;
        //adjMatrix[u][v] = weight;
        // split all edges of weight 2 into two 
        // edges of weight 1 each. The intermediate 
        // vertex number is maximum vertex number + 1, 
        // that is V. 
        if (weight == 2) {
            adj[v].add(v + this.V);
            adj[v + this.V].add(u);
        } else // Weight is 1 
        {
            adj[v].add(u); // Add w to v's list.
        }
    }
    //method showVertices()

    public String showVertices() {
        //System.out.println("--show  vertices--");
        String str = "";
        for (int i = 0; i < vertexList.size(); i++) {
            str += ((Vertex1) vertexList.get(i)).getLabel() + " \n\n";
        }

        return str;
    }
    //method showAdjacencyMatrix()

    public void showAdjacencyMatrix() {
        System.out.println("--showAdjacencyMatrix--");

        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < vertexList.size(); j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        Object[][] x = new Object[5][7];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (j == 0) {
                    x[i][j] = header2[i];
                } else if (j == 1) {
                    x[i][j] = adjMatrix[i][0];
                } else {
                    x[i][j] = adjMatrix[i][j - 1];
                }
            }
        }
        model1 = new DefaultTableModel(x, header);
    }
    // print shortest path from a source vertex 's' to 
    // destination vertex 'd'. 

    public int printShortestPath(int[] parent, int s, int d) {
        level = 0;

        // If we reached root of shortest path tree 
        if (parent[s] == -1) {
            str2 += "Shortest Path from " + ((Vertex1) vertexList.get(s)).getLabel()
                    + " to " + ((Vertex1) vertexList.get(d)).getLabel() + " is " + ((Vertex1) vertexList.get(s)).getLabel() + " ";
            return level;
        }
        //System.out.println("parent[s] =  " +parent[s]  );
        printShortestPath(parent, parent[s], d);

        level++;
        if (s < this.V) {
            str2 += ((Vertex1) vertexList.get(s)).getLabel() + " ";
        }

        return level;
    }

    // finds shortest path from source vertex 's' to 
    // destination vertex 'd'. 
    // This function mainly does BFS and prints the 
    // shortest path from src to dest. It is assumed 
    // that weight of every edge is 1 
    public int findShortestPath(String src, String dest) {
        boolean[] visited = new boolean[2 * V];
        int[] parent = new int[2 * V];
        //int indexOfsrc = vertexList.indexOf(src);
        //int indexOfdest = vertexList.indexOf(dest);
        //System.out.println("Src "+src.getLabel());
        int indexOfsrc = -1;
        int indexOfdest = -1;
        String sr[] = src.split(" ");
        String d[] = dest.split(" ");
        
        for(int i = 0;i<vertexList.size();i++){
            if (vertexList.get(i).getLabel().equalsIgnoreCase(sr[2])){
                indexOfsrc = i;
            }
            if (vertexList.get(i).getLabel().equalsIgnoreCase(d[2])){
                indexOfdest = i;
            }
        }
        System.out.println(indexOfsrc+" "+indexOfdest);
        //System.out.println("from findShortest--> indexOfsrc: "+ indexOfsrc + " and indexOfdest: " + indexOfdest);
        // Initialize parent[] and visited[] 
        for (int i = 0; i < 2 * V; i++) {
            visited[i] = false;
            parent[i] = -1;
        }

        // Create a queue for BFS 
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it 
        visited[indexOfsrc] = true;
        queue.add(indexOfsrc);
        while (!queue.isEmpty()) {
            
            // Dequeue a vertex from queue and print it 
            int s = queue.peek();
            //System.out.println("s is "+s);
            if (s == indexOfdest) {
                return printShortestPath(parent, s, indexOfdest) * 800;
            }
            queue.poll();
            // Get all adjacent vertices of the dequeued vertex s 
            // If a adjacent has not been visited, then mark it 
            // visited and enqueue it 
            for (int i : this.adj[s]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    parent[i] = s;
                }
            }
        }
        return 0;
    }
}

public class projectneeraotongrod extends javax.swing.JFrame {

    static Graph1 g = new Graph1(5);
    ; 
   String start, end;
    public static String[] header = {"Faculty of Science",
        "Faculty of Education",
        "Faculty of Pharmacy",
        "Faculty of Engineering",
        "Faculty of Arts"};

    /**
     * Creates new form ProjectNee
     */
    public projectneeraotongrod() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jaddEgde = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jShow = new javax.swing.JButton();
        jShowVer = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Faculty in Silpakorn Univerty");
        setBackground(new java.awt.Color(204, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jaddEgde.setBackground(new java.awt.Color(204, 204, 255));
        jaddEgde.setFont(new java.awt.Font("Letter Gothic Std", 1, 18)); // NOI18N
        jaddEgde.setText("addEdge");
        jaddEgde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddEgdeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Letter Gothic Std", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Start location");

        jLabel3.setFont(new java.awt.Font("Letter Gothic Std", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("End location");

        jLabel5.setFont(new java.awt.Font("Letter Gothic Std", 1, 24)); // NOI18N
        jLabel5.setText("Sorce");

        jLabel6.setFont(new java.awt.Font("Letter Gothic Std", 1, 24)); // NOI18N
        jLabel6.setText("Destination");

        jShow.setBackground(new java.awt.Color(204, 204, 255));
        jShow.setFont(new java.awt.Font("Letter Gothic Std", 1, 18)); // NOI18N
        jShow.setText("show ShortestPath& ShortDistance");
        jShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jShowActionPerformed(evt);
            }
        });

        jShowVer.setBackground(new java.awt.Color(204, 204, 255));
        jShowVer.setFont(new java.awt.Font("Letter Gothic Std", 1, 24)); // NOI18N
        jShowVer.setText("Press it before !!");
        jShowVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jShowVerActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(0, 204, 204));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Faculty of Science", "Faculty of Education", "Faculty of Pharmacy", "Faculty of Engineering", "Faculty of Arts" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setBackground(new java.awt.Color(0, 204, 204));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Faculty of Science", "Faculty of Education", "Faculty of Pharmacy", "Faculty of Engineering", "Faculty of Arts", "Faculty of Painting" }));

        jComboBox4.setBackground(new java.awt.Color(0, 204, 204));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Faculty of Science", "Faculty of Education", "Faculty of Pharmacy", "Faculty of Engineering", "Faculty of Arts", "Faculty of Painting" }));

        jComboBox5.setBackground(new java.awt.Color(0, 204, 204));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Faculty of Science", "Faculty of Education", "Faculty of Pharmacy", "Faculty of Engineering", "Faculty of Arts" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Letter Gothic Std", 1, 24)); // NOI18N
        jLabel1.setText("Scale for this map");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mimi\\Desktop\\8.jpg")); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(1390, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jShowVer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(735, 735, 735)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addComponent(jaddEgde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jShowVer)
                        .addGap(182, 182, 182)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jaddEgde, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(870, 870, 870)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jShow))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jaddEgdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddEgdeActionPerformed
        // TODO add your handling code here:
        try {
            String start = jComboBox5.getItemAt(jComboBox5.getSelectedIndex());
            String end = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
            //System.out.println(g.getVertex(start).getLabel());
            //System.out.println(g.getVertex(end).getLabel());
            g.addEdge(start, end, new Random().nextInt(2) + 1);
            g.showAdjacencyMatrix();
            jTable1.setModel(g.model1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.jShow, "Please input your data before.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jaddEgdeActionPerformed

    private void jShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jShowActionPerformed
        // TODO add your handling code here:
        try {
            //g.showAdjacencyMatrix();
            Vertex1 src = g.getVertex(jComboBox3.getItemAt(jComboBox3.getSelectedIndex()));
            Vertex1 dest = g.getVertex(jComboBox4.getItemAt(jComboBox4.getSelectedIndex()));
            System.out.println("src: " + src.getLabel());
            String str = "";
            str += g.findShortestPath(jComboBox3.getItemAt(jComboBox3.getSelectedIndex()), jComboBox4.getItemAt(jComboBox4.getSelectedIndex()));
            jTextArea2.setText("Shortest distance from " + src.getLabel() + " to " + dest.getLabel() + " is " + str + " m" + "\n" + g.str2);
//            jTextField9.setText(g.str2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.jShow, "Please input your data before.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        //jTextField9.setText(g.printShortestPath2(parent, w, w));
    }//GEN-LAST:event_jShowActionPerformed

    private void jShowVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jShowVerActionPerformed
        // TODO add your handling code here:
        Vertex1 v1 = new Vertex1("Science");
        Vertex1 v2 = new Vertex1("Education");
        Vertex1 v3 = new Vertex1("Pharmacy");
        Vertex1 v4 = new Vertex1("Engineering");
        Vertex1 v5 = new Vertex1("Arts");

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        

        jTextArea1.setText(g.showVertices());

    }//GEN-LAST:event_jShowVerActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(projectneeraotongrod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(projectneeraotongrod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(projectneeraotongrod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(projectneeraotongrod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new projectneeraotongrod().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jShow;
    private javax.swing.JButton jShowVer;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jaddEgde;
    // End of variables declaration//GEN-END:variables
}
