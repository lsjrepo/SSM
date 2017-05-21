/*
package com.test.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.test.beans.Department;
import com.test.beans.Employee;
import com.test.dao.*;
import netscape.security.ForbiddenTargetException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
*/
/**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 * 
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。	
 *  *  * @author lfy
 *
 *//*

public class MyBatisTest {
	

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	*/
/**
	 * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
	 * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。 
	 * 3、将sql映射文件注册在全局配置文件中
	 * 4、写代码：
	 * 		1）、根据全局配置文件得到SqlSessionFactory；
	 * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
	 * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
	 * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
	 * 
	 * @throws IOException
	 *//*

	@Test
	public void test() throws IOException {

		// 2、获取sqlSession实例，能直接执行已经映射的sql语句
		// sql的唯一标识：statement Unique identifier matching the statement to use.
		// 执行sql要用的参数：parameter A parameter object to pass to the statement.
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			Employee employee = openSession.selectOne(
					"com.test.dao.EmployeeMapper.getEmpById", 1);
			System.out.println(employee);
		} finally {
			openSession.close();
		}

	}

		@Test
		public void test01() throws IOException {
			// 1、获取sqlSessionFactory对象
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			// 2、获取sqlSession对象
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				// 3、获取接口的实现类对象
				//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
				EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
				Employee employee = mapper.getEmpById(1);
				System.out.println(mapper.getClass());
				System.out.println(employee);
			} finally {
				openSession.close();
			}

		}
		@Test
		public void test2() throws IOException{
			// 1、获取sqlSessionFactory对象
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			// 2、获取sqlSession对象
			SqlSession openSession = sqlSessionFactory.openSession();
			try{
			EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(employee);
			}finally {
				openSession.close();
			}
		}
		@Test
	    public void test03() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			//测试添加
			*/
/*Employee employee = new Employee(null,"superman","24238788@qq.com", "1");
			mapper.addEmp(employee);
			System.out.println(employee.getId());*//*

			//测试修改
			*/
/*Employee employee = new Employee(1, "Tom", "jerry@123.com", "0");
			boolean updateEmp = mapper.updateEmp(employee);
			System.out.println(updateEmp);*//*

			//测试删除
			mapper.deleteEmpById(2);
			//2、手动提交数据
			openSession.commit();
		}finally{
			openSession.close();
		}

	}

	@Test
	public void test04() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		try{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> lsit=mapper.getEmpsByLastNameLike("%e%");
			for (Employee employee:lsit){
				System.out.println(employee);
			}
		}
		finally
		{
			openSession.close();
		}

	}
	@Test
	public void test05() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		try
		{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Map<Integer,Employee> map=mapper.getEmpByIdReturnMap(1);
			Map<Integer,Employee> map2=mapper.getEmpByLastNameLikeReturnMap("%e%");
			System.out.println(map);
		}
		finally
		{
			openSession.close();
		}

	}
	@Test
	public void test06() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		try
		{
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			*/
/*Employee empById=mapper.getEmpAndDept(1);
			System.out.println(empById);
			System.out.println(empById.getDept());*//*

			Employee employee=mapper.getEmpByIdStep(1);
			System.out.println(employee);
			System.out.println(employee.getEmail());
		}
		finally
		{
			openSession.close();
		}

	}

	//sql拼装的时候可能会发生错误:
	// where 1=1 后面全部跟and
	//mybatis使用where标签将所有的查询条件包括在内
	//where标签只会去掉第一个多出来的And或者or
	*/
/**//*




	@Test
	public void testDynamicSqlTest() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
			try
			{
			    EmployeeMapperDymanicSQL mapper=openSession.getMapper(EmployeeMapperDymanicSQL.class);
				*/
/*Employee employee=new Employee(1,"lili",null,"1");*//*

				List<Employee> emps=mapper.getEmpsByConditionForeach(Arrays.asList(1,2,3,4));
			    for (Employee e:emps){
					System.out.println(e);
				}

		}
		finally {
			openSession.close();
		}
	}
	@Test
	public void testBatchSave() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		try
		{
			EmployeeMapperDymanicSQL mapper=openSession.getMapper(EmployeeMapperDymanicSQL.class);
			List<Employee> emps=new ArrayList<Employee>();
			emps.add(new Employee(null,"jazy","12131212@126.com","1",new Department(1)));
			emps.add(new Employee(null,"2Pac","34353456666@126.com","1",new Department(1)));
			mapper.addEmps(emps);
			openSession.commit();
		}
		finally {
			openSession.close();
		}

	}
	*/
/*
	* 两级缓存:
	* 一级缓存:(本地缓存)
	* 与数据库的同一次会话期间的数据都会放在本地缓存中
	* 以后如果要获取相同的数据直接从缓存中拿,没有必要再去
	* 查询数据库
	* 二级缓存(全局缓存)
	*
	* *//*

	@Test
	public void testSecondLevelCache() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//1、获取到的SqlSession不会自动提交数据
		SqlSession openSession = sqlSessionFactory.openSession();
		SqlSession openSession2=sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			EmployeeMapper mapper1=openSession2.getMapper((EmployeeMapper.class));
			Employee emp01=mapper.getEmpById(1);
			System.out.println(emp01);
			openSession.close();

			Employee emp02=mapper1.getEmpById(1);
			System.out.println(emp02);
			openSession2.close();
		}
		finally {

		}
	}
}
*/
