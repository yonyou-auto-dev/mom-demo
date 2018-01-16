package com.yonyou.gtmc.demo_f4.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("EMP")
@IdName("EMPNO")
public class Emp extends Model {

}
