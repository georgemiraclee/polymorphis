import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Kelas MahasiswaUndip
class MahasiswaUndip {
    String nama;
    String nim;
    String fakultas;
    String prodi;
    int angkatan;

    public MahasiswaUndip(String nama, String nim, String fakultas, String prodi, int angkatan) {
        this.nama = nama;
        this.nim = nim;
        this.fakultas = fakultas;
        this.prodi = prodi;
        this.angkatan = angkatan;
    }

    public String getDetail() {
        return "Nama: " + nama + "\nNIM: " + nim + "\nFakultas: " + fakultas + "\nProdi: " + prodi + "\nAngkatan: " + angkatan;
    }

    public String getNIM() {
        return nim;
    }
}

// Kelas GUI
public class MahasiswaUndipGUI extends JFrame {
    private JTextArea textArea;
    private JTextField tfNama, tfNIM, tfAngkatan;
    private JComboBox<String> cbFakultas, cbProdi;
    private JButton btnTambah, btnTampilkan, btnHapus;
    private List<MahasiswaUndip> MahasiswaUndipList;
    private Map<String, String[]> fakultasProdiMap;

    public MahasiswaUndipGUI() {
        setTitle("Data Mahasiswa Undip");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        MahasiswaUndipList = new ArrayList<>();

        // Peta fakultas dan jurusan
        fakultasProdiMap = new HashMap<>();
        fakultasProdiMap.put("Teknik", new String[]{"Informatika", "Teknik Sipil", "Arsitektur"});
        fakultasProdiMap.put("Ekonomi", new String[]{"Manajemen", "Akuntansi", "Ekonomi Pembangunan"});
        fakultasProdiMap.put("Hukum", new String[]{"Ilmu Hukum", "Hukum Pidana", "Hukum Perdata"});
        fakultasProdiMap.put("Kedokteran", new String[]{"Kedokteran Umum", "Farmasi", "Kesehatan Masyarakat"});

        // Komponen input
        JLabel lblNama = new JLabel("Nama: ");
        tfNama = new JTextField();
        tfNama.setPreferredSize(new Dimension(250, 30));

        JLabel lblNIM = new JLabel("NIM: ");
        tfNIM = new JTextField();
        tfNIM.setPreferredSize(new Dimension(250, 30));

        JLabel lblFakultas = new JLabel("Fakultas: ");
        cbFakultas = new JComboBox<>(new String[]{"Teknik", "Ekonomi", "Hukum", "Kedokteran"});
        cbFakultas.setPreferredSize(new Dimension(250, 30));

        JLabel lblProdi = new JLabel("Prodi: ");
        cbProdi = new JComboBox<>(fakultasProdiMap.get("Teknik")); // Default value for Teknik
        cbProdi.setPreferredSize(new Dimension(250, 30));

        JLabel lblAngkatan = new JLabel("Angkatan: ");
        tfAngkatan = new JTextField();
        tfAngkatan.setPreferredSize(new Dimension(250, 30));

        // Tombol
        btnTambah = new JButton("Tambah Data");
        btnTambah.setPreferredSize(new Dimension(150, 30));

        btnTampilkan = new JButton("Tampilkan Semua Data");
        btnTampilkan.setPreferredSize(new Dimension(200, 30));

        btnHapus = new JButton("Hapus Data");
        btnHapus.setPreferredSize(new Dimension(150, 30));

        // Area Teks
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setPreferredSize(new Dimension(400, 200));

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
        add(btnHapus, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JScrollPane(textArea), gbc);

        // Event listener untuk tombol
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahDataMahasiswaUndip();
            }
        });

        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanSemuaData();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusDataMahasiswaUndip();
            }
        });

        // Event listener untuk combo box Fakultas
        cbFakultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFakultas = (String) cbFakultas.getSelectedItem();
                updateProdiComboBox(selectedFakultas);
            }
        });
    }

    private void updateProdiComboBox(String fakultas) {
        cbProdi.removeAllItems();
        for (String prodi : fakultasProdiMap.get(fakultas)) {
            cbProdi.addItem(prodi);
        }
    }

    private void tambahDataMahasiswaUndip() {
        String nama = tfNama.getText();
        String nim = tfNIM.getText();
        String fakultas = (String) cbFakultas.getSelectedItem();
        String prodi = (String) cbProdi.getSelectedItem();
        int angkatan = Integer.parseInt(tfAngkatan.getText());

        MahasiswaUndip mahasiswa = new MahasiswaUndip(nama, nim, fakultas, prodi, angkatan);
        MahasiswaUndipList.add(mahasiswa);

        tfNama.setText("");
        tfNIM.setText("");
        tfAngkatan.setText("");
        cbFakultas.setSelectedIndex(0);
        cbProdi.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil ditambahkan!");
    }

    private void tampilkanSemuaData() {
        StringBuilder sb = new StringBuilder();
        for (MahasiswaUndip m : MahasiswaUndipList) {
            sb.append(m.getDetail()).append("\n\n");
        }
        textArea.setText(sb.toString());
    }

    private void hapusDataMahasiswaUndip() {
        String nim = tfNIM.getText();
        boolean found = false;

        for (int i = 0; i < MahasiswaUndipList.size(); i++) {
            if (MahasiswaUndipList.get(i).getNIM().equals(nim)) {
                MahasiswaUndipList.remove(i);
                found = true;
                JOptionPane.showMessageDialog(this, "Data mahasiswa dengan NIM " + nim + " berhasil dihapus!");
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Data mahasiswa dengan NIM " + nim + " tidak ditemukan!");
        }

        tfNIM.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MahasiswaUndipGUI().setVisible(true);
            }
        });
    }
}