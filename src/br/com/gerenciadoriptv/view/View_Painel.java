package br.com.gerenciadoriptv.view;

import javax.swing.JOptionPane;
import br.com.gerenciadoriptv.model.Cliente;
import br.com.gerenciadoriptv.model.conexao.CrudCliente;
import br.com.gerenciadoriptv.model.conexao.ConnectionDB;
import br.com.gerenciadoriptv.model.encurtarlink.Encurtador;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class View_Painel extends javax.swing.JFrame {

    public View_Painel() {
        initComponents();
        LerTabela();
        jtfInicio.setText(PegarData());
        jtfVencimento.setText(PegarDataMaisTrinta());
    }

    public static void LembreteWhats(String nome2, String telefone2, String vencimento2) {
        String nome = nome2;
        String telefone = telefone2;
        String vencimento = vencimento2;

        System.out.println(nome);
        System.out.println(telefone);
        System.out.println(vencimento);

        try {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://api.whatsapp.com/send?phone=55" + telefone + "&text=Ol%C3%A1%20" + nome + "%2C%20gostaria%20de%20lembrar%20o%20vencimento%20do%20seu%20iptv%20dia%20" + vencimento + ".%20Para%20renovar%2C%20basta%20me%20avisar%20por%20aqui%20mesmo%20que%20eu%20irei%20atualizar%20o%20seu%20usu%C3%A1rio%20!"));
            } catch (URISyntaxException ex) {
                Logger.getLogger(Encurtador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Encurtador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String PegarData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        data = cal.getTime();

        return dateFormat.format(data);

    }

    public String PegarDataMaisTrinta() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataTeste = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataTeste);
        cal.add(Calendar.MONTH, 1);
        dataTeste = cal.getTime();
        return dateFormat.format(dataTeste);
    }

    public void RenovarCliente() {
        if (tb_clientes.getSelectedRow()
                != -1) {
            Cliente c = new Cliente();
            CrudCliente crud = new CrudCliente();

            c.setId((int) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 0));
            c.setVencimento(PegarDataMaisTrinta());

            crud.RenovarCliente(c);

            LerTabela();

        }

    }

    public static void AlterarCliente() {
        if (tb_clientes.getSelectedRow()
                != -1) {
            Cliente c = new Cliente();
            CrudCliente crud = new CrudCliente();

            c.setId((int) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 0));
            c.setNome(jtfNome.getText());
            c.setUsuario(jtfUsuario.getText());
            c.setSenha(jtfSenha.getText());
            c.setTelefone(jtfTelefone.getText());
            c.setEmail(jtfEmail.getText());
            c.setInicio(jtfInicio.getText());
            c.setVencimento(jtfVencimento.getText());

            crud.atualizarCliente(c);

            LerTabela();

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar.");
        }
    }

    public void CadastrarCliente() {
        Cliente c = new Cliente();
        try {
            c.setNome(jtfNome.getText());
            c.setUsuario(jtfUsuario.getText());
            c.setSenha(jtfSenha.getText());
            c.setTelefone(jtfTelefone.getText());
            c.setEmail(jtfEmail.getText());
            c.setInicio(jtfInicio.getText());
            c.setVencimento(jtfVencimento.getText());

            if (c.getNome().isEmpty() || c.getUsuario().isEmpty() || c.getSenha().isEmpty() || c.getTelefone().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome, Usuario, Senha e Telefone são obrigatórios");
            } else {
                CrudCliente crud = new CrudCliente();
                ConnectionDB dao = new ConnectionDB();
                crud.createCliente(c);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
        }
        LerTabela();
    }

    public static void LerTabela() {

        DefaultTableModel modelo = (DefaultTableModel) tb_clientes.getModel();
        modelo.setNumRows(0);
        CrudCliente crud = new CrudCliente();

        for (Cliente c : crud.readCliente()) {

            modelo.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getUsuario(),
                c.getSenha(),
                c.getTelefone(),
                c.getEmail(),
                c.getInicio(),
                c.getVencimento()
            });

        }
    }

    public void BuscarCliente(String nome) {

        DefaultTableModel modelo = (DefaultTableModel) tb_clientes.getModel();
        modelo.setNumRows(0);
        CrudCliente crud = new CrudCliente();

        for (Cliente c : crud.buscarCliente(nome)) {

            modelo.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getUsuario(),
                c.getSenha(),
                c.getTelefone(),
                c.getEmail(),
                c.getInicio(),
                c.getVencimento()
            });

        }
    }

    public void ExcluirCliente() {
        if (tb_clientes.getSelectedRow()
                != -1) {

            Cliente c = new Cliente();
            CrudCliente crud = new CrudCliente();

            c.setId((int) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 0));

            crud.deleteCliente(c);

            LerTabela();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione alguém para excluir.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_clientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnRenovar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnLembrete = new javax.swing.JButton();
        btnAbrirPainel = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnCadastrar2 = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jtfInicio = new javax.swing.JFormattedTextField();
        jtfNome = new javax.swing.JTextField();
        jtfVencimento = new javax.swing.JFormattedTextField();
        jtfEmail = new javax.swing.JTextField();
        jtfSenha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfTelefone = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tb_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "USUARIO", "SENHA", "TELEFONE", "E-MAIL", "INICIO", "VENCIMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_clientes);
        if (tb_clientes.getColumnModel().getColumnCount() > 0) {
            tb_clientes.getColumnModel().getColumn(0).setResizable(false);
            tb_clientes.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_clientes.getColumnModel().getColumn(1).setPreferredWidth(250);
            tb_clientes.getColumnModel().getColumn(2).setPreferredWidth(60);
            tb_clientes.getColumnModel().getColumn(3).setPreferredWidth(60);
            tb_clientes.getColumnModel().getColumn(4).setPreferredWidth(110);
            tb_clientes.getColumnModel().getColumn(5).setPreferredWidth(200);
            tb_clientes.getColumnModel().getColumn(6).setPreferredWidth(60);
            tb_clientes.getColumnModel().getColumn(7).setPreferredWidth(60);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gerenciamento"));

        btnRenovar.setText("Renovar");
        btnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarActionPerformed(evt);
            }
        });

        jButton2.setText("Bloquear");

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnLembrete.setText("Lembrete");
        btnLembrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLembreteActionPerformed(evt);
            }
        });

        btnAbrirPainel.setText("Abrir Painel");
        btnAbrirPainel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirPainelActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setToolTipText("");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCadastrar2.setText("Excluir");
        btnCadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar2ActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRenovar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrirPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLembrete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCadastrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRenovar)
                    .addComponent(btnAbrirPainel)
                    .addComponent(btnCadastrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLembrete)
                    .addComponent(btnLimpar)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnBuscar)
                    .addComponent(btnCadastrar2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        try {
            jtfInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jtfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomeActionPerformed(evt);
            }
        });

        try {
            jtfVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("Usuário");

        try {
            jtfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("Nome");

        jLabel6.setText("Início");

        jLabel2.setText("E-mail");

        jLabel7.setText("Vencimento");

        jLabel5.setText("Telefone");

        jLabel4.setText("Senha");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jtfTelefone)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jtfInicio)
                    .addComponent(jtfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(29, 29, 29)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomeActionPerformed

    }//GEN-LAST:event_jtfNomeActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        CadastrarCliente();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar2ActionPerformed
        String cliente = (String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 1);

        int alt = JOptionPane.showConfirmDialog(null, "Você vai excluir o usuário: " + cliente.split(" ")[0]);
        System.out.println(alt);
        if (alt == 0) {
            ExcluirCliente();
        }
    }//GEN-LAST:event_btnCadastrar2ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarCliente(JOptionPane.showInputDialog("Qual o nome do cliente:"));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int alt = JOptionPane.showConfirmDialog(null, "Você vai editar o usuário.");
        System.out.println(alt);
        if (alt == 0) {
            AlterarCliente();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_clientesMouseClicked
        jtfNome.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 1));
        jtfUsuario.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 2));
        jtfSenha.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 3));
        jtfTelefone.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 4));
        jtfEmail.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 5));
        jtfInicio.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 6));
        jtfVencimento.setText((String) tb_clientes.getValueAt(tb_clientes.getSelectedRow(), 7));
    }//GEN-LAST:event_tb_clientesMouseClicked

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        RenovarCliente();
    }//GEN-LAST:event_btnRenovarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        jtfNome.setText("");
        jtfUsuario.setText("");
        jtfSenha.setText("");
        jtfTelefone.setText("");
        jtfEmail.setText("");
        jtfInicio.setText(PegarData());
        jtfVencimento.setText(PegarDataMaisTrinta());

    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnLembreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLembreteActionPerformed

        LembreteWhats(jtfNome.getText().split(" ")[0], jtfTelefone.getText().replaceAll("[^0-9]", ""), jtfVencimento.getText());

    }//GEN-LAST:event_btnLembreteActionPerformed

    private void btnAbrirPainelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirPainelActionPerformed

        try {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("http://cms.cloudnation.vip/"));
            } catch (URISyntaxException ex) {
                Logger.getLogger(Encurtador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Encurtador.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnAbrirPainelActionPerformed

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
            java.util.logging.Logger.getLogger(View_Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_Painel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirPainel;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCadastrar2;
    private javax.swing.JButton btnLembrete;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRenovar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextField jtfEmail;
    public static javax.swing.JFormattedTextField jtfInicio;
    public static javax.swing.JTextField jtfNome;
    public static javax.swing.JTextField jtfSenha;
    public static javax.swing.JFormattedTextField jtfTelefone;
    public static javax.swing.JTextField jtfUsuario;
    public static javax.swing.JFormattedTextField jtfVencimento;
    public static javax.swing.JTable tb_clientes;
    // End of variables declaration//GEN-END:variables
}
