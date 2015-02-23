-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 23, 2015 at 02:44 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `miniblogdemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
`id` int(20) unsigned NOT NULL,
  `comment` varchar(254) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_id` int(20) unsigned NOT NULL,
  `user_id` int(16) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
`id` int(20) unsigned NOT NULL,
  `content` varchar(2048) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `title` varchar(100) NOT NULL,
  `user_id` int(16) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `content`, `create_at`, `modified_at`, `status`, `title`, `user_id`) VALUES
(5, 'test', '2015-02-22 03:58:41', '2015-02-22 03:58:41', 1, 'test', 8);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
`id` int(16) unsigned NOT NULL,
  `access_token` varchar(64) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expired` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(16) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(16) unsigned NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(254) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(72) NOT NULL,
  `username` varchar(32) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `create_at`, `email`, `firstname`, `lastname`, `modified_at`, `password`, `username`) VALUES
(2, '2015-02-15 00:45:14', 'dang.huy@mulodo.com2', 'le', 'huy', '2015-02-15 00:45:14', '3e1aa305b7682bdf90dcb662abe47ca4d5d8fbfa5384ece916e5469b8fc72731', 'ledanghuy2'),
(3, '2015-02-15 02:22:24', 'dang.huy@mulodo.com3', 'le', 'huy', '2015-02-15 02:22:24', '3e1aa305b7682bdf90dcb662abe47ca4d5d8fbfa5384ece916e5469b8fc72731', 'ledanghuy3'),
(8, '2015-02-20 03:50:32', 'dang.huy@mulodsdso.com', 'le', 'huy', '2015-02-20 03:50:32', '3e1aa305b7682bdf90dcb662abe47ca4d5d8fbfa5384ece916e5469b8fc72731', 'user200'),
(9, '2015-02-20 05:58:01', 'dang.huy@mulodo.com', 'le', 'huy', '2015-02-20 05:58:01', 'bb4eb4f1c068b8c68199a22e8415dd8e14fd94fbec6246209c3a6c40804312c3', 'ledanghuytestpro'),
(22, '2015-02-20 14:06:41', 'ledanghuy@mulodo.com', 'Le', 'Huy', '2015-02-20 14:06:41', '3e1aa305b7682bdf90dcb662abe47ca4d5d8fbfa5384ece916e5469b8fc72731', 'ledanghuy'),
(48, '2015-02-21 05:10:10', 'dang.huy@mulodo.comss', 'le', 'huy', '2015-02-21 05:10:10', 'db8758374dc6303f4252d07db32417821d73e285e28f254e71a8447384e777bb', 'ledanghuytest2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_comments_users` (`user_id`), ADD KEY `fk_comments_posts` (`post_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_posts_users` (`user_id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `access_token` (`access_token`), ADD KEY `fk_token_users` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
MODIFY `id` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
MODIFY `id` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
MODIFY `id` int(16) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(16) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=82;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
ADD CONSTRAINT `fk_comments_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
ADD CONSTRAINT `fk_comments_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
ADD CONSTRAINT `fk_posts_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
ADD CONSTRAINT `fk_token_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
