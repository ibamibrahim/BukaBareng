package com.bukantkpd.bukabareng.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibam on 5/28/2017.
 */

public class UserBuyingModel {
        @SerializedName("id_transaksi")
        @Expose
        private Integer idTransaksi;
        @SerializedName("nama_barang")
        @Expose
        private String namaBarang;
        @SerializedName("quantity")
        @Expose
        private Integer quantity;
        @SerializedName("harga_barang")
        @Expose
        private Integer hargaBarang;
        @SerializedName("tanggal_pembelian")
        @Expose
        private String tanggalPembelian;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("gambar")
        @Expose
        private String gambar;

        public Integer getIdTransaksi() {
            return idTransaksi;
        }

        public void setIdTransaksi(Integer idTransaksi) {
            this.idTransaksi = idTransaksi;
        }

        public String getNamaBarang() {
            return namaBarang;
        }

        public void setNamaBarang(String namaBarang) {
            this.namaBarang = namaBarang;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getHargaBarang() {
            return hargaBarang;
        }

        public void setHargaBarang(Integer hargaBarang) {
            this.hargaBarang = hargaBarang;
        }

        public String getTanggalPembelian() {
            return tanggalPembelian;
        }

        public void setTanggalPembelian(String tanggalPembelian) {
            this.tanggalPembelian = tanggalPembelian;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }
}
