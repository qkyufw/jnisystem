```shell
├─.idea # idea的配置文件
├─lib # 库文件
├─out # 一些输出，不用太关注
│  ├─artifacts
│  │  └─jdbcTest01_jar # 可执行的jar包，点击后进入登录界面
│  └─production
│      └─略
└─src
    ├─com # 数据库相关的
    │  ├─dao # 下面的是四个接口，分别对应管理员，服务队，志愿者三个数据库操作的接口
    │  │  └─impl # 四个实现类，分别对应上面上者的实际操作类和搜索操作的实现
    │  └─util # 工具类，其实也就是只用于连接数据库了一下
    ├─handler # 界面和jdbc的中间层
    ├─META-INF # 忽略
    ├─plds # 前端界面
    │  ├─admin # 管理员相关界面
    │  ├─vista # 服务队相关界面
    │  └─vo # 志愿者相关界面
    ├─pojo # 实体类
    └─test # 测试GUI用的应该是
```

