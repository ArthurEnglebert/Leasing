INSERT INTO `brands` (`id`, `name`) VALUES
(4, 'Audi'),
(3, 'BMW'),
(2, 'Citroën'),
(1, 'Peugeot');


INSERT INTO `models` (`id`, `name`, `brand_id`) VALUES
(1, '304', 1),
(2, '3008', 2),
(3, 'Picasso', 2),
(4, 'Z3', 3),
(5, 'Z4', 3),
(6, 'A1', 4),
(7, 'A2', 4),
(8, 'A3', 4),
(9, 'A4', 4);

INSERT INTO `cars` (`id`, `lock_status`, `number_plate`, `model_id`) VALUES
(1, 2, '1-AAA-111', 1),
(2, 2, '1-BBB-222', 2),
(3, 2, '1-CCC-333', 1);
