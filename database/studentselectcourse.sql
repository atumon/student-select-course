/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : studentselectcourse

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 21/12/2019 10:40:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `AdminId` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AdminPassword` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '111111',
  `AdminPhone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AdminOffice` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AdminId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('12345', '111111', '12311112222', '文宗楼123');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `CourseId` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CourseName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CourseCapacity` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `CoursePoint` decimal(3, 1) UNSIGNED NOT NULL DEFAULT 0.0,
  `CourseDept` int(11) NOT NULL,
  `CourseAddress` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CourseTeacherId` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`CourseId`) USING BTREE,
  INDEX `FK_CDept`(`CourseDept`) USING BTREE,
  INDEX `index_courseteacher`(`CourseTeacherId`) USING BTREE,
  CONSTRAINT `FK_CDept` FOREIGN KEY (`CourseDept`) REFERENCES `department` (`DepartmentNo`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_CTId` FOREIGN KEY (`CourseTeacherId`) REFERENCES `teacher` (`TeacherId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1111111', ' 高等数学', 20, 4.0, 1, '101', '11111');
INSERT INTO `course` VALUES ('1111112', '线性代数', 20, 4.0, 1, '102', '11112');
INSERT INTO `course` VALUES ('1111113', '概率论与数理统计', 20, 5.0, 1, '103', '11113');
INSERT INTO `course` VALUES ('1111114', '演员的自我修养', 10, 2.5, 2, '104', '11114');
INSERT INTO `course` VALUES ('1111115', '犯罪心理学', 15, 3.0, 2, '105', '11115');
INSERT INTO `course` VALUES ('1111116', '心理学概论', 15, 3.0, 2, '106', '11111');
INSERT INTO `course` VALUES ('1111117', '数据库系统实践', 15, 3.0, 1, '107', '11112');

-- ----------------------------
-- Table structure for coursetime
-- ----------------------------
DROP TABLE IF EXISTS `coursetime`;
CREATE TABLE `coursetime`  (
  `CourseTimeId` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CourseTimeWeek` int(11) NOT NULL,
  `CourseTimeClass` int(11) NOT NULL,
  PRIMARY KEY (`CourseTimeId`, `CourseTimeWeek`, `CourseTimeClass`) USING BTREE,
  INDEX `index_ctimecourseid`(`CourseTimeId`) USING BTREE,
  CONSTRAINT `FK_CTCId` FOREIGN KEY (`CourseTimeId`) REFERENCES `course` (`CourseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coursetime
-- ----------------------------
INSERT INTO `coursetime` VALUES ('1111111', 1, 1);
INSERT INTO `coursetime` VALUES ('1111111', 1, 2);
INSERT INTO `coursetime` VALUES ('1111111', 3, 1);
INSERT INTO `coursetime` VALUES ('1111111', 3, 2);
INSERT INTO `coursetime` VALUES ('1111112', 2, 1);
INSERT INTO `coursetime` VALUES ('1111112', 2, 2);
INSERT INTO `coursetime` VALUES ('1111112', 5, 1);
INSERT INTO `coursetime` VALUES ('1111112', 5, 2);
INSERT INTO `coursetime` VALUES ('1111113', 2, 5);
INSERT INTO `coursetime` VALUES ('1111113', 2, 6);
INSERT INTO `coursetime` VALUES ('1111114', 5, 5);
INSERT INTO `coursetime` VALUES ('1111114', 5, 6);
INSERT INTO `coursetime` VALUES ('1111114', 5, 7);
INSERT INTO `coursetime` VALUES ('1111115', 4, 9);
INSERT INTO `coursetime` VALUES ('1111115', 4, 10);
INSERT INTO `coursetime` VALUES ('1111116', 1, 7);
INSERT INTO `coursetime` VALUES ('1111116', 1, 8);
INSERT INTO `coursetime` VALUES ('1111117', 1, 1);
INSERT INTO `coursetime` VALUES ('1111117', 1, 2);
INSERT INTO `coursetime` VALUES ('1111117', 4, 3);
INSERT INTO `coursetime` VALUES ('1111117', 4, 4);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `DepartmentNo` int(11) NOT NULL,
  `DepartmentName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`DepartmentNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (0, '公共课程');
INSERT INTO `department` VALUES (1, '信息科学与工程学院');
INSERT INTO `department` VALUES (2, '心理学院');

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse`  (
  `SelectCourseSid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SelectCourseCid` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SelectCourseScore` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SelectCourseSid`, `SelectCourseCid`) USING BTREE,
  INDEX `index_sccourseid`(`SelectCourseCid`) USING BTREE,
  INDEX `index_scstudentid`(`SelectCourseSid`) USING BTREE,
  CONSTRAINT `FK_SCCid` FOREIGN KEY (`SelectCourseCid`) REFERENCES `course` (`CourseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SCSid` FOREIGN KEY (`SelectCourseSid`) REFERENCES `student` (`StudentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------
INSERT INTO `selectcourse` VALUES ('201711111111', '1111113', NULL);
INSERT INTO `selectcourse` VALUES ('201711111111', '1111114', NULL);
INSERT INTO `selectcourse` VALUES ('201711111111', '1111117', NULL);
INSERT INTO `selectcourse` VALUES ('201711111112', '1111112', NULL);
INSERT INTO `selectcourse` VALUES ('201711111112', '1111113', NULL);
INSERT INTO `selectcourse` VALUES ('201711111112', '1111114', NULL);
INSERT INTO `selectcourse` VALUES ('201711111120', '1111111', NULL);

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `AllowSelectCourse` tinyint(1) NOT NULL,
  `AllowInputScore` tinyint(1) NOT NULL,
  PRIMARY KEY (`AllowSelectCourse`, `AllowInputScore`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (1, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `StudentId` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `StudentName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `StudentDept` int(11) NOT NULL,
  `StudentSex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `StudentPassword` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '666666',
  `StudentNational` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `StudentSignYear` decimal(4, 0) NULL DEFAULT NULL,
  `StudentMajor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `StudentBirthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`StudentId`) USING BTREE,
  INDEX `FK_SDept`(`StudentDept`) USING BTREE,
  CONSTRAINT `FK_SDept` FOREIGN KEY (`StudentDept`) REFERENCES `department` (`DepartmentNo`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201711111111', '胡德水', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-01');
INSERT INTO `student` VALUES ('201711111112', '潘孜莱蒂', 1, '女', '666666', '维吾尔', 2017, '软件工程', '1999-01-02');
INSERT INTO `student` VALUES ('201711111113', '余俊智', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-03');
INSERT INTO `student` VALUES ('201711111114', '陆叶帆', 1, '女', '666666', '汉', 2017, '软件工程', '1999-01-04');
INSERT INTO `student` VALUES ('201711111115', '黎鸿禧', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-05');
INSERT INTO `student` VALUES ('201711111116', '邵梓瑶', 1, '女', '666666', '汉', 2017, '软件工程', '1999-01-06');
INSERT INTO `student` VALUES ('201711111117', '姜俊捷', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-07');
INSERT INTO `student` VALUES ('201711111118', '赵慧艳', 1, '女', '666666', '汉', 2017, '软件工程', '1999-01-08');
INSERT INTO `student` VALUES ('201711111119', '戴兴生', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-09');
INSERT INTO `student` VALUES ('201711111120', '萧惜霜', 1, '女', '666666', '汉', 2017, '软件工程', '1999-01-10');
INSERT INTO `student` VALUES ('201711111121', '汤子晋', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-11');
INSERT INTO `student` VALUES ('201711111122', '田永望', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-12');
INSERT INTO `student` VALUES ('201711111123', '苏阳炎', 1, '男', '666666', '汉', 2017, '软件工程', '1999-01-13');
INSERT INTO `student` VALUES ('201711111124', '任作人', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-14');
INSERT INTO `student` VALUES ('201711111125', '林宛曼', 1, '女', '666666', '汉', 2017, '物联网', '1999-01-15');
INSERT INTO `student` VALUES ('201711111126', '顾寻桃', 1, '女', '666666', '汉', 2017, '物联网', '1999-01-16');
INSERT INTO `student` VALUES ('201711111127', '黄悠柔', 1, '女', '666666', '汉', 2017, '物联网', '1999-01-17');
INSERT INTO `student` VALUES ('201711111128', '曾凌蝶', 1, '女', '666666', '汉', 2017, '物联网', '1999-01-18');
INSERT INTO `student` VALUES ('201711111129', '郑俊语', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-19');
INSERT INTO `student` VALUES ('201711111130', '陈濮存', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-20');
INSERT INTO `student` VALUES ('201711111131', '赵飞翔', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-21');
INSERT INTO `student` VALUES ('201711111132', '卢康乐', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-22');
INSERT INTO `student` VALUES ('201711111133', '范学文', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-23');
INSERT INTO `student` VALUES ('201711111134', '曾嘉熙', 1, '男', '666666', '汉', 2017, '物联网', '1999-01-24');
INSERT INTO `student` VALUES ('201711111135', '郭珂妍', 1, '女', '666666', '汉', 2017, '物联网', '1999-01-25');
INSERT INTO `student` VALUES ('201711111136', '邱易巧', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-01-26');
INSERT INTO `student` VALUES ('201711111137', '冯玲珑', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-01-27');
INSERT INTO `student` VALUES ('201711111138', '侯晓瑶', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-01-28');
INSERT INTO `student` VALUES ('201711111139', '谭冠玉', 2, '男', '666666', '汉', 2017, '认知心理学', '1999-01-29');
INSERT INTO `student` VALUES ('201711111140', '廖谷菱', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-01-30');
INSERT INTO `student` VALUES ('201711111141', '邵元忠', 2, '男', '666666', '汉', 2017, '认知心理学', '1999-01-31');
INSERT INTO `student` VALUES ('201711111142', '曹向梦', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-02-01');
INSERT INTO `student` VALUES ('201711111143', '崔玉兰', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-02-02');
INSERT INTO `student` VALUES ('201711111144', '龚子瑜', 2, '男', '666666', '汉', 2017, '认知心理学', '1999-02-03');
INSERT INTO `student` VALUES ('201711111145', '陈浩思', 2, '男', '666666', '汉', 2017, '认知心理学', '1999-02-04');
INSERT INTO `student` VALUES ('201711111146', '蒋峻熙', 2, '男', '666666', '汉', 2017, '认知心理学', '1999-02-05');
INSERT INTO `student` VALUES ('201711111147', '毛正思', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-02-06');
INSERT INTO `student` VALUES ('201711111148', '郑安琪', 2, '女', '666666', '汉', 2017, '认知心理学', '1999-02-07');
INSERT INTO `student` VALUES ('201711111149', '杨幼仪', 2, '女', '666666', '汉', 2017, '社会心理学', '1999-02-08');
INSERT INTO `student` VALUES ('201711111150', '田文柏', 2, '男', '666666', '汉', 2017, '社会心理学', '1999-02-09');
INSERT INTO `student` VALUES ('201711111151', '叶志勇', 2, '男', '666666', '汉', 2017, '社会心理学', '1999-02-10');
INSERT INTO `student` VALUES ('201711111152', '邹夏寒', 2, '女', '666666', '汉', 2017, '社会心理学', '1999-02-11');
INSERT INTO `student` VALUES ('201711111153', '毛志强', 2, '男', '666666', '汉', 2017, '社会心理学', '1999-02-12');
INSERT INTO `student` VALUES ('201711111154', '刘德辉', 2, '男', '666666', '汉', 2017, '社会心理学', '1999-02-13');
INSERT INTO `student` VALUES ('201711111155', '曾薏冉', 2, '女', '666666', '汉', 2017, '社会心理学', '1999-02-14');
INSERT INTO `student` VALUES ('201711111156', '罗烨伟', 2, '男', '666666', '汉', 2017, '社会心理学', '1999-02-15');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `TeacherId` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TeacherName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TeacherPhone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TeacherOffice` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TeacherDept` int(11) NOT NULL,
  `TeacherPassword` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '111111',
  `TeacherSex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TeacherTitle` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TeacherPoliticalRole` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TeacherNational` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TeacherSignDate` date NULL DEFAULT NULL,
  PRIMARY KEY (`TeacherId`) USING BTREE,
  INDEX `FK_TDept`(`TeacherDept`) USING BTREE,
  CONSTRAINT `FK_TDept` FOREIGN KEY (`TeacherDept`) REFERENCES `department` (`DepartmentNo`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('11111', '朱敏学', '12345678910', '信工楼223', 1, '111111', '男', '讲师', '党员', '汉', '2000-06-06');
INSERT INTO `teacher` VALUES ('11112', '彭晗日', '12345678910', '信工楼223', 1, '111111', '男', '讲师', '党员', '汉', '2000-06-07');
INSERT INTO `teacher` VALUES ('11113', '易志诚', '12345678910', '信工楼223', 1, '111111', '男', '副教授', '党员', '汉', '2000-06-08');
INSERT INTO `teacher` VALUES ('11114', '孙平心', '12346578910', '信工楼223', 2, '111111', '女', '教授', '党员', '汉', '2000-06-09');
INSERT INTO `teacher` VALUES ('11115', '万竹萱', '12345678910', '信工楼223', 2, '111111', '女', '讲师', '党员', '汉', '2000-06-10');

-- ----------------------------
-- View structure for coursetable
-- ----------------------------
DROP VIEW IF EXISTS `coursetable`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `coursetable` AS select `course`.`CourseId` AS `CourseId`,`course`.`CourseName` AS `CourseName`,`course`.`CourseAddress` AS `CourseAddress`,`coursetime`.`CourseTimeWeek` AS `Week`,`coursetime`.`CourseTimeClass` AS `Class`,`selectcourse`.`SelectCourseSid` AS `StudentId`,`teacher`.`TeacherName` AS `TeacherName`,`teacher`.`TeacherId` AS `TeacherId`,`func_getcoursetimechar`(`course`.`CourseId`) AS `CourseTimeChar` from (((`course` join `selectcourse`) join `teacher`) join `coursetime`) where ((`course`.`CourseId` = `selectcourse`.`SelectCourseCid`) and (`course`.`CourseId` = `coursetime`.`CourseTimeId`) and (`course`.`CourseTeacherId` = `teacher`.`TeacherId`));

-- ----------------------------
-- View structure for selectcourseview
-- ----------------------------
DROP VIEW IF EXISTS `selectcourseview`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `selectcourseview` AS select `course`.`CourseId` AS `CourseId`,`course`.`CourseName` AS `CourseName`,`func_returncourseremain`(`course`.`CourseId`) AS `CourseRemain`,`course`.`CoursePoint` AS `CoursePoint`,`course`.`CourseCapacity` AS `CourseCapacity`,`course`.`CourseAddress` AS `CourseAddress`,`teacher`.`TeacherName` AS `TeacherName`,`teacher`.`TeacherId` AS `TeacherId`,`course`.`CourseDept` AS `CourseDept` from (`course` join `teacher`) where (`course`.`CourseTeacherId` = `teacher`.`TeacherId`);

-- ----------------------------
-- View structure for teachernamed
-- ----------------------------
DROP VIEW IF EXISTS `teachernamed`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `teachernamed` AS select `course`.`CourseId` AS `CourseId`,`course`.`CourseName` AS `CourseName`,`course`.`CourseTeacherId` AS `TeacherId`,`student`.`StudentId` AS `StudentId`,`student`.`StudentName` AS `StudentName`,`student`.`StudentSex` AS `StudentSex`,`selectcourse`.`SelectCourseScore` AS `Score` from ((`course` join `selectcourse`) join `student`) where ((`course`.`CourseId` = `selectcourse`.`SelectCourseCid`) and (`student`.`StudentId` = `selectcourse`.`SelectCourseSid`));

-- ----------------------------
-- Function structure for func_checkcoursetime
-- ----------------------------
DROP FUNCTION IF EXISTS `func_checkcoursetime`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_checkcoursetime`(p_week INT,p_class INT,p_studentid char(12)) RETURNS int(11)
BEGIN
	DECLARE tmp_week INT;
	DECLARE tmp_class INT;
	DECLARE val INT DEFAULT 0;
	DECLARE c_selectedcourse CURSOR FOR
		SELECT coursetime.CourseTimeWeek,coursetime.CourseTimeClass 
		FROM coursetime,selectcourse 
		WHERE selectcourse.SelectCourseSid=p_studentid AND selectcourse.SelectCourseCid=coursetime.CourseTimeId;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET val=-1;
	OPEN c_selectedcourse;
	FETCH FROM c_selectedcourse INTO tmp_week,tmp_class;
	WHILE val!=-1 
		DO
			IF tmp_week=p_week AND tmp_class=p_class 
				THEN
					CLOSE c_selectedcourse;
					RETURN 1;
				END IF;
			FETCH FROM c_selectedcourse INTO tmp_week,tmp_class;
		END WHILE;
	CLOSE c_selectedcourse;
	RETURN 0;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for func_getcourseclasschar
-- ----------------------------
DROP FUNCTION IF EXISTS `func_getcourseclasschar`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_getcourseclasschar`(in_courseid char(7),in_week INT) RETURNS varchar(23) CHARSET utf8
BEGIN
	DECLARE tempclass INT;
	DECLARE returnchar VARCHAR(23) DEFAULT NULL;
	DECLARE val INT DEFAULT 0;
	DECLARE selectclass CURSOR FOR
		SELECT CourseTimeClass
		FROM coursetime
		WHERE CourseTimeId=in_courseid AND CourseTimeWeek=in_week;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET val=1;
	OPEN selectclass;
	FETCH selectclass INTO tempclass;
	WHILE val=0
		DO
			SET returnchar=CONCAT_WS(',',returnchar,tempclass);
			FETCH selectclass INTO tempclass;
	END WHILE;
	CLOSE selectclass;
	SET returnchar=CONCAT('第',returnchar,'节');
	RETURN returnchar;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for func_getcoursetimechar
-- ----------------------------
DROP FUNCTION IF EXISTS `func_getcoursetimechar`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_getcoursetimechar`(in_courseid char(7)) RETURNS varchar(255) CHARSET utf8
BEGIN
	DECLARE f_tempweek INT;
	DECLARE f_tempstring VARCHAR(10) DEFAULT '';
	DECLARE f_returnstring VARCHAR(255) DEFAULT '';
	DECLARE val INT DEFAULT 0;
	DECLARE selectweeks CURSOR FOR
		SELECT 
			CASE CourseTimeWeek
				WHEN 1 THEN '星期一'
				WHEN 2 THEN '星期二'
				WHEN 3 THEN '星期三'
				WHEN 4 THEN '星期四'
				WHEN 5 THEN '星期五'
				WHEN 6 THEN '星期六'
				ELSE '星期日'
			END,CourseTimeWeek
		FROM coursetime
		WHERE CourseTimeId=in_courseid
		GROUP BY CourseTimeWeek;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET val=1;
	OPEN selectweeks;
	FETCH selectweeks INTO f_tempstring,f_tempweek;
	WHILE val=0
		DO
			SET f_returnstring=CONCAT(f_returnstring,' | ',f_tempstring,' ',func_getcourseclasschar(in_courseid,f_tempweek));
			FETCH selectweeks INTO f_tempstring,f_tempweek;
		END WHILE;
	CLOSE selectweeks;
	SET f_returnstring=TRIM(LEADING ' | ' FROM f_returnstring);
	RETURN f_returnstring;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for func_returncourseremain
-- ----------------------------
DROP FUNCTION IF EXISTS `func_returncourseremain`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_returncourseremain`(p_courseid char(7)) RETURNS int(11)
BEGIN
	DECLARE returnint INT;
	DECLARE coursecap INT;
	SET returnint=(SELECT COUNT(*) FROM selectcourse WHERE selectcourse.SelectCourseCid=p_courseid);
	SET coursecap=(SELECT course.CourseCapacity FROM course WHERE course.CourseId=p_courseid);
	SET returnint=coursecap-returnint;
	RETURN returnint;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for func_returndeptname
-- ----------------------------
DROP FUNCTION IF EXISTS `func_returndeptname`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_returndeptname`(p_deptid INT) RETURNS varchar(40) CHARSET utf8
BEGIN
	DECLARE returnchar VARCHAR(40);
	SET returnchar=(SELECT DepartmentName FROM department WHERE department.DepartmentNo=p_deptid);
  RETURN returnchar;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for func_selectcourse
-- ----------------------------
DROP FUNCTION IF EXISTS `func_selectcourse`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_selectcourse`(p_studentid char(12),p_courseid char(7)) RETURNS int(11)
BEGIN
	DECLARE p_repeatselect INT;
	DECLARE p_coursepoint FLOAT;
	DECLARE p_courseremain INT;
	DECLARE p_coursecount INT;
	DECLARE p_week INT;
	DECLARE p_class INT;
	DECLARE val INT DEFAULT 0;
	DECLARE requestcoursetime CURSOR FOR
		SELECT coursetime.CourseTimeWeek,coursetime.CourseTimeClass 
		FROM coursetime
		WHERE coursetime.CourseTimeId=p_courseid;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET val=-2;
	SELECT COUNT(*)INTO p_repeatselect FROM selectcourse WHERE selectcourse.SelectCourseSid=p_studentid AND selectcourse.SelectCourseCid=p_courseid;
	IF p_repeatselect<>0 THEN 
		RETURN 1;
	END IF;
	SELECT SUM(course.CoursePoint)INTO p_coursepoint FROM course,selectcourse WHERE course.CourseId=selectcourse.SelectCourseCid AND selectcourse.SelectCourseSid=p_studentid;
	SET p_coursepoint=p_coursepoint+(SELECT course.CoursePoint FROM course WHERE course.CourseId=p_courseid);
	IF p_coursepoint>18 THEN
		RETURN 2;
	END IF;
	OPEN requestcoursetime;
	FETCH requestcoursetime INTO p_week,p_class;
	WHILE val!=-2
		DO
			IF func_checkcoursetime(p_week,p_class,p_studentid)!=0
				THEN
					CLOSE requestcoursetime;
					RETURN 3;
				END IF;
			FETCH requestcoursetime INTO p_week,p_class;
		END WHILE;
	CLOSE requestcoursetime;
	SET p_courseremain=func_returncourseremain(p_courseid);
	IF p_courseremain<=0
		THEN
			RETURN 4;
	END IF;
	INSERT INTO selectcourse(SelectCourseSid,SelectCourseCid) VALUES (p_studentid,p_courseid);
RETURN 0;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prc_begininputscore
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_begininputscore`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_begininputscore`()
BEGIN
	GRANT UPDATE(SelectCourseScore) ON studentselectcourse.selectcourse TO 'teacher';
	UPDATE state SET AllowInputScore=1;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prc_beginselectcourse
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_beginselectcourse`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_beginselectcourse`()
BEGIN
	GRANT INSERT ON studentselectcourse.selectcourse TO 'student';
	GRANT DELETE ON studentselectcourse.selectcourse TO 'student';
	UPDATE state SET AllowSelectCourse=1;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prc_endinputscore
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_endinputscore`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_endinputscore`()
BEGIN
	REVOKE UPDATE(SelectCourseScore) ON studentselectcourse.selectcourse FROM 'teacher';
	UPDATE state SET AllowInputScore=0;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prc_endselectcourse
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_endselectcourse`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prc_endselectcourse`()
BEGIN
	REVOKE INSERT ON studentselectcourse.selectcourse FROM 'student';
	REVOKE DELETE ON studentselectcourse.selectcourse FROM 'student';
	UPDATE state SET AllowSelectCourse=0;

	ALTER TABLE selectcourse ADD INDEX index_sccourseid (SelectCourseCid ASC);
	ALTER TABLE selectcourse ADD INDEX index_scstudentid (SelectCourseSid ASC);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `adminid_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `adminid_incheck` BEFORE INSERT ON `admin` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF LENGTH(NEW.AdminId)>5
	THEN
		SET msg = CONCAT('您输入的管理员账号：',NEW.AdminId,' 为无效的，请输入不大于5位管理员账号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `adminpass_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `adminpass_incheck` BEFORE INSERT ON `admin` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF LENGTH(NEW.AdminPassword)<6
THEN
 SET msg = CONCAT('您输入的密码：',new.AdminPassword,' 为无效的密码，请输入6-18位有效密码。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `adminpass_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `adminpass_upcheck` BEFORE UPDATE ON `admin` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF LENGTH(NEW.AdminPassword)<6
THEN
 SET msg = CONCAT('您修改的密码：',new.AdminPassword,' 为无效的密码，请输入6-18位有效密码。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `courseid_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `courseid_incheck` BEFORE INSERT ON `course` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF LENGTH(NEW.CourseId)<>7
	THEN
		SET msg = CONCAT('您输入的课程号：',NEW.CourseId,' 为无效的课程号，请输入7位有效课程号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `courseidnum_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `courseidnum_incheck` BEFORE INSERT ON `course` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF new.CourseId REGEXP '[^0-9.]'
	THEN
		SET msg = CONCAT('您输入的课程号：',NEW.CourseId,' 为无效的课程号，请输入7位有效课程号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `coursepoint_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `coursepoint_incheck` BEFORE INSERT ON `course` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF MOD(NEW.CoursePoint,0.5)<>0
	THEN
		SET msg = CONCAT('您设定的学分：',NEW.CoursePoint,' 不符合要求');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `courseid_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `courseid_upcheck` BEFORE UPDATE ON `course` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF LENGTH(NEW.CourseId)<>7
	THEN
		SET msg = CONCAT('您修改的课程号：',NEW.CourseId,' 为无效的课程号，请输入7位有效课程号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `courseidnum_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `courseidnum_upcheck` BEFORE UPDATE ON `course` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF new.CourseId REGEXP '[^0-9.]'
	THEN
		SET msg = CONCAT('您修改的课程号：',NEW.CourseId,' 为无效的课程号，请输入7位有效课程号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `coursepoint_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `coursepoint_upcheck` BEFORE UPDATE ON `course` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF MOD(NEW.CoursePoint,0.5)<>0
	THEN
		SET msg = CONCAT('您设定的学分：',NEW.CoursePoint,' 不符合要求');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table coursetime
-- ----------------------------
DROP TRIGGER IF EXISTS `coursetimeweek_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `coursetimeweek_incheck` BEFORE INSERT ON `coursetime` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.CourseTimeWeek<1 OR NEW.CourseTimeWeek>7
	THEN
		SET msg = CONCAT('您输入的周',NEW.CourseTimeWeek,'超出范围');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table coursetime
-- ----------------------------
DROP TRIGGER IF EXISTS `coursetimeclass_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `coursetimeclass_incheck` BEFORE INSERT ON `coursetime` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.CourseTimeClass<1 OR NEW.CourseTimeClass>10
	THEN
		SET msg = CONCAT('您输入的节数：',NEW.CourseTimeClass,'超出范围');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table coursetime
-- ----------------------------
DROP TRIGGER IF EXISTS `coursetimeweek_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `coursetimeweek_upcheck` BEFORE UPDATE ON `coursetime` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.CourseTimeWeek<1 OR NEW.CourseTimeWeek>7
	THEN
		SET msg = CONCAT('您输入的周',NEW.CourseTimeWeek,'超出范围');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table coursetime
-- ----------------------------
DROP TRIGGER IF EXISTS `coursetimeclass_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `coursetimeclass_upcheck` BEFORE UPDATE ON `coursetime` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.CourseTimeClass<1 OR NEW.CourseTimeClass>10
	THEN
		SET msg = CONCAT('您修改的节数：',NEW.CourseTimeClass,'超出范围');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table department
-- ----------------------------
DROP TRIGGER IF EXISTS `departmentNo_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `departmentNo_incheck` BEFORE INSERT ON `department` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.DepartmentNo>99 OR NEW.DepartmentNo<0
	THEN
		SET msg = CONCAT('您输入的学院号：',NEW.DepartmentNo,' 为无效的。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table department
-- ----------------------------
DROP TRIGGER IF EXISTS `departmentNo_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `departmentNo_upcheck` BEFORE UPDATE ON `department` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.DepartmentNo>99 OR NEW.DepartmentNo<0
	THEN
		SET msg = CONCAT('您输入的学院号：',NEW.DepartmentNo,' 为无效的。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table selectcourse
-- ----------------------------
DROP TRIGGER IF EXISTS `SelectCourseScore_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `SelectCourseScore_incheck` BEFORE INSERT ON `selectcourse` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.SelectCourseScore<0 OR NEW.SelectCourseScore>100
	THEN
		SET msg = CONCAT('您输入的成绩：',NEW.SelectCourseScore,'超出范围');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table selectcourse
-- ----------------------------
DROP TRIGGER IF EXISTS `SelectCourseScore_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `SelectCourseScore_upcheck` BEFORE UPDATE ON `selectcourse` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.SelectCourseScore<0 OR NEW.SelectCourseScore>100
	THEN
		SET msg = CONCAT('您输入的成绩：',NEW.SelectCourseScore,'超出范围');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `studentidlength_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `studentidlength_incheck` BEFORE INSERT ON `student` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF LENGTH(NEW.StudentId)<>12
	THEN
		SET msg = CONCAT('您输入的学号：',NEW.StudentId,' 为无效的学号，请输入12位有效学号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `StudentSex_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `StudentSex_incheck` BEFORE INSERT ON `student` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF (new.StudentSex!='男' AND new.StudentSex!='女')
THEN
 SET msg = CONCAT('您输入的性别：',NEW.StudentSex,' 错误。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `studentidnum_check`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `studentidnum_check` BEFORE INSERT ON `student` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF new.StudentId REGEXP '[^0-9.]'
THEN
 SET msg = CONCAT('您输入的学号：',NEW.StudentId,' 为无效的学号，请输入12位有效学号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `StudentSex_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `StudentSex_upcheck` BEFORE UPDATE ON `student` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF (new.StudentSex!='男' AND new.StudentSex!='女')
THEN
 SET msg = CONCAT('您修改的性别：',NEW.StudentSex,' 错误。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `studentpass_updatecheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `studentpass_updatecheck` BEFORE UPDATE ON `student` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF LENGTH(NEW.StudentPassword)<6 
THEN
 SET msg = CONCAT('您修改的密码：',NEW.StudentPassword,' 为无效的密码，请输入6-18位有效密码。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacherid_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacherid_incheck` BEFORE INSERT ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF LENGTH(NEW.TeacherId)<>5
	THEN
		SET msg = CONCAT('您输入的教师号：',NEW.TeacherId,' 为无效的教师号，请输入5位有效教师号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacheridnum_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacheridnum_incheck` BEFORE INSERT ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.TeacherId REGEXP '[^0-9.]'
	THEN
		SET msg = CONCAT('您输入的教师号：',NEW.TeacherId,' 为无效的教师号，请输入5位有效教师号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacherSex_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacherSex_incheck` BEFORE INSERT ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF (new.TeacherSex!='男' AND new.TeacherSex!='女')
THEN
 SET msg = CONCAT('您输入的性别：',NEW.TeacherSex,' 错误。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacherpass_incheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacherpass_incheck` BEFORE INSERT ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF LENGTH(NEW.TeacherPassword)<6
THEN
 SET msg = CONCAT('您输入的密码：',new.TeacherPassword,' 为无效的密码，请输入6-18位有效密码。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacherid_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacherid_upcheck` BEFORE UPDATE ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF LENGTH(NEW.TeacherId)<>5
	THEN
		SET msg = CONCAT('您输入的教师号：',NEW.TeacherId,' 为无效的教师号，请输入5位有效教师号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacheridnum_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacheridnum_upcheck` BEFORE UPDATE ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100); 
	IF NEW.TeacherId REGEXP '[^0-9.]'
	THEN
		SET msg = CONCAT('您输入的教师号：',NEW.TeacherId,' 为无效的教师号，请输入5位有效教师号。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacherSex_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacherSex_upcheck` BEFORE UPDATE ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
  IF (new.TeacherSex!='男' AND new.TeacherSex!='女')
THEN
 SET msg = CONCAT('您修改的性别：',NEW.TeacherSex,' 错误。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `teacherpass_upcheck`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `teacherpass_upcheck` BEFORE UPDATE ON `teacher` FOR EACH ROW BEGIN
	DECLARE msg varchar(100);
	IF new.TeacherPassword=''
	THEN SET new.TeacherPassword='111111';
	END IF;
  IF LENGTH(NEW.TeacherPassword)<6
THEN
 SET msg = CONCAT('您修改的密码：',new.TeacherPassword,' 为无效的密码，请输入6-18位有效密码。');
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
