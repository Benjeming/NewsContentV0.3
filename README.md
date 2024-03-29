## SharedPreferences的应用

### SharedPreference是什么



> **SharedPreferences**是Android平台上一个***轻量级***  的 ***存储辅助类***，用来保存应用的一些常用配置，它提供了string，set，int，long，float，boolean六种数据类型。最终数据是以xml形式进行存储。在应用中通常做一些简单数据的持久化缓存。SharedPreferences作为一个**轻量级存储**，所以就限制了它的使用场景

1. 因为**Shared** 是轻量级的储存，那么我们就不能存储过多的数据。我在使用的过程中，将从API中获取的数据放到 **shared** 中，当加载*ListView* 的时候，也就是第一次联网的时候，就将数据给保存到**shared**中，这样下次没有网络的时候，也可以离线观看，ListView标题。

### 实现逻辑

- 对于 ListView 

  1. 如果 **SharedPreference** 中有值，则直接对其中的值，进行**解析操作**。

     否则，则通过  **网络进行获取** ，并将获取的值 存入**SharedPreference** 中。

     以下为代码实现部分：

     ```java
      String saveJson = CacheUtils.getString(MainActivity.this, TARGET_URL);
             if (!TextUtils.isEmpty(saveJson)) {
     //            SharedPreferences中有值，则直接解析
                 Log.e(TAG, "Shared中有数据，现在进行解析....." );
                 gson = new Gson();
                 mData = gson.fromJson(saveJson, new TypeToken<ArrayList<JsonData>>() {
                 }.getType());
                 Log.e(TAG, "解析的数据为： "+mData.get(1).getTitle() );
     
                 initJsonData();
     
             }else {
                 Log.e(TAG, "联网请求...." );
             OkHttpUtils.sendRequestWithOkhttp(
                     TARGET_URL,
                     callback);}
         }
     
         Callback callback = new Callback() {
             @Override
             public void onFailure(@NotNull Call call, @NotNull IOException e) {
                 Log.e(TAG, "联网请求失败" );
             }
     
             @Override
             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
     //            将获取的数据缓存
                  CacheUtils.putString(MainActivity.this,TARGET_URL,response.body().string());
     //            用Gson解析数据
                 parseJsonWithGson(response);
     //            将Json数据 传入适配器，然后 初始化页面数据
                 initJsonData();
     
             }
     ```



- 解释下，这些代码的作用：

  > 1. String saveJson = CacheUtils.getString(MainActivity.this, TARGET_URL);
  >    - 获取 xml 文件；getSharedPreferences 
  > 2. if (!TextUtils.isEmpty(saveJson)) 
  >    - 如果 获取的值为空，说明 没有存储 **shared**文件，那么就要 联网请求。
  >    - 如果 获取的值不为空，说明 存储了 **shared**文件，那么只需要解析这个文件就可以了。

### CacheUtils 缓存工具类

1. 主要是由两部分构成，getString（），和 putString（）

   代码部分：

   ```java
   public class CacheUtils {
        // 缓存文本数据
       public static void putString(Context context, String key, String value) {
           SharedPreferences sp = context.getSharedPreferences("newscontent", Context.MODE_PRIVATE);
           sp.edit().putString(key, value).commit();
       }  
         //获取缓存的文本信息  
       public static String getString(Context context, String key) {
           String result = "";
           SharedPreferences sp = context.getSharedPreferences("newscontent", Context.MODE_PRIVATE);
           result = sp.getString(key, "");
           return result;
       }
   }
   ```

   - 代码解释：

     > 1. context.getSharedPreferences("newscontent", Context.MODE_PRIVATE); 
     >
     >    - “newscontent”: 是要操作的 Xml文件名。
     >    -  Context.MODE_PRIVATE ：读写模式。
     >    - context：当前活动.
     >2. sp.edit().putString(key, value).commit();
     > 
     >   - 其中 sp.edit()  相当于是 ***SharedPreference.Editor*** 的一个对象 ，即 editor。
     > 3.  result = sp.getString(key, ""); 
     >   - 首先初始化 空字符，如果  **sp**  中没值，就会返回 “ ” ，否则会 返回 **sp** 带过来的值。
     >    

## 结果

![SharedPreference](8_21(1).png)



- newscontent.xml  文件内容：

  1. Content 内容部分：

  ![部分内容](8_21(2).png)
  
  2. Title 标题部分
  
     ![标题部分](8_21(3).png)
