# TVSettingsLib
This lib can help you finsh the UI of TV Settings easily. And it is a little pretty.

#### 什么是Activity？？
  官方文档解释如下：

    /**
     * An activity is a single, focused thing that the user can do.  Almost all
     * activities interact with the user, so the Activity class takes care of
     * creating a window for you in which you can place your UI with
     * {@link #setContentView}.  */
简单解释：
  Activity是一个独立的、可聚焦的东东，几乎所有的Activity都与用户进行交互。更简单的说，我们在Android看到的每一个全屏界面，几乎都是一个Activity。它是UI的载体。

#### 什么是Fragment？？
   官方文档解释如下：
   ```
    /**
     * A Fragment is a piece of an application's user interface or behavior
     * that can be placed in an {@link Activity}.  Interaction with fragments
     * is done through {@link FragmentManager}, which can be obtained via
     * {@link Activity#getFragmentManager() Activity.getFragmentManager()} and
     * {@link Fragment#getFragmentManager() Fragment.getFragmentManager()}.*/
```
简单解释：
    用户接口或行为的一个区块，它可以放到Activity中。

#### Fragment能做什么？？
如下图所示：
  红色区域Topfragment 、蓝色区域Fragment1、白色区域Fragment2，是三个不同的区域。他们可以分别做不同的事情，比如Topfragment  播放视频、Fragment1 轮播图片、Fragment2 展示列表。
![](https://upload-images.jianshu.io/upload_images/6205286-d4be1969e79c9e2b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### 遇到了什么问题？？
时间背景：编写向导App的时候。
向导App包括几大部分：
    - 蓝牙连接
    - 语言设置
    - Wifi 连接（包括几个子界面）
    - 安装方式介绍（包括几个子界面）


旧的代码解决方案 :
![旧方案](https://upload-images.jianshu.io/upload_images/6205286-f28804254da0285f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
如上图所示：一次性把所有Fragment全部添加进来，显示Fragment1的时候，隐藏  Fragment2、Fragment3、Fragment4，显示Fragment2的时候，隐藏Fragment1、Fragment3、Fragment4，等等以此类推。

接口方法： Add（）、Hide（）、Show（）
缺点：
 - 如果有很多很多界面，一次性添加进来，需要很大的资源消耗，就会遇到我们常说的“程序很卡”
- Show 与 Hide 的逻辑复杂，添加新界面容易出错！！

新的代码解决方案 ：
    接口方法：Replace（）
   每次只是初始化一个Fragment，不需要考虑跟别的Fragment的关系。

####  replace( ) 的接口说明
     /**
     * Replace an existing fragment that was added to a container.  This is
     * essentially the same as calling {@link #remove(Fragment)} for all
     * currently added fragments that were added with the same containerViewId
     * and then {@link #add(int, Fragment, String)} with the same arguments
     * given here. */
    public FragmentTransaction replace(@IdRes int containerViewId,Fragment fragment,  String tag);
说明：先移除所有存在的Fragment， 然后把新的Fragment 添加进来。

**问题：**
当前容器里有4个Fragment，但是当调用replace接口之后，只有其中的2个Fragment被释放掉了，其余的两个还是继续存在，why？？这已经和文档的说明相矛盾了！！

#### 先休息一下眼睛
  
  ![](https://upload-images.jianshu.io/upload_images/6205286-df62a07a77221baa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/6205286-d1c7c9141f8b8169.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/6205286-22b33bb8a5abd593.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### Framework 代码追查
  重新创建App，专门来研究这个问题（replace不能删除之前所有）
  Activity中主要测试代码如下：

        @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mFragmentA = new FragmentA();
		mFragmentB = new FragmentB();
		mFragmentC = new FragmentC();
		mFragmentD = new FragmentD();
		mFragmentE = new FragmentE();
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		
		
		ft.add(R.id.container, mFragmentA);
		ft.add(R.id.container, mFragmentB);
		ft.add(R.id.container, mFragmentC);
		ft.add(R.id.container, mFragmentD);
		
		ft.commit();
		CustomLog.d(TAG , "getFragmentManager() name =" + getFragmentManager().getClass().getName());
		CustomLog.d(TAG , "FragmentTransaction name =" + ft.getClass().getName());
		
	}
      public void onBtnClick(View v){
		Toast.makeText(this, "on click~~~", Toast.LENGTH_LONG).show();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, mFragmentE);
		ft.commit();
	}
App运行，log如下：
![](https://upload-images.jianshu.io/upload_images/6205286-6efe2b2c32a9c39e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
说明：四个Fragment都完成了创建
然后点击替换按钮；
![](https://upload-images.jianshu.io/upload_images/6205286-4de06a8069417ec6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
Log结果看来，E成功添加进来，但是只有A C 被终止掉，BD 还在。

####  探究transaction.replace到底做了什么

  repalce（）方法来自  FragmentTransaction抽象类，小伙伴们都知道抽象类是不能直接使用的，他的实现类是 BackStackRecord.java。

对过框架层代码进行了对比，发现 Android 4.0 Android 5.0 Android 6.0 ，关于这一块的代码，都是一样的。

一路追查代码，最终实现的地方在BackStackRecord 的run（）方法里：
下面筛选了最核心的代码，

    public void run() {
    ...
    ...
      switch (op.cmd) {

                caseOP_REPLACE: {

                    Fragment f = op.fragment;

                    if (mManager.mAdded !=null) {

                        for (int i=0;i<mManager.mAdded.size(); i++) {

                            Fragment old =mManager.mAdded.get(i);

                            if (f == null ||old.mContainerId == f.mContainerId) {

                                if (old== f) {

                                    op.fragment = f =null;

                                } else {                  

                                    mManager.removeFragment(old,mTransition,mTransitionStyle); //delete  all using for

                                }

                            }

                        }

                    }

                    if (f !=null) {

                             mManager.addFragment(f,false); //Add the new one

                    }

                } break;
       ...
       ...
    ｝
可以看到
replace 则是先删除fragmentmanager中所有已添加的fragment，然后再添加当前fragment； 

得出结论：  
      replace 会先删除所有fragment  ，然后再添加传入的fragment对象；

好，问题来了：
点击replace按钮只有A C 被终止掉，BD 还在,并没有删除全部，这又是为什么？

带着疑问的态度进行了一次调试，在调试中终于找到了原因，问题就在这段代码：

    for (int i=0; i<mManager.mAdded.size(); i++) {
      Fragment old = mManager.mAdded.get(i);
      if (f ==null ||old.mContainerId == f.mContainerId) {
          mManager.removeFragment(old,mTransition, mTransitionStyle);
      }
    }

mManager.mAdded  是一个ArrayList<Fragment>  列表，在遍历的时候调用了mManager.removeFragment方法，而该方法调用了ArrayList的remove方法；

    public void removeFragment(Fragmentfragment, int transition, inttransitionStyle) {
                mAdded.remove(fragment);
    }  

也就是说在用for循环遍历ArrayList列表的时候使用了remove；
For循环遍历过程删除会造成ArrayList.size()不断变小，所以造成删除不完全的问题；你是否也被坑过。。。

附
Android 7.0核心代码如下：

    public void run() {
    ...
    ...
    case OP_REPLACE: {
                    Fragment f = op.fragment;
                    int containerId = f.mContainerId;
                    if (mManager.mAdded != null) {
                        for (int i = mManager.mAdded.size() - 1; i >= 0; i--) {
                            Fragment old = mManager.mAdded.get(i);
                            if (old.mContainerId == containerId) {
                                if (old == f) {
                                    op.fragment = f = null;
                                } else {
                                    if (op.removed == null) {
                                        op.removed = new ArrayList<Fragment>();
                                    }
                                    op.removed.add(old);
                                    old.mNextAnim = op.exitAnim;
                                    if (mAddToBackStack) {
                                        old.mBackStackNesting += 1;
                                    }
                                    mManager.removeFragment(old, mTransition, mTransitionStyle);  //delete  all using for
                                }
                            }
                        }
                    }
                    if (f != null) {
                        f.mNextAnim = op.enterAnim;
                        mManager.addFragment(f, false);  //Add the new one 
                    }
                }
                break;
          ...
          ...
    ｝

![](https://upload-images.jianshu.io/upload_images/6205286-99e98eb72539be01.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

聪明的你，应该能看出，在Android7.0上，问题已经修复。
#### 问题总结：
  虽然在7.0已经修复这个framework bug，但是为了向下兼容，我们在使用replace() 方法之前，还是要用remove 方法移除掉所有。
