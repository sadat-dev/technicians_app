-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2018 at 08:01 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fixkoro`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(18) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_user`, `first_name`, `last_name`, `email`, `password`) VALUES
(1, 'Fix', 'Koro', 'admin@fixkoro.com', '0000');

-- --------------------------------------------------------

--
-- Table structure for table `any_item_order`
--

CREATE TABLE IF NOT EXISTS `any_item_order` (
  `id_any_item_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` varchar(20) NOT NULL,
  `item_name` varchar(200) NOT NULL,
  `item_details` text,
  `message` text,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_any_item_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `call_sms_history`
--

CREATE TABLE IF NOT EXISTS `call_sms_history` (
  `id_call_sms_history` int(11) NOT NULL AUTO_INCREMENT,
  `id_registered_customer` int(11) NOT NULL,
  `id_technician` int(11) NOT NULL,
  `call_or_sms` enum('Call','SMS') NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_call_sms_history`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `call_sms_history`
--

INSERT INTO `call_sms_history` (`id_call_sms_history`, `id_registered_customer`, `id_technician`, `call_or_sms`, `create_date`) VALUES
(19, 14, 9, 'SMS', '2018-01-06 18:22:48');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `details` varchar(200) NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id_category`, `category_name`, `details`) VALUES
(21, 'Empori', ' 						');

-- --------------------------------------------------------

--
-- Table structure for table `ci_sessions`
--

CREATE TABLE IF NOT EXISTS `ci_sessions` (
  `id` varchar(40) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `timestamp` int(10) unsigned NOT NULL DEFAULT '0',
  `data` blob NOT NULL,
  KEY `ci_sessions_timestamp` (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ci_sessions`
--

INSERT INTO `ci_sessions` (`id`, `ip_address`, `timestamp`, `data`) VALUES
('862189392fae038c9e665719eff322c05dc149f6', '52.27.2.86', 1515449350, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353434393335303b),
('1dced5e31a14794a0a0bef28e453d5b521cebafd', '118.179.201.194', 1515489975, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353437343439353b6c6f676765645f696e7c623a313b757365725f69647c733a313a2231223b),
('9b42f58c9429f0960fbd61077fc23e0415a8810d', '123.108.244.47', 1515474856, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353437343835363b),
('d63f066991f31bcc6f25918db650f802f3df5337', '118.179.201.194', 1515490061, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353439303036313b),
('259177ead061813ea04ec8bb11f48c615b6b48e2', '118.179.201.194', 1515490063, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353439303036333b),
('10d54e959844002319769ba814297b7da4ed16b7', '103.199.168.50', 1515500403, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530303430333b),
('1542c75b7a12f861d861823e6aa9312d654044e2', '103.229.84.73', 1515509620, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530363131303b6c6f676765645f696e7c623a313b757365725f69647c733a313a2231223b),
('fe0d78f98626f07505a9f706624a9bf93c5d2e4b', '103.229.84.73', 1515506146, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530363134363b),
('de7fccd3ecef3084f6fa094b0597c9233fc29472', '103.229.84.73', 1515506150, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530363135303b),
('1b759abfcb4f39ff48376539045441679a6045c2', '103.229.84.73', 1515506152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530363135323b),
('e38ad11bd890f2aa344f0711316f588a3c280b2a', '103.229.84.73', 1515506182, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530363138323b),
('7838b1bb27e34aee8be7e58f93801ae78f324967', '103.229.84.73', 1515506233, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530363233333b),
('1idim96gq1497jch947lj9o5q3hfer2k', '::1', 1515509969, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353530393732393b6c6f676765645f696e7c623a313b757365725f69647c733a313a2231223b),
('8kostl71ik013685aqu0atmcc17vncl5', '::1', 1515523113, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353531313935353b6c6f676765645f696e7c623a313b757365725f69647c733a313a2231223b7374617475734d73677c733a32383a2246696c65732075706c6f61646564207375636365737366756c6c792e223b5f5f63695f766172737c613a313a7b733a393a227374617475734d7367223b733a333a226f6c64223b7d),
('uj6iivu9a95c1u4s3323o0fd52ejqnf5', '::1', 1515598015, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353539343231323b6c6f676765645f696e7c623a313b757365725f69647c733a313a2231223b),
('vlp6150tp6324salh02agdqa6sk0n0da', '::1', 1515603176, 0x5f5f63695f6c6173745f726567656e65726174657c693a313531353539383838383b6c6f676765645f696e7c623a313b757365725f69647c733a313a2231223b);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id_customer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `shipping_address` text,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_customer`, `name`, `email`, `phone`, `shipping_address`, `create_date`) VALUES
(1, 'Elisio', 'mm@gmail.com', '0172536983', 'Fhaka', '2017-09-16 14:32:41'),
(2, 'vdbg', 'ggfx@vc.com', '550848253877', 't y SD tfhy', '2017-11-09 06:52:07'),
(3, 'vdbg', 'ggfx@vc.com', '550848253877', 't y SD tfhy', '2017-11-09 06:57:03'),
(4, 'Asdf', 'asd@bdn.com', '986468646797', 'nxhsjjxhd ndndjjs', '2017-11-13 05:51:26'),
(5, 'Asdf', 'asd@bdn.com', '986468646797', 'nxhsjjxhd ndndjjs', '2017-11-13 06:13:11'),
(6, 'Rana', 'asd@bdn.com', '986468646797', 'nxhsjjxhd ndndjjs', '2017-11-25 13:06:21'),
(7, 'What the name', 'gdcdd@cdd.com', '254858488', 'adgjc fhdx', '2017-12-15 13:32:14'),
(8, 'What the name', 'gdcdd@cdd.com', '254858488', 'adgjc fhdx', '2017-12-24 14:40:10'),
(9, 'asfi', 'mybeatsbd@gmail.com', '01680465336', 'gulshan 1', '2018-01-05 10:32:29'),
(10, 'tameem', 'hydronkid1996@gmail.com', '01676083574', 'dhanmondi 2', '2018-01-06 09:03:39');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_man`
--

CREATE TABLE IF NOT EXISTS `delivery_man` (
  `id_delivery_man` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `phone2` varchar(20) DEFAULT NULL,
  `nid` varchar(30) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `blood_group` varchar(5) DEFAULT NULL,
  `present_address` varchar(150) DEFAULT NULL,
  `permenent_address` varchar(150) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_delivery_man`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `delivery_man`
--

INSERT INTO `delivery_man` (`id_delivery_man`, `first_name`, `last_name`, `email`, `phone`, `phone2`, `nid`, `image`, `blood_group`, `present_address`, `permenent_address`, `create_date`) VALUES
(4, 'Mr. XYZ', '', NULL, '017451278', NULL, NULL, NULL, NULL, NULL, NULL, '2017-08-28 08:23:38');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `id_category` int(11) NOT NULL,
  `id_subcategory` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `image` varchar(300) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `selling_price` varchar(100) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_item`),
  KEY `name` (`name`),
  KEY `id_category` (`id_category`),
  KEY `id_subcategory` (`id_subcategory`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id_item`, `id_category`, `id_subcategory`, `name`, `image`, `description`, `selling_price`, `quantity`) VALUES
(8, 21, 106, 'ORICO ST2-BK 38 in 1 Multifunction Screwdriver Set Hand Repair Tools for iPhone Xiaomi Notebook', 'aa383a4a2984dc03f1078fd2e9b5d1a6.jpg', 'When your cell phone, computer or any other electronic products are out of service, do you want to repair by yourself? In our daily life, this is a common phenomenon, so we must learn to deal with some small problems. This ORICO ST2-BK Premium Quality High Precision Telecommunications Tool Set is a useful set of tools for you. Made of high quality Germany S2 material production and processed by special high temperature, all heads of the screws feature high strength and strong h', '3000', 5),
(9, 21, 106, 'Bakeey™ 54 in 1 Multi-purpose Precision Screwdriver Sets Hand Repair Tools Kit for iPhone Laptop Xiaomi', '0dbea5e3ceff7759de0ca2357cafdc51.jpg', 'Description:\r\n-Product use: Mobile phones, Computers, Small Appliances Disassemble Tools\r\n-Professional portable hardware tools.\r\n-Light weight, Compact design.\r\n-High Hardness and Good Toughness.\r\n \r\nSpecifications:                                                            \r\nSize: 226*98*25MM\r\nMaterial: Chrome Vanadium Alloy Steel\r\n \r\nScrewdriver Bit Model:\r\nNut Driver: 2.5/ 3/ 3.5/ 4/ 4.5mm\r\nHex: 0.7/ 0.9/ 1/ 1.3/ 1.5/ 2/ 2.5/ 3/ 3.5/ 4mm\r\nPentalobe: 0.8/ 1.2mm\r\nSquare: SQ0/ SQ1\r\nTorx: T3/ T4', '4000', 5),
(10, 21, 106, 'Bakeey™ 45 in 1 Precision Hardware Screwdriver Set Repair Tool Kits for Xiaomi iPhone Notebook', '0b697ed69d5c9382c6d5f5314eaeeccf.jpg', 'Features:\r\n-45pcs in 1 screwdriver set, includes 42 batch heads and sleeves, 1 lengthening bar, 1 elbow anti-static tweezers,\r\n 1 special handle.\r\n-Non-slip rubber design of handle for comfortable to operate.\r\n-Comes with extension rod can be inserted into the handle for length adjustment.\r\n-Help you repair the device without damaging it.\r\n \r\nSpecifications:\r\nMaterial: PP & TPR & CR-V steel\r\nHardness: H.R.C. 50-52\r\nStorage Box Size: 155 x 110 x 30mm/6.10 x 4.33 x 1.18 inches\r\nColor: Black & yell', '3000', 10),
(11, 21, 106, '21 in 1 Repair Tool Kit Screwdriver Set For Mobile Phone', '1d24ab33dfd514952f1e0330cdb6f09a.jpg', 'Description\r\n \r\nFeatures:\r\n1. Brand new repair tool set\r\n2. With these repair tools, you''ll be able to easily disassemble your phones\r\n3. Quick disassemble and install your Phone without any damage\r\n4. Can be reused for many times\r\n5. Compatible with: all mobile phone\r\n \r\nPackage include:\r\n1 x Double-headed crowbar\r\n1 x Forceps\r\n1 x Torx/ Star Screw Drive\r\n1 x SIM CARD Tray Ejector Pin\r\n1 x Key ring\r\n1 x T5 Screw Driver\r\n1 x T6 Screw Driver\r\n1 x 1.2 Phillips Screw Driver\r\n1 x 1.5 Phillips Screw ', '4000', 10),
(12, 21, 106, 'LCD Screen Suction Cup Opening Plier Black Crowbar Repair Tool for Smartphone', '8ce10c369617865f671708cb4f9aba56.jpg', 'Description:\r\n\r\nMaterial: Plastic + Silicon\r\nColor: Black\r\n\r\nFeature:\r\n\r\n1. Ergonomically designed handle with slip-resistant textured grip for comfortable hold.\r\n2. Adjustable tablet holder to any size of tablets and mobiles.\r\n3. Muti-anglge PVC suction cup, easier to take apart every gadgets you want.\r\n4. Light weight,convenient to carry.\r\n5. With these repair tools, you''ll be able to easily disassemble your phones.\r\n6. Quick disassemble and install your Phone without any damage.\r\n7. Can be re', '1200', 5),
(13, 21, 112, 'Original Anti-Explosion Front And Back Tempered Glass Screen Protector For DOOGEE MIX 2', 'f721c9d4440ceec45d2c768eb136ce0a.jpeg', 'Compatibility	 DOOGEE                                                                   \r\n  Compatible Model	 Doogee Mix 2\r\n  Type	 Front & Back Protector\r\n  Screen Protector Function         	 Anti-Explosion\r\n  Screen Protector Feature	 9H Hardness,High Definition\r\n  Screen Protector Material	 Tempered Glass\r\n  Package Included	 1x Front Tempered Glass Screen Protector\r\n 1x Back Tempered Glass Screen Protector', '500', 5),
(14, 21, 112, '4D Curved Edge Full Cover Anti-Explosion Tempered Glass Screen Protector For OnePlus 5', 'a484dc7c38c785ca1a8c009d1e0ff388.jpg', 'Compatibility	 OnePlus                                                   \r\n  Compatible Model	 OnePlus 5\r\n  Screen Protector Function      	  Anti-Explosion,Anti-Fingerprint,Anti-Scratch,Anti-Glare\r\n  Screen Protector Feature	 4D Curved Edge,Full Screen Cover,High Definition (HD),9H Hardness\r\n  Screen Protector Material	 Tempered Glass', '400', 5),
(15, 21, 112, 'NACODEX 9H Anti-burst Tempered Glass Screen Protector For ZTE Nubia Z9 mini', 'b9099c7bf2db44d4620d13dc50114341.jpg', 'Description:\r\nBrand Name: NACODEX\r\nType: Screen Protective Film\r\nMaterial: Tempered Glass\r\nHardness: 9H\r\nThickness: 0.3mm\r\nCompatible Model: For ZTE Nubia Z9 mini\r\n\r\nFeature:\r\n1. Smooth touch, better avoid edge cracking of ZTE Nubia Z9 mini smartphone.\r\n2. Anti-shatter glass, prevent glass fragmentation, protecting user from injury when glass is broken,\r\n3. Only 0.3mm thickness of the film, not affect the normal operation to the screen.\r\n4. Highly transparent surface, display the original color ', '400', 5),
(16, 21, 112, 'Anti-Scratch Transparent Clear Screen Protector Film Shield For Garmin Fenix 2', '818132385fb3c119c0bff246563ab965.jpg', 'Features:\r\nProtect your watch from bumps and scratches without compromising the feel of your watch\r\nThin, strong and durable\r\nNo nasty substances are left when the screen protector is removed\r\nPieces cut to perfectly fit your device offering the best protection and allowing your device to remain elegant\r\nReduce-glare, help minimize annoying surface glare under bright lights\r\nCompatible With: For Garmin Fenix 2\r\nDiameter: Approx. 3.6cm/ 1.42"\r\n\r\nPackage Included:\r\n1 x Screen Protector For Garmin', '400', 5),
(17, 21, 113, 'iPhone 6s', '0842ed0944044ca6031052a39e970c2e.png', 'FEATURES\r\nSPECIFICATIONS\r\nSTORAGE\r\n32GB 64GB 128GB1\r\nDISPLAY\r\nRetina HD display with 3D Touch\r\n4.7-inch (diagonal)\r\n1334-by-750-pixel resolution at 326 ppi\r\nDIMENSIONS\r\nHeight: 138.3 mm (5.44 inches)2\r\nWidth : 67.1 mm (2.64 inches)2\r\nDepth : 7.1 mm (0.28 inch)2\r\nWEIGHT\r\n143 grams (5.04 ounces)2\r\nCHIP\r\nA9 chip with 64-bit architecture and integrated M9 motion coprocessor - 70 per cent faster CPU and 90 per cent faster GPU than A8 chip\r\nWIRELESS AND CELLULAR\r\nGSM/EDGE\r\nUMTS/HSPA+\r\nDC-HSDPA\r\nCDMA E', '25000', 10),
(18, 21, 113, 'iPhone 6s Plus', '97256618fa32c4f06b77fd80a4e8ccef.png', 'STORAGE\r\n32GB1\r\nDISPLAY\r\nRetina HD display with 3D Touch\r\n5.5-inch (diagonal)\r\n1920-by-1080-pixel resolution at 401 ppi\r\nDIMENSIONS\r\nHeight: 158.2 mm (6.23 inches)\r\nWidth: 77.9 mm (3.07 inches)\r\nDepth: 7.3 mm (0.29 inch)\r\nWEIGHT\r\nWeight: 192 grams (6.77 ounces)\r\nCHIP\r\nA9 chip with 64-bit architecture and integrated M9 motion coprocessor — 70 per cent faster CPU and 90 per cent faster GPU than A8 chip\r\nWIRELESS AND CELLULAR\r\nGSM/EDGE\r\nUMTS/HSPA+\r\nDC-HSDPA\r\nCDMA EV-DO Rev. A\r\n4G LTE Advanced3\r\n802', '28000', 10);

-- --------------------------------------------------------

--
-- Table structure for table `item_images`
--

CREATE TABLE IF NOT EXISTS `item_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_item` int(11) NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `item_images`
--

INSERT INTO `item_images` (`id`, `id_item`, `file_name`, `create_date`) VALUES
(13, 8, '9d214479904511afca43396bb6e12c1d.jpg', '2018-01-10 09:58:27');

-- --------------------------------------------------------

--
-- Table structure for table `ordered_items`
--

CREATE TABLE IF NOT EXISTS `ordered_items` (
  `id_ordered_items` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  `id_sub_category` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  PRIMARY KEY (`id_ordered_items`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `ordered_items`
--

INSERT INTO `ordered_items` (`id_ordered_items`, `id_order`, `id_category`, `id_sub_category`, `id_item`, `quantity`) VALUES
(1, 69, 15, 101, 4, 1),
(2, 69, 15, 103, 5, 1),
(3, 70, 15, 102, 4, 1),
(4, 71, 15, 103, 6, 1),
(5, 71, 15, 103, 4, 1),
(6, 72, 15, 102, 5, 1),
(7, 73, 15, 103, 6, 1),
(8, 74, 21, 103, 5, 1),
(14, 79, 21, 103, 6, 4),
(15, 79, 21, 103, 5, 2),
(16, 80, 21, 103, 5, 3),
(17, 80, 21, 103, 5, 1),
(18, 81, 21, 106, 8, 1),
(19, 82, 21, 106, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_customer` int(11) NOT NULL,
  `total_amount` double NOT NULL,
  `total_quantity` varchar(50) NOT NULL,
  `payment_method` varchar(15) NOT NULL,
  `promo_code` varchar(12) DEFAULT NULL,
  `id_delivery_man` int(11) DEFAULT NULL,
  `message` varchar(250) NOT NULL DEFAULT 'No message',
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `device_id` varchar(50) DEFAULT NULL,
  `status` enum('Pending','Delivered','Rejected') NOT NULL DEFAULT 'Pending',
  `admin_message` text,
  `deliver_date` date DEFAULT NULL,
  `shipping_cost` double DEFAULT NULL,
  `shipping_method` varchar(200) DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `discount_value` double DEFAULT NULL,
  `discount_type` enum('amount','percentage') DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=83 ;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id_order`, `id_customer`, `total_amount`, `total_quantity`, `payment_method`, `promo_code`, `id_delivery_man`, `message`, `order_date`, `device_id`, `status`, `admin_message`, `deliver_date`, `shipping_cost`, `shipping_method`, `discount_amount`, `discount_value`, `discount_type`) VALUES
(81, 9, 3100, '1', '', NULL, NULL, 'i want it asap.', '2018-01-05 10:32:29', 'ec8be6b15f9882ac', '', NULL, NULL, 100, NULL, NULL, NULL, NULL),
(82, 10, 3100, '1', '', NULL, NULL, '', '2018-01-06 09:03:39', '4ecd597498fc9100', '', NULL, NULL, 100, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pass_recover_tokens`
--

CREATE TABLE IF NOT EXISTS `pass_recover_tokens` (
  `id_token` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `token` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `status` enum('valid','invalid') NOT NULL,
  `expiration_date` varchar(30) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_token`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `pass_recover_tokens`
--

INSERT INTO `pass_recover_tokens` (`id_token`, `id_user`, `token`, `email`, `status`, `expiration_date`, `create_date`) VALUES
(2, 8, 'Xk02zKOalVYY1DCDvROT', 'musabbir.mamun@gmail.com', 'invalid', '2017-09-20 07:11:30', '2017-09-18 07:11:30'),
(3, 15, 'qjyx4Kvqe0dGM5i41rNL', 'musabbir.mamun@gmail.com', 'valid', '2017-10-05 17:58:14', '2017-10-03 15:58:14'),
(4, 15, 'XxuWC2tBsT7pOBwCx7gW', 'musabbir.mamun@gmail.com', 'valid', '2017-10-05 17:58:18', '2017-10-03 15:58:18');

-- --------------------------------------------------------

--
-- Table structure for table `registered_customer`
--

CREATE TABLE IF NOT EXISTS `registered_customer` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `status` enum('published','unpublished') NOT NULL DEFAULT 'published',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `registered_customer`
--

INSERT INTO `registered_customer` (`id_user`, `name`, `phone`, `email`, `password`, `address`, `status`, `create_date`, `update_date`) VALUES
(13, 'MM', '5986659768', 'mmnn@gmail.com', '0000', 'Lalmatia, 12/A', 'published', '2017-10-25 14:23:10', '2017-11-25 11:02:18'),
(14, 'abtahi', '01975515969', 'abtahinoor2000@gmail.com', '0000', 'new dohs', 'published', '2017-12-26 16:09:51', '0000-00-00 00:00:00'),
(15, 'MN', '01726315133', 'musabbir.mamun@gmail.com', '0000', 'Here and there', 'published', '2018-01-03 09:20:44', '0000-00-00 00:00:00'),
(16, 'farhad', '01679090669', 'farhadhasan75@gmail.com', 'fixkoro', 'nikunja', 'published', '2018-01-05 09:42:21', '0000-00-00 00:00:00'),
(17, 'tameem', '01676083574', 'hydronoid1996@gmail.com', 'transform', 'dhanmondi 2', 'published', '2018-01-06 09:02:28', '0000-00-00 00:00:00'),
(18, 'Ali', '01718433338', 'alichowdhury141@gmail.com', 'surahfalaq3004', 'Mohammadpur', 'published', '2018-01-06 18:19:09', '0000-00-00 00:00:00'),
(19, 'Rubel', '258369', 'rubel@gmail.co', '0000', 'Uttara', 'published', '2018-01-07 05:04:03', '0000-00-00 00:00:00'),
(20, 'exor', '01619262692', 'exorcist.shovan@gmail.com', 'abcd1234GG', '', 'published', '2018-01-07 05:49:01', '0000-00-00 00:00:00'),
(21, 'obidul', '01729832790', 'obaidulhaque@gmail.com', '0000', 'dhanmondi', 'published', '2018-01-07 06:52:09', '0000-00-00 00:00:00'),
(22, 'Afifa noor', '01714091143', 'afifaaroni@gmail.com', 'hatchpatch', 'Mohakhali dohs road 22', 'published', '2018-01-07 15:22:22', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `service_images`
--

CREATE TABLE IF NOT EXISTS `service_images` (
  `id_image` int(11) NOT NULL AUTO_INCREMENT,
  `id_service_request` int(11) NOT NULL,
  `image` varchar(200) NOT NULL,
  `uploaded_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_image`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=63 ;

--
-- Dumping data for table `service_images`
--

INSERT INTO `service_images` (`id_image`, `id_service_request`, `image`, `uploaded_date`) VALUES
(6, 12, '20171026063656261100.jpeg', '2017-10-26 06:36:56'),
(7, 12, '20171026063656797400.jpeg', '2017-10-26 06:36:56'),
(8, 12, '20171026063657149500.jpeg', '2017-10-26 06:36:57'),
(9, 12, '20171026063657207600.jpeg', '2017-10-26 06:36:57'),
(10, 1, '20171107045930120000.jpeg', '2017-11-13 09:07:06'),
(11, 2, '20171125130735130000.jpeg', '2017-11-25 13:07:35'),
(12, 2, '20171125130736980000.jpeg', '2017-11-25 13:07:36'),
(13, 2, '20171125130739720000.jpeg', '2017-11-25 13:07:39'),
(14, 3, '20171215141358160000.jpeg', '2017-12-15 14:13:58'),
(15, 4, '20171219173106690000.jpeg', '2017-12-19 17:31:06'),
(16, 4, '20171219173109820000.jpeg', '2017-12-19 17:31:09'),
(17, 4, '20171219173113980000.jpeg', '2017-12-19 17:31:13'),
(18, 4, '20171219173117950000.jpeg', '2017-12-19 17:31:17'),
(19, 4, '20171219173122810000.jpeg', '2017-12-19 17:31:22'),
(20, 4, '20171219173125890000.jpeg', '2017-12-19 17:31:25'),
(21, 5, '20171219173557640000.jpeg', '2017-12-19 17:35:57'),
(22, 5, '20171219173600890000.jpeg', '2017-12-19 17:36:00'),
(23, 5, '20171219173604330000.jpeg', '2017-12-19 17:36:04'),
(24, 5, '20171219173610150000.jpeg', '2017-12-19 17:36:10'),
(25, 5, '20171219173613360000.jpeg', '2017-12-19 17:36:13'),
(26, 5, '20171219173614210000.jpeg', '2017-12-19 17:36:14'),
(27, 5, '20171219173617920000.jpeg', '2017-12-19 17:36:17'),
(28, 6, '20171224121442970000.jpeg', '2017-12-24 12:14:42'),
(29, 9, '20171226161227730000.jpeg', '2017-12-26 16:12:27'),
(30, 9, '20171226161230900000.jpeg', '2017-12-26 16:12:30'),
(31, 9, '20171226161234190000.jpeg', '2017-12-26 16:12:34'),
(32, 9, '20171226161242550000.jpeg', '2017-12-26 16:12:42'),
(33, 9, '20171226161244220000.jpeg', '2017-12-26 16:12:44'),
(34, 9, '20171226161248910000.jpeg', '2017-12-26 16:12:48'),
(35, 11, '20171227145530440000.jpeg', '2017-12-27 14:55:30'),
(36, 11, '20171227145534810000.jpeg', '2017-12-27 14:55:34'),
(37, 11, '20171227145538390000.jpeg', '2017-12-27 14:55:38'),
(38, 12, '20171227145556120000.jpeg', '2017-12-27 14:55:56'),
(39, 12, '20171227145602770000.jpeg', '2017-12-27 14:56:02'),
(40, 12, '20171227145606440000.jpeg', '2017-12-27 14:56:06'),
(41, 13, '20171227181813740000.jpeg', '2017-12-27 18:18:13'),
(42, 14, '20171227182221610000.jpeg', '2017-12-27 18:22:21'),
(43, 15, '20171227182316820000.jpeg', '2017-12-27 18:23:16'),
(44, 15, '20171227182324160000.jpeg', '2017-12-27 18:23:24'),
(45, 15, '20171227182326560000.jpeg', '2017-12-27 18:23:26'),
(46, 15, '20171227182329160000.jpeg', '2017-12-27 18:23:29'),
(47, 16, '20171230051656360000.jpeg', '2017-12-30 05:16:56'),
(48, 17, '20180103092216830000.jpeg', '2018-01-03 09:22:16'),
(49, 17, '20180103092221210000.jpeg', '2018-01-03 09:22:21'),
(50, 17, '20180103092230750000.jpeg', '2018-01-03 09:22:30'),
(51, 18, '20180105110942410000.jpeg', '2018-01-05 11:09:42'),
(52, 18, '20180105110946070000.jpeg', '2018-01-05 11:09:46'),
(53, 18, '20180105110950720000.jpeg', '2018-01-05 11:09:50'),
(54, 20, '20180106090726350000.jpeg', '2018-01-06 09:07:26'),
(55, 22, '20180106090756850000.jpeg', '2018-01-06 09:07:56'),
(56, 23, '20180106090804730000.jpeg', '2018-01-06 09:08:04'),
(57, 24, '20180106090817270000.jpeg', '2018-01-06 09:08:17'),
(58, 25, '20180107065531480000.jpeg', '2018-01-07 06:55:31'),
(59, 25, '20180107065534900000.jpeg', '2018-01-07 06:55:34'),
(60, 25, '20180107065537200000.jpeg', '2018-01-07 06:55:37'),
(61, 25, '20180107065550280000.jpeg', '2018-01-07 06:55:50'),
(62, 25, '20180107065553750000.jpeg', '2018-01-07 06:55:53');

-- --------------------------------------------------------

--
-- Table structure for table `service_requests`
--

CREATE TABLE IF NOT EXISTS `service_requests` (
  `id_service_req` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `service_name` varchar(50) NOT NULL,
  `description` text,
  `status` enum('Requested','Pending','Delivered','Rejected') NOT NULL DEFAULT 'Requested',
  `served_by` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_service_req`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `service_requests`
--

INSERT INTO `service_requests` (`id_service_req`, `id_user`, `service_name`, `description`, `status`, `served_by`, `create_date`, `update_date`) VALUES
(1, 13, 'Laptop', 'f SG d gfg ggf', 'Rejected', '', '2017-11-13 09:07:06', '2018-01-05 11:08:41'),
(2, 13, 'Games', 'Game pad not working', 'Rejected', '', '2017-11-25 13:07:31', '2017-12-15 14:12:52'),
(3, 13, 'Mobile', 'Broken screen', 'Rejected', '', '2017-12-15 14:13:56', '2018-01-05 11:08:44'),
(4, 13, 'Mobile', 'i have problem with screen\n \n\n\n\n\n\n', 'Rejected', '', '2017-12-19 17:31:01', '2017-12-19 17:41:24'),
(5, 13, 'Mobile', 'i have problem with screen\n \n\n\n\n\n\n', 'Rejected', '', '2017-12-19 17:35:52', '2017-12-19 17:42:55'),
(6, 13, 'Mobile', 'amar phone er display nostso s5\n', 'Rejected', '', '2017-12-24 12:14:40', '2018-01-05 11:08:17'),
(7, 13, 'Laptop', '', 'Rejected', '', '2017-12-24 14:35:57', '2018-01-05 11:08:20'),
(8, 13, 'Laptop', '', 'Rejected', '', '2017-12-24 14:36:14', '2018-01-05 11:08:23'),
(9, 14, 'Laptop', 'laptop er screen kaj kore na\n', 'Rejected', '', '2017-12-26 16:12:22', '2018-01-05 11:08:47'),
(10, 13, 'Games', '', 'Rejected', '', '2017-12-26 17:18:19', '2018-01-05 11:08:26'),
(11, 13, 'Mobile', 'Screen has broken', 'Rejected', '', '2017-12-27 14:55:19', '2017-12-27 14:59:07'),
(12, 13, 'Mobile', 'Screen has broken', 'Rejected', '', '2017-12-27 14:55:46', '2017-12-27 14:58:14'),
(13, 13, 'Laptop', 'Battery is dead', 'Rejected', '', '2017-12-27 18:18:04', '2018-01-05 11:08:50'),
(14, 13, 'Games', 'dhgf gjg yhuj', 'Rejected', '', '2017-12-27 18:21:57', '2018-01-05 11:08:28'),
(15, 13, 'Laptop', 'DG JB g', 'Rejected', '', '2017-12-27 18:23:12', '2018-01-05 11:08:31'),
(16, 13, 'Others', 'Don''t you repair watch?', 'Rejected', '', '2017-12-30 05:16:54', '2018-01-05 11:08:33'),
(17, 15, 'Others', 'ggfg ggf fg gght\n', 'Rejected', '', '2018-01-03 09:22:03', '2018-01-05 11:08:52'),
(18, 14, 'Mobile', 'i broke my nokia phone any suggestion?\n', 'Rejected', '', '2018-01-05 11:09:36', '2018-01-06 18:57:45'),
(19, 17, 'Laptop', 'ram', 'Rejected', '', '2018-01-06 09:05:59', '2018-01-06 18:57:48'),
(20, 17, 'Mobile', 'screen ', 'Rejected', '', '2018-01-06 09:07:24', '2018-01-06 18:57:50'),
(21, 17, 'Games', 'hdhsn\n', 'Rejected', '', '2018-01-06 09:07:41', '2018-01-06 18:57:53'),
(22, 17, 'LCD TV', 'hshsn', 'Rejected', '', '2018-01-06 09:07:50', '2018-01-06 18:57:56'),
(23, 17, 'MP3 and MP4', 'hdhsh', 'Requested', '', '2018-01-06 09:08:01', '0000-00-00 00:00:00'),
(24, 17, 'Others', 'hdhdj', 'Requested', '', '2018-01-06 09:08:11', '0000-00-00 00:00:00'),
(25, 13, 'Games', 'Checking', 'Rejected', '', '2018-01-07 06:55:26', '2018-01-07 07:01:37'),
(26, 20, 'LCD TV', '', 'Requested', '', '2018-01-07 07:10:35', '0000-00-00 00:00:00'),
(27, 20, 'Mobile', 'test', 'Requested', '', '2018-01-09 05:14:16', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `subcategory`
--

CREATE TABLE IF NOT EXISTS `subcategory` (
  `id_subcategory` int(11) NOT NULL AUTO_INCREMENT,
  `id_category` int(11) NOT NULL,
  `subcategory_name` varchar(150) NOT NULL,
  `remark` varchar(150) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_subcategory`),
  KEY `id_category` (`id_category`),
  KEY `subcategory_name` (`subcategory_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=114 ;

--
-- Dumping data for table `subcategory`
--

INSERT INTO `subcategory` (`id_subcategory`, `id_category`, `subcategory_name`, `remark`, `image`) VALUES
(113, 21, 'Second Hand Smartphone', '', NULL),
(106, 21, 'Replacement Parts', '', '2294fbbea3b221bcaa170f5bf779bbad.png'),
(112, 21, 'Screen Protectors', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE IF NOT EXISTS `technician` (
  `id_technician` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` int(15) NOT NULL,
  `working_area` text,
  `special_on` varchar(250) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `longitude` varchar(20) NOT NULL DEFAULT '23.7511289',
  `lattitude` varchar(20) NOT NULL DEFAULT '90.3886628',
  `location` text,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `publication_status` enum('Enable','Disable') NOT NULL DEFAULT 'Enable',
  PRIMARY KEY (`id_technician`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `technician`
--

INSERT INTO `technician` (`id_technician`, `name`, `email`, `phone`, `working_area`, `special_on`, `image`, `longitude`, `lattitude`, `location`, `create_date`, `publication_status`) VALUES
(1, 'Talukdar', 'talukdar@gmail.com', 215641871, 'Banani, Gulshan', 'Mobile device', 'no_image.png', '', '', NULL, '2017-11-05 02:18:13', 'Disable'),
(2, 'Kashem Hasan', 'kashem@hashem.com', 2147483647, 'Dhanmondi', 'Laptop repair', '878389138eb3e88879ccfc5897f0ace0.jpg', '90.3886628', '23.7511289', NULL, '2017-11-05 05:19:33', 'Disable'),
(3, 'Raihan', 'haduahu@hud.com', 1680465336, 'Uttara', 'Game pad repairing', 'bcb6a3ad4b42f7ace5bb06a029b86a7c.jpg', '90.3886628', '23.7511289', NULL, '2017-11-05 06:26:09', 'Enable'),
(4, 'Test', 'hdgyau@jai.com', 1508702785, 'adwad', 'dwada', NULL, '', '', NULL, '2017-12-12 12:52:33', 'Disable'),
(5, 'Abdul', 'abdul@gmail.com', 1680465336, 'Bashundhara', 'Mobile repairing', '9277425c375b007882e4cd710741a980.jpg', '90.3886628', '23.7511289', NULL, '2017-12-19 17:47:38', 'Enable'),
(6, 'Selim Sheikh', 'selim@gmail.com', 1680465336, 'Dhanmondi', 'Game pad reapairing', '54c4136153ca975571adc1815b6b5393.jpeg', '90.4513256', '20.7845412', NULL, '2018-01-03 11:00:41', 'Enable'),
(7, 'Fahim Rahman', 'fahim@gmail.com', 1680465336, 'Paltan', 'Monitor servicing', '947609cf9da1410a0db36b5ad3ca0791.jpg', '90.2356451', '20.14521548', NULL, '2018-01-03 11:02:52', 'Enable'),
(8, 'Zishan rahman', 'zishan123@gmail.com', 1680465336, 'Gulshan 1', 'Mobile repairing', '5c59f495d1cc85c3912849e5edae2a79.jpg', '90.4513256', '23.7511289', NULL, '2018-01-05 10:18:08', 'Enable'),
(9, 'Niyamul Sany', '', 1534553064, 'Smartphone expert', 'Smartphone Expert', '3f59839f0889c1e897adb2cfe4fa9873.jpg', '90.3922371', '23.7421021', NULL, '2018-01-06 18:16:19', 'Enable');

-- --------------------------------------------------------

--
-- Table structure for table `top_items`
--

CREATE TABLE IF NOT EXISTS `top_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_top_item` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `top_items`
--

INSERT INTO `top_items` (`id`, `id_top_item`, `create_date`) VALUES
(5, 1445, '2017-09-15 04:27:38');

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE IF NOT EXISTS `unit` (
  `id_unit` int(11) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(10) NOT NULL,
  `decimal_or_not` enum('Yes','No') NOT NULL,
  PRIMARY KEY (`id_unit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `user_role` enum('user','doctor') NOT NULL,
  `country` varchar(50) NOT NULL,
  `phone` int(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `dr_type` varchar(50) DEFAULT NULL,
  `degrees` varchar(150) NOT NULL,
  `experience` int(3) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `reviews` varchar(250) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `fb_url` varchar(200) DEFAULT NULL,
  `skype_url` varchar(200) DEFAULT NULL,
  `twitter_url` varchar(200) DEFAULT NULL,
  `linkedin_url` varchar(200) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `device_id` varchar(50) DEFAULT NULL,
  `status` enum('published','unpublished') NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `first_name`, `last_name`, `user_role`, `country`, `phone`, `email`, `password`, `dr_type`, `degrees`, `experience`, `likes`, `reviews`, `image`, `fb_url`, `skype_url`, `twitter_url`, `linkedin_url`, `create_date`, `update_date`, `device_id`, `status`) VALUES
(15, 'Abu', 'Raihan', 'user', 'Bangladesh', 17258456, 'musabbir.mamun3@gmail.com', '39dfa55283318d31afe5a3ff4a0e3253e2045e43', '', '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-10-03 13:21:05', '2017-10-25 14:21:15', NULL, 'published');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
