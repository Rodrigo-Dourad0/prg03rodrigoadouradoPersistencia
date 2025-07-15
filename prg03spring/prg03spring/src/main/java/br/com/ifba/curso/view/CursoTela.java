package br.com.ifba.curso.view;

import br.com.ifba.curso.controller.CursoController;
import br.com.ifba.curso.entity.Curso;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class CursoTela extends JFrame {

    @Autowired
    private CursoController controller;

    private JButton btnAdicionar;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JTable tblCursos;
    private JTextField txtPesquisar;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;

    public CursoTela() {
        initComponents();
    }

    @PostConstruct
    public void init() {
        getContentPane().setBackground(new Color(126, 192, 238));
        carregarCursosNaTabela();
        this.setVisible(true);
    }

    private void initComponents() {
        btnAdicionar = new JButton("➕");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        txtPesquisar = new JTextField("Pesquisar...");
        jLabel1 = new JLabel("Adicionar Curso");

        tblCursos = new JTable(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "ID", "Código", "Carga Horária", "Situação"}
        ));
        jScrollPane1 = new JScrollPane(tblCursos);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnAdicionar.addActionListener(evt -> btnAdicionarActionPerformed());
        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed());
        btnEditar.addActionListener(evt -> btnEditarActionPerformed());
        btnRemover.addActionListener(evt -> btnRemoverActionPerformed());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBuscar)
                                                .addGap(339, 339, 339)
                                                .addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(93, 93, 93)
                                                        .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar)
                                        .addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnRemoverActionPerformed() {
        String idStr = JOptionPane.showInputDialog(this, "Informe o ID do curso a ser removido:");
        if (idStr == null || idStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID do curso não informado.");
            return;
        }

        try {
            Long idCurso = Long.parseLong(idStr.trim());
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o curso com ID '" + idCurso + "'?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                Curso curso = controller.findById(idCurso);
                if (curso != null) {
                    controller.delete(curso);
                    JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
                    carregarCursosNaTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Curso com ID '" + idCurso + "' não encontrado.");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido! Informe um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover curso: " + e.getMessage());
        }
    }

    private void btnEditarActionPerformed() {
        String idStr = JOptionPane.showInputDialog(this, "Informe o ID do curso a ser editado:");
        if (idStr == null || idStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID do curso não informado.");
            return;
        }

        try {
            Long idCurso = Long.parseLong(idStr.trim());
            Curso curso = controller.findById(idCurso);
            if (curso == null) {
                JOptionPane.showMessageDialog(this, "Curso com ID '" + idCurso + "' não encontrado.");
                return;
            }

            JTextField campoNome = new JTextField(curso.getNome());
            JTextField campoCodigo = new JTextField(curso.getCodigo());
            JTextField campoCargaHoraria = new JTextField(String.valueOf(curso.getCargaHoraria()));
            JCheckBox checkAtivo = new JCheckBox("Ativo");
            checkAtivo.setSelected(curso.isAtivo());

            Object[] campos = {"Nome:", campoNome, "Código do Curso:", campoCodigo,
                    "Carga Horária:", campoCargaHoraria, "Situação:", checkAtivo};

            int opcao = JOptionPane.showConfirmDialog(this, campos,
                    "Editar Curso ID " + idCurso, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (opcao == JOptionPane.OK_OPTION) {
                curso.setNome(campoNome.getText());
                curso.setCodigo(campoCodigo.getText());
                curso.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText()));
                curso.setAtivo(checkAtivo.isSelected());

                controller.update(curso);
                JOptionPane.showMessageDialog(this, "Curso atualizado com sucesso!");
                carregarCursosNaTabela();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID ou carga horária inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao editar curso: " + e.getMessage());
        }
    }

    private void btnAdicionarActionPerformed() {
        JTextField campoNome = new JTextField();
        JTextField campoCodigo = new JTextField();
        JTextField campoCargaHoraria = new JTextField();

        Object[] campos = {"Nome:", campoNome, "Código do Curso:", campoCodigo, "Carga Horária:", campoCargaHoraria};

        int opcao = JOptionPane.showConfirmDialog(null, campos, "Adicionar Novo Curso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Curso curso = new Curso();
                curso.setNome(campoNome.getText());
                curso.setCodigo(campoCodigo.getText());
                curso.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText()));
                curso.setAtivo(true);
                controller.save(curso);
                JOptionPane.showMessageDialog(null, "Curso salvo com sucesso!");
                carregarCursosNaTabela();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Carga horária inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar curso: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnBuscarActionPerformed() {
        String nomeBusca = txtPesquisar.getText().trim();
        if (nomeBusca.isEmpty() || nomeBusca.equalsIgnoreCase("Pesquisar...")) {
            carregarCursosNaTabela();
            return;
        }

        try {
            List<Curso> cursos = controller.findByNome(nomeBusca);
            var model = (javax.swing.table.DefaultTableModel) tblCursos.getModel();
            model.setRowCount(0);

            for (Curso curso : cursos) {
                Object[] row = {
                        curso.getNome(),
                        curso.getId(),
                        curso.getCodigo(),
                        curso.getCargaHoraria(),
                        curso.isAtivo() ? "Ativo" : "Inativo"
                };
                model.addRow(row);
            }

            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum curso encontrado para: " + nomeBusca);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar cursos: " + e.getMessage());
        }
    }

    private void carregarCursosNaTabela() {
        try {
            List<Curso> cursos = controller.findAll();
            var model = (javax.swing.table.DefaultTableModel) tblCursos.getModel();
            model.setRowCount(0);

            for (Curso curso : cursos) {
                Object[] row = {
                        curso.getNome(),
                        curso.getId(),
                        curso.getCodigo(),
                        curso.getCargaHoraria(),
                        curso.isAtivo() ? "Ativo" : "Inativo"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar cursos: " + e.getMessage());
        }
    }
}
