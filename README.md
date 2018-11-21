# wealth-sentinel-demo
分析sentinel内核原理时使用

基本思路：

还是AOP这一套东西。
- 在需要进行限流的地方，进行打点。
- 打点的时候，记录资源使用情况
- 资源超标，则进行相应的限流或降级，或熔断。
- 资源超标处理的过程，是一个典型的职责链模式
- 定时记录日志，所以写IO压力不大
- 每１０秒（可修改配置）定时上报资源情况
- 资源上报，是典型的命令模式
- 性能处理非常高，对系统运行无压力，不影响线上性能
