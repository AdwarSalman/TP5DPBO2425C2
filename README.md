# TP5DPBO2425C2

🛒 Aplikasi Manajemen Data Produk (Java Swing x MySQL)

Project ini merupakan aplikasi manajemen data produk berbasis Java Swing yang terhubung langsung dengan database MySQL.
Aplikasi ini dikembangkan untuk memenuhi Tugas Praktikum 5 mata kuliah Desain Pemrograman Berorientasi Objek (DPBO) 2024/2025 – Kelas C2,
dengan tujuan menerapkan konsep CRUD (Create, Read, Update, Delete) pada sistem berbasis GUI.

Melalui aplikasi ini, pengguna dapat menambahkan, mengubah, menghapus, dan melihat daftar produk secara langsung melalui tampilan grafis interaktif.
Setiap perubahan tersimpan secara otomatis di dalam database db_product.

🙏🏻 Janji

Saya Muhammad Adwar Salman dengan NIM 2300484 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

💡 Fitur Utama

Menampilkan daftar produk dalam tabel GUI.

Menambahkan data produk baru ke database (Create).

Mengubah data produk yang sudah ada (Update).

Menghapus data produk dari database (Delete).

Validasi input: tidak boleh ada kolom kosong dan ID produk harus unik.

Antarmuka GUI responsif menggunakan JTable, JComboBox, dan JOptionPane.

🗄️ Struktur Database

Database: db_product
Tabel: product

Kolom	Tipe Data	Keterangan
no	INT (AI)	Primary Key (Auto Increment)
id	VARCHAR(255)	ID unik produk
nama	VARCHAR(255)	Nama produk
harga	DOUBLE	Harga produk
kategori	VARCHAR(255)	Jenis produk
stok	INT	Jumlah stok produk
merek	VARCHAR(255)	Nama merek produk
🧰 Teknologi yang Digunakan
Komponen	Keterangan
☕ Java Swing	Pembuatan tampilan GUI aplikasi
🗄️ MySQL	Database utama untuk penyimpanan produk
🔗 JDBC (Java Database Connectivity)	Penghubung Java dan MySQL
💻 IntelliJ IDEA	IDE utama untuk pengembangan
📦 mysql-connector-j-9.4.0.jar	Library konektor untuk MySQL
📁 Struktur Class
Nama Class	Fungsi Utama
Product	Menyimpan struktur data produk (model)
Database	Mengatur koneksi dan eksekusi query SQL
ProductMenu	Mengelola GUI dan logika CRUD
🧠 Atribut dan Deskripsi
Product.java
Atribut	Tipe Data	Deskripsi
id	String	ID unik produk
nama	String	Nama produk
harga	double	Harga produk
kategori	String	Kategori produk
stok	int	Jumlah stok produk
merek	String	Merek produk
Database.java
Atribut	Tipe Data	Deskripsi
connection	Connection	Menyimpan koneksi ke database MySQL
statement	Statement	Menjalankan query SQL
Method	Deskripsi
Database()	Konstruktor yang membuka koneksi JDBC ke MySQL
selectQuery(String sql)	Menjalankan query SELECT dan mengembalikan ResultSet
insertUpdateDeleteQuery(String sql)	Menjalankan query INSERT, UPDATE, atau DELETE
getStatement()	Mengambil objek statement aktif
ProductMenu.java
Atribut	Komponen	Deskripsi
mainPanel	JPanel	Panel utama aplikasi
idField, namaField, hargaField, stokField	JTextField	Input data produk
kategoriComboBox, merekField	JComboBox / JTextField	Input kategori & merek
productTable	JTable	Menampilkan data produk
addButton, updateButton, deleteButton, cancelButton	JButton	Tombol aksi CRUD
idLabel, namaLabel, hargaLabel, kategoriLabel, stokLabel, merekLabel	JLabel	Label teks GUI
database	Database	Objek koneksi database
selectedIndex	int	Indeks baris tabel yang dipilih
🪟 Komponen GUI
Komponen	Fungsi
JPanel	Panel utama aplikasi
JLabel	Label teks untuk setiap field
JTextField	Input ID, Nama, Harga, Stok, Merek
JComboBox	Dropdown untuk memilih Kategori
JButton	Tombol aksi (Add, Update, Delete, Cancel)
JTable	Menampilkan seluruh data produk
JScrollPane	Membungkus JTable agar bisa di-scroll
⚙️ Method Utama di ProductMenu
Method	Fungsi
setTable()	Mengambil dan menampilkan seluruh data produk dari database
insertData()	Menambahkan produk baru (dengan validasi kolom & duplikat ID)
updateData()	Mengubah data produk berdasarkan ID
deleteData()	Menghapus produk berdasarkan ID
clearForm()	Mengosongkan input dan mengatur ulang tombol
main()	Menjalankan GUI utama aplikasi
🔄 Alur Program

Menjalankan Program

main() memanggil konstruktor ProductMenu()

GUI utama muncul di layar.

Menampilkan Data

setTable() dipanggil untuk mengambil seluruh data dari tabel product.

Data ditampilkan di JTable.

Menambahkan Data

Pengguna mengisi seluruh field dan klik Add.

Program akan:

Mengecek apakah kolom kosong.

Mengecek apakah ID sudah ada.

Jika valid:

INSERT INTO product (id, nama, harga, kategori, stok, merek)
VALUES (...);


Menampilkan pesan sukses dan memperbarui tabel.

Memperbarui Data

Pengguna memilih data → form otomatis terisi.

Klik Update untuk menyimpan perubahan.

Program menjalankan:

UPDATE product
SET nama='...', harga=..., kategori='...', stok=..., merek='...'
WHERE id='...';


Tabel diperbarui otomatis.

Menghapus Data

Klik Delete → muncul konfirmasi.

Jika disetujui:

DELETE FROM product WHERE id='...';


Tabel diperbarui.

Membatalkan Input

Tombol Cancel mengosongkan seluruh field input.

📸 Dokumentasi
