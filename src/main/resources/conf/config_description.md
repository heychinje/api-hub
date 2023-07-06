#Mongod config file  # Mongod配置文件
#MongoDB configuration files use the YAML format.  # MongoDB配置文件使用YAML格式
#The following example configuration file contains several mongod settings. # 下面的示例配置文件包含几个mongod设置
#
########Example Start(示例开始)########
#systemLog:  # 系统日志配置
#   destination: file  # 日志输出的目的地，file | syslog，如果指定file，必须指定systemlog.path
#   path: "/var/log/mongodb/mongodb.log" # 日志路径
#   logAppend: true # 写日志的模式，ture：追加 | false：覆盖（默认）。ture：实例重启时，在老的日志文件末尾继续添加
#storage: 
#   journal:
#      enabled: true  # 是否开启journal日志持久存储，journal日志用来数据恢复，通常用于故障恢复。64位默认true，32位默认false，建议开启，仅对mongod进程有效。
#processManagement:
#   fork: true  # 是否守护进程在后台运行
#net:
#   bindIp: 127.0.0.1  # 绑定Ip
#   port: 27017 # 监听端口号
#setParameter:
#   enableLocalhostAuthBypass: false # 本地主机绕过身份验证，0或false：禁止，1或true：启动（默认）
#
########Example End(示例结束)########
#
########Core Options
systemLog: # 日志相关参数
#   verbosity: 0    # 日志级别  默认值0，包含"info"信息，大于0的值，会包含debug信息
#   quiet: <boolean>  # 是否减少日志的输出量，不建议在生成环境下开启，跟踪错误比较困难
#   traceAllException: <boolean>  # 打印调试详细信息，用于支持相关的故障排除的附加日志
#   syslogFacility: user  # 用于登录时信息到syslog Facility水平，指定的值必须由你的操作系统实现支持syslog。要使用此选项，您必须启用–syslog
   path: "/data/mongodb/data/conf/mongodb.log"  # 日志路径
   logAppend: true   # 写日志的模式
#   logRotate: <string>    # 日志回转，防止一个日志文件特别大  rename: 重命名日志文件 | reopen：使用Linux日志rotate特性，关闭并重新打开此日志文件，可以避免日志丢失，但是logAppend必须为true
   destination: file  # 日志输出的目的地
#   timeStampFormat: iso8601-local # 指定日志格式的时间戳格式
#   component: # 为不同的组件指定各自的日志信息级别
#      accessControl:
#         verbosity: 0
#      command:
#         verbosity: 0
#      # COMMENT additional component verbosity settings omitted for brevity
#      storage:
#         verbosity: 0
#         journal:
#            verbosity: <int>
#      write:
#         verbosity: 0
#
#
########ProcessManagement Options
processManagement: # 进程相关参数
   fork: true  # 是否守护进程在后台运行
   pidFilePath: "/data/mongodb/data/conf/mongod.pid" # fork为true时，将mongod/mongos进程ID写入指定的文件，如果不指定，将不会创建PID文件
#
#
#########Net Options
net:  # 网络相关参数
   port: 27020    # 监听端口号
   bindIp: 0.0.0.0   # mongod/monogs进程绑定的IP
#   maxIncomingConnections: 65536 # mongod/mongos进程允许的最大连接数
#   wireObjectCheck: true  # 客户端写入数据时，mongos/mongod检测数据的有效性(BSON)，如果数据格式不良，此insert、update操作将会被拒绝；默认值为true。
#   ipv6: false # 是否支持ipv6
#   unixDomainSocket: # 适用于Unix系统，启用或禁用监听Unix域的套接字
#      enabled: true
#      pathPrefix: "/tmp"
#      filePermissions: 0700
#   http:  # http相关
#      enabled: false
#      JSONPEnabled: false
#      RESTInterfaceEnabled: false
#   ssl: # ssl相关
#      sslOnNormalPorts: <boolean>  # deprecated since 2.6
#      mode: <string>
#      PEMKeyFile: <string>
#      PEMKeyPassword: <string>
#      clusterFile: <string>
#      clusterPassword: <string>
#      CAFile: <string>
#      CRLFile: <string>
#      allowConnectionsWithoutCertificates: <boolean>
#      allowInvalidCertificates: <boolean>
#      allowInvalidHostnames: false
#      FIPSMode: <boolean>
#
#
########security Options
security: # 安全相关参数
   keyFile:  /data/mongodb/data/keyfile/security  # 对于副本集模式，指定副本集节点间身份验证密钥文件的路径，注意这个只是副本集节点间的认证，跟Mongodb认证和授权不是一回事
   clusterAuthMode: keyFile
   authorization: enabled # 开启或关闭副本集模式下的MongoDB认证功能
   #javascriptEnabled:  true
########security.sasl Options
#   sasl:
#      hostName: <string>
#      serviceName: <string>
#      saslauthdSocketPath: <string>
#
#
#########setParameter Option
setParameter: # 自定义变量
   enableLocalhostAuthBypass: false  # 本地主机绕过身份验证
#   <parameter1>: <value1>
#   <parameter2>: <value2>
#
#
#########storage Options
storage: # 存储引擎相关参数
   dbPath: "/data/mongodb/data/conf"  # mongod进程存储数据目录，此配置仅对mongod进程有效
#   indexBuildRetry: true # 当构建索引时mongod意外关闭，再次启动是否重新构建索引，默认值为true
#   repairPath: "/data/db/_tmp" # 配合–repair启动命令参数，在repair期间使用此目录存储临时数据，repair结束后此目录下数据将被删除，此配置仅对mongod进程有效（不建议在配置文件中配置，而是使用mongod启动命令指定）
#   journal:
#      enabled: true # 是否开启journal日志持久存储，journal日志用来数据恢复，通常用于故障恢复。64位默认true，32位默认false，建议开启，仅对mongod进程有效。
#   directoryPerDB: false # 是否将不同DB的数据存储在不同的目录中，dbPath的子目录，目录名为db的名称
#   syncPeriodSecs: 60 # mongod使用fsync操作将数据flush到磁盘的时间间隔，默认值为60s，强烈建议不要修改此值
   engine: "wiredTiger"  # mongodb数据库的存储引擎，wiredTiger存储引擎（3.2开始默认使用）
#########storage.mmapv1 Options
#   mmapv1: # mmapv1存储引擎相关参数
#      preallocDataFiles: true
#      nsSize: 16
#      quota:
#         enforced: false
#         maxFilesPerDB: 8
#      smallFiles: false
#      journal:
#         debugFlags: <int>
#         commitIntervalMs: 100   # 100 or 30
#########storage.wiredTiger Options
#   wiredTiger: # wiredTiger存储引擎相关参数
#      engineConfig:
#         cacheSizeGB: <number>  # 使用所有数据的最大缓存大小，wiredTiger缓存工作集数据的内存大小，单位：GB
#         statisticsLogDelaySecs: 0  # 默认值设置为0，wiredtiger不做日志统计
#         journalCompressor: "snappy" # journal日志的压缩算法，可选值为"none"、"snappy"（默认）、"zlib"。
#         directoryForIndexes: false  # 是否将索引和collections数据分别存储在dbPath单独的目录中。即index数据保存"index"子目录，collections数据保存在"collection"子目录。默认值为false，仅对mongod有效。
#      collectionConfig:
#         blockCompressor: "snappy"  # collection数据压缩算法，可选值"none"、"snappy"、"zlib"。
#      indexConfig:
#         prefixCompression: true # 是否对索引数据使用"前缀压缩"（prefix compression，一种算法）。
#
#
##########operationProfiling Options
#operationProfiling: # 慢查询相关参数
#   slowOpThresholdMs: 100 # 判定一个操作是“慢查询”的时间阀值，单位毫秒
#   mode # 操作的性能信息将会被写入日志文件中，off：关闭、slowOp：on，只包含慢操作日志、all：on，记录所有操作。
 
数据库profiling会影响性能，建议只在性能调试阶段开启。此参数仅对mongod有效。
 
#########replication Options
replication:  # 复制集相关参数
#   oplogSizeMB: <int>  # replication操作日志的最大尺寸，单位：MB
   replSetName: cfgsvr  # "复制集"的名称，复制集中的所有mongd实例都必须有相同的名字，sharding分布式下，不同的sharding应该使用不同的replSetName。仅对mongod有效。
#   secondaryIndexPrefetch: all # 默认值all，复制集中的secondary
#
#
##########sharding Options
sharding: # 分片相关参数
   clusterRole: configsvr    # 在sharding集群中，此mongod实例的角色，可选值：configsvr-->config server，默认侦听27019端口；shardsvr-->sharding（分片），默认侦听27018端口
                             # --此配置仅对mongod有效。通常config server和sharding server需要使用各自的配置文件。
#   archiveMovedChunks: True # 当chunks因为"负载平衡"而迁移到其他节点时，mongod是否将这些chunks归档，并保存在dbPath下"moveChunk"目录下，mongod不会删除moveChunk下的文件。默认为true。
#
#
#########auditLog Options
#auditLog: # 审计相关参数
#   destination: <string>   # 开启审计，需指定审计记录的输出方式 syslog | console | file
#   format: <string>   # 目标文件的输出文件格式  JSON | BSON
#   path: <string>   # 如果审计时间输入为文件，那么这里就需要指定文件的完整路径及文件名
#   filter: <string> # 过滤器，可以限制审计系统记录的操作类型
#
#
#########snmp Options
#snmp: # SNMP监控相关参数
#   subagent: <boolean>
#   master: <boolean>
#
#
########mongos-only Options 
## mongos配置文件的单独设置
#replication:  
#   localPingThresholdMs: 15
#
#sharding:
#   autoSplit: true
#   configDB: 10.50.16.34:27017,10.50.16.35:27017,10.50.16.36:27017,10.50.16.18:27017 # 监听的配置服务器，只能有1个或者3个。configs为配置服务器的副本集名字
#   chunkSize: 64
#
#
########Windows Service Options
## windows系统设置
#processManagement:
#   windowsService:
#      serviceName: <string>
#      displayName: <string>
#      description: <string>
#      serviceUser: <string>
#      servicePassword: <string>