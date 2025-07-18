# Aplikasi Peduli Lingkungan

Aplikasi desktop Java untuk membantu menjaga lingkungan melalui tracking aktivitas ramah lingkungan, perhitungan jejak karbon, dan edukasi.

## Fitur Utama

### 🏠 Dashboard
- Overview statistik aktivitas lingkungan
- Quick actions untuk akses cepat
- Quote lingkungan harian

### ♻️ Pelacak Sampah
- Catat sampah yang didaur ulang
- 10 jenis sampah berbeda dengan poin reward
- Riwayat lengkap dengan tanggal dan deskripsi
- Statistik total berat dan poin

### 🌱 Kalkulator Karbon
- Hitung jejak karbon bulanan
- Kategori: listrik, gas, transportasi, penerbangan
- Perbandingan dengan standar global
- Tips pengurangan emisi

### 💡 Tips Lingkungan
- 40+ tips lingkungan terorganisir
- Kategori: hemat energi, air, sampah, transportasi, konsumsi, rumah hijau
- Tips harian acak
- Fitur bagikan dan cetak

### 🏆 Tantangan Lingkungan
- Tantangan harian, mingguan, bulanan
- Sistem poin dan reward
- Progress tracking
- Fitur berbagi tantangan

## Teknologi

- **Bahasa**: Java 11+
- **GUI**: Java Swing
- **Build Tool**: Maven
- **IDE**: Apache Netbeans (kompatibel)

## Cara Menjalankan

### Di Apache Netbeans:
1. Buka Netbeans
2. Pilih `File > Open Project`
3. Navigasi ke folder `PeduliLingkungan`
4. Klik `Open Project`
5. Klik tombol `Run` atau tekan `F6`

### Via Command Line:
```bash
# Compile dan jalankan dengan Maven
mvn clean compile exec:java

# Atau buat JAR executable
mvn clean package
java -jar target/PeduliLingkungan-1.0.0.jar
```

## Struktur Proyek

```
PeduliLingkungan/
├── src/main/java/com/pedulilingkungan/
│   ├── Main.java                 # Entry point aplikasi
│   ├── ui/
│   │   ├── MainFrame.java        # Frame utama
│   │   └── panels/              # Panel-panel UI
│   │       ├── DashboardPanel.java
│   │       ├── WasteTrackerPanel.java
│   │       ├── CarbonCalculatorPanel.java
│   │       ├── TipsPanel.java
│   │       └── ChallengePanel.java
│   └── model/
│       ├── WasteEntry.java       # Model data sampah
│       └── WasteType.java        # Enum jenis sampah
├── pom.xml                       # Konfigurasi Maven
└── README.md                     # Dokumentasi
```

## Kontribusi

Aplikasi ini dapat dikembangkan lebih lanjut dengan:
- Database untuk penyimpanan data
- Export data ke PDF/Excel
- Integrasi dengan API cuaca
- Fitur komunitas online
- Gamifikasi lebih lanjut

## Lisensi

Open source - bebas digunakan dan dimodifikasi untuk tujuan edukasi dan lingkungan.
