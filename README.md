# TablesView
刻度盘 迈表 圆形进度盘
Android-小强随手撸的自定义控件

![image](https://github.com/githubwangjunqiang/TablesView/blob/master/img_foder/xiaoguo.jpg)

项目当中用到了 类似迈表盘的需求 随后开源出来 一起交流学习 有问题提交issues

使用方式：下面是Android studio 使用方式 其他方式请自己生成jar

1 添加仓库：在项目的build.gradle 添加jitpack仓库

    repositories {
            google()
            jcenter()
            maven { url 'https://jitpack.io' }
        }

2 添加依赖库:在app根目录下的 build.gradle 添加如下代码

    dependencies {
	        implementation 'com.github.githubwangjunqiang:TablesView:1.0'
	}

3 xml 中引用：MaiTablesView 此控件继承自FrameLayout 您可以随意添加子view

    <com.xiaoqiang.xqtablesview.MaiTablesView
            android:id="@+id/main_view_mai"
            android:layout_width="250dp"
            android:layout_height="250dp"
            app:tables_color="@android:color/white"
            app:tables_colorprogress="@android:color/holo_red_dark"
            app:tables_groups="8"
            app:tables_groupscount="10"
            app:tables_percentagelong="0.1"
            app:tables_percentageshort="0.05"
            app:tables_progressvalue="10"
            app:tables_strokewidth="4dp"
            app:tables_strokewidthprogress="6dp">

            <TextView
                android:onClick="doClick"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="点击我随即旋转哦"
                android:textColor="#fff" />
        </com.xiaoqiang.xqtablesview.MaiTablesView>

        属性介绍（一下代码不用添加 只是描述）：

            <!--    表盘自定义属性-->
                <!--        有几组数据 默认一组-->
                < name="tables_groups" format="integer" />
                <!--        一组有几个刻度值  默认10个-->
                < name="tables_groupscount" format="integer" />
                <!--        如果刻度长度值 有长有短  那么此值代表长刻度值占view 宽度的 百分比值 默认0.1-->
                < name="tables_percentagelong" format="float" />
                <!--        如果刻度长度值 有长有短  那么此值代表短刻度值占view 宽度的 百分比值 默认0.05-->
                < name="tables_percentageshort" format="float" />
                <!--        刻度的宽度 默认8px-->
                < name="tables_strokewidth" format="dimension" />
                <!--        刻度的宽度 进度值 默认10px-->
                < name="tables_strokewidthprogress" format="dimension" />
                <!--        刻度的颜色 默认白色-->
                < name="tables_color" format="color" />
                <!--        刻度的颜色 进度值 默认红色-->
                < name="tables_colorprogress" format="color" />
                <!--        进度值 默认0  不能大于总刻度值-->
                < name="tables_progressvalue" format="integer" />

            代码中可以使用view.set***(**) 方式设置 同上，代码设置的属性值会覆盖xml中设置的值


4代码中初始化：

     MaiTablesView mMaiTablesView = findViewById(R.id.main_view_mai);

     //设置进度值
             mMaiTablesView.setProgressValue(2);

             progressvalue 属性是进度值 您可以在代码当中随意设置 不能超过刻度总和
          也可以结合动画设置

     // 内置了 随机动画 调用如下代码 会自动旋转进度值和关闭旋转
            mMaiTablesView.startRandomRoate();//此函数为开启动画
            mMaiTablesView.closeRandomRoate();//此函数为关闭动画


5如果您 开启了混淆 请添加下面的混淆规则 本库都是自定义view 所以 不要混淆本库 本库包名 com.xiaoqiang.xqtablesview

        -keep public class com.xiaoqiang.xqtablesview.**
	
	

6支持Androidx

