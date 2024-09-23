import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Kelas utama Mahasiswa
class Mahasiswa {
    String nama;
    String nim;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getDetail() {
        return "Nama: " + nama + "\nNIM: " + nim;
    }
}

// Kelas Fakultas (turunan Mahasiswa)
class Fakultas extends Mahasiswa {
    String fakultas;

    public Fakultas(String nama, String nim, String fakultas) {
        super(nama, nim);
        this.fakultas = fakultas;
    }

    @Override
    public String getDetail() {
        return super.getDetail() + "\nFakultas: " + fakultas;
    }
}

// Kelas Prodi (turunan Fakultas)
class Prodi extends Fakultas {
    String prodi;

    public Prodi(String nama, String nim, String fakultas, String prodi) {
        super(nama, nim, fakultas);
        this.prodi = prodi;
    }

    @Override
    public String getDetail() {
        return super.getDetail() + "\nProdi: " + prodi;
    }
}

// Kelas Angkatan (turunan Prodi)
class Angkatan extends Prodi {
    int angkatan;

    public Angkatan(String nama, String nim, String fakultas, String prodi, int angkatan) {
        super(nama, nim, fakultas, prodi);
        this.angkatan = angkatan;
    }

    @Override
    public String getDetail() {
        return super.getDetail() + "\nAngkatan: " + angkatan;
    }
}

// Kelas GUI Program
public class MahasiswaGUI extends JFrame {
    private JTextArea textArea;
    private JTextField tfNama, tfNIM, tfAngkatan;
    private JComboBox<String> cbFakultas, cbProdi;
    private JButton btnTambah, btnTampilkan;
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaGUI() {
        setTitle("Data Mahasiswa Undip");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        mahasiswaList = new ArrayList<>();

        // Komponen input
        JLabel lblNama = new JLabel("Nama: ");
        tfNama = new JTextField();
        tfNama.setPreferredSize(new Dimension(250, 30)); // Set ukuran text field

        JLabel lblNIM = new JLabel("NIM: ");
        tfNIM = new JTextField();
        tfNIM.setPreferredSize(new Dimension(250, 30)); // Set ukuran text field

        JLabel lblFakultas = new JLabel("Fakultas: ");
        cbFakultas = new JComboBox<>(new String[]{"Teknik", "Ekonomi", "Hukum", "Kedokteran"});
        cbFakultas.setPreferredSize(new Dimension(250, 30)); // Set ukuran combo box

        JLabel lblProdi = new JLabel("Prodi: ");
        cbProdi = new JComboBox<>(new String[]{"Informatika", "Manajemen", "Ilmu Hukum", "Kedokteran Umum"});
        cbProdi.setPreferredSize(new Dimension(250, 30)); // Set ukuran combo box

        JLabel lblAngkatan = new JLabel("Angkatan: ");
        tfAngkatan = new JTextField();
        tfAngkatan.setPreferredSize(new Dimension(250, 30)); // Set ukuran text field

        // Tombol
        btnTambah = new JButton("Tambah Data");
        btnTambah.setPreferredSize(new Dimension(150, 30)); // Set ukuran tombol

        btnTampilkan = new JButton("Tampilkan Semua Data");
        btnTampilkan.setPreferredSize(new Dimension(200, 30)); // Set ukuran tombol

        // Area Teks
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setPreferredSize(new Dimension(400, 200)); // Perbesar area teks

        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(lblNama, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfNama, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        add(lblNIM, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfNIM, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        add(lblFakultas, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(cbFakultas, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProdi, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(cbProdi, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        add(lblAngkatan, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tfAngkatan, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(btnTambah, gbc);
        gbc.gridx = 1;
        add(btnTampilkan, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JScrollPane(textArea), gbc);

        // Event listener untuk tombol
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahDataMahasiswa();
            }
        });

        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanSemuaData();
            }
        });
    }

    private void tambahDataMahasiswa() {
        String nama = tfNama.getText();
        String nim = tfNIM.getText();
        String fakultas = (String) cbFakultas.getSelectedItem();
        String prodi = (String) cbProdi.getSelectedItem();
        int angkatan = Integer.parseInt(tfAngkatan.getText());

        Mahasiswa mahasiswa = new Angkatan(nama, nim, fakultas, prodi, angkatan);
        mahasiswaList.add(mahasiswa);

        // Reset form setelah menambah data
        tfNama.setText("");
        tfNIM.setText("");
        tfAngkatan.setText("");
        cbFakultas.setSelectedIndex(0);
        cbProdi.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil ditambahkan!");
    }

    private void tampilkanSemuaData() {
        StringBuilder sb = new StringBuilder();
        for (Mahasiswa m : mahasiswaList) {
            sb.append(m.getDetail()).append("\n\n");
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaGUI().setVisible(true);
            }
        });
    }
}
