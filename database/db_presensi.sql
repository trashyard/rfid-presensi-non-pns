-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 21, 2022 at 09:08 AM
-- Server version: 5.7.33
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_presensi`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_jadwal`
--

CREATE TABLE `tb_detail_jadwal` (
  `id` varchar(12) NOT NULL,
  `hari` int(1) NOT NULL,
  `jam` time DEFAULT NULL,
  `durasi` time DEFAULT NULL,
  `id_karyawan` int(11) NOT NULL,
  `id_jadwal` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_detail_jadwal`
--

INSERT INTO `tb_detail_jadwal` (`id`, `hari`, `jam`, `durasi`, `id_karyawan`, `id_jadwal`) VALUES
('JD00001', 4, '20:00:00', '04:00:00', 2, 'JBINDORPL1.1'),
('JD00002', 1, '19:00:00', '04:00:00', 2, 'JFISIKRPL1.1'),
('JD00008', 4, '19:00:00', '04:00:00', 2, 'J00001NONE.1'),
('JD00009', 0, '15:15:00', '02:00:00', 16, 'JBINDORPL1.1');

--
-- Triggers `tb_detail_jadwal`
--
DELIMITER $$
CREATE TRIGGER `format_idDetailJadwal` BEFORE INSERT ON `tb_detail_jadwal` FOR EACH ROW BEGIN
	DECLARE countColumn INT;
    DECLARE lastColumn INT;
    SET @countColumn = (select count(*) from tb_detail_jadwal);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_detail_jadwal order by id desc limit 1),3), '0', ' ')),' ', '0'));
	IF @countColumn = 0 THEN
		SET NEW.id = CONCAT("JD", RIGHT(CONCAT('00000', (@countColumn + 1)),5));
	ELSE
		SET NEW.id = CONCAT("JD", RIGHT(CONCAT('00000', @lastColumn + 1),5));
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_jabatan`
--

CREATE TABLE `tb_jabatan` (
  `id` varchar(6) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jabatan`
--

INSERT INTO `tb_jabatan` (`id`, `nama`) VALUES
('JB0001', 'P3K'),
('JB0002', 'Non-PNS'),
('JB0003', 'Pegawai'),
('JB0004', 'Satpam');

--
-- Triggers `tb_jabatan`
--
DELIMITER $$
CREATE TRIGGER `format_idJabatan` BEFORE INSERT ON `tb_jabatan` FOR EACH ROW BEGIN
	DECLARE countColumn INT;
    DECLARE lastColumn INT;
    SET @countColumn = (select count(*) from tb_jabatan);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_jabatan order by id desc limit 1),3), '0', ' ')),' ', '0'));
	IF @countColumn = 0 THEN
		SET NEW.id = CONCAT("JB", RIGHT(CONCAT('0000', (@countColumn + 1)),4));
	ELSE
		SET NEW.id = CONCAT("JB", RIGHT(CONCAT('0000', @lastColumn + 1),4));
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_jadwal`
--

CREATE TABLE `tb_jadwal` (
  `id` varchar(12) NOT NULL,
  `id_mapel` varchar(11) DEFAULT NULL,
  `id_kelas` varchar(10) DEFAULT NULL,
  `status` enum('datang','mengajar','pulang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jadwal`
--

INSERT INTO `tb_jadwal` (`id`, `id_mapel`, `id_kelas`, `status`) VALUES
('J00001NONE.1', '001', 'NONE.1', 'mengajar'),
('J00002NONE.1', '002', 'NONE.1', 'mengajar'),
('J0ALGOTKJ1.3', 'ALGO', 'TKJ1.3', 'mengajar'),
('JBINDORPL1.1', 'BINDO', 'RPL1.1', 'mengajar'),
('JBINDOTKJ1.3', 'BINDO', 'TKJ1.3', 'mengajar'),
('JFISIKRPL1.1', 'FISIKA', 'RPL1.1', 'mengajar'),
('JFISIKTKJ1.3', 'FISIKA', 'TKJ1.3', 'mengajar'),
('JMATDARPL1.1', 'MATDAS', 'RPL1.1', 'mengajar');

--
-- Triggers `tb_jadwal`
--
DELIMITER $$
CREATE TRIGGER `format_idJadwal` BEFORE INSERT ON `tb_jadwal` FOR EACH ROW BEGIN
		SET NEW.id = CONCAT("J", RIGHT(CONCAT('0000', LEFT(NEW.id_mapel, 5), NEW.id_kelas),11));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_idJadwal` BEFORE UPDATE ON `tb_jadwal` FOR EACH ROW BEGIN
		SET NEW.id = CONCAT("J", RIGHT(CONCAT('000000', LEFT(NEW.id_mapel, 3), NEW.id_kelas),9)) ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_jurusan`
--

CREATE TABLE `tb_jurusan` (
  `id` int(11) NOT NULL,
  `id_jurusan` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jurusan`
--

INSERT INTO `tb_jurusan` (`id`, `id_jurusan`, `nama`) VALUES
(1, 'RPL', 'Rekayasa Perangkat Lunak'),
(2, 'TKJ', 'Teknik Komputer dan Jaringan'),
(3, 'TKR', 'Teknik Kendaraan Ringan'),
(4, 'TSM', 'Teknik Sepeda Motor'),
(5, 'AGRH', 'Agribisnis dan Holtikutura'),
(6, 'MM', 'Multimedia'),
(7, 'NONE', '-');

-- --------------------------------------------------------

--
-- Table structure for table `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `id` int(11) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `username` varchar(6) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `nama` varchar(50) NOT NULL,
  `status` enum('admin','user') NOT NULL,
  `jenis_kelamin` enum('P','L') NOT NULL,
  `id_jabatan` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`id`, `nik`, `username`, `password`, `nama`, `status`, `jenis_kelamin`, `id_jabatan`) VALUES
(2, '3525017006750017', 'r', '$2a$12$FkrVFEAdrpXw3n1aT3Hn5esmTPnmwEtxobt5fiY4tmot7f4nvYT5W', 'Raihan', 'admin', 'L', 'JB0001'),
(7, '3525012005596332', 'd', '$2a$12$iR5RAZjh6OYpShaVqJ4kDOCiXbhZf4ugXmV02pcwyPmvLMKYPK6FS', 'Devi', 'admin', 'P', 'JB0003'),
(8, '3525012005534534', NULL, NULL, 'David', 'user', 'L', 'JB0004'),
(9, '3525012005598643', NULL, NULL, 'Akber', 'user', 'L', 'JB0004'),
(15, '4538495897298271', NULL, NULL, 'Gandi Geblekus', 'user', 'P', 'JB0002'),
(16, '0987654538765433', NULL, NULL, 'Vinda Rosi', 'user', 'P', 'JB0001');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(10) NOT NULL,
  `angkatan` enum('1','2','3') NOT NULL,
  `id_ruang` int(11) DEFAULT NULL,
  `id_jurusan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kelas`
--

INSERT INTO `tb_kelas` (`id`, `nama`, `angkatan`, `id_ruang`, `id_jurusan`) VALUES
('NONE.1', 'NONE', '1', 6, 7),
('RPL1.1', 'RPL 1', '1', 1, 1),
('TKJ1.3', 'TKJ 1', '3', 5, 2);

--
-- Triggers `tb_kelas`
--
DELIMITER $$
CREATE TRIGGER `format_idKelas` BEFORE INSERT ON `tb_kelas` FOR EACH ROW BEGIN
	SET NEW.id = CONCAT(REPLACE(NEW.nama, ' ', ''), ".", NEW.angkatan);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_idKelas` BEFORE UPDATE ON `tb_kelas` FOR EACH ROW BEGIN
	SET NEW.id = CONCAT(REPLACE(NEW.nama, ' ', ''), ".", NEW.angkatan);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_mapel`
--

CREATE TABLE `tb_mapel` (
  `id` varchar(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_mapel`
--

INSERT INTO `tb_mapel` (`id`, `nama`) VALUES
('001', 'Datang'),
('002', 'Pulang'),
('ALGO', 'Algoritma'),
('BINDO', 'Bahasa Indonesia'),
('FISIKA', 'Fisika'),
('MATDAS', 'Matematika Dasar'),
('PEMDAS', 'Pemrograman Dasar'),
('SEJINDO', 'Sejarah Indonesia');

-- --------------------------------------------------------

--
-- Table structure for table `tb_presensi`
--

CREATE TABLE `tb_presensi` (
  `id` varchar(11) NOT NULL,
  `tanggal` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `keterangan` enum('Hadir','Telat','Alpa','?','Izin') CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `id_detail_jadwal` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_presensi`
--

INSERT INTO `tb_presensi` (`id`, `tanggal`, `keterangan`, `id_detail_jadwal`) VALUES
('P0000000001', '2022-05-27 19:03:06', 'Hadir', 'JD00001'),
('P0000000002', '2022-05-31 19:42:49', 'Izin', 'JD00002'),
('P0000000003', '2022-06-20 11:24:32', 'Telat', 'JD00002'),
('P0000000004', '2022-06-20 11:24:39', 'Telat', 'JD00002'),
('P0000000005', '2022-06-20 11:24:39', 'Telat', 'JD00002'),
('P0000000006', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000007', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000008', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000009', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000010', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000011', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000012', '2022-06-20 11:24:39', '?', 'JD00002'),
('P0000000013', '2022-03-01 11:31:17', '?', 'JD00001'),
('P0000000014', '2022-03-01 11:31:17', '?', 'JD00001'),
('P0000000015', '2022-03-01 11:31:17', '?', 'JD00001'),
('P0000000016', '2022-03-01 11:31:17', '?', 'JD00001'),
('P0000000017', '2022-03-01 11:31:17', '?', 'JD00001'),
('P0000000018', '2022-03-01 11:31:17', '?', 'JD00001'),
('P0000000019', '2022-04-23 11:31:43', '?', 'JD00008'),
('P0000000020', '2022-04-23 11:31:43', '?', 'JD00008'),
('P0000000021', '2022-04-23 11:31:43', '?', 'JD00008'),
('P0000000022', '2022-04-23 11:31:43', '?', 'JD00008'),
('P0000000023', '2022-04-23 11:31:43', '?', 'JD00008');

--
-- Triggers `tb_presensi`
--
DELIMITER $$
CREATE TRIGGER `insert_attendance&formatId` BEFORE INSERT ON `tb_presensi` FOR EACH ROW BEGIN

	DECLARE start_time DATETIME;
    DECLARE end_time DATETIME;
    DECLARE late_time DATETIME;
    DECLARE alpa_time DATETIME;
    DECLARE countColumn INT;
    DECLARE lastColumn INT;
    
    SET @countColumn = (select count(*) from tb_presensi);
	SET @lastColumn = (select REPLACE(LTRIM(REPLACE(substring((select id from tb_presensi order by id desc limit 1),3), '0', ' ')),' ', '0'));
	SET @start_time = cast((select addtime(current_date, tb_detail_jadwal.jam) from tb_detail_jadwal where id = NEW.id_detail_jadwal) as datetime);
    SET @end_time = cast((select addtime(current_date, addtime(tb_detail_jadwal.jam, tb_detail_jadwal.durasi)) from tb_detail_jadwal where id = NEW.id_detail_jadwal) as datetime);
	SET @late_time = cast((select addtime(current_date, addtime(tb_detail_jadwal.jam, "00:15:00")) from tb_detail_jadwal where id = NEW.id_detail_jadwal) as datetime);
	SET @alpa_time = cast((select addtime(current_date, addtime(tb_detail_jadwal.jam, "00:45:00")) from tb_detail_jadwal where id = NEW.id_detail_jadwal) as datetime);
    
	IF NEW.tanggal > @start_time && NEW.tanggal < @late_time THEN
    		SET NEW.keterangan = "Hadir";
		ELSEIF NEW.tanggal > @late_time && NEW.tanggal < @alpa_time THEN 
			SET NEW.keterangan = "Telat";
		ELSEIF NEW.tanggal > @alpa_time && NEW.tanggal < @end_time THEN 
        	SET NEW.keterangan = "Alpa";
		ELSE
			SET NEW.keterangan = "?";
        END IF;
        
        IF @countColumn = 0 THEN
		SET NEW.id = CONCAT("P", RIGHT(CONCAT('0000000000', (@countColumn + 1)),10));
	ELSE
		SET NEW.id = CONCAT("P", RIGHT(CONCAT('0000000000', @lastColumn + 1),10));
	END IF;
    
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_ruang`
--

CREATE TABLE `tb_ruang` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_ruang`
--

INSERT INTO `tb_ruang` (`id`, `nama`) VALUES
(1, '3.1'),
(2, '3.2'),
(3, '3.4'),
(4, '3.5'),
(5, '5.5'),
(6, 'NONE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_jadwal`
--
ALTER TABLE `tb_detail_jadwal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_jadwal` (`id_jadwal`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kelas` (`id_kelas`),
  ADD KEY `id_mapel` (`id_mapel`);

--
-- Indexes for table `tb_jurusan`
--
ALTER TABLE `tb_jurusan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_jurusan` (`id_jurusan`);

--
-- Indexes for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nik` (`nik`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id_jabatan` (`id_jabatan`);

--
-- Indexes for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ruang` (`id_ruang`),
  ADD KEY `id_jurusan` (`id_jurusan`);

--
-- Indexes for table `tb_mapel`
--
ALTER TABLE `tb_mapel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_presensi`
--
ALTER TABLE `tb_presensi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_detail_jadwal` (`id_detail_jadwal`);

--
-- Indexes for table `tb_ruang`
--
ALTER TABLE `tb_ruang`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_jurusan`
--
ALTER TABLE `tb_jurusan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tb_ruang`
--
ALTER TABLE `tb_ruang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_detail_jadwal`
--
ALTER TABLE `tb_detail_jadwal`
  ADD CONSTRAINT `tb_detail_jadwal_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `tb_karyawan` (`id`),
  ADD CONSTRAINT `tb_detail_jadwal_ibfk_3` FOREIGN KEY (`id_jadwal`) REFERENCES `tb_jadwal` (`id`);

--
-- Constraints for table `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  ADD CONSTRAINT `tb_jadwal_ibfk_8` FOREIGN KEY (`id_kelas`) REFERENCES `tb_kelas` (`id`),
  ADD CONSTRAINT `tb_jadwal_ibfk_9` FOREIGN KEY (`id_mapel`) REFERENCES `tb_mapel` (`id`);

--
-- Constraints for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD CONSTRAINT `tb_karyawan_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `tb_jabatan` (`id`);

--
-- Constraints for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD CONSTRAINT `tb_kelas_ibfk_1` FOREIGN KEY (`id_ruang`) REFERENCES `tb_ruang` (`id`),
  ADD CONSTRAINT `tb_kelas_ibfk_2` FOREIGN KEY (`id_jurusan`) REFERENCES `tb_jurusan` (`id`);

--
-- Constraints for table `tb_presensi`
--
ALTER TABLE `tb_presensi`
  ADD CONSTRAINT `tb_presensi_ibfk_1` FOREIGN KEY (`id_detail_jadwal`) REFERENCES `tb_detail_jadwal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
