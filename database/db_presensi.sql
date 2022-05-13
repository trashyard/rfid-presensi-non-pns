-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 13, 2022 at 04:47 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

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
  `id` int(11) NOT NULL,
  `hari` int(1) NOT NULL,
  `jam` time DEFAULT NULL,
  `durasi` time DEFAULT NULL,
  `id_karyawan` int(11) NOT NULL,
  `id_jadwal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_detail_jadwal`
--

INSERT INTO `tb_detail_jadwal` (`id`, `hari`, `jam`, `durasi`, `id_karyawan`, `id_jadwal`) VALUES
(1, 1, '13:00:00', '02:00:00', 6, 4),
(2, 1, '15:00:00', '02:00:00', 6, 4),
(3, 2, '20:00:00', '02:00:00', 6, 5),
(4, 1, '23:00:00', '02:00:00', 7, 8),
(11, 4, '21:20:00', '02:00:00', 6, 9),
(13, 0, '22:00:00', '02:00:00', 6, 8),
(14, 1, '10:00:00', '02:00:00', 6, 9);

-- --------------------------------------------------------

--
-- Table structure for table `tb_jabatan`
--

CREATE TABLE `tb_jabatan` (
  `id` int(11) NOT NULL,
  `id_jabatan` varchar(6) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jabatan`
--

INSERT INTO `tb_jabatan` (`id`, `id_jabatan`, `nama`) VALUES
(1, 'JB001', 'P3K'),
(2, 'JB002', 'Satpam'),
(3, 'JB003', 'Non PNS'),
(4, 'JB004', 'Pegawai'),
(5, 'JB005', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_jadwal`
--

CREATE TABLE `tb_jadwal` (
  `id` int(11) NOT NULL,
  `id_mapel` int(11) DEFAULT NULL,
  `id_kelas` int(11) DEFAULT NULL,
  `status` enum('datang','mengajar','pulang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jadwal`
--

INSERT INTO `tb_jadwal` (`id`, `id_mapel`, `id_kelas`, `status`) VALUES
(4, 1, 1, 'mengajar'),
(5, 2, 1, 'mengajar'),
(6, 3, 1, 'mengajar'),
(7, 4, 1, 'mengajar'),
(8, 5, 1, 'mengajar'),
(9, 6, 1, 'mengajar');

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
(6, 'MM', 'Multimedia');

-- --------------------------------------------------------

--
-- Table structure for table `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `id` int(11) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `username` varchar(6) NOT NULL,
  `password` varchar(6) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `status` enum('admin','user') NOT NULL,
  `jenis_kelamin` enum('P','L') NOT NULL,
  `id_jabatan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`id`, `nik`, `username`, `password`, `nama`, `status`, `jenis_kelamin`, `id_jabatan`) VALUES
(1, '3525010609510002', 'ADM001', 'admin', 'Andini Ayuningsih', 'admin', 'P', 1),
(6, '3525017006750042', 'r', 'r', 'Raihan', 'user', 'L', 4),
(7, '3525016611770002', 'a', 'a', 'Andini <3', 'user', 'P', 4),
(8, '3525016812770001', '', '', 'Farhan', 'user', 'L', 4),
(9, '3525013006770017', '', '', 'Ayunda', 'user', 'P', 4),
(10, '3525012005590001', 'x', 'x', 'Samsul', 'admin', 'L', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id` int(11) NOT NULL,
  `id_kelas` varchar(10) NOT NULL,
  `nama` varchar(10) NOT NULL,
  `angkatan` enum('1','2','3') NOT NULL,
  `id_ruang` int(11) DEFAULT NULL,
  `id_jurusan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kelas`
--

INSERT INTO `tb_kelas` (`id`, `id_kelas`, `nama`, `angkatan`, `id_ruang`, `id_jurusan`) VALUES
(1, 'RPL1.1', 'RPL 1', '1', 1, 1),
(2, 'RPL2.1', 'RPL 2', '1', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_mapel`
--

CREATE TABLE `tb_mapel` (
  `id` int(11) NOT NULL,
  `id_mapel` varchar(11) DEFAULT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_mapel`
--

INSERT INTO `tb_mapel` (`id`, `id_mapel`, `nama`) VALUES
(1, 'MATDAS', 'Matematika Dasar'),
(2, 'PEMDAS', 'Pemrograman Dasar'),
(3, 'FISIKA', 'Fisika'),
(4, 'SEJINDO', 'Sejarah Indonesia'),
(5, 'BINDO', 'Bahasa Indonesia'),
(6, 'ALGO', 'Algoritma');

-- --------------------------------------------------------

--
-- Table structure for table `tb_presensi`
--

CREATE TABLE `tb_presensi` (
  `id` int(11) NOT NULL,
  `tanggal` datetime NOT NULL DEFAULT current_timestamp(),
  `keterangan` enum('Hadir','Telat','Alpa','?') CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `id_detail_jadwal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_presensi`
--

INSERT INTO `tb_presensi` (`id`, `tanggal`, `keterangan`, `id_detail_jadwal`) VALUES
(29, '2022-05-13 21:43:07', 'Telat', 11);

--
-- Triggers `tb_presensi`
--
DELIMITER $$
CREATE TRIGGER `insert_attendance` BEFORE INSERT ON `tb_presensi` FOR EACH ROW BEGIN
	DECLARE start_time DATETIME;
    DECLARE end_time DATETIME;
    DECLARE late_time DATETIME;
    DECLARE alpa_time DATETIME;
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
(5, '5.5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_jadwal`
--
ALTER TABLE `tb_detail_jadwal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_karyawan` (`id_karyawan`),
  ADD KEY `id_jadwal` (`id_jadwal`);

--
-- Indexes for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_jabatan` (`id_jabatan`);

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_mapel` (`id_mapel`);

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
-- AUTO_INCREMENT for table `tb_detail_jadwal`
--
ALTER TABLE `tb_detail_jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_mapel`
--
ALTER TABLE `tb_mapel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_presensi`
--
ALTER TABLE `tb_presensi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_detail_jadwal`
--
ALTER TABLE `tb_detail_jadwal`
  ADD CONSTRAINT `tb_detail_jadwal_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `tb_karyawan` (`id`),
  ADD CONSTRAINT `tb_detail_jadwal_ibfk_2` FOREIGN KEY (`id_jadwal`) REFERENCES `tb_jadwal` (`id`);

--
-- Constraints for table `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  ADD CONSTRAINT `tb_jadwal_ibfk_6` FOREIGN KEY (`id_mapel`) REFERENCES `tb_mapel` (`id`),
  ADD CONSTRAINT `tb_jadwal_ibfk_7` FOREIGN KEY (`id_mapel`) REFERENCES `tb_mapel` (`id`),
  ADD CONSTRAINT `tb_jadwal_ibfk_8` FOREIGN KEY (`id_kelas`) REFERENCES `tb_kelas` (`id`);

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
