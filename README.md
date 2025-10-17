🛒 Aplikasi Manajemen Data Produk (Java Swing x MySQL)

Project ini merupakan aplikasi manajemen data produk berbasis Java Swing yang terhubung langsung dengan database MySQL.
Aplikasi ini dikembangkan untuk memenuhi Tugas Praktikum 5 mata kuliah Desain Pemrograman Berorientasi Objek (DPBO) 2024/2025 – Kelas C2,
dengan tujuan menerapkan konsep CRUD (Create, Read, Update, Delete) pada sistem berbasis GUI.

Melalui aplikasi ini, pengguna dapat menambahkan, mengubah, menghapus, dan melihat daftar produk secara langsung melalui tampilan grafis interaktif.
Setiap perubahan tersimpan secara otomatis di dalam database db_product.

🙏🏻 Janji

Saya Muhammad Adwar Salman dengan NIM 2401539 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

💡 Fitur Utama

• Menampilkan daftar produk dalam tabel GUI
• Menambahkan data produk baru ke database (Create)
• Mengubah data produk yang sudah ada (Update)
• Menghapus data produk dari database (Delete)
• Validasi input agar tidak ada kolom kosong
• ID produk harus unik
• Tampilan GUI interaktif dengan komponen JTable, JComboBox, dan JOptionPane

🗄️ Struktur Database

Database: db_product
Tabel: product

Kolom dalam tabel product:
• no → INT AUTO_INCREMENT (Primary Key)
• id → VARCHAR(255)
• nama → VARCHAR(255)
• harga → DOUBLE
• kategori → VARCHAR(255)
• stok → INT
• merek → VARCHAR(255)

🧰 Teknologi yang Digunakan

• Java Swing → untuk membangun tampilan GUI
• MySQL → sebagai sistem penyimpanan data
• JDBC (Java Database Connectivity) → menghubungkan Java dengan MySQL
• IntelliJ IDEA → IDE pengembangan utama
• mysql-connector-j-9.4.0.jar → library konektor MySQL

📦 Struktur Class
Class yang digunakan:

• Product → menyimpan struktur data produk (model)
• Database → mengatur koneksi dan eksekusi query SQL
• ProductMenu → mengelola GUI dan logika CRUD

🧠 Atribut dan Deskripsi
Product.java

Atribut:
• id → String → ID unik produk
• nama → String → Nama produk
• harga → double → Harga produk
• kategori → String → Kategori produk
• stok → int → Jumlah stok produk
• merek → String → Merek produk

Database.java

Atribut:
• connection → Connection → Menyimpan koneksi ke database MySQL
• statement → Statement → Menjalankan query SQL

Method:
• Database() → Konstruktor untuk membuka koneksi JDBC ke MySQL
• selectQuery(String sql) → Menjalankan perintah SELECT dan mengembalikan ResultSet
• insertUpdateDeleteQuery(String sql) → Menjalankan perintah INSERT, UPDATE, DELETE
• getStatement() → Mengambil objek statement aktif

ProductMenu.java

Atribut:
• mainPanel → JPanel → Panel utama aplikasi
• idField, namaField, hargaField, stokField → JTextField → Input data produk
• kategoriComboBox → JComboBox → Pilihan kategori
• merekField → JTextField → Input merek produk
• productTable → JTable → Menampilkan data produk dari database
• addButton, updateButton, deleteButton, cancelButton → JButton → Tombol aksi CRUD
• idLabel, namaLabel, hargaLabel, kategoriLabel, stokLabel, merekLabel → JLabel → Label teks GUI
• database → Database → Objek koneksi database
• selectedIndex → int → Menyimpan indeks baris yang dipilih

🪟 Komponen GUI

• JPanel → panel utama aplikasi
• JLabel → label teks untuk setiap field
• JTextField → input untuk ID, Nama, Harga, Stok, dan Merek
• JComboBox → dropdown kategori produk
• JButton → tombol aksi CRUD (Add, Update, Delete, Cancel)
• JTable → menampilkan seluruh data produk
• JScrollPane → pembungkus JTable agar bisa di-scroll

⚙️ Method Utama di ProductMenu

• setTable() → menampilkan seluruh data produk dari database ke JTable
• insertData() → menambahkan produk baru (dengan validasi kolom dan duplikat ID)
• updateData() → memperbarui data produk berdasarkan ID
• deleteData() → menghapus produk berdasarkan ID
• clearForm() → mengosongkan semua input dan mengatur ulang tombol
• main() → menjalankan GUI utama aplikasi

🔄 Alur Program

Menjalankan Program
• Method main() memanggil konstruktor ProductMenu()
• GUI utama muncul di layar

Menampilkan Data
• Method setTable() dijalankan untuk mengambil seluruh data dari tabel product
• Data ditampilkan di JTable

Menambah Data
• Pengguna mengisi form input dan klik tombol Add
• Program memvalidasi kolom kosong dan duplikat ID
• Jika valid, dijalankan query:

INSERT INTO product (id, nama, harga, kategori, stok, merek)
VALUES (...);


• Data baru tampil di tabel

Memperbarui Data
• Pengguna memilih data pada tabel → form otomatis terisi
• Klik Update untuk menyimpan perubahan
• Program menjalankan query:

UPDATE product
SET nama='...', harga=..., kategori='...', stok=..., merek='...'
WHERE id='...';


Menghapus Data
• Klik Delete dan konfirmasi dialog
• Jika disetujui, dijalankan query:

DELETE FROM product WHERE id='...';


• Data terhapus dari tabel

Membatalkan Input
• Tombol Cancel mengosongkan semua field input

📸 Dokumentasi

Insert

https://github.com/user-attachments/assets/681cb519-61d1-473c-97dc-1ec8d4618c75

Update + ID


https://github.com/user-attachments/assets/4d152f2c-2a58-4566-9a47-13bba872f856

