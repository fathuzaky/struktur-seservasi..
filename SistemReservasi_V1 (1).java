import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// 1. STRUKTUR DATA
class StrukturReservasi {
    int ID;
    String nama;
    String kategori; 
    String tanggal;
    String waktu;
    int jumlahOrang;
    String Status;

    // kategori struktur
    public StrukturReservasi(int ID, String nama, String kategori, String tanggal, String waktu, int jumlahOrang, String Status) {
        this.ID = ID;
        this.nama = nama;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.jumlahOrang = jumlahOrang;
        this.Status = Status;
    }
}   

// 2. SISTEM RESERVASI (MAIN CLASS)
public class SistemReservasi_V1 {
    static final int MAX_DATA = 225; // kenapa 225 karena kita gak mau menghabiskan resource komputer dan sekiranya cukup untuk 6 bulan kedepan
    static StrukturReservasi[] DataR = new StrukturReservasi[MAX_DATA];
    static int totalData = 0;
    static int currentId = 1;
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "data_reservasi.txt";
   
    // 3. MAIN MENU
    public static void main(String[] args) {
        SistemMasuk(); 
        int pilihan;

        do {
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║                SISTEM RESERVASI RESTORAN               ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  [1] Tambah Reservasi Baru                             ║");
            System.out.println("║  [2] Tampilkan Semua Reservasi                         ║");
            System.out.println("║  [3] Edit Reservasi                                    ║");
            System.out.println("║  [4] Hapus Reservasi                                   ║");
            System.out.println("║  [5] Cari Reservasi                                    ║");
            System.out.println("║  [6] Urutkan Reservasi                                 ║");
            System.out.println("║  [7] Tampilkan Statistik                               ║");
            System.out.println("║  [8] Simpan/Load Data ke File                          ║");
            System.out.println("║  [0] Keluar                                            ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print(" [?] Pilih menu (0-8): ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.err.println("----------------------------");
            
            switch (pilihan) {
                case 1: tambahData(); break;
                case 2: tampilkanData(); break;
                case 3: editData(); break;
                case 4: hapusData(); break;
                case 5: MenuCari(); break;
                case 6: menuUrut(); break;
                case 7: TampilkanStatistik(); break;
                case 8: Pengelolaandata(); break;
                case 0: System.out.println("Terima kasih telah menggunakan sistem kami"); break;
                default: System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

   
    // 4. data dummy
    // Data contoh dummy
    static void SistemMasuk() {
        DataR[totalData++] = new StrukturReservasi(currentId++, "Badi", "Outdoor", "15-05-2026", "18:00", 4, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Audi", "Reguler", "15-05-2026", "19:30", 2, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Siti", "Outdoor", "17-05-2026", "12:00", 6, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Rina", "Reguler", "17-05-2026", "20:00", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Dewi", "Outdoor", "17-05-2026", "13:00", 5, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Andi", "Reguler", "20-05-2026", "18:30", 4, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Lina", "Outdoor", "22-05-2026", "19:00", 2, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Rudi", "Reguler", "22-05-2026", "12:30", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Maya", "Outdoor", "22-05-2026", "20:00", 6, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Fajar","Reguler", "20-05-2026", "18:00", 4, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Aji","Outdoor", "25-05-2026", "19:30", 2, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Sari","Reguler", "25-05-2026", "12:00", 6, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Rina","Outdoor", "25-05-2026", "20:00", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Dewi","Reguler", "28-05-2026", "13:00", 5, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Andi","Outdoor", "28-05-2026", "18:30", 4, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Lina","Reguler", "30-05-2026", "19:00", 2, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Rudi","Outdoor", "30-05-2026", "12:30", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Maya","Reguler", "01-06-2026", "20:00", 6, "Aktif");   
        DataR[totalData++] = new StrukturReservasi(currentId++, "Fajar","Outdoor", "01-06-2026", "18:00", 4, "Aktif");  
        DataR[totalData++] = new StrukturReservasi(currentId++, "Aji","Reguler", "01-06-2026", "19:30", 2, "Aktif");       
        DataR[totalData++] = new StrukturReservasi(currentId++, "Sari","Outdoor", "04-06-2026", "12:00", 6, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Rina","Reguler", "04-06-2026", "20:00", 10, "Aktif");              
        DataR[totalData++] = new StrukturReservasi(currentId++, "Andi","Reguler", "04-06-2026", "18:30", 4, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Lina","Outdoor", "08-06-2026", "19:00", 2, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Rudi","Reguler", "08-06-2026", "12:30", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Maya","Outdoor", "10-06-2026", "20:00", 6, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Dewi","Outdoor", "08-06-2026", "13:00", 5, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Line","Outdoor", "10-06-2026", "20:00", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Nisa","Reguler", "10-06-2026", "20:00", 3, "Aktif");
        DataR[totalData++] = new StrukturReservasi(currentId++, "Zaky","Outdoor", "10-06-2026", "20:00", 3, "Aktif");
    }

    
    // 5. CRUD (CREATE, READ, UPDATE, DELETE)
    // Fitur Tambah Data + Validasi Kapasitas Maksimal 30 Orang per Tanggal
    static void tambahData() {
        System.out.println("\n--- TAMBAH RESERVASI BARU ---");
        System.out.println("[1] lanjutkan menambah data reservasi baru");
        System.out.println("[2] Kembali ke Menu Utama");
        System.out.print("Pilih opsi: [1-2] ");
        int opsi = scanner.nextInt();
        scanner.nextLine(); 
        
        if (opsi == 2) {
            main(null);
            return;
        } else if (opsi != 1) {
            System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama===");
            main(null);
            return;
        }

        if (totalData >= MAX_DATA) {
            System.out.println("---Jumlah data sudah mencapai batas maksimum---");
            return;
        }
        
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan kategori (Reguler/Outdoor): ");
        String kategori = scanner.nextLine();
        System.out.print("Masukkan tanggal (DD-MM-YYYY): ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan waktu (HH:MM): ");
        String waktu = scanner.nextLine();
        System.out.print("Masukkan jumlah orang: ");
        int jumlahOrang = scanner.nextInt();
        scanner.nextLine();  
        
        int totalOrangDihariIni = 0;
        
        // Looping untuk ngitung total orang di tanggal & jam yang sama
        for (int i = 0; i < totalData; i++) {
            if (!DataR[i].Status.equalsIgnoreCase("Dihapus") && DataR[i].tanggal.equals(tanggal)) {
                totalOrangDihariIni += DataR[i].jumlahOrang;
            }
        }

        // Kalau total orang yang udah ada + orang yang mau daftar > 30, TOLAK!
        if (totalOrangDihariIni + jumlahOrang > 30) {
            int sisaSlot = 30 - totalOrangDihariIni;
            System.out.println("\n [!] GAGAL BOOKING: Kapasitas penuh!");
            System.out.println("     Tamu terdaftar di tgl " + tanggal + " : " + totalOrangDihariIni + " orang.");
            System.out.println("     Sisa slot yang tersedia                  : " + sisaSlot + " orang.");
            System.out.println("     Tindakan: Jumlah orang yang dimasukkan melebihi kapasitas, pilih tanggal lain/ hubungi manajer untuk info lebih lanjut.");
            return; // Keluar dari fungsi tanpa menambah data
        }

        DataR[totalData] = new StrukturReservasi(currentId++, nama, kategori, tanggal, waktu, jumlahOrang, "Aktif");
        totalData++;
        System.out.println("---Reservasi berhasil ditambahkan.---");
    }

    // Fitur Tampilkan Data
    static void tampilkanData() {
        if (totalData == 0) {
            System.out.println("---Tidak ada data reservasi.---");
            return;
        }

        // MEMBUAT SUB-MENU UNTUK 2 SECTION PILIHAN
        System.out.println("\n=== OPSI TAMPILKAN DATA ===");
        System.out.println("[1] Tampilkan Ringkasan Total Data (Berupa Angka)");
        System.out.println("[2] Tampilkan Semua Detail Reservasi (Tanpa Terkecuali)");
        System.out.println("[3] Kembali ke Menu Utama");
        System.out.print("Pilih opsi tampilan: ");
        int opsi = scanner.nextInt();
        scanner.nextLine(); // Membersihkan sisa enter
        
        // opsi 1 untuk menampilkan ringkasan total data reservasi aktif maupun tidak
        if (opsi == 1) {
            int aktif = 0;
            int tidakAktif = 0;

            for (int i = 0; i < totalData; i++) {
                if (DataR[i].Status.equalsIgnoreCase("Dihapus")) {
                    tidakAktif++;
                } else {
                    aktif++; 
                }
            }

            System.out.println("\n=== RINGKASAN TOTAL DATA RESERVASI ===");
            System.out.println("Jumlah Reservasi Aktif       : " + aktif);
            System.out.println("Jumlah Reservasi Tidak Aktif : " + tidakAktif);
            System.out.println("Total Keseluruhan Data       : " + totalData);
            System.out.println("---------------------------------------");

        } else if (opsi == 2) {
            // opsi 2 untuk menapilkan semua data secara detail, menggunakan static tampilkansatudata
            System.out.println("\n ======================= DAFTAR SEMUA DETAIL RESERVASI ========================");
            for (int i = 0; i < totalData; i++) {
                tampilkanSatuData(DataR[i]); 
            }
            System.out.println("--------------------------------------------------------------------------------");

        } else if (opsi == 3) {
            main(null);
            
        } else {
            System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama ===");
        }
    }

    // untuk edit data kita belum membuat fitur cancel, jadi untuk sementara akan di jadikan Next fitur saja
    // fitur untuk mengedit data reservasi ada cancel nya coyyy
    static void editData() {
        System.out.print("Masukkan ID reservasi yang ingin diedit: ");
        int id = scanner.nextInt();
        int cancel = -1;
        scanner.nextLine();

        for (int i = 0; i < totalData; i++) {
            if (DataR[i].ID == id) {
                System.out.println("Data ditemukan");
                System.out.println("Nama: " + DataR[i].nama);
                System.out.println("Kategori: " + DataR[i].kategori);
                System.out.println("Tanggal: " + DataR[i].tanggal);
                System.out.println("Waktu: " + DataR[i].waktu);
                System.out.println("Jumlah Orang: " + DataR[i].jumlahOrang);

                System.out.print("Apakah Anda yakin ingin mengubah data ini [y/n]: ");
                String konfirmasi = scanner.nextLine();
                
                if (konfirmasi.equalsIgnoreCase("n")) {
                    System.out.println("=== Perubahan dibatalkan, Kembali ke menu utama ===");
                    return;
                }

                String nama = "";
                while (nama.trim().isEmpty()) {
                    System.out.print("Masukkan nama baru                 : "); 
                    nama = scanner.nextLine(); 
                    if (nama.trim().isEmpty()) System.out.println(" [!] Error: Nama tidak boleh kosong!");
                }

                String kategori = "";
                while (kategori.trim().isEmpty()) {
                    System.out.print("Masukkan kategori baru (Reguler/Outdoor): ");
                    kategori = scanner.nextLine();
                    if (kategori.trim().isEmpty()) System.out.println(" [!] Error: Kategori tidak boleh kosong!");
                }

                String tanggal = "";
                while (tanggal.trim().isEmpty()) {
                    System.out.print("Masukkan tanggal baru (DD-MM-YYYY) : ");
                    tanggal = scanner.nextLine();
                    if (tanggal.trim().isEmpty()) System.out.println(" [!] Error: Tanggal tidak boleh kosong!");
                }

                String waktu = "";
                while (waktu.trim().isEmpty()) {
                    System.out.print("Masukkan waktu baru (HH:MM)        : ");
                    waktu = scanner.nextLine();
                    if (waktu.trim().isEmpty()) System.out.println(" [!] Error: Waktu tidak boleh kosong!");
                }

                int jumlahOrang = 0;
                while (true) {
                    System.out.print("Masukkan jumlah orang baru         : ");
                    try {
                        jumlahOrang = Integer.parseInt(scanner.nextLine());
                        if (jumlahOrang > 0) break; // Kalau bener, keluar dari loop
                        System.out.println(" [!] Error: Jumlah orang harus lebih dari 0!");
                    } catch (NumberFormatException e) {
                        System.out.println(" [!] Error: Input harus berupa ANGKA!");
                    }
                }

                int totalOrangDihariIni = 0;
                
                // Looping ngecek kapasitas di tanggal & waktu yang baru
                for (int j = 0; j < totalData; j++) {
                    if (!DataR[j].Status.equalsIgnoreCase("Dihapus") && 
                         DataR[j].tanggal.equals(tanggal) && 
                         DataR[j].waktu.equals(waktu) && 
                         DataR[j].ID != id) { // KUNCInya di sini Jangan hitung jumlah orang milik ID dia sendiri
                         
                        totalOrangDihariIni += DataR[j].jumlahOrang;
                    }
                }

                // Kalau kapasitas yang udah ada + jumlah orang edit > 30, TOLAK!
                if (totalOrangDihariIni + jumlahOrang > 30) {
                    int sisaSlot = 30 - totalOrangDihariIni;
                    System.out.println("\n [!] GAGAL EDIT: Kapasitas sesi penuh!");
                    System.out.println("     Tamu terdaftar di tgl " + tanggal + " jam " + waktu + " : " + totalOrangDihariIni + " orang.");
                    System.out.println("     Sisa slot yang tersedia                  : " + sisaSlot + " orang.");
                    System.out.println("      Tindakan: jumlah orang yang dimasukkan melebihi kapasitas, pilih tanggal lain/ hubungi manajer untuk info lebih lanjut.");
                    return; // Gagal edit, data lama tetap aman
                }

                DataR[i] = new StrukturReservasi(id, nama, kategori, tanggal, waktu, jumlahOrang, "Aktif");
                System.out.println("Reservasi berhasil diubah.");
                return;
               
            }
        }
        System.out.println("Reservasi dengan ID " + id + " tidak ditemukan.");
    }
    // kita sengaja tidak menambahkan parameter limit semisal ada yang kosong di bagian strukturnya, karena kita belum menambahkan fitur cancel
    // jadi sementara masih seperti ini

    // 6.hapus data
    static void hapusData() {
        System.out.println("\n=== [4] MENU HAPUS DATA ===");
        System.out.println("[1] Hapus Data Individual (Berdasarkan ID)");
        System.out.println("[2] Hapus Data Massal (Berdasarkan Nama)");
        System.out.println("[3] Hapus SEMUA Data (Reset Sistem)");
        System.out.println("[4] Kembali ke Menu Utama");
        System.out.print("Pilih opsi penghapusan [1-4]: ");
        int opsiHapus = scanner.nextInt();
        scanner.nextLine();

        if (opsiHapus == 4) {
            main(null);
        }

        // Hapus data berdasarkan ID 
        if (opsiHapus == 1) {
            System.out.print("\nMasukkan ID reservasi yang ingin dihapus: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            for (int i = 0; i < totalData; i++) {
                if (DataR[i].ID == id) {
                    if (DataR[i].Status.equalsIgnoreCase("Dihapus")) {
                        System.out.println("Reservasi dengan ID " + id + " sudah di hapus di dalam sistem!");
                        return;
                    }

                    System.out.println("\n--- DATA YANG AKAN DIHAPUS ---");
                    System.out.println("ID       : " + DataR[i].ID);
                    System.out.println("Nama     : " + DataR[i].nama);
                    System.out.println("Kategori : " + DataR[i].kategori);
                    System.out.println("------------------------------");

                    System.out.print("Apakah Anda yakin ingin menghapus data ini? (y/n): ");
                    String verif1 = scanner.nextLine();

                    if (!verif1.equalsIgnoreCase("y")) {
                        System.out.println("Penghapusan dibatalkan. Kembali ke menu utama.");
                        return; 
                    }

                    System.out.print("Ketik 'SETUJU' untuk memfinalisasi: ");
                    String verif2 = scanner.nextLine();

                    if (verif2.equals("SETUJU")) {
                        DataR[i].Status = "Dihapus"; 
                        System.out.println("Delete berhasil. Reservasi ID " + id + " telah dinonaktifkan.");
                    } else {
                        System.out.println("Kata kunci SALAH! Tindakan dibatalkan demi keamanan data.");
                    }
                    return; 
                }
            }
            System.out.println("Data dengan ID " + id + " tidak ditemukan.");

        // Hapus data yang memiliki kriteria nama sama
        } else if (opsiHapus == 2) {
            System.out.print("\nMasukkan NAMA pelanggan yang ingin dihapus: ");
            String targetNama = scanner.nextLine();
            int countData = 0;

            // Hitung dulu ada berapa data aktif dengan nama tersebut
            for (int i = 0; i < totalData; i++) {
                if (DataR[i].nama.equalsIgnoreCase(targetNama) && !DataR[i].Status.equalsIgnoreCase("Dihapus")) {
                    countData++;
                }
            }

            if (countData == 0) {
                System.out.println("Tidak ditemukan data aktif atas nama '" + targetNama + "'.");
                return;
            }

            System.out.println("Ditemukan " + countData + " reservasi aktif atas nama '" + targetNama + "'.");
            System.out.print("Yakin ingin menghapus SEMUANYA? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                
                System.out.print("Ketik 'SETUJU' untuk memfinalisasi penghapusan massal: ");
                if (scanner.nextLine().equals("SETUJU")) {
                    // Lakukan soft delete ke semua nama yang cocok
                    for (int i = 0; i < totalData; i++) {
                        if (DataR[i].nama.equalsIgnoreCase(targetNama)) {
                            DataR[i].Status = "Dihapus";
                        }
                    }
                    System.out.println("Delete massal sukses! " + countData + " reservasi atas nama " + targetNama + " telah dinonaktifkan.");
                } else {
                    System.out.println("Kata kunci SALAH! Tindakan dibatalkan.");
                }
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }

        // hapus semua data (reset sistem)
        } else if (opsiHapus == 3) {
            System.out.println("\n!!! PERINGATAN !!!");
            System.out.println("Tindakan ini akan me-reset/menghapus SELURUH data reservasi yang ada di sistem.");
            System.out.print("Apakah Anda benar-benar yakin? (y/n): ");
            
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Ketik 'KOSONGKAN' untuk mengeksekusi: ");
                
                if (scanner.nextLine().equals("KOSONGKAN")) {
                    // Loop ke semua data dan matikan statusnya (Soft Delete masal)
                    for (int i = 0; i < totalData; i++) {
                        DataR[i].Status = "Dihapus";
                    }
                    System.out.println("RESET BERHASIL! Seluruh data di dalam sistem telah di Hapus.");
                } else {
                    System.out.println("Kata kunci SALAH! silahkan coba lagi.");
                }
            } else {
                System.out.println("Tindakan dibatalkan. Kembali ke menu utama.");
            }

        } else {
            System.out.println("Opsi tidak valid. Kembali ke menu utama.");
        }
    }

    // 7. searching data    
    // rESERVASI CARI ADA 3 MENU CARA
    static void MenuCari() {
        System.out.println("=== MENU CARI RESERVASI ===");
        System.out.println("[1] Cari berdasarkan Nama");
        System.out.println("[2] Cari berdasarkan ID");
        System.out.println("[3] Cari berdasarkan Kategori Lainnya (Tanggal/Waktu/Jumlah Orang)");
        System.out.println("[4] Kembali ke Menu Utama");
        System.out.print("Pilih metode pencarian: [1-4] ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        
        if (pilihan == 1) {
            linearSearchNama();
        } else if (pilihan == 2) {
            bubbleSortID(); 
            binarySearchId();
        } else if (pilihan == 3) {
            cariKategori();
        } else if (pilihan == 4) {
            main(null);
        } else {
            System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama===");
        }
    }

    // SELURUH BAGIAN FUNCTION UNTUK SEARCHING
    // Linear Search
    static void linearSearchNama() { 
        System.out.print("Masukkan Nama yang dicari: ");
        String dicari = scanner.nextLine();
        boolean found = false;

        System.out.println("\n ---------------------------- Hasil Pencarian Nama ----------------------------");
        for (int i = 0; i < totalData; i++) {
            if (DataR[i].nama.toLowerCase().contains(dicari.toLowerCase()) && !DataR[i].Status.equals("Dihapus")) {
                tampilkanSatuData(DataR[i]);
                found = true;
            }
        }
        if (!found) System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama ===");
    }

    // Binary Search
    static void binarySearchId() { 
        System.out.print("Masukkan ID yang dicari: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int left = 0, right = totalData - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (DataR[mid].ID == id) {
                if (!DataR[mid].Status.equals("Dihapus")) {
                    tampilkanSatuData(DataR[mid]);
                    found = true;
                } else {
                    System.out.println("Data dengan ID " + id + " ditemukan, tetapi statusnya sudah 'Dihapus'.");
                    found = true;
                }
                break;
            } else if (DataR[mid].ID < id) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        if (!found) System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama ===");
    }

    // Cari Kategori 
    static void cariKategori() { 
        System.out.print("Masukkan Kategori yang dicari (Kategori/Tanggal/Waktu/Jumlah Orang): ");
        String target = scanner.nextLine();
        boolean found = false;

        System.out.println("\n--------------------------- Hasil Pencarian Kategori ---------------------------");
        for (int i = 0; i < totalData; i++) {
            if ((DataR[i].tanggal.equalsIgnoreCase(target) || 
                 DataR[i].waktu.equalsIgnoreCase(target) || 
                 DataR[i].kategori.equalsIgnoreCase(target) || 
                 String.valueOf(DataR[i].jumlahOrang).equals(target)) && 
                !DataR[i].Status.equals("Dihapus")){
                tampilkanSatuData(DataR[i]);
                found = true;
                
            }
        }
        if (!found) System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama ===");
    }

    // SELURUH FUNCTION ALGORITMA SORTING
    // 8. sorting data    
    static void menuUrut() {
        System.out.print("Pilih metode pengurutan:\n ");
        System.out.println("[1] Mengurutkan data berdasarkan ID");
        System.out.println("[2] Mengurutkan data berdasarkan Nama ");
        System.out.println("[3] Mengurutkan Berdasarkan Jumlah Orang Terbanyak");
        System.out.println("[4] Kembali ke Menu Utama");  
        System.out.print("Pilihan: [1-4] ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan == 1) {
            bubbleSortID();
        } else if (pilihan == 2) {
            selectionsortNama();
        } else if (pilihan == 3) {
            SortByJumlah();
        } else if (pilihan == 4) {
            main(null);
        } else {
            System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama ===");
        }

        System.out.println("--- Data berhasil diurutkan. Silakan cek menu Tampilkan Semua Reservasi. ---");
    }
    
    // FUNCTION UNTUK BUBBLE SORT 
    // Functions nya search bubble sort untuk binary search
    static void bubbleSortID() {
        for (int i = 0; i < totalData - 1; i++) {
            for (int j = 0; j < totalData - i - 1; j++) {
                if (DataR[j].ID > DataR[j + 1].ID) {
                    StrukturReservasi temp = DataR[j];
                    DataR[j] = DataR[j + 1];
                    DataR[j + 1] = temp;
                }
            }
        }
    }

    // Selection sort untuk mengurutkan berdasarkan nama secara alfabet
    static void selectionsortNama() {
        for (int i = 0; i < totalData - 1; i++) {
            int N = i;
            for (int j = i + 1; j < totalData; j++) {
                if (DataR[j].nama.compareToIgnoreCase(DataR[N].nama) < 0) {
                    N = j;
                }
            }
            StrukturReservasi temp = DataR[N];
            DataR[N] = DataR[i];
            DataR[i] = temp;
        }
    }

    // Bubble sort untuk mengurutkan berdasarkan jumlah orang terbanyak
    static void SortByJumlah() {
        for (int i = 0; i < totalData - 1; i++) {
            for (int j = 0; j < totalData - i - 1; j++) {
                if (DataR[j].jumlahOrang < DataR[j + 1].jumlahOrang) {
                    StrukturReservasi temp = DataR[j];
                    DataR[j] = DataR[j + 1];
                    DataR[j + 1] = temp;
                }
            }
        }
    }

    // 8. menampilkan data
    // ====================Menampilkan 1 data=======================
    static void tampilkanSatuData(StrukturReservasi r) {
        System.out.printf("| %-4d | %-25s | %-15s | %-10d | %-10s |\n",
                r.ID, r.nama, r.kategori, r.jumlahOrang, r.Status);
    }
    
    static void tampilkansemuaData() {
        if (totalData == 0) {
            System.out.println(" [!] Belum ada data yang tercatat.");
            return;
        }

        // 1. CETAK ATAP SPREADSHEET (Cuma sekali di paling atas)
        System.out.println("\n---  DATA SEMUA RESERVASI  ---");
        System.out.println("+------+---------------------------+-----------------+------------+------------+");
        System.out.printf("| %-4s | %-25s | %-15s | %-10s | %-10s |\n", "ID", "Nama Pemesan", "Kategori", "Jml Orang", "Status");
        System.err.println("+------+---------------------------+-----------------+------------+------------+");

        // 2. LOOPING ISI DATA (Manggil fungsi tampilkanSatuData yang udah rapi rata kiri)
        for (int i = 0; i < totalData; i++) {
            tampilkanSatuData(DataR[i]); 
        }

        // 3. CETAK LANTAI PENUTUP (Cuma sekali di paling bawah setelah loop beres)
        System.out.println("+------+---------------------------+-----------------+------------+------------+");
    }


    // 9. menampilkan statistik
    static void TampilkanStatistik() {
        if (totalData == 0) {
            System.out.println("Belum ada data untuk dianalisis.");
            return;
        }
        
        int aktif = 0, dihapus = 0, totalOrang = 0;
        int outdoor = 0, reguler = 0;

        String[] tglUnik = new String[totalData];
        int[] orangPerTgl = new int[totalData];
        int jumlahTglUnik = 0;

        // Looping sekali untuk ngitung semua metrik
        for (int i = 0; i < totalData; i++) {
            if (DataR[i].Status.equalsIgnoreCase("Dihapus")) {
                dihapus++;
            } else {
                aktif++;
                totalOrang += DataR[i].jumlahOrang;
                
                if (DataR[i].kategori.equalsIgnoreCase("Outdoor")) {
                    outdoor++;
                } else if (DataR[i].kategori.equalsIgnoreCase("Reguler")) {
                    reguler++;
                }
         
                String tgl = DataR[i].tanggal;
                int jmlTamu = DataR[i].jumlahOrang;
                boolean tglDitemukan = false;

                // Cari tanggal di array tglUnik
                for (int j = 0; j < jumlahTglUnik; j++) {
                    if (tglUnik[j].equals(tgl)) {
                        orangPerTgl[j] += jmlTamu; 
                        tglDitemukan = true;
                        break;
                    }
                }

                // Kalau belum ada, tambahkan sebagai tanggal baru
                if (!tglDitemukan) {
                    tglUnik[jumlahTglUnik] = tgl;
                    orangPerTgl[jumlahTglUnik] = jmlTamu;
                    jumlahTglUnik++;
                }
            }
        }

        for (int i = 0; i < jumlahTglUnik - 1; i++) {
            for (int j = 0; j < jumlahTglUnik - 1 - i; j++) {
                
                // kita pecah string tanggal jadi jadi bagian DDMMYYYY buat dibandingin nilainya
                String[] parts1 = tglUnik[j].split("-");
                int val1 = Integer.parseInt(parts1[2]) * 10000 + Integer.parseInt(parts1[1]) * 100 + Integer.parseInt(parts1[0]);

                String[] parts2 = tglUnik[j+1].split("-");
                int val2 = Integer.parseInt(parts2[2]) * 10000 + Integer.parseInt(parts2[1]) * 100 + Integer.parseInt(parts2[0]);
                // habis itu kita bandingin nilai val1 dan val2 buat tau mana yang lebih dulu semakin kecil nilainya, berarti semakin dulu tanggalnya

                // Bubble Sort
                if (val1 > val2) {
                    String tempTgl = tglUnik[j];
                    tglUnik[j] = tglUnik[j+1];
                    tglUnik[j+1] = tempTgl;

                    // Tukar Jumlah Orang 
                    int tempOrang = orangPerTgl[j];
                    orangPerTgl[j] = orangPerTgl[j+1];
                    orangPerTgl[j+1] = tempOrang;
                }
            }
        }
        // Mencari kategori terfavorit
        String terfavorit = ""; 
        int max = Math.max(reguler, outdoor);
        
        if (max == 0) {
            terfavorit = "--- Belum ada pesanan aktif ---";
        } else {
            String favs = "";
            if (reguler == max) favs += "Reguler ";
            if (outdoor == max) favs += "Outdoor ";
            // Merapikan teks jika ada yang seri (misal: "VIP & Reguler")
            terfavorit = favs.trim().replace(" ", " & ") + " (" + max + " pesanan)";
        }

        // Layout tampilan statistik
        System.out.println("\n--------------------------------------------------------");
        System.out.println("|             STATISTIK RESERVASI RESTORAN             |");
        System.out.println("--------------------------------------------------------");
        System.out.printf ("| %-52s |\n", "[ SUMMARY ]");
        System.out.printf ("| %-28s : %-21s |\n", " Total Semua Record", totalData + " data");
        System.out.printf ("| %-28s : %-21s |\n", " Reservasi Sesi Aktif", aktif + " reservasi");
        System.out.printf ("| %-28s : %-21s |\n", " Reservasi Dibatalkan", dihapus + " reservasi");
        System.out.printf ("| %-28s : %-21s |\n", " Total Tamu Hadir", totalOrang + " orang");
        System.out.println("--------------------------------------------------------");
        System.out.printf ("| %-52s |\n", "[ TREN KATEGORI (Sesi Aktif) ]");
        System.out.printf ("| %-28s : %-21s |\n", " Kategori Reguler", reguler + " pesanan");
        System.out.printf ("| %-28s : %-21s |\n", " Kategori Outdoor", outdoor + " pesanan");
        System.out.println("|------------------------------------------------------|");
        System.out.printf ("| %-16s %-35s |\n", " -> Terfavorit :", terfavorit);
        System.out.println("--------------------------------------------------------");
        System.out.printf ("| %-52s |\n", "[ REKAP KAPASITAS PER TANGGAL ]");
        
        // !tanggal unik ketemu, outputnya ini
        if (jumlahTglUnik == 0) {
            System.out.printf ("| %-52s |\n", "  Belum ada data tanggal aktif.");
        } else {
            for (int k = 0; k < jumlahTglUnik; k++) {
                System.out.printf ("|  Tanggal %-19s : %-21s |\n", tglUnik[k], orangPerTgl[k] + " orang");
            }
        }
        System.out.println("------------------------------------------------------");
    
    }


    // 10. pengelolaan data (file I/O)    
    // kelola data (simpan ke file dan load dari file)
    static void Pengelolaandata() {
        System.out.println("\n=== KELOLA FILE DATA (I/O) ===");
        System.out.println("[1] Simpan/Backup Data ke File");
        System.out.println("[2] Muat/Restore Data dari File");
        System.out.println("[3] Kembali ke Menu Utama");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine();

        if (opsi == 1) {
            simpanKeFile();
        } else if (opsi == 2) {
            loadDariFile();
        } else if (opsi == 3) {
            main(null);
        } else {
            System.out.println("=== [X] Opsi tidak valid. Kembali ke menu utama===");
        }
    }

    // kita simpen ke file formatnya
    static void simpanKeFile() {
        try {
            // FILE_NAME udah lu definisiin di atas (data_reservasi.txt)
            FileWriter writer = new FileWriter(FILE_NAME); 
            
            for (int i = 0; i < totalData; i++) {
                StrukturReservasi r = DataR[i];
                // Memisahkan tiap kolom pakai titik koma (;)
                writer.write(r.ID + ";" + r.nama + ";" + r.kategori + ";" + r.tanggal + ";" + r.waktu + ";" + r.jumlahOrang + ";" + r.Status + "\n");
            }
            writer.close();
            System.out.println("SUKSES: " + totalData + " data berhasil disimpan ke dalam file " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("GAGAL: Terjadi kesalahan saat menulis file - " + e.getMessage());
        }
    }

    static void loadDariFile() {
        try {
            File file = new File(FILE_NAME);
            Scanner fileScanner = new Scanner(file);
            
            totalData = 0; // Reset array dari awal biar gak numpuk
            int maxIdFound = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";"); // Pecah teks berdasarkan titik koma (;)
                
                // Pastikan formatnya benar (ada 7 kolom)
                if (data.length == 7) {
                    int id = Integer.parseInt(data[0]);
                    String nama = data[1];
                    String kategori = data[2];
                    String tanggal = data[3];
                    String waktu = data[4];
                    int jumlah = Integer.parseInt(data[5]);
                    String status = data[6];

                    // Masukkan ke array utama
                    DataR[totalData++] = new StrukturReservasi(id, nama, kategori, tanggal, waktu, jumlah, status);
                    
                    // Cari ID tertinggi buat ngelanjutin Auto-Increment
                    if (id > maxIdFound) {
                        maxIdFound = id;
                    }
                }
            }
            fileScanner.close();
            
            // KUNCI PENTING: Update Auto Increment agar gak tabrakan ID
            currentId = maxIdFound + 1; 
            
            System.out.println("SUKSES: " + totalData + " data berhasil dimuat dari file " + FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("GAGAL: File " + FILE_NAME + " tidak ditemukan. Silakan Simpan data terlebih dahulu.");
        } catch (Exception e) {
            System.out.println("GAGAL: Terjadi kesalahan format pada file (File Corrupt).");
        }
    }
}

// catatan kendala dan solusi
// kendala 
// 1. Terkendala dengan struktur awal dalam data reservasi karena belum ada gambaran yang jelas
// 2. Terkendala dengan struktur menu yang awalnya masih abu abu dan belum ada gambaran yang jelas dan akhirnya brainstorming dengan teman teman dan AI untuk menentukan fitur apa saja yang akan di buat untuk menunya
// 3. terkendala dengan fungsi sorting dan searching karena pengimplementasian dalam logic code nya kami yang cukup kompleks dan belum memiliki pengalaman yang cukup dalam membuat fungsi sorting dan searching, sehingga kami harus banyak mencari referensi dan mencoba beberapa kali dan bantuan AI untuk mendapatkan hasil yang sesuai dengan yang kami inginkan.
// 4. function dalam java serta logicnya masih cenderung dasar mengakibatkan kami terkendala dalam membuat fungsi yang cukup kompleks seperti sorting dan searching, sehingga kami harus banyak mencari referensi dan mencoba beberapa kali untuk mendapatkan hasil yang sesuai dengan yang kami inginkan.