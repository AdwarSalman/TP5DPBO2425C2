    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;

    public class ProductMenu extends JFrame {
        public static void main(String[] args) {
            // buat object window
            ProductMenu menu = new ProductMenu();

            // atur ukuran window
            menu.setSize(700,600);

            // letakkan window di tengah layar
            menu.setLocationRelativeTo(null);

            // isi window
            menu.setContentPane(menu.mainPanel);

            // ubah warna background
            menu.getContentPane().setBackground(Color.lightGray);

            // tampilkan window
            menu.setVisible(true);

            // agar program ikut berhenti saat window diclose
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        // index baris yang diklik
        private int selectedIndex = -1;

        // list untuk menampung semua produk
        private Database database;
        private JPanel mainPanel;
        private JTextField idField;
        private JTextField namaField;
        private JTextField hargaField;
        private JTable productTable;
        private JButton addUpdateButton;
        private JButton cancelButton;
        private JButton deleteButton;
        private JComboBox<String> kategoriComboBox;
        private JLabel stokLabel;     // <--- DEKLARASI KOMPONEN BARU 1 (Stok)
        private JLabel merekLabel;   // <--- DEKLARASI KOMPONEN BARU 2 (Merek)
        private JLabel titleLabel;
        private JLabel idLabel;
        private JLabel namaLabel;
        private JLabel hargaLabel;
        private JLabel kategoriLabel;
        private JSpinner stokSpinner;   // untuk input stok menggunakan spinner untuk bonus
        private JTextField merekField;  // untuk input merek


        // constructor
        public ProductMenu() {
            //buat objek database
            database = new Database();

            // isi tabel produk
            productTable.setModel(setTable());

            // ubah styling title
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

            // atur isi combo box
            String[] kategoriData = { "???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis" };
            kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

            // sembunyikan button delete
            deleteButton.setVisible(false);

            // saat tombol add/update ditekan
            addUpdateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedIndex == -1) {
                        insertData();
                    } else {
                        updateData();
                    }
                }
            });
            // saat tombol delete ditekan
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Panggil method yang sudah di isi dengan validasi dan confirmation prompt
                    deleteData();
                }
            });
            // saat tombol cancel ditekan
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearForm();
                }
            });
            // saat salah satu baris tabel ditekan
            productTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // Cek agar tidak crash jika tabel kosong
                    if (productTable.getSelectedRow() == -1) {
                        return;
                    }

                    // ubah selectedIndex menjadi baris tabel yang diklik
                    selectedIndex = productTable.getSelectedRow();

                    // 1. Ambil value dari tabel (DENGAN INDEX KOLOM YANG BENAR)
                    String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                    String curNama = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                    String curHarga = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                    String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();

                    // Stok: Index 5 (Harus di-parse ke int untuk JSpinner)
                    int curStok = Integer.parseInt(productTable.getModel().getValueAt(selectedIndex, 5).toString());

                    // Merek: Index 6
                    String curMerek = productTable.getModel().getValueAt(selectedIndex, 6).toString();

                    // 2. ubah isi textfield dan komponen
                    idField.setText(curId);
                    namaField.setText(curNama);
                    hargaField.setText(curHarga);
                    kategoriComboBox.setSelectedItem(curKategori);

                    // JSpinner menggunakan setter
                    stokSpinner.setValue(curStok);
                    merekField.setText(curMerek);

                    // 3. Logika Tombol
                    // ubah button "Add" menjadi "Update"
                    addUpdateButton.setText("Update");

                    // tampilkan button delete
                    deleteButton.setVisible(true);
                }
            });
        }

        public final DefaultTableModel setTable() {
            // tentukan kolom tabel
            Object[] cols = { "No", "ID", "Nama", "Harga", "Kategori", "Stok", "Merek" };

            // buat objek tabel dengan kolom yang sudah dibuat
            DefaultTableModel tmp = new DefaultTableModel(null, cols);

            try {
                ResultSet resultSet = database.selectQuery("SELECT no, id, nama, harga, kategori, stok, merek FROM product");
                // isi tabel dengan hasil query
                int i = 0;
                while (resultSet.next()) {
                    Object[] row = new Object[7];
                    row[0] = resultSet.getInt("no");         // <-- kolom auto increment dari DB
                    row[1] = resultSet.getString("id");
                    row[2] = resultSet.getString("nama");
                    row[3] = resultSet.getDouble("harga");
                    row[4] = resultSet.getString("kategori");
                    row[5] = resultSet.getInt("stok");
                    row[6] = resultSet.getString("merek");

                    tmp.addRow(row);
                    i++;
                }
            } catch (SQLException e) {
                // lempar ulang exception dalam bentuk runtime exception
                throw new RuntimeException(e);
            }

            return tmp;
        }

        public void insertData() {
            try {
                String id = idField.getText().trim();
                String nama = namaField.getText().trim();
                double harga = Double.parseDouble(hargaField.getText().trim());
                String kategori = kategoriComboBox.getSelectedItem().toString();
                int stok = (int) stokSpinner.getValue();
                String merek = merekField.getText().trim();

                // validasi input kosong
                if (id.isEmpty() || nama.isEmpty() || merek.isEmpty() || kategori.equals("???")) {
                    JOptionPane.showMessageDialog(mainPanel, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // cek ID sudah ada di database
                ResultSet rs = database.selectQuery("SELECT id FROM product WHERE id = '" + id + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(mainPanel, "ID sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // query insert
                String sqlQuery = "INSERT INTO product (id, nama, harga, kategori, stok, merek) VALUES ('"
                        + id + "', '" + nama + "', " + harga + ", '" + kategori + "', " + stok + ", '" + merek + "')";

                // jalankan query — nggak perlu catch SQLException di sini
                database.insertUpdateDeleteQuery(sqlQuery);

                // refresh tabel
                productTable.setModel(setTable());
                clearForm();

                JOptionPane.showMessageDialog(mainPanel, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


        public void updateData() {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Pilih data yang ingin diubah.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String id = idField.getText().trim();
                String nama = namaField.getText().trim();
                double harga = Double.parseDouble(hargaField.getText().trim());
                String kategori = kategoriComboBox.getSelectedItem().toString();
                int stok = (int) stokSpinner.getValue();
                String merek = merekField.getText().trim();

                if (id.isEmpty() || nama.isEmpty() || merek.isEmpty() || kategori.equals("???")) {
                    JOptionPane.showMessageDialog(mainPanel, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "UPDATE product SET nama='" + nama + "', harga=" + harga + ", kategori='" + kategori
                        + "', stok=" + stok + ", merek='" + merek + "' WHERE id='" + id + "'";

                database.insertUpdateDeleteQuery(sql);

                productTable.setModel(setTable());
                clearForm();

                JOptionPane.showMessageDialog(mainPanel, "Data berhasil diubah.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                selectedIndex = -1;

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


        // tombol Delete
        public void deleteData() {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String id = productTable.getModel().getValueAt(selectedIndex, 1).toString();

            int dialogResult = JOptionPane.showConfirmDialog(
                    mainPanel,
                    "Anda yakin ingin menghapus data dengan ID: " + id + "?",
                    "Konfirmasi Hapus Data",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (dialogResult == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM product WHERE id='" + id + "'";
                database.insertUpdateDeleteQuery(sql);

                productTable.setModel(setTable());
                clearForm();

                JOptionPane.showMessageDialog(mainPanel, "Data berhasil dihapus.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                selectedIndex = -1;
            }
        }



        public void clearForm() {
            // kosongkan semua textfield dan combo box
            idField.setText("");
            namaField.setText("");
            hargaField.setText("");
            kategoriComboBox.setSelectedIndex(0);
            stokSpinner.setValue(0); // <--- Reset JSpinner ke nilai awal (0)
            merekField.setText("");  // <--- Kosongkan JTextField Merek

            // ubah button "Update" menjadi "Add"
            addUpdateButton.setText("Add");

            // sembunyikan button delete
            deleteButton.setVisible(false);

            // ubah selectedIndexx menjadi -1 (tidak ada baris yang dipilih)
            selectedIndex = -1;
        }

    }