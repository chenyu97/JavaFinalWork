# My Huluwa

*151220013 陈彧*

<img src="README-Image\1.PNG" style="zoom:60%" />

### 功能实现

程序中实现了一个二维空间，空间中的每一个设定的位置可以且仅可以站立一个生物体（葫芦娃、爷爷、蛇精、蝎子精和蛤蟆精）。

在程序运行最初，七个葫芦娃和老爷爷以某种阵型站立在二维空间的左侧，而蛇精、蝎子精和蛤蟆精以另外一种阵型站立在二维空间右侧，如下图：

<img src="README-Image\2.PNG" style="zoom:60%" />



程序中每一个生物体的运动都是凭借创建一个线程来实现的，即在线程对应的函数`run()`中添加相应的代码。

在程序运行最初，我们通过按下空格键，来触发所有生物体各自对应线程执行`start()`，接下来每一个生物体即可按照各自的模式进行运动。

每一个线程对应的函数`run()`中实现的主要是让每一个生物体向自己的敌人前进，并且当遇到敌人时，实现两者之一败下阵来的效果。

当某一方败下阵来后，其形象会转变为一块墓牌，表示其已阵亡。而胜利的一方会继续前进，寻找下一个敌人。

当某一方的生物全部死亡时，游戏结束。 

### 额外要求实现

程序运行过程中，有游戏过程记录的功能。当我们每一次按下空格键开始运行游戏时，游戏过程记录的功能就被触发，直到游戏结束后，游戏过程记录的功能被关闭。之后，我们可以在工程目录下找到*record*这个文件，该文件中包含了游戏过程记录的数据，如下图：

<img src="README-Image\3.PNG" style="zoom:60%" />

程序运行过程中，我们有两个时段可以进行之前的游戏记录回放的操作。一个是在战斗开始前（按下空格键前），另一个是战斗结束后。我么通过按下L键来实现该功能，再按下L键后，我们发现弹出文件对话框（如下图），我们在其中打开之前保存的游戏过程记录文件，即可在游戏界面上重现之前的游戏过程。

<img src="README-Image\4.PNG" style="zoom:60%" />

本程序利用了面向对象编程方法来实现，体现了封装、继承和多态等面向对象的基本特性。在程序中实现了多种类，如`Field`、`Player`和`Huluwa`等，在每一个类中较好地封装了其包含的数据成员和成员函数。此外，程序中存在着继承的关系，如`Huluwa`继承自`Player`（如下图），并且也重写了如`run()`等成员函数，以便于之后很好地实现多态的特性。

<img src="README-Image\6.PNG" style="zoom:60%" />

结合课上学的多种设计原则，在程序中用到了一部分，如CARP合成/聚合复用原则，在程序中优先使用对象合成/聚合，而不是继承。

程序中使用了课上所讲的异常处理，比如我们在进行文件输入输出时，需要添加异常处理语句，以防产生`IOException`类型的异常，如果有我们可以进行一定的处理。

课上学习的集合类型我们在程序中也利用了我们将类`Player`创建的众多`player`存放在一个`ArrayList`中进行管理，对该集合类型进行访问指定下标的对象元素，以及添加删除某个对象元素的操作还是比较方便的。

同时，在程序中也用到了课上学习的范型，如上面提到的`ArrayList<T>`中的`T`即为`Player`，使用了范型很好地提升了该集合类型的通用性。

另外，程序中也有多处注解，比如在子类继承父类时，若重写了某个成员函数，则会在新写的成员函数前添加`@override`，很好地起到了标注作用。

关于输入输出机制，主要是通过程序中读写文件来体现的。通过读写文件，我们很好地实现了保存游戏过程和重放游戏过程的功能。

并且，在程序中也加入了关键字`synchronized`来控制多线程之间的协同问题，保证不同进程不能同时使用同一块资源。

我们也为程序编写了单元测试用例，如：

``` java
package nju.java;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HuluwaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Field field = new Field();		
		Huluwa huluwa = new Huluwa(30, 30, field, "7wa.png", COLOR.values()[6], SENIORITY.values()[6], 0);		
		assertEquals(huluwa.getIsLive(), true);
		assertEquals(huluwa.getIsGood(), true);	
	}
}
```

本工程为*Maven*工程，可以通过`mvn clean test package`等命令进行管理。

<img src="README-Image\5.PNG" style="zoom:80%" />

我将自己认为最精彩的某一次战斗过程记录文件存放在了工程目录下的文件夹*record-folder*中。

### 实验心得

游戏的实现，以及面向对象的编程思想等之前也在像高级程序设计（C++）这样的课程中有过学习，在本课程的葫芦娃实验中，我觉得自己收获比较大的有以下几个方面：

**多线程编程：**之前也只在操作系统课程中接触过线程的概念，在本次实验中是第一次利用高级程序语言进行多线程的编程，并且感觉其实现的效果还是比较令自己满意的。

**输入输出机制：**第一次实现了利用GUI界面来进行对文件的存取操作，提升了用户友好性。

**单元测试：**实现了对程序代码局部的测试，很好地降低了原本代码测试的复杂程度。

### 致谢

感谢课程老师提供了实验框架代码（GUI部分），虽说是GUI部分，但是其实很多方面都已经帮我们提前实现好了（输入输出、多线程等部分），大大降低了我们实验的困难程度，使得我们可以更快速地入手本实验的编写。