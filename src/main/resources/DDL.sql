USE test;

CREATE TABLE `customer` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nama` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`balance` BIGINT(20) NOT NULL,
	`owed_to_id` INT(11) NULL DEFAULT NULL,
	`owed_to_nominal` BIGINT(20) NULL DEFAULT NULL,
	`keterangan` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `nama` (`nama`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;


INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_to_nominal`, `keterangan`) VALUES (1, 'valentino', 166000, NULL, NULL, 'Owed $100000 from abet');
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_to_nominal`, `keterangan`) VALUES (2, 'donny', 605000, NULL, NULL, NULL);
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_to_nominal`, `keterangan`) VALUES (3, 'bayu', 1200000, 2, 500000, NULL);
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_to_nominal`, `keterangan`) VALUES (4, 'abet', 820000, NULL, NULL, NULL);
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_to_nominal`, `keterangan`) VALUES (5, 'vincent', 550000, 3, 250000, NULL);
