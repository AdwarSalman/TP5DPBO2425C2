-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_product
CREATE DATABASE IF NOT EXISTS `db_product` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_product`;

-- Dumping structure for table db_product.product
CREATE TABLE IF NOT EXISTS `product` (
  `no` int NOT NULL AUTO_INCREMENT,
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `stok` int NOT NULL,
  `merek` varchar(255) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_product.product: ~10 rows (approximately)
REPLACE INTO `product` (`no`, `id`, `nama`, `harga`, `kategori`, `stok`, `merek`) VALUES
	(1, 'PRD001', 'Laptop Inspiron 14', 8500000, 'Elektronik', 10, 'Dell'),
	(2, 'PRD002', 'Smartphone Galaxy A15', 3500000, 'Elektronik', 25, 'Samsung'),
	(3, 'PRD003', 'Headphone WH-1000XM4', 2500000, 'Elektronik', 15, 'Sony'),
	(4, 'PRD004', 'Monitor 24 inch', 2100000, 'Elektronik', 8, 'LG'),
	(5, 'PRD005', 'Keyboard Mechanical', 450000, 'Aksesoris', 30, 'Rexus'),
	(6, 'PRD006', 'Mouse Wireless', 150000, 'Aksesoris', 40, 'Logitech'),
	(7, 'PRD007', 'Printer Inkjet L3210', 1200000, 'Perangkat Kantor', 5, 'Epson'),
	(8, 'PRD008', 'Flashdisk 64GB', 85000, 'Penyimpanan', 50, 'Sandisk'),
	(9, 'PRD009', 'Smartwatch Versa 4', 1800000, 'Aksesoris', 12, 'Fitbit'),
	(10, 'PRD010', 'Speaker Bluetooth Go 3', 600000, 'Elektronik', 20, 'JBL');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
