# LuaJit-Android
在Android上运行LuaJit并与Java交互

基于Androlua修改，使Lua5.1在安卓上速度更佳

以下是luajit与lua5.1的性能对比
| Lua5.1 | LuaJit2.1.0-beta3 |
| :-----: | :-----: |
| 循环一千万次 | 同理 |
| 大约13秒 | 平均0.12秒 |

`**平均luajit比lua快10倍左右**`
