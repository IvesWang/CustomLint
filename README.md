## CustomLint
自定义的lint逻辑，可用于Android Studio等IntelliJ IDEA。
当你需要为你的项目或者开发团队制定一些内部的lint逻辑时，可使用这种方式。

## Getting Start
方案一：

1.clone本库<br/>
2.在lint-rules这个java module中修改规则后重新编译出jar包<br/>
3.讲jar包放到custom-lint这个module下的libs目录下<br/>
4.在主module的build.gradle文件中加入：
```
compile (name:'aarlib-release', ext:'aar')
```
5.这样引用之后，**重启IDEA**即可应用你的lint规则。

方案二：

直接使用本库已经编译好的lint规则，
你只需要在主module的build.gradle文件中加入以下配置：
```
compile 'com.github.iveswang:custom-lint:1.0'
```
配置好后，同样需要**重启IDEA**才会生效。

不过对于方案二，目前本库只提供了“静态常量命名不允许有小写”这一个规则，后续会继续加入更多的规则。
如果你有更多的规则建议，请告诉我吧，暂时没想到其它的。因为idea里面本身已有一套比较丰富的lint规则了。

```
Tips:
以上方案使用场景都是Android Studio，如要使用其它类型的IDEA或project，也可使用本库中的jar，
具体做法请参照以下blog进行探索哈。
```
## Blog
关于更多使用自定义lint的知识可参看我的博客：

http://www.jianshu.com/p/5991bca931e2

 ## License

```
Copyright 2017 Ives Wang

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
file except in compliance with the License. You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
either express or implied. See the License for the specific language governing permissions
and limitations under the License.
