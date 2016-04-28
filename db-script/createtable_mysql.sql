DROP TABLE IF EXISTS `trip_user`;

CREATE TABLE `user` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `login` VARCHAR(255) NOT NULL COMMENT '账号',
  `password` VARCHAR(255) NOT NULL COMMENT '密码',
  `name` VARCHAR(255) NOT NULL COMMENT '姓名',
  `gender` CHAR(1) DEFAULT '0' COMMENT '性别\n0：未知\n1：男\n2：女',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)
) COMMENT='用户';