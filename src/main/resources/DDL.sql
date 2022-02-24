USE test;

CREATE TABLE `customer` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nama` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`balance` BIGINT(20) NULL DEFAULT NULL,
	`owed_to_id` INT(11) NULL DEFAULT NULL,
	`owed_nominal` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `nama` (`nama`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;

INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_nominal`) VALUES (1, 'valentino', 5066000, NULL, NULL);
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_nominal`) VALUES (2, 'donny', 605000, NULL, NULL);
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_nominal`) VALUES (3, 'bayu', 1200000, NULL, NULL);
INSERT INTO `customer` (`id`, `nama`, `balance`, `owed_to_id`, `owed_nominal`) VALUES (4, 'abet', 440000, NULL, NULL);
