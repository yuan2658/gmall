# gmall
简单的电商系统

------------------------
12.6 安装zookeeper和dubbo

zookeeper安装: 下载压缩包直接解压  
配置文件zoo_sample.cfg --> zoo.cfg 设置dataDir/dataLogDir 其他默认

dubbo安装: 下载dubbo-admin 打war包 直接解压
配置tomcat server.xml  
```<Context docBase="/opt/dubbo-admin/" path="" reloadable="true"/>```

--------------------------
12.7 zookeeper，dubbo和springboot整合

文件配置参数 包名一致 注意包版本冲突

-----------------------
12.8  后台管理模块 
商品属性分类查询、属性信息的添加