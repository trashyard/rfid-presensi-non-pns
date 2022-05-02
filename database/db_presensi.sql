-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 02, 2022 at 05:38 PM
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
  `status` enum('?','recorded') NOT NULL DEFAULT '?',
  `id_karyawan` int(11) NOT NULL,
  `id_jadwal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_detail_jadwal`
--

INSERT INTO `tb_detail_jadwal` (`id`, `hari`, `jam`, `durasi`, `status`, `id_karyawan`, `id_jadwal`) VALUES
(2, 0, '20:40:35', '02:40:32', 'recorded', 6, 1),
(3, 0, '20:40:01', '02:48:00', 'recorded', 6, 2);

--
-- Triggers `tb_detail_jadwal`
--
DELIMITER $$
CREATE TRIGGER `insert_presensi` AFTER UPDATE ON `tb_detail_jadwal` FOR EACH ROW INSERT INTO tb_presensi VALUES (NULL, current_timestamp(), 'hadir', old.id)
$$
DELIMITER ;

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
(4, 'JB004', 'Pegawai');

-- --------------------------------------------------------

--
-- Table structure for table `tb_jadwal`
--

CREATE TABLE `tb_jadwal` (
  `id` int(11) NOT NULL,
  `status` enum('datang','mengajar','pulang') NOT NULL,
  `id_mapel` int(11) DEFAULT NULL,
  `id_kelas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jadwal`
--

INSERT INTO `tb_jadwal` (`id`, `status`, `id_mapel`, `id_kelas`) VALUES
(1, 'mengajar', 6, 1),
(2, 'mengajar', 5, 1),
(3, 'mengajar', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_jurusan`
--

CREATE TABLE `tb_jurusan` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jurusan`
--

INSERT INTO `tb_jurusan` (`id`, `nama`) VALUES
(1, 'Rekayasa Perangkat Lunak'),
(2, 'Teknik Komputer dan Jaringan'),
(3, 'Teknik Kendaraan Ringan'),
(4, 'Teknik Sepeda Motor'),
(5, 'Agribisnis dan Holtikutura'),
(6, 'Multimedia');

-- --------------------------------------------------------

--
-- Table structure for table `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `id` int(11) NOT NULL,
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

INSERT INTO `tb_karyawan` (`id`, `username`, `password`, `nama`, `status`, `jenis_kelamin`, `id_jabatan`) VALUES
(1, 'ADM001', 'admin', 'Andini Ayuningsih', 'admin', 'P', 1),
(3, 'ADM003', 'admin3', 'Yunanda', 'admin', 'L', 3),
(4, 'ADM004', 'admin4', 'Supriyadi', 'admin', 'L', 4),
(5, 'ADM005', 'admin5', 'Anggun ', 'admin', 'P', 1),
(6, 'r', 'r', 'Raihan', 'user', 'L', 4),
(7, 'a', 'a', 'Andini <3', 'user', 'P', 4),
(8, '', '', 'Farhan', 'user', 'L', 4),
(9, '', '', 'Ayunda', 'user', 'P', 4),
(10, 'x', 'x', 'Samsul', 'admin', 'L', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id` int(11) NOT NULL,
  `nama` varchar(10) NOT NULL,
  `angkatan` enum('1','2','3') NOT NULL,
  `id_ruang` int(11) DEFAULT NULL,
  `id_jurusan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kelas`
--

INSERT INTO `tb_kelas` (`id`, `nama`, `angkatan`, `id_ruang`, `id_jurusan`) VALUES
(1, 'RPL 1', '1', 1, 1),
(2, 'RPL 2', '1', 2, 1);

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
  `keterangan` enum('hadir','sakit','izin','alpa') NOT NULL,
  `id_detail_jadwal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_presensi`
--

INSERT INTO `tb_presensi` (`id`, `tanggal`, `keterangan`, `id_detail_jadwal`) VALUES
(1, '2022-05-02 21:59:14', 'hadir', 2),
(2, '2022-05-02 21:59:20', 'hadir', 3);

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
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`id`),
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
-- AUTO_INCREMENT for table `tb_detail_jadwal`
--
ALTER TABLE `tb_detail_jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_jadwal`
--
ALTER TABLE `tb_jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
