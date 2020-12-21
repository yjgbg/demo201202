use `demo201202`;
create table data_insertion_config
(
    `id`         int primary key auto_increment,
    `key`        varchar(255),
    `sql_format` varchar(511)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;