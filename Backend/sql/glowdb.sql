-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 17, 2022 at 04:00 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `glowdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `exercises`
--

CREATE TABLE `exercises` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `running` varchar(255) DEFAULT NULL,
  `dancing` varchar(255) NOT NULL,
  `boxing` varchar(255) NOT NULL,
  `baseball` varchar(255) NOT NULL,
  `basketball` varchar(255) NOT NULL,
  `football` varchar(255) NOT NULL,
  `swimming` varchar(255) NOT NULL,
  `skiing` varchar(255) NOT NULL,
  `hiking` varchar(255) NOT NULL,
  `gymnastics` varchar(255) NOT NULL,
  `tennis` varchar(255) NOT NULL,
  `golf` varchar(255) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exercises`
--

INSERT INTO `exercises` (`id`, `user_id`, `running`, `dancing`, `boxing`, `baseball`, `basketball`, `football`, `swimming`, `skiing`, `hiking`, `gymnastics`, `tennis`, `golf`, `date`) VALUES
(2, 18, '3 hours', '', '', '', '', '', '', '', '', '', '', '', '2222022'),
(3, 17, 'hhhhh', '22', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '452022'),
(4, 19, '1 hour', '', '', '', '', '', '', '', '', '', '', '', '552022');

-- --------------------------------------------------------

--
-- Table structure for table `food_consumptions`
--

CREATE TABLE `food_consumptions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `breakfast` varchar(255) NOT NULL,
  `lunch` varchar(255) NOT NULL,
  `dinner` varchar(255) NOT NULL,
  `snack` varchar(255) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food_consumptions`
--

INSERT INTO `food_consumptions` (`id`, `user_id`, `breakfast`, `lunch`, `dinner`, `snack`, `date`) VALUES
(1, 17, 'oat+milk', 'burger', '', 'chocolate', '14/5/2022'),
(2, 18, 'chocolate', 'spaghetti bolognese', 'cereal', '', '15/5/2022');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `date_of_birth` varchar(50) NOT NULL,
  `weight` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `gender`, `date_of_birth`, `weight`, `height`, `date`) VALUES
(1, 'Charbel', 'Daoud', 'charbel.daoud@lau.lb', '$2y$10$TptHQ57Mm8jbDiuBCkbtsOSrfLVUrDwzXNlwDjmP5ap0l9H.pqQQC', 'female', '22/2/2002', 56, 123, '1352022');

-- --------------------------------------------------------

--
-- Table structure for table `water_consumptions`
--

CREATE TABLE `water_consumptions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nb_of_glasses` int(11) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `water_consumptions`
--

INSERT INTO `water_consumptions` (`id`, `user_id`, `nb_of_glasses`, `date`) VALUES
(1, 17, 7, '15/12/2022'),
(2, 17, 3, '15/12/2022'),
(5, 19, 2, '1323');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exercises`
--
ALTER TABLE `exercises`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food_consumptions`
--
ALTER TABLE `food_consumptions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `water_consumptions`
--
ALTER TABLE `water_consumptions`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exercises`
--
ALTER TABLE `exercises`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `food_consumptions`
--
ALTER TABLE `food_consumptions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `water_consumptions`
--
ALTER TABLE `water_consumptions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
