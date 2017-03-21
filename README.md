# ElasticSearch5.1.1 + Flume1.7 + Kibana5.1.1收集日志部署方案

## 环境准备
- JDK8的安装
```
  1.download jdk-8u111-linux-x64.tar.gz
  2.tar zxvf jdk-8u111-linux-x64.tar.gz
  3.mkdir /usr/java
  4.mv jdk1.8.0_111 /usr/java
  5.update-alternatives --install /usr/bin/java java   /usr/java/jdk1.8.0_111/bin/java 1100
  6.java -version
 ```
 
 - ElasticSearch的安装
 	- 软件安装 
      ```
      1.download elasticsearch-5.1.1.tar.gz
      2.tar zxvf elasticsearch-5.1.1.tar.gz
      3.mv elasticsearch-5.1.1 /usr/local/es
      ```
    - elasticsearch.yml的配置
      ```
      假设配置2台服务器
      cluster.name: my-application
      node.name: node-1
      network.host: 192.168.10.1
      network.bind_host: 192.168.10.1
      network.publish_host: 192.168.10.1
      node.master: true
      node.data: true
      http.port: 9200
      http.enabled: true
      discovery.zen.ping.unicast.hosts: ["192.168.10.1","192.168.10.2"]
      #如果安装head插件需要新增以下两项
      http.cors.enabled: true
      http.cors.allow-origin: "*"
      ----------------------------------------------------------------------
      cluster.name: my-application
      node.name: node-2
      network.host: 192.168.10.2
      network.bind_host: 192.168.10.2
      network.publish_host: 192.168.10.2
      http.port: 9201
      node.master: false
      node.data: true
      http.enabled: true
      discovery.zen.ping.unicast.hosts: ["192.168.10.1","192.168.10.2"]
      #如果安装head插件需要新增以下两项
      http.cors.enabled: true
      http.cors.allow-origin: "*"
      ```
    - elasticsearch start
      ```
       1. 非root用户启动（ $ElasticSearch/bin/elasticsearch）
      ```
      
    - ElasticSearch之head的插件安装 (非必需安装项)
      ```
      1.git clone git://github.com/mobz/elasticsearch-head.git
      2.cd elasticsearch-head
      3.npm instal
      4.编辑Gruntfile.js  文件93行添加hostname:'0.0.0.0'
      5.启动 grunt server
      6.访问 http://localhost:9100
      ```
 - Flume的安装
   - 软件安装
     ```
     1. download apache-flume-1.7.0-bin.tar.gz
     2. tar -zxvf apache-flume-1.7.0-bin.tar.gz
     3. cd apache-flume-1.7.0-bin
     4. mv apache-flume-1.7.0-bin /usr/local/flume
     ```
   
   - 国内环境配置相关的文件（用到AWS S3存储）
     ```
     rm -f $flume_home/lib/jackson*
     rm -f $flume_home/lib/flume-ng-elasticsearch-sink-1.7.0.jar
     mv $flume_home/conf/log4j.properties $flume_home/conf/log4j2.properties
     cp cn/lib/*  $flume_home/lib/
     cp conf/*  $flume_home/conf/
     ```
   - Flume收集Nginx的日志，Business的日志
   		- Nginx 日志格式
   		```
        '{"app_name":"$app_name","trace_id":"$request_id","remote_addr":"$remote_addr","http_x_forwarded_for":"$http_x_forwarded_for","remote_user":"$remote_user","request":"$request","request_body":"$request_body","request_url":"$request_uri","status":"$status","body_bytes_sent":"$body_bytes_sent","bytes_sent":"$bytes_sent","connection":"$connection","connection_requests":"$connection_requests","msec":"$msec","pipe":"$pipe","http_referer":"$http_referer","http_user_agent":"$http_user_agent","request_length":"$request_length","request_time":"$request_time","upstream_response_time":"$upstream_response_time","time_local":"$time_local","gzip_ratio":"$gzip_ratio"}';
        ```
   
     
   
 