# ssm整合

创建maven工程

导入Maven坐标

编写实体类

编写Mapper接口

编写Service接口

编写Service接口实现

编写Controller

编写相应配置文件

```
• Spring配置文件:applicationContext.xml 
• SprngMVC配置文件:spring-mvc.xml
• MyBatis映射文件:AccountMapper.xml
• MyBatis核心文件:sqlMapConfig.xml
• 数据库连接信息文件:jdbc.properties • Web.xml文件:web.xml
• 日志文件:log4j.xml
```

## Spring整合MyBatis

将SqlSessionFactory配置到Spring容器中

```xml
<!--加载jdbc.properties-->
<context:property-placeholder location="classpath:jdbc.properties"/> 
<!--配置数据源-->
<bean id= "dataS ource" class ="com.mchange.v2.c3p0.ComboPooledDataSource">
  <property name="driverClass" value="${jdbc.driver}"/> 
  <property name="jdbcUrl" value="${jdbc.url}"/> 
  <property name="user" value="${jdbc.username}"/> 
  <property name="password" value="${jdbc.password}"/>
</bean>
<!--配置MyBatis的SqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="configLocation" value="classpath:sqlMapConfig.xml"/> 
</bean>
```

扫描Mapper，让Spring容器产生Mapper实现类

```xml
<!--配置Mapper扫描-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<property name="basePackage" value="com.itheima.mapper"/> 
</bean>
```

配置声明式事务控制

```xml
<!--配置声明式事务控制-->
<bean id="transacionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"/> 
</bean>
<tx:advice id="txAdvice" transaction-manager="transacionManager"> 
  <tx:attributes>
  	<tx:method name="*"/>
  </tx:attributes>
</tx:advice> 
<aop:config>
  <aop:pointcut id="txPointcut" expression="execution(*com.itheima.service.impl.*. *(..))"/>
  <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/> 
</aop:config>
```

Service实现类代码

```java
@Service( "accountService")
public class AccountServiceImpl implements AccountService {
  @Autowired
  private AccountMapper accountMapper;
  
  public void save(Account account) { 
    accountMapper.save(account);
  }
  public List<Account> findAll() {
    return accountMapper.findAll(); 
  }
}
```

