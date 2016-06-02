import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// zrobic zapis odczyt kontaktow z/do pliku
// serializacja deserializacja (obudowac w JFileChooser)
//wklej.to/U91mx

public class Main extends JFrame{
    private JList list1;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField tfName;
    private JTextField tfPhone;
    private JComboBox cbCategory;
    private JPanel main;
    private JButton editButton;
    private JTextField tfEmail;

    public void addMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");

        JMenu saveMenu = new JMenu("Save");
        //saveMenu.setMnemonic('S');
        JMenu loadMenu = new JMenu("Load");
        fileMenu.add(saveMenu);
        fileMenu.add(loadMenu);
        JMenuItem exportToText = new JMenuItem("Export");
        exportToText.setMnemonic('e');
        JMenuItem saveTo = new JMenuItem("Save");
        saveTo.setMnemonic('s');
        // export
        exportToText.setIcon((new ImageIcon("resources/images/export.png")));
        exportToText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_MASK));
        // save
        saveTo.setIcon((new ImageIcon("resources/images/import.gif")));
        saveTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));

        saveTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Text files", "txt");
                jFileChooser.setFileFilter(fileNameExtensionFilter);
                if(jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    String path = jFileChooser.getSelectedFile().getAbsolutePath() + ".txt";
                    //zapis
                }
                if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    String path = jFileChooser.getSelectedFile().getAbsolutePath() + ".txt";
                    //odczyt
                }
            }
        });

        saveMenu.add(exportToText);
        saveMenu.add(saveTo);

        JMenuItem importFromText = new JMenuItem("Import");
        importFromText.setMnemonic('i');
        JMenuItem loadFrom = new JMenuItem("Load");
        loadFrom.setMnemonic('l');
        //import
        importFromText.setIcon((new ImageIcon("resources/images/export.png")));
        importFromText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.ALT_MASK));
        //load
        loadFrom.setIcon((new ImageIcon("resources/images/import.gif")));
        loadFrom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_MASK));

        loadMenu.add(importFromText);
        loadMenu.add(loadFrom);
        menuBar.add(fileMenu);
    }

    public Main() {
        setContentPane(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenu();
        pack();

        cbCategory.setModel(new DefaultComboBoxModel<>(Category.values()));

        list1.setModel(new ContactModel());
        setVisible(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = tfName.getText();
                String phone = tfPhone.getText();
                String email = tfEmail.getText();
                Category category = (Category) cbCategory.getSelectedItem();

                Contact c = new Contact(name, phone, email, category);
                ContactModel model = (ContactModel) list1.getModel();
                model.add(c);

                tfName.setText("");
                tfPhone.setText("");
                tfEmail.setText("");
                cbCategory.setSelectedIndex(0);
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Contact c = (Contact) list1.getSelectedValue();
                tfName.setText(c.getName());
                tfPhone.setText(c.getPhone());
                tfEmail.setText(c.getEmail());
            }
        });
        {
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    deleteContact();
                }
            });
        }
        list1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_DELETE){
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if(result == JOptionPane.OK_OPTION){
                        deleteContact();
                    }

                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Contact c = (Contact) list1.getSelectedValue();

                c.setName(tfName.getText());
                c.setPhone(tfPhone.getText());
                c.setEmail(tfEmail.getText());
                c.setCategory((Category) cbCategory.getSelectedItem());
                ContactModel model = (ContactModel) list1.getModel();
                model.refresh();
            }
        });
    }

    private void deleteContact() {
        ContactModel model = (ContactModel) list1.getModel();
        Contact c = (Contact) list1.getSelectedValue();
        model.remove(c);
    }

    public static void main(String[] args) {

        Main mw = new Main();
    }
}