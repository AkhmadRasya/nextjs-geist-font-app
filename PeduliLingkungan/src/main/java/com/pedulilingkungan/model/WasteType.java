package com.pedulilingkungan.model;

/**
 * Enum untuk jenis-jenis sampah yang dapat didaur ulang
 */
public enum WasteType {
    PLASTIC("Plastik", 15, "Botol plastik, kemasan makanan, kantong plastik"),
    PAPER("Kertas", 10, "Koran, majalah, kardus, kertas kantor"),
    GLASS("Kaca", 8, "Botol kaca, toples, pecahan kaca"),
    METAL("Logam", 20, "Kaleng aluminium, besi, tembaga"),
    ORGANIC("Organik", 5, "Sisa makanan, daun, ranting"),
    ELECTRONIC("Elektronik", 50, "HP bekas, komputer, baterai"),
    TEXTILE("Tekstil", 12, "Pakaian bekas, kain, sepatu"),
    BATTERY("Baterai", 25, "Baterai AA, AAA, baterai HP"),
    OIL("Minyak Jelantah", 18, "Minyak goreng bekas"),
    CARDBOARD("Kardus", 8, "Kotak kardus, kemasan online");
    
    private final String displayName;
    private final int pointsPerKg;
    private final String description;
    
    WasteType(String displayName, int pointsPerKg, String description) {
        this.displayName = displayName;
        this.pointsPerKg = pointsPerKg;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getPointsPerKg() {
        return pointsPerKg;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getEmoji() {
        switch (this) {
            case PLASTIC: return "🥤";
            case PAPER: return "📄";
            case GLASS: return "🍶";
            case METAL: return "🥫";
            case ORGANIC: return "🍃";
            case ELECTRONIC: return "📱";
            case TEXTILE: return "👕";
            case BATTERY: return "🔋";
            case OIL: return "🛢️";
            case CARDBOARD: return "📦";
            default: return "♻️";
        }
    }
    
    @Override
    public String toString() {
        return getEmoji() + " " + displayName + " (" + pointsPerKg + " poin/kg)";
    }
    
    /**
     * Get environmental impact description
     */
    public String getEnvironmentalImpact() {
        switch (this) {
            case PLASTIC:
                return "Plastik membutuhkan 450-1000 tahun untuk terurai. Daur ulang 1 kg plastik menghemat 2 kg CO2.";
            case PAPER:
                return "Daur ulang 1 ton kertas menyelamatkan 17 pohon dan menghemat 26.500 liter air.";
            case GLASS:
                return "Kaca dapat didaur ulang tanpa batas tanpa kehilangan kualitas. Menghemat 30% energi produksi.";
            case METAL:
                return "Daur ulang aluminium menghemat 95% energi dibanding produksi dari bahan mentah.";
            case ORGANIC:
                return "Sampah organik dapat dijadikan kompos yang menyuburkan tanah dan mengurangi metana.";
            case ELECTRONIC:
                return "E-waste mengandung logam berharga dan bahan berbahaya yang perlu penanganan khusus.";
            case TEXTILE:
                return "Industri tekstil adalah pencemar air terbesar kedua. Daur ulang tekstil sangat penting.";
            case BATTERY:
                return "Baterai mengandung logam berat berbahaya yang dapat mencemari tanah dan air.";
            case OIL:
                return "1 liter minyak jelantah dapat mencemari 1 juta liter air. Dapat didaur ulang jadi biodiesel.";
            case CARDBOARD:
                return "Kardus mudah didaur ulang dan dapat digunakan hingga 7 kali sebelum serat rusak.";
            default:
                return "Setiap sampah yang didaur ulang membantu mengurangi pencemaran lingkungan.";
        }
    }
    
    /**
     * Get recycling tips
     */
    public String getRecyclingTips() {
        switch (this) {
            case PLASTIC:
                return "• Bersihkan dari sisa makanan\n• Pisahkan tutup dari botol\n• Hindari plastik hitam (sulit didaur ulang)";
            case PAPER:
                return "• Pisahkan dari plastik dan logam\n• Hindari kertas berminyak\n• Sobek menjadi potongan kecil";
            case GLASS:
                return "• Pisahkan berdasarkan warna\n• Buang tutup logam/plastik\n• Hati-hati dengan pecahan";
            case METAL:
                return "• Bersihkan dari sisa makanan\n• Pisahkan aluminium dari besi\n• Lepaskan label jika memungkinkan";
            case ORGANIC:
                return "• Pisahkan dari sampah lain\n• Potong kecil untuk kompos\n• Hindari daging dan tulang";
            case ELECTRONIC:
                return "• Hapus data pribadi\n• Lepaskan baterai\n• Bawa ke pusat daur ulang khusus";
            case TEXTILE:
                return "• Pastikan dalam kondisi bersih\n• Pisahkan kancing dan resleting\n• Donasikan yang masih layak pakai";
            case BATTERY:
                return "• Jangan buang ke tempat sampah biasa\n• Kumpulkan di tempat khusus\n• Hindari kontak dengan air";
            case OIL:
                return "• Saring dari kotoran\n• Simpan dalam wadah tertutup\n• Bawa ke pengepul minyak jelantah";
            case CARDBOARD:
                return "• Ratakan dan lipat\n• Buang selotip dan staples\n• Pastikan kering dan bersih";
            default:
                return "• Bersihkan sebelum didaur ulang\n• Pisahkan berdasarkan jenis\n• Bawa ke tempat daur ulang terdekat";
        }
    }
}
