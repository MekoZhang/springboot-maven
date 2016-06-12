/* System Tables */
DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_dict`;
DROP TABLE IF EXISTS `sys_log`;
DROP TABLE IF EXISTS `sys_menu`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_role_menu`;
DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `login_name` VARCHAR(100) NOT NULL COMMENT '登录名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `name` VARCHAR(100) NOT NULL COMMENT '姓名',
  `email` VARCHAR(200) COMMENT '邮箱',
  `phone` VARCHAR(200) COMMENT '电话',
  `mobile` VARCHAR(200) COMMENT '手机',
  `user_type` CHAR(1) COMMENT '用户类型',
  `login_ip` VARCHAR(100) COMMENT '最后登陆IP',
  `login_date` DATETIME COMMENT '最后登陆时间',
  `login_flag` VARCHAR(64) COMMENT '是否可登录',
  `create_by` VARCHAR(64) NOT NULL COMMENT '创建者',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) NOT NULL COMMENT '更新者',
  `update_date` DATETIME NOT NULL COMMENT '更新时间',
  `remarks` VARCHAR(255) COMMENT '备注信息',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='用户表';

CREATE TABLE `sys_dict` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `value` VARCHAR(100) NOT NULL COMMENT '数据值',
  `label` VARCHAR(100) NOT NULL COMMENT '标签名',
  `type` VARCHAR(100) NOT NULL COMMENT '类型',
  `description` VARCHAR(100) NOT NULL COMMENT '描述',
  `sort` DECIMAL(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` VARCHAR(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` VARCHAR(64) NOT NULL COMMENT '创建者',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) NOT NULL COMMENT '更新者',
  `update_date` DATETIME NOT NULL COMMENT '更新时间',
  `remarks` VARCHAR(255) COMMENT '备注信息',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='字典表';

CREATE TABLE `sys_log` (
  `id` VARCHAR(64) NOT NULL COMMENT '编号',
  `type` CHAR(1) DEFAULT '1' COMMENT '日志类型',
  `title` VARCHAR(255) DEFAULT '' COMMENT '日志标题',
  `create_by` VARCHAR(64) COMMENT '创建者',
  `create_date` DATETIME COMMENT '创建时间',
  `remote_addr` VARCHAR(255) COMMENT '操作IP地址',
  `user_agent` VARCHAR(255) COMMENT '用户代理',
  `request_uri` VARCHAR(255) COMMENT '请求URI',
  `method` VARCHAR(5) COMMENT '操作方式',
  `params` TEXT COMMENT '操作提交的数据',
  `exception` TEXT COMMENT '异常信息',
  PRIMARY KEY (`id`)
) COMMENT='日志表';

CREATE TABLE `sys_menu` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `parent_id` VARCHAR(64) NOT NULL COMMENT '父级编号',
  `parent_ids` VARCHAR(2000) NOT NULL COMMENT '所有父级编号',
  `name` VARCHAR(100) NOT NULL COMMENT '名称',
  `sort` DECIMAL(10,0) NOT NULL COMMENT '排序',
  `href` VARCHAR(2000) COMMENT '链接',
  `target` VARCHAR(20) COMMENT '目标',
  `icon` VARCHAR(100) COMMENT '图标',
  `is_show` CHAR(1) NOT NULL COMMENT '是否在菜单中显示',
  `permission` VARCHAR(200) COMMENT '权限标识',
  `create_by` VARCHAR(64) NOT NULL COMMENT '创建者',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) NOT NULL COMMENT '更新者',
  `update_date` DATETIME NOT NULL COMMENT '更新时间',
  `remarks` VARCHAR(255) COMMENT '备注信息',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='菜单表';

CREATE TABLE `sys_role` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `name` VARCHAR(100) NOT NULL COMMENT '角色名称',
  `is_sys` VARCHAR(64) COMMENT '是否系统数据',
  `useable` VARCHAR(64) COMMENT '是否可用',
  `create_by` VARCHAR(64) NOT NULL COMMENT '创建者',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) NOT NULL COMMENT '更新者',
  `update_date` DATETIME NOT NULL COMMENT '更新时间',
  `remarks` VARCHAR(255) COMMENT '备注信息',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='角色表';

CREATE TABLE `sys_role_menu` (
  `role_id` VARCHAR(36) NOT NULL COMMENT '角色编号',
  `menu_id` VARCHAR(36) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) COMMENT='角色-菜单';

CREATE TABLE `sys_user_role` (
  `user_id` VARCHAR(36) NOT NULL COMMENT '用户编号',
  `role_id` VARCHAR(36) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) COMMENT='用户-角色';

/* Trip Tables */
DROP TABLE IF EXISTS `trip_user`;

CREATE TABLE `trip_user` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `login` VARCHAR(255) NOT NULL COMMENT '账号',
  `password` VARCHAR(255) NOT NULL COMMENT '密码',
  `name` VARCHAR(255) NOT NULL COMMENT '姓名',
  `gender` CHAR(1) DEFAULT '0' COMMENT '性别\n0：未知\n1：男\n2：女',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)
) COMMENT='用户';